<template>
  <v-layout column justify-center align-center>
    <v-flex xs12 sm8 md6>
      <div>
        <h1>Search Movies</h1>
        <br>
        <form @submit.prevent="submit">
          <input placeholder="Enter Movie Title" v-model="search" autofocus>
        </form>
      </div>
    </v-flex>
  </v-layout>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      search: ""
    };
  },
  methods: {
    submit(event) {
      alert("Search Initiated");
    }
  }
};
</script>

<style>
* {
  text-align: center;
}

h1 {
  padding: 20px;
}
</style>