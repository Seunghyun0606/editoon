<template>
  <v-app>


    <v-main>

      <MainIndex/>


    </v-main>
  </v-app>
</template>

<script>
import MainIndex from '@/views/MainIndex';

export default {
  name: 'App',

  components: {
    MainIndex,
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