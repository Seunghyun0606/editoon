import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)


// const SERVER_URL = 'http://localhost:8080/editoon/'
const SERVER_URL = 'https://j3b308.p.ssafy.io/editoon/'
const Django_SERVER_URL = 'http://localhost:8000/ai/ImgtoAnime/'
// const DJANGO_URL = ''

export default new Vuex.Store({
  state: {
    isIndex: true,
    isNotEditor: true,
    loginDialog: false,
    signUpDialog: false,
    saveCanvasDialog: false,
    showMytoonDialog: false,
    changePasswordDialog: false,
    
    isLogin: false,

    // 나중에 새로고침에 대비해서 쿠키에 넣어야할수도있음 생각해두자.
    userNumber: '',
    userEditoonImages: [],
    userThumbnails: [],
    // _id, subject, thumbnail, createDate로 온다.

    userEmail: '',
    userInfo: {
      no: '',
      email: '',
      name: '',
      image: '',  // 유저 아이콘
    },

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
    saveCanvasDialogInit(state, check) {
      state.saveCanvasDialog = check
    },
    showMytoonDialogInit(state, check) {
      state.showMytoonDialog = check
    },
    setLoginStatus(state, check) {
      state.isLogin = check
    },
    imageFromDjango(state, images) {
      state.convertedImages.push(images)
      // 이미지 어떻게 넘어오는지 봐야할듯.
    },
    setUserInfo(state, info) {
      state.userInfo = info
    },
    setUserEditoonImages(state, images) {
      state.userEditoonImages = images
    },
    setUserEditoonThumbnails(state, info) {
      state.getUserEditoonThumbnails = info
    }


  },
  actions: {
    // 회원가입 인증절차
    // 이메일보내기aaa
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
      axios.post( SERVER_URL + 'nonmember/email/authCheck', signUpData)
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
      console.log(signUpData)
      axios.post( SERVER_URL + 'nonmember/signUp', signUpData)
        .then( res => {
          console.log(res.data)
          console.log('회원가입성공')
          alert("회원가입 성공, 로그인해주세요")
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
    login({ commit, dispatch }, loginData) {
      axios.post( SERVER_URL + 'login', loginData)
        .then( res => {
          console.log(res.data)
          alert("로그인성공")
          commit('setLoginStatus', true)
          dispatch('getUserInfo')
          // 딱히 해줄일이 없다.
        })
        .catch( err => {
          alert('로그인 실패')
          console.log(err)
          // 토큰 만료시 , HttpStatus === 406일때 토큰 만료이기 때문에 토큰을 다시 받는 로직 만들어야한다.
        })
    },
    logout() {
      axios.post( SERVER_URL + 'account/logout' )
        .then( res => {
          console.log(res.data)
          alert('logout 성공')
          this.commit('setLoginStatus', false)
          // 쿠키에 이름이 어떻게 저장되는지 보고, 나중에 다 삭제해줘야함.
          // 무조건 success로 옴
        })
        .catch( err => {
          console.log(err)
          alert('logout 실패')
          // 에러가 뜨면 서버에러임
        })
    },
    // 유저정보 가져오기
    getUserInfo({ commit }) {
      axios.post( SERVER_URL + 'account/getLoginInfo' )
        .then( res => {
          alert('유저정보 가져오기 성공')
          console.log(res.data)
          commit('setUserInfo', res.data.map.loginInfoDTO)
        })
        .catch( err => {
          console.log(err)
          alert('유저정보 가져오기 실패')
        })
    },
    // 유저정보 변경
    changeUserInfo({ state, commit, dispatch }, changeInfo) {
      axios.post( SERVER_URL + 'account/v1/nameAndImageModify', changeInfo, {
        headers: {
          email: state.userInfo.email
        }
      })
        .then( res => {
          console.log(res.data)
          commit('setUserInfo', res.data)
          // 체인지한다음에 표시되는 부분이 있는가?
          // 아마도 있다면 로그인하고나서 로그아웃으로 바뀌고 옆에 아이콘뜨게?
          // 그럼 이미지랑 유저네임이 떠야하는가? 일단은 보류하자.
          // 유저 정보를 다시 갱신시켜서 받아야하는데 어디서 받는가?
          dispatch('getUserInfo')
        })
        .catch( err => {
          console.log(err)
        })
    },
    // 비밀번호 변경
    // email이랑 password를 넘겨준다
    // 일단 다 성공으로 보내주기때문에 result에서 걸러야함. status가 다르면 다른에러.
    changePassword({ state }, changeInfo ) {
      axios.post( SERVER_URL + 'account/v1/passwordModify', changeInfo, {
        headers: {
          email: state.userInfo.email
        }
      })
        .then( res => {
          console.log(res.data)
          if ( res.data.result === 'success' ) {
            alert("비밀번호 변경이 완료되었습니다.")
            state.changePasswordDialog = false
          }
          else {
            alert("비밀번호를 다시 확인해주세요")
          }
        })
        .catch( err => {
          console.log(err)
          alert('서버에러')
        })
    },
    // 유저정보 삭제
    // email password
    deleteUser({ state }, userInfo) {
      alert('정말?')
      axios.post( SERVER_URL + 'account/v1/delete', userInfo, {
        headers: {
          email: state.userInfo.email
        }
      })
        .then( res => {
          console.log(res.data)
          alert('삭제가 완료되었습니다.')
        })
        .catch( err => {
          console.log(err)
          alert('삭제 실패')
          // 내가 쿠키를 제거해야하는가? 서버에서 한다..
        })
    },
    // 유저가 저장한 editoon image 목록(썸네일) 보여주기
    getUserEditoonThumbnails({ state, commit }) {
      console.log(1, state.userInfo.email)
      // axios.get( SERVER_URL + 'editoon/v1/getEditoonThumbnails/' + 'limseung10@gmail.com', { email: 'limseung10@gmail.com' }, {
      //   headers: {
      //     email: 'limseung10@gmail.com'
      //   }
      axios.get( SERVER_URL + 'editoon/v1/getEditoonThumbnails/' + `${state.userInfo.email}`, { email: state.userInfo.email }, {
        headers: {
          email: state.userInfo.email
        }
        })
        .then( res => {
          console.log(res.data)
          console.log('check')
          commit('setUserEditoonThumbnails', res.data.map)
          alert('썸네일 받기 성공')
        })
        .catch( err => {
          alert('썸네일 받기 실패')
          console.log(err)
        })
    },


    // 유저가 저장한 editton image 보여주기
    getUserEditoonImages({ state, commit }, book_id) {
      axios.get( SERVER_URL + 'editoon/v1/getEditoonDetail', { email: state.userInfo.email, _id: book_id }, {
        headers: {
          email: state.userInfo.email
        }
      })
        .then( res => {
          console.log(res.data)
          commit('showMytoonDialogInit', true)
          commit('setUserEditoonImages', res.data.map)
        })
        .catch( err => {
          console.log(err)
        })
    },
    // 성공메시지만 온다. 다른건 없음.
    canvasImageToSpring({ state }, canvasForms) {
      state
      axios.post( SERVER_URL + 'editoon/v1/saveEditoonDetail', canvasForms, {
        headers: {
          // 'Content-Type': 'multipart/form-data',
          email: state.userInfo.email

          // email: 'limseung10@gmail.com'
        }
      })
        .then (res => {
          console.log(res.data)
          if ( res.data.result === 'noImage' ) {
            alert('이미지없음')
          }
          else if (res.data.result === 'noThumbnail') {
            alert('썸네일이 없음')
          }
          else {
            alert('성공적으로 저장되었습니다.')
          }

        })
        .catch ( err => {
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

  },
  modules: {
  }
})
