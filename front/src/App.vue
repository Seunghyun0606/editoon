<template>
  <v-app>
    <NavBar/>
     <!-- v-if="!isIndex"  -->
    <!-- :class="[ isIndex ? '' : navMargin ]" -->
    <v-main style="margin-top: 64px;">

      <router-view></router-view>



    </v-main>
  </v-app>
</template>

<script>
import NavBar from '@/components/NavBar';
// import MainIndex from '@/views/MainIndex';

export default {
  name: 'App',
  computed: {
    isIndex() {
      return this.$store.state.isIndex
    }
  },

  components: {
    // MainIndex,
    NavBar,
  },

  data() {
    return {
      navMargin: 'nav-margin'
    }
  }
};
</script>

<style>

.nav-margin {
  margin-top: 64px;
}

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