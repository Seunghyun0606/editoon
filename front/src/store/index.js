import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)


const SERVER_URL = 'http://localhost:8080/editoon/'
// const DJANGO_URL = ''

export default new Vuex.Store({
  state: {
    isIndex: true,
    isNotEditor: true,
    loginDialog: false,
    signUpDialog: false,
    changePasswordDialog: false,
    
    emailCodeValidate: false,
  },
  getters: {

  },
  mutations: {
    isIndex(state, check) {
      state.isIndex = check
    },
    isNotEditor(state, check) {
      state.isNotEditor = check
    },
    isEmailCodeValidated(state, check) {
      state.emailCodeValidate = check
    }

  },
  actions: {
    // 회원가입 인증절차
    sendValidationEmail({ commit }, email) {
      console.log(commit, email)
      axios.get( SERVER_URL + `nonmember/email/authSend/` + email)
        .then( res => {
          if ( res.data.result === 'fail' ) {
            alert('이미 가입된 이메일입니다.')
          }
          else {
            console.log(res)
          }
        })
        .catch( err => {
          console.log(err)
        })
    },
    emailVerificateCode({ commit }, signUpData ) {
      commit
      axios.post( SERVER_URL + 'nonmember/email/authCheck/', signUpData,
          {
            headers: {
              xsrfCookieName: 'XSRF-TOKEN', xsrfHeaderName: 'X-XSRF-TOKEN'
            },
          }
        )
        .then( res => {
          console.log(res.data)
        })
        .catch( err => {
          console.log(err)
        })
    }
  },
  modules: {
  }
})
