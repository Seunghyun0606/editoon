<template>
  <v-app>
    <NavBar/>
     <!-- v-if="!isIndex"  -->
    <!-- :class="[ isIndex ? '' : navMargin ]" -->
    <v-main :class="[ isIndex ? '' : navMargin ]">

      <router-view></router-view>



    </v-main>
    <Footer v-if="isNotEditor"/>

  </v-app>
</template>

<script>
import NavBar from '@/components/NavBar';
import Footer from '@/components/Footer';
// import MainIndex from '@/views/MainIndex';

export default {
  name: 'App',
  components: {
    // MainIndex,
    NavBar,
    Footer
  },
  computed: {
    isNotEditor() {
      return this.$store.state.isNotEditor
    }
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