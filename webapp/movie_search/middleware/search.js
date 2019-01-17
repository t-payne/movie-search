import axios from "axios";

export default function({ params, store }) {
  return axios
    .get(`http://localhost:8080/movies?search=${params.id}`)
    .then(response => {
      store.commit("add", response.data);
    });
}
