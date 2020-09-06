<template>
  <v-app-bar
    absolute
    color="transparent"
    class="px-10"
    flat
  >
    <!-- src="https://picsum.photos/1920/1080?random" -->
    <!-- shrink-on-scroll -->
    <!-- scroll-target="#scrolling-techniques-2" -->
    <!-- <template v-slot:img="{ props }">
      <v-img
        v-bind="props"
        gradient="to top right, rgba(19,84,122,.5), rgba(128,208,199,.8)"
      ></v-img>
    </template> -->

    <!-- <v-app-bar-nav-icon></v-app-bar-nav-icon> -->

    <!-- <v-toolbar-title>Title</v-toolbar-title> -->
    <v-tabs align-with-title>
      <v-tab router-link to="/">Index</v-tab>
      <v-tab router-link to="/webtoonindex">Get started</v-tab>
      <v-tab router-link to="/abs">Best arts</v-tab>
      <!-- <v-tab router-link to="/Profile">Profile</v-tab> -->
      <!-- <v-tab router-link to="/Lolbody">Lolbody</v-tab> -->

      <!-- <v-tab router-link to="/Prac">Prac</v-tab> -->

    </v-tabs>

    <v-spacer></v-spacer>
    <v-tabs right hide-slider optional>
      <v-tab >Sign Up</v-tab>
      <v-tab>Log in</v-tab>

    </v-tabs>

    <!-- <v-btn icon>
      <v-icon>mdi-magnify</v-icon>
    </v-btn>

    <v-btn icon>
      <v-icon>mdi-heart</v-icon>
    </v-btn>
 -->
    <!-- <v-btn icon>
      <v-icon>mdi-dots-vertical</v-icon>
    </v-btn> -->
  </v-app-bar>
</template>


<script>

export default {
  name: 'NavBar',
  data() {
    return {

    }
  },

}
</script>

<style>

</style>