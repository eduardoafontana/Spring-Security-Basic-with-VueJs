<template>
  <div class="login">
    <h3>This is an login page</h3>
    <br>
    <br>
    <input v-model="user" placeholder="user">
    <br>
    <input v-model="password" placeholder="password">
    <br>
    <button @click="logItIn">Login</button>
    <br>
    <button @click="getUser">Get User</button>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Home',
  components: {
  },
  data() {
    return {
      user: 'user',
      password: ''
    }
  },
  methods: {
    logItIn: function () {
      console.log(this.user, this.password);
      var self = this;

      axios.defaults.withCredentials = true;

      axios.get('http://localhost:8080/user', { 
        headers: { 
          Authorization : 'Basic ' + btoa(self.user + ':' + self.password), 
          'X-Requested-With': 'XMLHttpRequest'
        }
      }).then(function (response) {
          console.log(response);
      });

    },

    getUser: function () {
      axios.defaults.withCredentials = true;

      axios.get('http://localhost:8080/getHello').then(function (response) {
          console.log(response);
      });

      axios.get('http://localhost:8080/user'
      ).then(function (response) {
          console.log(response);
      });
    }
  }
};
</script>