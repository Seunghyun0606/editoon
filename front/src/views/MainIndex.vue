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
        <div class="text" style="cursor: pointer">
          <span>E</span>
          <span>D</span>
          <span>I</span>
          <span>T</span>
          <span>O</span>
          <span>O</span>
          <span>N</span>
        </div>

        <div>
          <label>사진!!!:</label>
          <input type="file" id="imgselector" multiple />
          <v-btn @click="sendimg">test</v-btn>
        </div>

        <!-- </v-col> -->
      </v-row>

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
      <!-- </v-col> -->
      <!-- </v-row> -->
      <div>
        test
        <img
          :src="'data:image/png;base64,' + `${test123}`"
          alt=""
          style="
            width: 100px;
            height: 100px;
            background-color: black;
            z-index: 9999;
          "
        />
      </div>
      <div
        @click="scrollDown(1)"
        class="mx-auto d-flex justify-center main-scroll-down"
      >
        <v-icon style="color: white; font-size: 36px">mdi-chevron-down</v-icon>
      </div>
    </v-container>

    <v-container
      fluid
      class="px-10 main-background"
      :style="mainBackgroundImage.two"
    >
      <v-row
        class="align-content-center justify-center d-flex"
        :class="{
          'fade-in': activeClass.second,
          'fade-out': !activeClass.second,
        }"
        style="height: 90%; width: 100%"
      >
        <v-col md="4" lg="4" cols="10" class="pa-0">
          <v-row>
            <v-col class="main-index-txt">사진에서 만화로 빠르고 쉽게!</v-col>
          </v-row>

          <v-row>
            <v-col style="font-size: 1.7em">
              <div>
                한 번만 클릭하면 몇 초만에 사진에서 만화로 바꿀 수 있습니다.
                지금 당장, 당신의 셀피를 만화로 만들어 보세요!
              </div>
              <div>
                A single click of our Editoon, you'll go from photo to cartoon
                in seconds. Make your selfie into a cartoon right now!!
              </div>
            </v-col>
          </v-row>
        </v-col>
        <v-col
          justify-center
          md="6"
          cols="10"
          class="mx-md-10 mx-sm-0 mx-xs-0 mx-lg-5 pa-0"
          style="height: 60vh"
        >
          <!-- <img style="width: 100%;" :src="require('@/assets/main_second.jpg')" alt /> -->

          <VueCompareImage
            style="height: 100%"
            :leftImage="require('@/assets/iu.jpg')"
            :rightImage="require('@/assets/iu_cartoon.jpg')"
          />
        </v-col>
      </v-row>
      <div
        @click="scrollDown(2)"
        class="mx-auto d-flex justify-center main-scroll-down"
        :class="{
          'fade-in': activeClass.second,
          'fade-out': !activeClass.second,
        }"
      >
        <v-icon style="color: white; font-size: 36px">mdi-chevron-down</v-icon>
      </div>
    </v-container>

    <!-- main 3 페이지 -->

    <v-container
      fluid
      class="px-10 main-background"
      :style="mainBackgroundImage.three"
    >
      <v-row
        class="align-content-center justify-center"
        :class="{
          'fade-in': activeClass.third,
          'fade-out': !activeClass.third,
        }"
        style="height: 90%"
      >
        <v-col md="6" cols="10" class="mx-md-10 mx-sm-0 mx-xs-0 pa-0">
          <img style="width: 100%" :src="require('@/assets/webtoon.png')" alt />
        </v-col>

        <v-col md="4" lg="4" cols="10" class="pa-0">
          <v-row>
            <v-col class="main-index-txt">사진을 웹툰으로!</v-col>
          </v-row>

          <v-row>
            <v-col style="font-size: 1.7rem">
              <div>
                빠르고 간단하게, 몇번의 클릭만으로 웹툰을 만들어보세요. 간단한
                배치와 말풍선을 추가하여 꾸밀수있습니다!
              </div>
              <div>
                Quickly and simply, create a webtoon with just a few clicks. you
                can decorate it by adding simple arrangements and speech
                bubbles!
              </div>
            </v-col>
          </v-row>
        </v-col>
      </v-row>

      <div
        @click="scrollDown(3)"
        class="mx-auto d-flex justify-center main-scroll-down"
        :class="{
          'fade-in': activeClass.third,
          'fade-out': !activeClass.third,
        }"
      >
        <v-icon style="color: white; font-size: 36px">mdi-chevron-down</v-icon>
      </div>
    </v-container>

    <!-- main 4 페이지 -->
    <!-- <img :src="require('@/assets/1-2.jpg')" class="main-background-last" alt /> -->

    <v-container
      fluid
      class="px-10 main-background-last"
      :style="[mainBackgroundImage.four]"
    >
      <v-row style="height: 90%">
        <v-col
          :class="{
            'fade-in': activeClass.fourth,
            'fade-out': !activeClass.fourth,
          }"
        >
          <v-row style="height: 30vh; text-align: center" class="align-center">
            <v-col class="main-index-txt">대표작품들을 감상해보세요</v-col>
          </v-row>
          <v-row class="justify-space-around">
            <v-col class="mx-2">
              <img
                style="width: 100%; height: 300px"
                src="https://picsum.photos/1920/1080?random"
                alt
              />
            </v-col>
            <v-col class="mx-2">
              <img
                style="width: 100%; height: 300px"
                src="https://picsum.photos/1920/1080?random"
                alt
              />
            </v-col>
            <v-col class="mx-2">
              <img
                style="width: 100%; height: 300px"
                src="https://picsum.photos/1920/1080?random"
                alt
              />
            </v-col>
            <v-col class="mx-2">
              <img
                style="width: 100%; height: 300px"
                src="https://picsum.photos/1920/1080?random"
                alt
              />
            </v-col>
            <v-col class="mx-2">
              <img
                style="width: 100%; height: 300px"
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
        :class="{
          'fade-in': activeClass.fourth,
          'fade-out': !activeClass.fourth,
        }"
      >
        <v-icon style="color: black; font-size: 36px">mdi-chevron-up</v-icon>
      </div>
    </v-container>
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
          backgroundImage: "url('" + require(`@/assets/simple12.gif`) + "')",
        },
        two: {
          backgroundImage: "url('" + require(`@/assets/simple2.jpg`) + "')",
        },
        three: {
          backgroundImage: "url('" + require(`@/assets/simple2.jpg`) + "')",
        },
        four: {
          backgroundImage: "url('" + require(`@/assets/simple11.gif`) + "')",
        },
      },
      activeClass: {
        second: false,
        third: false,
        fourth: false,
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
        })
        .catch((err) => {
          console.log(err);
        });
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
    getStated() {
      this.$router.push("photoeditor");
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
      if (currentPlace > y + y / 5 && currentPlace < 2 * y + y / 5) {
        this.activeClass.second = true;
        this.activeClass.third = false;
        this.activeClass.fourth = false;
      } else if (currentPlace > 2 * y + y / 5 && currentPlace < 3 * y + y / 5) {
        this.activeClass.second = false;
        this.activeClass.third = true;
        this.activeClass.fourth = false;
      } else if (currentPlace > 3 * y + y / 5) {
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
</style>
