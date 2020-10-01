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
    
    isLogin: false,

    // 나중에 새로고침에 대비해서 쿠키에 넣어야할수도있음 생각해두자.
    userNumber: '',
    userEditoonImages: [],
    userEmail: '',

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
    setLoginStatus(state, check) {
      state.isLogin = check
    },
    imageFromDjango(state, images) {
      state.convertedImages.push(images)
      // 이미지 어떻게 넘어오는지 봐야할듯.
    },
    setUserEditoonImages(state, images) {
      state.userEditoonImages = images
    },


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
    // login check
    login({ commit }, loginData) {
      axios.post( SERVER_URL + 'login', loginData)
        .then( res => {
          console.log(res.data)
          commit('setLoginStatus', true)
          // 딱히 해줄일이 없다.
        })
        .catch( err => {
          alert('로그인 실패')
          console.log(err)
          // 토큰 만료시 , HttpStatus === 406일때 토큰 만료이기 때문에 토큰을 다시 받는 로직 만들어야한다.
        })
    },
    logout() {
      axios.post( SERVER_URL + '/account/logout/' )
        .then( res => {
          console.log(res.data)
          // 쿠키에 이름이 어떻게 저장되는지 보고, 나중에 다 삭제해줘야함.
          // 무조건 success로 옴
        })
        .catch( err => {
          console.log(err)
          // 에러가 뜨면 서버에러임
        })
    },
    // 유저정보 변경
    changeUserInfo({ commit }, changedInfo) {
      axios.post( SERVER_URL + 'account/v1/nameAndImageModify', changedInfo)
        .then( res => {
          console.log(res.data)
          commit
          // 체인지한다음에 표시되는 부분이 있는가?
          // 아마도 있다면 로그인하고나서 로그아웃으로 바뀌고 옆에 아이콘뜨게?
          // 그럼 이미지랑 유저네임이 떠야하는가? 일단은 보류하자.
          // 유저 정보를 다시 갱신시켜서 받아야하는데 어디서 받는가?
        })
        .catch( err => {
          console.log(err)
        })
    },
    // 비밀번호 변경
    // email이랑 password를 넘겨준다
    changePassword({ state }, changedInfo ) {
      axios.post( SERVER_URL + 'account/v1/passwordModify', changedInfo)
        .then( res => {
          console.log(res.data)
          alert("비밀번호 변경이 완료되었습니다.")
          state.changePasswordDialog = false
        })
        .catch( err => {
          console.log(err)
          alert("비밀번호를 다시 확인해주세요")
        })
    },


    // 유저가 저장한 editton image 보여주기
    getUserEditoonImages({ state, commit }) {
      axios.get( SERVER_URL + 'v1/getEditoonDetail/' + `${state.userEmail}/` + `${state.userNumber}/`)
        .then( res => {
          console.log(res.data)
          commit('setUserEditoonImages', res.data)
        })
        .catch( err => {
          console.log(err)
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

    },
    canvasImageToSpring({ commit }, canvasForms) {
      axios.post( SERVER_URL + 'editoon/v1/saveEditoonDetail/', canvasForms, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
        .then (res => {
          commit
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
