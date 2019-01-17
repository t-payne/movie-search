<template>
  <div>
    <h1>Search results for: {{$route.params.id}}</h1>
    {{$store.state.movies}}
  </div>
</template>
<script>
export default {
  middleware: "search"
};
</script>
