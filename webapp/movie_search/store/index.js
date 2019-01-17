import Vuex from "vuex";

const createStore = () => {
  return new Vuex.Store({
    state: () => ({
      movies: []
    }),
    mutations: {
      add(state, payload) {
        state.movies = payload;
      }
    }
  });
};

export default createStore;
