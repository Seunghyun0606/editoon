<template>
  <v-app>

    <!-- <NavBar/> -->

    <v-main>

      <MainIndex/>


    </v-main>
  </v-app>
</template>

<script>
// import NavBar from '@/components/NavBar';
import MainIndex from '@/views/MainIndex';

export default {
  name: 'App',

  components: {
    MainIndex,
    // NavBar,
  },

  data: () => ({
    //
  }),
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