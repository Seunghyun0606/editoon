import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isIndex: false,
    loginDialog: false,
  },
  mutations: {
    isIndex(state, check) {
      state.isIndex = check
    }
  },
  actions: {
  },
  modules: {
  }
})
