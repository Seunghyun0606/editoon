<template>
  <div>
    <v-container
      fluid
      class="main-background-first pt-16"
      :style="[mainBackgroundImage.one]"
      style="background-color: #fb9"
    >
      <v-row
        style="height: 90%; justify-content: center; align-content: center"
        class="pt-16"
      >
        <!-- <v-col style="padding: 3vh 6vw;">
          <v-row> -->
        <!-- <v-col class="text-center main-index-txt"> -->
        <div @click="moveToEditor" class="text" style="cursor: pointer">
          <span class="main-index-txt">E</span>
          <span class="main-index-txt">D</span>
          <span class="main-index-txt">I</span>
          <span class="main-index-txt">T</span>
          <span class="main-index-txt">O</span>
          <span class="main-index-txt">O</span>
          <span class="main-index-txt">N</span>
        </div>
      </v-row>
      <div
        @click="scrollDown(1)"
        class="mx-auto d-flex justify-center main-scroll-down"
      >
        <v-icon style="color: white; font-size: 36px">mdi-chevron-down</v-icon>
      </div>
    </v-container>

    <!-- 타임라인시작 -->
    <v-container fluid class="px-10">
      <h1 class="text-center main-index-txt" style="margin-top: 100px">
        Wecolme to Editoon!
      </h1>

      <div class="history-tl-container" style="margin-top: 100px">
        <ul class="tl">
          <li @click="scrollDown(2)" class="tl-item" ng-repeat="item in retailer_history">
            <div class="timestamp">
              <img
                width="450"
                style="margin-right: -130px"
                src="@/assets/step1.png"
              />
            </div>
            <div class="item-title">
              Step1. <br />
              사진 찰칵!<br />
              <small style="font-size: 20px"
                >일상 속에서 본인의 개성을 <br />드러낼 멋진 사진을
                찍어보세요</small
              >
            </div>

            <!-- <div class="timestamp"><img src="@/assets/step1.png" /></div> -->
          </li>
          <li @click="scrollDown(3)" class="tl-item" ng-repeat="item in retailer_history">
            <div class="timestamp">
              Step2.<br />
              에디툰으로 편집<br />
              <small style="font-size: 20px"
                >몇 번의 클릭만으로 손쉽게 사진을 <br />편집하고 나만의 웹툰을
                만들어보세요</small
              >
            </div>
            <div class="item-title">
              <img
                width="450"
                style="margin-left: -60px"
                src="@/assets/step2.png"
              />
            </div>
            <div @click.stop="scrollDown(4)" class="item-title" style="margin-top: 280px">
              Step3. <br />간단히 완성!<br />
              <small style="font-size: 20px">
                가장 빠르고 효과좋은 애니메이션 Tool <br />에디툰을
                이용해보세요!
              </small>
            </div>
          </li>
          <li @click="scrollDown(4)" class="tl-item" ng-repeat="item in retailer_history">
            <div class="timestamp">
              <img
                width="450"
                style="margin-top: -1000px; margin-right: -130px"
                src="@/assets/step3.png"
              />
            </div>
          </li>
        </ul>
      </div>
    </v-container>
    <!-- 타임라인끝 -->
    <v-container
      fluid
      class="px-10 main-background"
      :style="mainBackgroundImage.three"
      @click="scrollDown(5)"
    >
      <v-row
        class="align-content-center justify-center py-10"
        :class="{
          'fade-in': activeClass.third,
          'fade-out': !activeClass.third,
        }"
        style="height: 20%"
      >
        <h1 class="main-index-txt">How to use...</h1>
      </v-row>
      <v-row
        class="align-content-center justify-center pb-12"
        :class="{
          'fade-in': activeClass.third,
          'fade-out': !activeClass.third,
        }"
        style=""
      >
        <img style="width: 40%;" :src="require('@/assets/webtoon.png')" alt />
      </v-row>
      <!-- <div
        @click="scrollDown(5)"
        class="mx-auto d-flex justify-center main-scroll-down"
        :class="{
          'fade-in': activeClass.third,
          'fade-out': !activeClass.third,
        }"
      >
        <v-icon style="color: white; font-size: 36px">mdi-chevron-down</v-icon>
      </div> -->
    </v-container>

    <!-- 다음페이지 -->

    <v-container
      fluid
      class="px-10 main-background"
      :style="[mainBackgroundImage.four]"
      @click="scrollDown(6)"
    >
      <v-row
        style="height:100%;"
      >
        <v-col
          :class="{
            'fade-in': activeClass.fourth,
            'fade-out': !activeClass.fourth,
          }"
        >
          <v-row style="height: 30vh; text-align: center" class="align-center">
            <v-col class="main-index-txt"> Take a look at sample!</v-col>
          </v-row>
          <v-row class="justify-space-around">
            <v-col class="mx-2" @click.stop>
              <VueCompareImage
                style="height: 100%; width:100%;"
                :leftImage="require('@/assets/lee1.jpg')"
                :rightImage="require('@/assets/lee2.jpg')"
              />
            </v-col>
            <v-col class="mx-2" @click.stop>
              <VueCompareImage
                style="height: 100%; width:100%;"
                :leftImage="require('@/assets/seo1.jpg')"
                :rightImage="require('@/assets/seo2.jpg')"
              />
            </v-col>
            <v-col class="mx-2" @click.stop>
              <VueCompareImage
                style="height: 100%; width:100%;"
                :leftImage="require('@/assets/suji1.jpg')"
                :rightImage="require('@/assets/suji2.jpg')"
              />
            </v-col>
            <v-col class="mx-2" @click.stop>
              <VueCompareImage
                style="height: 100%; width:100%;"
                :leftImage="require('@/assets/iu1.jpg')"
                :rightImage="require('@/assets/iu2.jpg')"
              />
            </v-col>

            <v-col class="mx-2" @click.stop>
              <VueCompareImage
                style="height: 100%; width:100%;"
                :leftImage="require('@/assets/you1.jpg')"
                :rightImage="require('@/assets/you2.jpg')"
              />
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
      <!-- <div
        @click="scrollDown(6)"
        class="mx-auto d-flex justify-center main-scroll-down"
        :class="{
          'fade-in': activeClass.fourth,
          'fade-out': !activeClass.fourth,
        }"
      >
        <v-icon style="color: white; font-size: 36px">mdi-chevron-down</v-icon>
      </div> -->
    <v-container
      fluid
      class="px-10 main-background"
      :style="[mainBackgroundImage.four]"
      :class="{
        'fade-in': activeClass.fourth,
        'fade-out': !activeClass.fourth,
      }"

    >
      <v-row style="height: 100%;">
        <v-col style="align-self: center;">
          <v-row class="align-content-center justify-center">
            <h1 class="main-index-txt">Are you ready?</h1>
          </v-row>
          <v-row class="align-content-center justify-center">
            <h1>지금 바로 에디툰을 시작해보세요!!</h1>
          </v-row>
          <v-row
            class="wrap align-content-center justify-center"
            style="height:200px"
          >
            <button class="buttonn" @click="moveToEditor">Get started!</button>
          </v-row>

        </v-col>
      </v-row>
      <!-- <v-row class="align-content-center justify-center py-10">
        <h1 class="main-index-txt">Are you ready?</h1>
      </v-row>
       -->
      <!-- <div
        @click="scrollDown(0)"
        class="mx-auto main-scroll-down d-flex justify-center"
        :class="{
          'fade-in': activeClass.fourth,
          'fade-out': !activeClass.fourth,
        }"
      >
        <v-icon style="color: black; font-size: 36px">mdi-chevron-up</v-icon>
      </div> -->
    </v-container>

    <div
      fluid
      class="px-10 main-background-last"
      :style="[mainBackgroundImage.four]"
    ></div>
    <div v-show="viewPortHeight / 2 <= current" @click="scrollDown(0)">
      <v-icon
        class="main-scroll-up"
        style="
          z-index: 9999;
          background-color: black;
          width: 40px;
          height: 40px;
          border-radius: 50%;
          color: white;
          font-size: 36px;
          position: fixed;
          bottom: 50px;
          right: 50px;
        "
        >mdi-chevron-up</v-icon
      >
    </div>
  </div>
</template>

<script>
import VueCompareImage from "vue-compare-image";
import axios from "axios";
export default {
  name: "Index",
  components: {
    VueCompareImage,
  },
  data() {
    return {
      test123: "",
      dialog: false,
      isActive: true,
      loginData: {
        username: "",
        email: "",
        password: "",
      },
      current: 0,
      mainBackgroundImage: {
        one: {
          // backgroundImage: "url('" + require(`@/assets/simple12.gif`) + "')",
          backgroundImage: "url('" + require(`@/assets/back.gif`) + "')",
        },
        two: {
          backgroundImage: "url('" + require(`@/assets/green2.jpg`) + "')",
        },
        three: {
          backgroundImage: "url('" + require(`@/assets/green2.jpg`) + "')",
        },
        four: {
          backgroundImage: "url('" + require(`@/assets/green2.jpg`) + "')",
        },
      },
      activeClass: {
        second: false,
        third: false,
        fourth: false,
      },
    };
  },

  computed: {
    viewPortHeight() {
      return window.innerHeight;
    },
  },

  methods: {
    sendimg() {
      const formData = new FormData();
      const file = document.getElementById("imgselector");
      formData.append("img1", file.files[0]);
      formData.append("img2", file.files[1]);

      axios
        .post("http://j3b308.p.ssafy.io:8000/ai/ImgtoAnime/", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((res) => {
          console.log(1, res.data);
          console.log(2, res);
          this.test123 = res.data;
          console.log(this.test123);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    moveToEditor() {
      this.$router.push("PhotoEditor");
    },
    currentScrollPlace() {
      this.current = document.documentElement.scrollTop;
      this.fadeInOut(this.current);
    },
    isIndex() {
      this.$store.commit("isIndex", true);
    },
    isNotEditor() {
      this.$store.commit("isNotEditor", true);
    },
    clickLogin() {
      this.dialog = true;
    },
    scrollDown(page) {
      let y = this.viewPortHeight;
      y *= page;
      window.scrollTo({ top: y, left: 0, behavior: "smooth" });
    },

    // fadeInOut
    fadeInOut(current) {
      let y = this.viewPortHeight;
      let currentPlace = current + y;
      if (currentPlace > y + y / 5 && currentPlace < 4 * y + y / 5) {
        this.activeClass.second = true;
        this.activeClass.third = false;
        this.activeClass.fourth = false;
      } else if (currentPlace > 4 * y + y / 5 && currentPlace < 5 * y + y / 5) {
        this.activeClass.second = false;
        this.activeClass.third = true;
        this.activeClass.fourth = false;
      } else if (currentPlace > 5 * y + y / 5) {
        this.activeClass.second = false;
        this.activeClass.third = false;
        this.activeClass.fourth = true;
      } else {
        this.activeClass.second = false;
        this.activeClass.third = false;
        this.activeClass.fourth = false;
      }
    },
  },
  created() {
    this.isIndex();
    this.isNotEditor();
    if ( localStorage.getItem("isLogin") ) {
      this.$store.state.isLogin = true
      this.$store.dispatch('getUserInfo')
    }
  },
  // 계속해서 scroll이 변하는 것을 추적하기 위함.
  mounted() {
    window.addEventListener("scroll", this.currentScrollPlace);
  },
  destroy() {
    window.removeEventListener("scroll", this.currentScrollPlace);
  },
};
</script>

<style lang="scss">
.fade-in {
  animation: fade-in 2s;
  opacity: 1;
}

@keyframes fade-in {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

.fade-out {
  animation: fade-out 2s;
  opacity: 0;
}

@keyframes fade-out {
  from {
    opacity: 1;
  }
  to {
    opacity: 0;
  }
}
.check {
  margin-top: 0 !important;
}

.main-scroll-up:hover {
  cursor: pointer;
  background-color: rgba(255, 255, 255, 0.4) !important;
}

.main-scroll-down {
  background-color: rgba(255, 255, 255, 0.4);
  border-radius: 50%;
  height: 40px;
  width: 40px;
  position: relative;
}

.main-scroll-down:hover {
  cursor: pointer;
  background-color: rgba(255, 255, 255, 0.3) !important;
}

.test-box {
  border: 1px solid white;
  color: white;
}

.main-background-first {
  height: 100vh;
  background-attachment: fixed;
  background-size: cover;
  position: relative;
}

// .main-background-first::after {
//   background-color: black !important;
//   opacity: 0.3;
//   position: absolute;
//   content: "";
//   width: 100%;
//   height: calc(100%);
//   top: 0;
//   left: 0;
// }

.main-background {
  height: 100vh;
  background-attachment: fixed;
  background-size: cover;
}

.main-background-last {
  height: 5vh;
  background-attachment: fixed;
  background-size: cover;
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

/* .abs-site-link {
  position: fixed;
  bottom: 20px;
  left: 20px;
  color: hsla(0, 0%, 0%, 0.6);
  font-size: 16px;
} */

.text {
  font-size: 40px;
  display: flex;
  justify-content: center;
  transform: translateY(-50%);
  // margin: 250px auto;
  text-align: center;
  // border: 1px solid #000;
  transition: transform 0.3s ease-in-out;

  &:hover {
    transform: rotateX(35deg), translateY(-50%);
    span {
      color: #ccc;
      &:nth-child(odd) {
        transform: skewY(15deg);
        // background-color: #f00;
        // box-shadow: 0 60px 20px rgba(0,0,0,0.1);
      }
      &:nth-child(even) {
        transform: skewY(-15deg);
        background-color: #f9f9f9;
        color: #a6a6a6;
        // box-shadow: 0 60px 20px rgba(0,0,0,0.1);
      }
    }
  }

  > span {
    display: block;
    background-color: #fff;
    width: 150px;
    height: 150px;
    line-height: 150px;
    transition: transform 0.3s ease-in-out, color 0.3s ease-in-out,
      background-color 0.3s ease-in-out;
    box-shadow: 0 40px 50px rgba(0, 0, 0, 0.1);
    &:first-child {
      border-radius: 5px 0 0 5px;
    }
  }
}

.history-tl-container {
  font-family: "Roboto", sans-serif;
  width: 0%;
  margin: auto;
  display: block;
  position: relative;
}
.history-tl-container ul.tl {
  margin: 20px 0;
  padding: 0;
  display: inline-block;
}
.history-tl-container ul.tl li {
  list-style: none;
  margin: auto;
  // margin-left: 200px;
  min-height: 50px;
  /*background: rgba(255,255,0,0.1);*/
  border-left: 3px solid #9faba3;
  padding: 0 0 50px 30px;
  position: relative;
}
.history-tl-container ul.tl li:last-child {
  border-left: 0;
}
.history-tl-container ul.tl li::before {
  position: absolute;
  left: -20px;
  top: -5px;
  content: " ";
  border: 8px solid rgba(255, 255, 255, 0.74);
  border-radius: 500%;
  background: #7edec0;
  // #258cc7
  height: 40px;
  width: 40px;
  transition: all 500ms ease-in-out;
}
.history-tl-container ul.tl li:hover::before {
  border-color: #7edec0;
  transition: all 1000ms ease-in-out;
}
ul.tl li .item-title {
  position: relative;
  top: -300px;
  right: -40%;
  color: #8d8d8d;
  // background-color: red;
  font-size: 35px;
}
ul.tl li .item-detail {
  color: rgba(0, 0, 0, 0.5);
  font-size: 15px;
}
ul.tl li .timestamp {
  color: #8d8d8d;
  position: relative;
  width: 100%;
  left: -150%;
  text-align: right;
  font-size: 35px;
  // background-color: red;
}

//sdfsdf

.wrap {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.buttonn {
  min-width: 300px;
  min-height: 60px;
  font-family: "Nunito", sans-serif;
  font-size: 22px;
  text-transform: uppercase;
  letter-spacing: 1.3px;
  font-weight: 700;
  color: #313133;
  background: #4fd1c5;
  background: linear-gradient(
    90deg,
    rgba(129, 230, 217, 1) 0%,
    rgba(79, 209, 197, 1) 100%
  );
  border: none;
  border-radius: 1000px;
  box-shadow: 12px 12px 24px rgba(79, 209, 197, 0.64);
  transition: all 0.3s ease-in-out 0s;
  cursor: pointer;
  outline: none;
  position: relative;
  padding: 10px;
}

buttonn::before {
  content: "";
  border-radius: 1000px;
  min-width: calc(300px + 12px);
  min-height: calc(60px + 12px);
  border: 6px solid #00ffcb;
  box-shadow: 0 0 60px rgba(0, 255, 203, 0.64);
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  opacity: 0;
  transition: all 0.3s ease-in-out 0s;
}

.buttonn:hover,
.buttonn:focus {
  color: #313133;
  transform: translateY(-6px);
}

buttonn:hover::before,
buttonn:focus::before {
  opacity: 1;
}

buttonn::after {
  content: "";
  width: 30px;
  height: 30px;
  border-radius: 100%;
  border: 6px solid #00ffcb;
  position: absolute;
  z-index: -1;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  animation: ring 1.5s infinite;
}
</style>
