<template>
  <v-app>

     <NavBar v-if="isIndex" />

    <v-main>
      <router-view>

      </router-view>

      <!-- <MainIndex/> -->


    </v-main>
  </v-app>
</template>

<script>
import NavBar from '@/components/NavBar';
// import MainIndex from '@/views/MainIndex';

export default {
  name: 'App',

  components: {
    // MainIndex,
    NavBar,
  },

  data() {
    return {
      isIndex: false,
    }
  }
};
</script>

<style>

.container {
  margin: 0;
  padding: 0;
}

.row {
  margin: 0;
  padding: 0;
}
.col {
  margin: 0;
  padding: 0;
}

</style>