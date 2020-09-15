import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isIndex: true,
    isNotEditor: true,
    loginDialog: false,
    signUpDialog: false,
    changePasswordDialog: false,
  },
  mutations: {
    isIndex(state, check) {
      state.isIndex = check
    },
    isNotEditor(state, check) {
      state.isNotEditor = check
    },

  },
  actions: {
  },
  modules: {
  }
})
