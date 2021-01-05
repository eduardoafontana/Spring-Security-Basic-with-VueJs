<template>
<div>
  <div class="container-fluid">
      Home
      <br>
      <button @click="getHello">Hello Get</button>
      <br>
      <button @click="postHello">Hello Post</button>
      <br>
      <button @click="putHello">Hello Put</button>
      <br>
      <button @click="deleteHello">Hello Delete</button>
  </div>
</div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Home',
  components: {
  },
  methods: {
    getHello: function () {
      axios.defaults.withCredentials = true;

      axios.get('http://localhost:8080/getHello').then(function (response) {
          console.log(response);
      });
    },
    postHello: function () {
      axios.defaults.withCredentials = true;

      axios.post('http://localhost:8080/postHello').then(function (response) {
          console.log(response);
      });
    },
    putHello: function () {
      axios.defaults.withCredentials = true;

      axios.put('http://localhost:8080/putHello').then(function (response) {
          console.log(response);
      });
    },
    deleteHello: function () {
      axios.defaults.withCredentials = true;
      
      axios.delete('http://localhost:8080/deleteHello').then(function (response) {
          console.log(response);
      });
    },
  }
};
</script>

<style>
h5 {
  color: green;
}

.logo {
  max-width: 50% !important
}
</style>
