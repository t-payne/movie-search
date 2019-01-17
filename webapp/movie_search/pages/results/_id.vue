<template>
  <div>
    <h1>Results for {{$route.params.id}}</h1>
    <div v-if="movieHasResults">
      <div v-for="(movie, index) in movieData">
        <Card
          :title="movie.title"
          :image="movie.poster_image_url"
          :popularity_summary="movie.popularity_summary"
        />
      </div>
    </div>
    <div v-else>
      <h1>No results found</h1>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import Card from "~/components/Card.vue";

export default {
  asyncData({ params }) {
    return axios
      .get(`http://localhost:8080/movies?search=${params.id}`)
      .then(response => {
        return { movieData: response.data };
      });
  },
  components: {
    Card
  },
  middleware: "search",
  computed: {
    movieHasResults() {
      return this.movieData.length > 0;
    }
  }
};
</script>
