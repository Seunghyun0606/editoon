<template>
  <div>
    <img class="test" :src="require('@/assets/1-4.jpg')" alt />
    <!-- <img class="test ani1" :src="require('@/assets/1.jpg')" alt="">
    <img class="test ani2" :src="require('@/assets/2.jpg')" alt="">
    <img class="test ani3" :src="require('@/assets/3.jpg')" alt="">-->
    <!-- <div class="test1"></div> -->

    <v-container fluid style="height: 100vh; padding-top: 15px;">
      <v-row style="height: 80%;">
        <v-col md="5" sm="12" style="padding: 3vh 6vw;">
          <v-row>
            <v-col class="main-index-txt text-sm-center text-md-left">Start your webtoon experience</v-col>
          </v-row>

          <v-row>
            <v-col class="sub-index-txt my-5 text-sm-center text-md-left">대충 설명들어갈 예정</v-col>
          </v-row>

          <v-row style="width: 150px; height: 200px;" class="mx-sm-auto mx-md-auto">
            <v-col
              style="z-index: 10; background-color: blue; text-align:center; padding: 10px; color:white; border-radius: 5px;"
              class="align-self-center"
            >
              Get Started
            </v-col>
          </v-row>
          <!-- <v-row style="width: 150px;" class="my-5 mx-sm-auto mx-md-0">
            <v-col
              style="z-index: 10;  background-color: blue; text-align:center; padding: 10px; color:white; border-radius: 5px;"
              class="align-self-center;"
            >Look around
            </v-col>
          </v-row> -->
        </v-col>

        <v-col
          md="7"
          sm="6"
          class="mx-sm-auto"
          style="z-index: 10; position: relative;  align-self: center; "
        >
          <img :src="require('@/assets/test_main_right.jpg')" style="width: 100%;" alt />
        </v-col>
      </v-row>
    </v-container>

    <!-- main 두번째 페이지 -->
    <!-- backgroundImage: 'url(\'' + require(`@/assets/1-3.png`) + '\')', -->
    <v-container fluid class="px-10" :style="{ height: '100vh', zIndex: 10 }">
      <v-row class="align-content-center justify-center" style="height: 100%;">
        <v-col md="6" cols="10" class="mx-md-10 mx-sm-0 mx-xs-0 pa-0">
          <img style="width: 100%;" :src="require('@/assets/main_second.jpg')" alt />
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


    <!-- main 세번재 페이지 -->
    <v-container fluid class="px-10" style="height: 100vh;">
      <v-row style="height: 30vh; text-align: center;" class="align-center">
        <v-col class="main-index-txt">
          대표작품들을 감상해보세요
        </v-col>
      </v-row>
      <v-row class="justify-space-around">
        <v-col class="mx-2">
          <img style="width: 100%; height: 300px;" src="https://picsum.photos/1920/1080?random" alt="">
        </v-col>
        <v-col class="mx-2">
          <img style="width: 100%; height: 300px;" src="https://picsum.photos/1920/1080?random" alt="">
        </v-col>
        <v-col class="mx-2">
          <img style="width: 100%; height: 300px;" src="https://picsum.photos/1920/1080?random" alt="">
        </v-col>
        <v-col class="mx-2">
          <img style="width: 100%; height: 300px;" src="https://picsum.photos/1920/1080?random" alt="">
        </v-col>
        <v-col class="mx-2">
          <img style="width: 100%; height: 300px;" src="https://picsum.photos/1920/1080?random" alt="">
        </v-col>
      </v-row>

    </v-container>

  </div>

</template>

<script>
// import LoginModal from '@/components/login/LoginModal'

export default {
  name: "Index",
  components: {
    // LoginModal
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

  computed: {
  },

  methods: {
    isIndex() {
      this.$store.commit("isIndex", true);
    },
    clickLogin() {
      this.dialog = true;
    },
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
</style>