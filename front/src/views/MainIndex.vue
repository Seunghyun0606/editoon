<template>

<div>
  
  <img class="test" :src="require('@/assets/mockupGif.gif')" alt="">
  <v-container style="height: 100vh; " class="mx-auto">
    <v-row style="height: 80%; text-align: center;" class="align-content-center">
      <v-col class="test2 ">
        여기에 메인 텍스트?
      </v-col>

    </v-row>
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
    <v-row style="text-align: end;">
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
    </v-row>
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
export default {
  name: 'Index',
  data() {
    return {
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

// watch: {
//   top (val) {
//     this.bottom = !val
//   },
//   right (val) {
//     this.left = !val
//   },
//   bottom (val) {
//     this.top = !val
//   },
//   left (val) {
//     this.right = !val
//   },
// },


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
  opacity: 0.3;
  position: absolute;
  z-index: 0;
}

.test2 {
  /* position: absolute; */
  font-size: 50px;
  z-index: 10;  
  
}

</style>