package com.springboot.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	@LocalServerPort
	private int port;

	private TestRestTemplate template = new TestRestTemplate();

	@Test
	public void homePageLoads() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:"
				+ port + "/", String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void userEndpointProtected() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:"
				+ port + "/user", String.class);
		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}

	@Test
	public void resourceEndpointProtected() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:"
				+ port + "/resource", String.class);
		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}

	@Test
	public void loginSucceeds() {
		TestRestTemplate template = new TestRestTemplate("user", "password");
		ResponseEntity<String> response = template.getForEntity("http://localhost:" + port
				+ "/user", String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void postHelloTest() {
		TestRestTemplate template = new TestRestTemplate("user", "password");
		ResponseEntity<String> response = template.getForEntity("http://localhost:" + port + "/getHello", String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		HttpHeaders requestHeadersAuth = new HttpHeaders();

		List<String> cookies = response.getHeaders().getValuesAsList("Set-Cookie");
        String cookieValues = cookies.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(";"));
        cookieValues = cookieValues.replace("Path=/;JSESSIONID", "JSESSIONID");
        
        String xsrf = cookies.stream().filter(x -> x.startsWith("XSRF-TOKEN")).findFirst().get();
        String xsrfGuid = xsrf.split("=")[1].replace("; Path", "");

        requestHeadersAuth.add("Cookie", cookieValues);
        requestHeadersAuth.add("X-XSRF-TOKEN", xsrfGuid);

		ResponseEntity<String> responsePost = template.exchange("http://localhost:" + port + "/postHello", HttpMethod.POST, new HttpEntity<String>("null", requestHeadersAuth), String.class);
		assertEquals(HttpStatus.OK, responsePost.getStatusCode());
	}
}