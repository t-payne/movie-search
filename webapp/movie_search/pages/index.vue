<template>
  <v-layout column justify-center align-center>
    <v-flex xs12 sm8 md6>
      <div>
        <h1>Search Movies</h1>
        <br>
        <form @submit.prevent="submit">
          <v-text-field v-model="search" label="Enter Movie Title" outline clearable></v-text-field>
          <br>
          <v-btn @click="submit">Search</v-btn>
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