import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isIndex: false,
    loginDialog: false,
    signUpDialog: false,
    changePasswordDialog: false,
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
