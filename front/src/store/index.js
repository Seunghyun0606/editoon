import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isNotEditor: true,
    loginDialog: false,
    signUpDialog: false,
    changePasswordDialog: false,
  },
  mutations: {
    isNotEditor(state, check) {
      state.isNotEditor = check
    }
  },
  actions: {
  },
  modules: {
  }
})
