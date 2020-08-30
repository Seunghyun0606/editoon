<template>

<div>
  
  <!-- <img class="test" :src="require('@/assets/mockupGif.gif')" alt=""> -->
  <img class="test ani1" :src="require('@/assets/1.jpg')" alt="">
  <img class="test ani2" :src="require('@/assets/2.jpg')" alt="">
  <img class="test ani3" :src="require('@/assets/3.jpg')" alt="">
  <div class="test1"></div>
  <!-- 2번안 -->
  <v-container fluid style="height: 100vh; " class="">
    <v-row style="text-align: center;">
      <v-col class="align-self-center">
        <v-row>
          <v-col class="main-index-txt">
            우리가족서재

          </v-col>
        </v-row>
        <v-row>
          <v-col style="flex-grow: 0; flex-basis: 70%;" class="mx-auto sub-index-txt">
            대충 설명이란것 대충 설명이란것 대충 설명이란것 대충 설명이란것 대충 설명이란것 대충 설명이란것 대충 설명이란것 
          </v-col>

        </v-row>
      </v-col>
      <v-col cols=5 style="background-color: rgba(0, 0, 0, 0.4); z-index: 10; height: 100vh;">
        <v-row style="height: 100%" class="align-content-center">
          <v-col class="main-index-txt">
            대충로그인 들어갈 예정

          </v-col>
        </v-row>

      </v-col>
    </v-row>

  <!-- 1번안 -->
  <!-- <v-container style="height: 100vh; text-align: center; padding-top: 12%; " class="mx-auto"> -->
    <!-- <v-row class="">
      <v-col class="main-index-txt">
        우리 가족 서재
      </v-col>
    </v-row>
    <v-row class="justify-center">
      <v-col style="flex-grow: 0; flex-basis: 70%;" class="my-6 sub-index-txt">
        대충 여기에 설명이 들어갈 거란 뜻
        대충 여기에 설명이 들어갈 거란 뜻대충 여기에 설명이 들어갈 거란 뜻대충 여기에 설명이 들어갈 거란 뜻대충 여기에 설명이 들어갈 거란 뜻
      </v-col>
    </v-row>
    <v-row class="justify-center">
      <v-col @click.stop="clickLogin" style="flex-grow: 0; flex-basis: 8%;" class="btn-login">
        Login
      </v-col>
    </v-row>

    <v-dialog
      v-model="dialog"
      max-width="60%"
    >

      <v-container fluid style="height: 80vh;">
        <v-row style="height: 100%; text-align: center; background-color: rgba(0, 0, 0, 0.8)">
          <v-col cols=6>
            <img style="height: 100%; width: 100%;" :src="require('@/assets/mockupGif.gif')" alt="">
          </v-col >
          <v-col cols=6 class="main-index-txt align-self-center">
            대충 로그인 UI 들어갈 예정
          </v-col>
        </v-row>

      </v-container>
    </v-dialog> -->

    <!-- <LoginModal :dialog="dialog" /> -->







    <!-- 1안, 눌러서 나오기 -->
    <!-- <v-row>
      <v-col style="text-align: end;">
        
      <v-speed-dial
        v-model="fab"
        :top="top"
        :bottom="bottom"
        :right="right"
        :left="left"
        :direction="direction"
        :open-on-hover="hover"
        :transition="transition"
      >
        <template v-slot:activator>
          <v-btn
            v-model="fab"
            color="blue darken-2"
            dark
            fab
          >
            <v-icon v-if="fab">mdi-close</v-icon>
            <v-icon v-else>mdi-account-circle</v-icon>
          </v-btn>
        </template>
        <v-btn
          fab
          dark
          small
          color="green"
        >
          <v-icon>mdi-pencil</v-icon>
        </v-btn>
        <v-btn
          fab
          dark
          small
          color="indigo"
        >
          <v-icon>mdi-plus</v-icon>
        </v-btn>
        <v-btn
          fab
          dark
          small
          color="red"
        >
          <v-icon>mdi-delete</v-icon>
        </v-btn>
      </v-speed-dial>
      </v-col>
    </v-row> -->

    <!-- 2안, 처음부터 3개 -->
    <!-- <v-row style="text-align: end;">
      <v-col>
        <v-btn
          fab
          dark
          small
          color="green"
        >
          <v-icon>mdi-pencil</v-icon>
        </v-btn>
      </v-col>
    </v-row>
    <v-row style="text-align: end;" class="py-2">
      <v-col>
        <v-btn
          fab
          dark
          small
          color="indigo"
        >
          <v-icon>mdi-plus</v-icon>
        </v-btn>
      </v-col>
    </v-row>
    <v-row style="text-align: end;">
      <v-col>
        
        <v-btn
          fab
          dark
          small
          color="red"
        >
          <v-icon>mdi-delete</v-icon>
        </v-btn>

      </v-col>
    </v-row> -->
  </v-container>


</div>

  <!-- <div >
    <div>
      <div class="test" :style="{ backgroundImage: 'url(\'' + require(`@/assets/mockupGif.gif`) + '\')'}"></div>
      <img class="test" :src="require('@/assets/mockupGif.gif')" alt="">

    </div>

    <div class="test2 mx-auto">
      test
    </div>
  </div> -->


</template>

<script>
// import LoginModal from '@/components/login/LoginModal'


export default {
  name: 'Index',
  components: {
    // LoginModal
  },
  data() {
    return {
      dialog: false,

      direction: 'top',
      fab: false,
      fling: false,
      hover: false,
      tabs: null,
      top: false,
      right: false,
      bottom: false,
      left: false,
      transition: 'slide-y-reverse-transition',
  }
},

computed: {
  activeFab () {
    switch (this.tabs) {
      case 'one': return { class: 'purple', icon: 'account_circle' }
      case 'two': return { class: 'red', icon: 'edit' }
      case 'three': return { class: 'green', icon: 'keyboard_arrow_up' }
      default: return {}
    }
  },

},

methods: {
  clickLogin() {
    this.dialog = true
  }
}

}
</script>

<style>

.background {
  background-image: url('/assets/mockupGif.gif');
  /* background-repeat: no-repeat; */

}

.test {
  /* background-size: 100vw;
  height: 100vw; */
  width: 100vw;
  height: 100vh;
  /* opacity: 0.3; */
  position: absolute;
  z-index: 0;
}

.test1 {
  width: 100vw;
  height: 100vh;
  opacity: 0.4;
  position: absolute;
  z-index: 5;
  background-color: black;

}

.main-index-txt {
  /* position: absolute; */
  color: white;
  font-weight: 900;
  font-size: 50px;
  z-index: 10;  
  
}

.sub-index-txt {
  color: white;
  font-size: 30px;
  z-index: 10;
}

.btn-login {
  color: white;
  font-size: 20px;
  z-index: 10;
  border: 0.1px solid white;
  border-radius: 20px;
}

.btn-login:hover {
  background-color: rgba(255, 255, 255, 0.2);
  cursor: pointer;
}

.ani1 {
  /* opacity: 0.5; */
  /* animation: 7s infinite; */
    animation-duration: 8s;
    animation-timing-function: ease;
    animation-delay: 0s;
    animation-iteration-count: infinite;
    animation-direction: normal;
    animation-fill-mode: none;
    animation-play-state: running;
    animation-name: fading;


  /* animation-direction: normal;
  animation-duration: 5s;
  animation-iteration-count: infinite;
  animation-play-state: running;
  animation-timing-function: ease; */


}

.ani2 {
    /* animation: 7s infinite; */
    animation-duration: 8s;
    animation-timing-function: ease;
    animation-delay: 4s;
    animation-iteration-count: infinite;
    animation-direction: normal;
    animation-fill-mode: none;
    /* animation-play-state: running; */
    animation-name: fading;

}

.ani3 {

    /* animation: 7s infinite; */
    animation-duration: 8s;
    animation-timing-function: ease;
    animation-delay: 4s;
    animation-iteration-count: infinite;
    animation-direction: normal;
    animation-fill-mode: none;
    /* animation-play-state: running; */
    animation-name: fading;

}



</style>