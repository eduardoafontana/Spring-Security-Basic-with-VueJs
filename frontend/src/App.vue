<template>
<div id="app">

  <nav class="navbar navbar-light bg-light">
    <router-link to="/home">Home</router-link>
    <router-link to="/about">About</router-link>
    <button @click="logItOut">Logout</button>
  </nav>

  <router-view></router-view>
</div>
</template>

<script>
import axios from 'axios'

export default {
  components: {
    
  },
  methods: {
    logItOut: function () {

      axios.defaults.withCredentials = true;

      axios.post('http://localhost:8080/logout').then(function (response) {
          console.log('logged out executed: ', response);

          axios.defaults.withCredentials = false;
      });

      console.log('logged out executed');
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

</style>
