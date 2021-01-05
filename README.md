# Spring-Security-Basic-with-VueJs

## Authenticated backend:
* Keeps hash-map login session until logout is performed.
* It has the endpoint */user* to return user and roles context.
* POST, PUT and DELETE verbs work.
* -> REVIEW: When logging in via form, it does not generate SessionId.

## Unauthenticated backend:
* Blocks any request without authentication for any endpoint.
* Authenticates by sending GET request with authentication and withCredentials to any endpoint. But it would be best to center this on */user*.

## Unauthenticated front-end:
* To login, the front-end must send a request to any back-end end-point with the authentication data in the *Basic authentication* header and with *withCredentials=true*. The backend will do automatic authentication.
* In order not to have the browser login dialog screen, for unauthenticated requests, the front-end must send *XMLHttpRequest* in the header.

## Authenticated front-end:
* To access endpoints on the backend, the front must send *withCredentials=true* on requests for the authentication cookie to be sent (no need to resend authentication data).
* You can call the backend endpoints without sending *XMLHttpRequest* or *Basic authentication*. However, if for some reason the session goes down, browsing will display the login screen, so it is best to always send the *XMLHttpRequest*.
* To log out you must send POST to the endpoint */logout* and redirect to the desired page. You must set *withCredentials=false* on success response, in order to delete the cookie from the browser session.