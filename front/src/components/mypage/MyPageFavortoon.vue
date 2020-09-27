<template>
  <v-layout row wrap>
    <v-flex xs12 sm6 md4 lg3 v-for="book in books" :key="book.id">
      <v-card hover class="ma-3" min-height="300" @click="reviewBook(book.id)">
        <v-layout column align-center fill-height class="text-center">
          <!-- <v-img src="https://picsum.photos/1920/1080?random"></v-img> -->
          <v-img src="https://j3b308.p.ssafy.io/images/profileImg/200927_c1cb4f6611.JPG"></v-img>
          
          <v-card-title class="font-weight-light">{{ book.title }} | {{ book.time }} </v-card-title>
          <!-- <v-spacer></v-spacer> -->
        </v-layout>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>
  export default {
  name: "MyPageFavortoon",
  props: {
      books: Array,
      imageURL: String,
  },
  data() {
      return {
      };
    },
  methods: {
    reviewBook(bookId) {
      this.$router.push(`reviewbook/${bookId}`)
    },
  }
  };
</script>

<style scoped>
  .v-card:hover {
    background: #FFA000;
  }
</style>
