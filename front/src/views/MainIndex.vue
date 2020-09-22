<template>
  <div>
    <!-- <div class="main-background-first" :style="{backgroundImage: 'url(\'' + require(`@/assets/bg2.jpg`) + '\')', backgroundAttachment: 'fixed', backgroundSize: 'cover'}"></div> -->
    <!-- <img class="main-background-first" :src="require('@/assets/bg2.jpg')" alt /> -->
    <!-- <img class="test ani1" :src="require('@/assets/1.jpg')" alt="">
    <img class="test ani2" :src="require('@/assets/2.jpg')" alt="">
    <img class="test ani3" :src="require('@/assets/3.jpg')" alt="">-->
    <!-- <div class="test1"></div> -->

    <v-container
      fluid
      class="main-background-first pt-16"
      :style="[mainBackgroundImage.one]"
      style="background-color: #fb9;"
    >
      <v-row style="height: 90%;">
        <v-col style="padding: 3vh 6vw;">
          <v-row>
            <v-col class="text-center main-index-txt">
              <!-- <v-col
              class="text-center white--text main-index-txt text-sm-center text-md-left"
            > -->
              <div class="text">
                <span>E</span>
                <span>D</span>
                <span>I</span>
                <span>T</span>
                <span>O</span>
                <span>O</span>
                <span>N</span>
              </div>
              <!-- <h1 class="d-inline-block block-effect" style="--td: 1.2s">
                <div
                  class="block-reveal white--text"
                  style="--bc: #fff263; --d: .1s;"
                >
                  <strong>Editoon</strong>
                </div>
                <div
                  class="text-center block-reveal white--text text-md-left"
                  style="--bc: #ffbb63; --d: .5s"
                >
                  <strong>Just do it</strong>
                </div>
              </h1> -->
            </v-col>
          </v-row>
          <!-- 
          <v-row>
            <v-col
              class="text-center sub-index-txt my-5 text-sm-center text-md-left"
              >Start your webtoon experience</v-col
            >
          </v-row> -->

          <!-- <v-row>
            <v-col align="center">
              <v-btn
                x-large
                color="rgb(23, 46, 252)"
                class="white--text mx-auto "
                @click="getStated"
                >Get Started</v-btn
              >
            </v-col>
          </v-row> -->
          <!-- <v-row style="width: 150px;" class="my-5 mx-sm-auto mx-md-0">
            <v-col
              style="z-index: 10;  background-color: blue; text-align:center; padding: 10px; color:white; border-radius: 5px;"
              class="align-self-center;"
            >Look around
            </v-col>
          </v-row>-->
        </v-col>

        <!-- <v-col
          md="7"
          sm="6"
          class="mx-sm-auto p-3"
          style="z-index: 10; position: relative;  align-self: center; "
        >
          <img :src="require('@/assets/main_img.png')" style="width: 85%;" alt />
        </v-col>-->
      </v-row>
      <div
        @click="scrollDown(1)"
        class="mx-auto d-flex justify-center main-scroll-down"
      >
        <v-icon style="color: white; font-size: 36px;">mdi-chevron-down</v-icon>
      </div>
    </v-container>

    <!-- main 2 페이지 -->
    <!-- backgroundImage: 'url(\'' + require(`@/assets/1-3.png`) + '\')', -->

    <!-- <img :src="require('@/assets/1-1.jpg')" class="main-background" alt /> -->
    <!-- <v-parallax
      :src="require('@/assets/1-1.jpg')"
      class="main-background"
    ></v-parallax>-->

    <v-container
      fluid
      class="px-10 main-background"
      :style="mainBackgroundImage.two"
    >
      <v-row class="align-content-center justify-center" style="height: 90%;">
        <v-col md="6" cols="10" class="mx-md-10 mx-sm-0 mx-xs-0 pa-0">
          <img
            style="width: 100%;"
            :src="require('@/assets/main_img.png')"
            alt
          />
        </v-col>

        <v-col md="4" lg="4" cols="10" class="pa-0">
          <v-row>
            <v-col class="main-index-txt">대충 소개 제목</v-col>
          </v-row>

          <v-row>
            <v-col style="font-size: 1rem;">
              대충 소개 내용 대충대충 소개2 내용대충대충3 소개 내용대충대충 소개
              내용대충대충 소개 내용대충 1대충 소개 내용대충대충 소개 내용
              대충대충 소개2 내용대충대충3 소개 내용대충대충 소개 내용대충대충
              소개 내용대충 1대충 소개 내용대충대충 소개 내용 대충대충 소개2
              내용대충대충3 소개 내용대충대충 소개 내용대충대충 소개 내용대충
              1대충 소개 내용대충대충 소개 내용 대충대충 소개2 내용대충대충3
              소개 내용대충대충 소개 내용대충대충 소개 내용대충 1대충 소개
              내용대충
            </v-col>
          </v-row>
        </v-col>
      </v-row>
      <div
        @click="scrollDown(2)"
        class="mx-auto d-flex justify-center main-scroll-down"
      >
        <v-icon style="color: white; font-size: 36px;">mdi-chevron-down</v-icon>
      </div>
    </v-container>

    <!-- main 3 페이지 -->
    <!-- backgroundImage: 'url(\'' + require(`@/assets/1-3.png`) + '\')', -->
    <!-- <img :src="require('@/assets/1-3.jpg')" class="main-background" alt /> -->

    <v-container
      fluid
      class="px-10 main-background"
      :style="mainBackgroundImage.three"
    >
      <v-row class="align-content-center justify-center" style="height: 90%;">
        <v-col md="4" lg="4" cols="10" class="pa-0">
          <v-row>
            <v-col class="main-index-txt">대충 소개 제목</v-col>
          </v-row>

          <v-row>
            <v-col style="font-size: 1rem;">
              대충 소개 내용 대충대충 소개2 내용대충대충3 소개 내용대충대충 소개
              내용대충대충 소개 내용대충 1대충 소개 내용대충대충 소개 내용
              대충대충 소개2 내용대충대충3 소개 내용대충대충 소개 내용대충대충
              소개 내용대충 1대충 소개 내용대충대충 소개 내용 대충대충 소개2
              내용대충대충3 소개 내용대충대충 소개 내용대충대충 소개 내용대충
              1대충 소개 내용대충대충 소개 내용 대충대충 소개2 내용대충대충3
              소개 내용대충대충 소개 내용대충대충 소개 내용대충 1대충 소개
              내용대충
            </v-col>
          </v-row>
        </v-col>
        <v-col md="6" cols="10" class="mx-md-10 mx-sm-0 mx-xs-0 pa-0">
          <!-- <img style="width: 100%;" :src="require('@/assets/main_second.jpg')" alt /> -->
          <VueCompareImage
            :leftImage="require('@/assets/1-1.jpg')"
            :rightImage="require('@/assets/1-2.jpg')"
          />
        </v-col>
      </v-row>
      <div
        @click="scrollDown(3)"
        class="mx-auto d-flex justify-center main-scroll-down"
      >
        <v-icon style="color: white; font-size: 36px;">mdi-chevron-down</v-icon>
      </div>
    </v-container>

    <!-- main 4 페이지 -->
    <!-- <img :src="require('@/assets/1-2.jpg')" class="main-background-last" alt /> -->

    <v-container
      fluid
      class="px-10 main-background-last"
      :style="[mainBackgroundImage.four]"
    >
      <v-row style="height: 90%;">
        <v-col>
          <v-row style="height: 30vh; text-align: center;" class="align-center">
            <v-col class="main-index-txt">대표작품들을 감상해보세요</v-col>
          </v-row>
          <v-row class="justify-space-around">
            <v-col class="mx-2">
              <img
                style="width: 100%; height: 300px;"
                src="https://picsum.photos/1920/1080?random"
                alt
              />
            </v-col>
            <v-col class="mx-2">
              <img
                style="width: 100%; height: 300px;"
                src="https://picsum.photos/1920/1080?random"
                alt
              />
            </v-col>
            <v-col class="mx-2">
              <img
                style="width: 100%; height: 300px;"
                src="https://picsum.photos/1920/1080?random"
                alt
              />
            </v-col>
            <v-col class="mx-2">
              <img
                style="width: 100%; height: 300px;"
                src="https://picsum.photos/1920/1080?random"
                alt
              />
            </v-col>
            <v-col class="mx-2">
              <img
                style="width: 100%; height: 300px;"
                src="https://picsum.photos/1920/1080?random"
                alt
              />
            </v-col>
          </v-row>
        </v-col>
      </v-row>
      <div
        @click="scrollDown(0)"
        class="mx-auto main-scroll-down d-flex justify-center"
      >
        <v-icon style="color: black; font-size: 36px;">mdi-chevron-up</v-icon>
      </div>
    </v-container>
    <div v-show="viewPortHeight / 2 <= current" @click="scrollDown(0)">
      <v-icon
        class="main-scroll-up"
        style="z-index: 9999; background-color: black; width: 40px; height: 40px; border-radius: 50%; color: white; font-size: 36px; position: fixed; bottom: 50px; right: 50px;"
        >mdi-chevron-up</v-icon
      >
    </div>
  </div>
</template>

<script>
// import LoginModal from '@/components/login/LoginModal'
import VueCompareImage from "vue-compare-image";

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
      current: 0,
      mainBackgroundImage: {
        one: {
          backgroundImage: "url('" + require(`@/assets/simple10.gif`) + "')",
        },
        two: {
          backgroundImage: "url('" + require(`@/assets/simple2.jpg`) + "')",
        },
        three: {
          backgroundImage: "url('" + require(`@/assets/simple2.jpg`) + "')",
        },
        four: {
          backgroundImage: "url('" + require(`@/assets/simple6.jpg`) + "')",
        },
      },
    };
  },
  // watch: {
  //   current: {
  //     immediate: true,
  //     handler() {
  //       this.current = document.documentElement.scrollTop

  //     }
  //   }
  // },

  computed: {
    viewPortHeight() {
      return window.innerHeight;
    },
  },

  methods: {
    currentScrollPlace() {
      this.current = document.documentElement.scrollTop;
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
    getStated() {
      this.$router.push("photoeditor");
    },
    scrollDown(page) {
      var y = this.viewPortHeight;
      y *= page;
      window.scrollTo({ top: y, left: 0, behavior: "smooth" });
    },
  },
  created() {
    this.isIndex();
    this.isNotEditor();
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
}

.main-background {
  height: 100vh;
  background-attachment: fixed;
  background-size: cover;
}
.main-background-last {
  height: 100vh;
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
  margin-top: 40vh;
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
</style>
