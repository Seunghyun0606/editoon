import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)


// const SERVER_URL = 'http://localhost:8080/editoon/'
const SERVER_URL = 'https://j3b308.p.ssafy.io/editoon/'
const Django_SERVER_URL = 'https://j3b308.p.ssafy.io/ai/ImgtoAnime/'
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
    deleteUserDialog: false,
    
    isLogin: false,

    checkLoading: {
      isSaveOnlineLoading: false,
      isConvertedLoading: false,
      isMoveImageToCanvasLoading: false,
    },

    // 나중에 새로고침에 대비해서 쿠키에 넣어야할수도있음 생각해두자.
    userEditoonImages: {},
    userEditoonThumbnails: [],
    // _id, subject, thumbnail, createDate로 온다.

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

      if ( state.convertedImages.length  > 17 ) {
        state.convertedImages.shift()
      }

      state.convertedImages.push(images)
      // console.log('check', images)
      // 이미지 어떻게 넘어오는지 봐야할듯.
    },
    setUserInfo(state, info) {
      state.userInfo = info
    },
    setUserEditoonImages(state, images) {
      state.userEditoonImages = images
      // console.log(images)
    },
    setUserEditoonThumbnails(state, info) {
      // console.log(2, info)
      state.userEditoonThumbnails = info
    }


  },
  actions: {
    // 회원가입 인증절차
    // 이메일보내기aaa
    signUpSendValidationEmail({ commit }, email) {
      // console.log(commit, email)
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
        .catch( () => {
          // console.log(err)
          
          alert('서버 에러입니다. 다음에 다시 시도해주세요.')
          commit('signUpIsEmailSend', false)

        })
    },
    // 코드보내기.
    signUpEmailVerificateCode({ commit }, signUpData ) {
      axios.post( SERVER_URL + 'nonmember/email/authCheck', signUpData)
        .then( res => {
          
          // console.log(res.data)
          // console.log('코드 보내기 성공') 
          if ( res.data.result === 'fail ') {
            commit('signUpCodeValidation', false)
          }
          else {
            commit('signUpCodeValidation', true)
          }
        })
        .catch( () => {
          alert('서버에러 입니다. 다음에 다시 시도해주세요.')
          // console.log(err)
        })
    },
    // 회원가입하기.
    signUp({ commit }, signUpData ) {
      // console.log(signUpData)
      axios.post( SERVER_URL + 'nonmember/signUp', signUpData)
        .then( () => {
          // console.log(res.data)
          // console.log('회원가입성공')
          alert("회원가입 성공, 로그인해주세요")
          commit('signUpStatus', true)
          commit('signUpInit', false)
          return true
        })
        .catch( () => {

          // console.log(err)
          // console.log('회원가입실패')
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
        .catch( () => {
          alert('서버 오류로 인한 로그인 실패입니다. 나중에 다시 시도해주세요.')
          // console.log(err)
          // 토큰 만료시 , HttpStatus === 406일때 토큰 만료이기 때문에 토큰을 다시 받는 로직 만들어야한다.
        })
    },
    logout() {
      axios.post( SERVER_URL + 'account/logout' )
        .then( () => {
          alert('logout')
          this.commit('setLoginStatus', false)
          // 쿠키에 이름이 어떻게 저장되는지 보고, 나중에 다 삭제해줘야함.
          // 무조건 success로 옴
        })
        .catch( err => {
          console.log(err)
          alert('서버에러로 인한 logout 실패입니다. 나중에 다시 시도해주세요.')
          // 에러가 뜨면 서버에러임
        })
    },
    // 유저정보 가져오기
    getUserInfo({ commit }) {
      axios.post( SERVER_URL + 'account/getLoginInfo' )
        .then( res => {
          // alert('유저정보 가져오기 성공')
          // console.log(res.data)
          commit('setUserInfo', res.data.map.loginInfoDTO)
        })
        .catch( () => {
          // console.log(err)
          alert('서버 오류로 인한 유저정보 가져오기 실패입니다. 나중에 다시 시도해주세요.')
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
          // console.log(res.data)
          commit('setUserInfo', res.data)
          // 체인지한다음에 표시되는 부분이 있는가?
          // 아마도 있다면 로그인하고나서 로그아웃으로 바뀌고 옆에 아이콘뜨게?
          // 그럼 이미지랑 유저네임이 떠야하는가? 일단은 보류하자.
          // 유저 정보를 다시 갱신시켜서 받아야하는데 어디서 받는가?
          dispatch('getUserInfo')
          alert('변경 완료')
        })
        .catch( () => {
          alert('서버 오류로 인한 변경 실패입니다. 나중에 다시 시도해주세요.')
          // console.log(err)
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
          // console.log(res.data)
          if ( res.data.result === 'success' ) {
            alert("비밀번호 변경이 완료되었습니다.")
            state.changePasswordDialog = false
          }
          else {
            alert("비밀번호를 다시 확인해주세요")
          }
        })
        .catch( err => {
          err
          // console.log(err)
          alert('서버에러')
        })
    },
    // 유저정보 삭제
    deleteUser({ state, dispatch }, userInfo) {
      axios.post( SERVER_URL + 'account/v1/delete', userInfo, {
        headers: {
          email: state.userInfo.email
        }
      })
        .then( res => {
          if ( res.data.result === 'fail') {
            alert('비밀번호 혹은 이메일을 확인해주세요')
          }
          else {
            alert('삭제가 완료되었습니다.')
            state.deleteUserDialog = false
            dispatch('logout')
            this.$router.push('MainIndex')
          }
        })
        .catch( () => {
          alert('서버 오류로 삭제에 실패했습니다. 나중에 다시 시도해주세요')
        })
    },
    // 유저가 저장한 editoon image 목록(썸네일) 보여주기
    getUserEditoonThumbnails({ state, commit }) {
      axios.get( SERVER_URL + 'editoon/v1/getEditoonThumbnails/' + `${state.userInfo.email}`, { email: state.userInfo.email }, {
        headers: {
          email: state.userInfo.email
        }
        })
        .then( res => {
          commit('setUserEditoonThumbnails', res.data.map.editoonDetailList)
        })
        .catch( err => {
          alert('서버 오류로 썸네일을 불러오지 못했습니다. 다음에 다시 시도해주세요.')
          console.log(err)
        })
    },


    // 유저가 저장한 editton image 보여주기
    getUserEditoonImages({ state, commit }, book_id) {
      axios.get( SERVER_URL + 'editoon/v1/getEditoonDetail/' + `${state.userInfo.email}/` + `${book_id}`, {}, {
        headers: {
          email: state.userInfo.email
        }
      })
        .then( res => {
          // console.log(123, res.data)
          commit('showMytoonDialogInit', true)
          commit('setUserEditoonImages', res.data.map.editoonDetailDTO)
        })
        .catch( err => {
          console.log(err)
        })
    },
    // 성공메시지만 온다. 다른건 없음.
    canvasImageToSpring({ state, commit }, canvasForms) {
      return axios.post( SERVER_URL + 'editoon/v1/saveEditoonDetail', canvasForms, {
        headers: {
          // 'Content-Type': 'multipart/form-data',
          email: state.userInfo.email
          // email: 'limseung10@gmail.com'
        }
      })
        .then (res => {
          if ( res.data.result === 'noImage' ) {
            alert('이미지가 없습니다.')
          }
          else if (res.data.result === 'noThumbnail') {
            alert('썸네일이 없습니다.')
          }
          else {
            commit('saveCanvasDialogInit', false)
            alert('성공적으로 저장되었습니다.')
          }

        })
        .catch ( err => {
          alert("서버 에러발생!")
          console.log(err)
        })
    },
    dropZoneImageToDjango({ commit }, djangoImageForm) {

      return axios.post( Django_SERVER_URL, djangoImageForm, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
        .then (res => {
          commit('imageFromDjango', res.data)
          // console.log(res.data)
        })
        .catch ( err => {
          alert('에러발생!')
          err
          // console.log(err)
        })

    },

  },
  modules: {
  }
})
