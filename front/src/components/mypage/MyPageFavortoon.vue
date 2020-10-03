<template>
  <v-layout row wrap>
    <v-flex xs12 sm6 md4 lg3 v-for="book in userThumbnails" :key="book.id">
      <v-card hover class="ma-3" min-height="300" @click="reviewBook(book._id)">
        <v-layout column align-center fill-height class="text-center">
          <!-- <v-img src="https://picsum.photos/1920/1080?random"></v-img> -->
          <!-- <v-img src="https://j3b308.p.ssafy.io/image/editoon/(회원 no)/(에디툰 이미지)"></v-img> -->
          <v-img src="https://j3b308.p.ssafy.io/image/profileImg/200927_c1cb4f6611.JPG"></v-img>
          <!-- <v-img :src="book.thumbnail"></v-img> -->
          
          <v-card-title class="font-weight-light">{{ book.subject }} | {{ book.createDate }} </v-card-title>
          <!-- <v-spacer></v-spacer> -->
        </v-layout>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>
import { mapState } from 'vuex'
// _id, subject, thumbnail, createDate
  export default {
  name: "MyPageFavortoon",
  computed: {
    ...mapState(['userThumbnails'])
    // 나중에 props로 넘어오는 것 없애고, books 대신에 editoonImages 넣자.
  },
  data() {
      return {
      };
    },
  methods: {
    reviewBook(bookId) {
      // 클릭하면 모달형식으로 이미지를 보여줘야할듯
      // 그러면 넘겨줘야하는건 id값.
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
