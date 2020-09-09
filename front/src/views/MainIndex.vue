<template>
  <div>
    <img class="test" :src="require('@/assets/bg2.jpg')" alt />
    <!-- <img class="test ani1" :src="require('@/assets/1.jpg')" alt="">
    <img class="test ani2" :src="require('@/assets/2.jpg')" alt="">
    <img class="test ani3" :src="require('@/assets/3.jpg')" alt="">-->
    <!-- <div class="test1"></div> -->

    <v-container fluid style="height: 100vh; padding-top: 15px;">
      <v-row style="height: 80%;">
        <v-col md="5" sm="12" style="padding: 3vh 6vw;">
          <v-row>
            <v-col class="white--text main-index-txt text-sm-center text-md-left">
              <h1 class="block-effect" style="--td: 1.2s">
                <div class="block-reveal white--text" style="--bc: #fff263; --d: .1s;">
                  <strong>Editoon</strong>
                </div>
                <div class="block-reveal white--text" style="--bc: #ffbb63; --d: .5s">
                  <strong>Just do it</strong>
                </div>
              </h1>
            </v-col>
          </v-row>

          <v-row>
            <v-col
              class="sub-index-txt my-5 text-sm-center text-md-left"
            >Start your webtoon experience</v-col>
          </v-row>

          <v-row style="width: 150px; height: 200px;" class="mx-sm-auto mx-md-auto">
            <v-col style="z-index: 10;">
              <v-btn x-large color="rgb(23, 46, 252)" class="white--text mt-16" @click="getStated">Get Started</v-btn>
            </v-col>
          </v-row>
          <!-- <v-row style="width: 150px;" class="my-5 mx-sm-auto mx-md-0">
            <v-col
              style="z-index: 10;  background-color: blue; text-align:center; padding: 10px; color:white; border-radius: 5px;"
              class="align-self-center;"
            >Look around
            </v-col>
          </v-row>-->
        </v-col>

        <v-col
          md="7"
          sm="6"
          class="mx-sm-auto p-3"
          style="z-index: 10; position: relative;  align-self: center; "
        >
          <!-- <img :src="require('@/assets/main_img.png')" style="width: 85%;" alt /> -->
        </v-col>
      </v-row>
    </v-container>

    <!-- main 2 페이지 -->
    <!-- backgroundImage: 'url(\'' + require(`@/assets/1-3.png`) + '\')', -->
    <v-container fluid class="px-10" :style="{ height: '100vh', zIndex: 10 }">
      <v-row class="align-content-center justify-center" style="height: 100%;">
        <v-col md="6" cols="10" class="mx-md-10 mx-sm-0 mx-xs-0 pa-0">
          <img style="width: 100%;" :src="require('@/assets/main_img.png')" alt />
        </v-col>

        <v-col md="4" lg="4" cols="10" class="pa-0">
          <v-row>
            <v-col class="main-index-txt">대충 소개 제목</v-col>
          </v-row>

          <v-row>
            <v-col style="font-size: 1rem;">
              대충 소개 내용 대충대충 소개2 내용대충대충3 소개 내용대충대충 소개 내용대충대충 소개 내용대충
              1대충 소개 내용대충대충 소개 내용 대충대충 소개2 내용대충대충3 소개 내용대충대충 소개 내용대충대충 소개 내용대충
              1대충 소개 내용대충대충 소개 내용 대충대충 소개2 내용대충대충3 소개 내용대충대충 소개 내용대충대충 소개 내용대충
              1대충 소개 내용대충대충 소개 내용 대충대충 소개2 내용대충대충3 소개 내용대충대충 소개 내용대충대충 소개 내용대충
              1대충 소개 내용대충
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>

    <!-- main 3 페이지 -->
    <!-- backgroundImage: 'url(\'' + require(`@/assets/1-3.png`) + '\')', -->
    <v-container fluid class="px-10" :style="{ height: '100vh', zIndex: 10 }">
      <v-row class="align-content-center justify-center" style="height: 100%;">
        <v-col md="4" lg="4" cols="10" class="pa-0">
          <v-row>
            <v-col class="main-index-txt">대충 소개 제목</v-col>
          </v-row>

          <v-row>
            <v-col style="font-size: 1rem;">
              대충 소개 내용 대충대충 소개2 내용대충대충3 소개 내용대충대충 소개 내용대충대충 소개 내용대충
              1대충 소개 내용대충대충 소개 내용 대충대충 소개2 내용대충대충3 소개 내용대충대충 소개 내용대충대충 소개 내용대충
              1대충 소개 내용대충대충 소개 내용 대충대충 소개2 내용대충대충3 소개 내용대충대충 소개 내용대충대충 소개 내용대충
              1대충 소개 내용대충대충 소개 내용 대충대충 소개2 내용대충대충3 소개 내용대충대충 소개 내용대충대충 소개 내용대충
              1대충 소개 내용대충
            </v-col>
          </v-row>
        </v-col>
        <v-col md="6" cols="10" class="mx-md-10 mx-sm-0 mx-xs-0 pa-0">
          <!-- <img style="width: 100%;" :src="require('@/assets/main_second.jpg')" alt /> -->
          <VueCompareImage :leftImage="require('@/assets/1-1.jpg')" :rightImage="require('@/assets/1-2.jpg')" />
        </v-col>
      </v-row>
    </v-container>

    <!-- main 4 페이지 -->
    <v-container fluid class="px-10" style="height: 100vh;">
      <v-row style="height: 30vh; text-align: center;" class="align-center">
        <v-col class="main-index-txt">대표작품들을 감상해보세요</v-col>
      </v-row>
      <v-row class="justify-space-around">
        <v-col class="mx-2">
          <img style="width: 100%; height: 300px;" src="https://picsum.photos/1920/1080?random" alt />
        </v-col>
        <v-col class="mx-2">
          <img style="width: 100%; height: 300px;" src="https://picsum.photos/1920/1080?random" alt />
        </v-col>
        <v-col class="mx-2">
          <img style="width: 100%; height: 300px;" src="https://picsum.photos/1920/1080?random" alt />
        </v-col>
        <v-col class="mx-2">
          <img style="width: 100%; height: 300px;" src="https://picsum.photos/1920/1080?random" alt />
        </v-col>
        <v-col class="mx-2">
          <img style="width: 100%; height: 300px;" src="https://picsum.photos/1920/1080?random" alt />
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
// import LoginModal from '@/components/login/LoginModal'
import VueCompareImage from 'vue-compare-image';



export default {
  name: "Index",
  components: {
    // LoginModal
    VueCompareImage,
  },
  data() {
    return {
      dialog: false,

      loginData: {
        username: "",
        email: "",
        password: "",
      },
    };
  },

  computed: {},

  methods: {
    isIndex() {
      this.$store.commit("isIndex", true);
    },
    clickLogin() {
      this.dialog = true;
    },
    getStated() {
      this.$router.push('photoeditor')
    }
  },
  created() {
    this.isIndex();
  },
};
</script>

<style>
.test-box {
  border: 1px solid white;
  color: white;
}

.background {
  background-image: url("/assets/mockupGif.gif");
  /* background-repeat: no-repeat; */
}

.test {
  /* background-size: 100vw;
  height: 100vw; */
  width: 100vw;
  height: calc(100vh + 64px);
  /* opacity: 0.3; */
  position: absolute;
  top: -64px;
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
  position: relative;
  color: black;
  font-weight: 900;
  font-size: 50px;
  z-index: 10;
}

.sub-index-txt {
  color: black;
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

*,
*::before,
*::after {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

/* other */
.info {
  margin: 20px 0;
  text-align: center;
}

p {
  color: #2e2e2e;
  margin-bottom: 20px;
}

/* block-$ */
.block-effect {
  font-size: calc(8px + 6vw);
}

.block-reveal {
  --t: calc(var(--td) + var(--d));

  color: transparent;
  padding: 4px;

  position: relative;
  overflow: hidden;

  animation: revealBlock 0s var(--t) forwards;
}

.block-reveal::after {
  content: "";

  width: 0%;
  height: 100%;
  padding-bottom: 4px;

  position: absolute;
  top: 0;
  left: 0;

  background: var(--bc);
  animation: revealingIn var(--td) var(--d) forwards,
    revealingOut var(--td) var(--t) forwards;
}

/* animations */
@keyframes revealBlock {
  100% {
    color: #0f0f0f;
  }
}

@keyframes revealingIn {
  0% {
    width: 0;
  }

  100% {
    width: 100%;
  }
}

@keyframes revealingOut {
  0% {
    transform: translateX(0);
  }

  100% {
    transform: translateX(100%);
  }
}

.abs-site-link {
  position: fixed;
  bottom: 20px;
  left: 20px;
  color: hsla(0, 0%, 0%, 0.6);
  font-size: 16px;
}
</style>