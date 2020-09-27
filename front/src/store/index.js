import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)


const SERVER_URL = 'http://localhost:8080/editoon/'
const Django_SERVER_URL = 'http://localhost:8000/ai/ImgtoAnime/'
// const DJANGO_URL = ''

export default new Vuex.Store({
  state: {
    isIndex: true,
    isNotEditor: true,
    loginDialog: false,
    signUpDialog: false,
    changePasswordDialog: false,
    
    signUpValidation: {
      isSendEmail: false,
      codeValidate: false,
      signUpStatus: false,
    },
    convertedImages: [],
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
    signUpInit(state, check) {
      state.signUpDialog = check
      state.signUpValidation.isSendEmail = check
      state.signUpValidation.codeValidate = check
      state.signUpValidation.signUpStatus = check
    },
    signUpIsEmailSend(state, check) {
      state.signUpValidation.isSendEmail = check
    },
    signUpCodeValidation(state, check) {
      state.signUpValidation.codeValidate = check
    },
    signUpStatus(state, check) {
      state.signUpValidation.signUpstatus = check
    },
    imageFromDjango(state, images) {
      state.convertedImages.push(images)
      // 이미지 어떻게 넘어오는지 봐야할듯.
    }

  },
  actions: {
    // 회원가입 인증절차
    // 이메일보내기
    signUpSendValidationEmail({ commit }, email) {
      console.log(commit, email)
      axios.get( SERVER_URL + `nonmember/email/authSend/` + email)
        .then( res => {
          if ( res.data.result === 'fail' ) {
            alert('이미 가입된 이메일입니다.')
          }
          else {
            alert('메일을 전송했습니다.')
            commit('signUpIsEmailSend', true)
          }
        })
        .catch( err => {
          console.log(err)
          commit('signUpIsEmailSend', false)

        })
    },
    // axios.post( SERVER_URL + 'nonmember/email/authCheck/', signUpData,
    //     {
      //       headers: {
        //         xsrfCookieName: 'XSRF-TOKEN', xsrfHeaderName: 'X-XSRF-TOKEN'
        //       },
        //     }
        //   )
    // 코드보내기.
    signUpEmailVerificateCode({ commit }, signUpData ) {
      axios.post( SERVER_URL + 'nonmember/email/authCheck/', signUpData)
        .then( res => {
          console.log(res.data)
          console.log('코드 보내기 성공')
          commit('signUpCodeValidation', true)
        })
        .catch( err => {
          commit('signUpCodeValidation', false)
          console.log(err)
        })
    },
    // 회원가입하기.
    signUp({ commit }, signUpData ) {
      axios.post( SERVER_URL + 'nonmember/signUp/', signUpData)
        .then( res => {
          console.log(res.data)
          console.log('회원가입성공')
          commit('signUpStatus', true)
          commit('signUpInit', false)
          return true
        })
        .catch( err => {
          console.log(err)
          console.log('회원가입실패')
          return false
        })
    },
    dropZoneImageToDjango({ commit }, djangoImageForm) {

      axios.post( Django_SERVER_URL, djangoImageForm, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
        .then (res => {
          commit('imageFromDjango', res.data)
          console.log(res.data)
        })
        .catch ( err => {
          console.log(err)
        })

    }

  },
  modules: {
  }
})
