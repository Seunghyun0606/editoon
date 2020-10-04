<template>
  <v-layout row wrap>
    <v-flex xs12 sm6 md4 lg3 v-for="thumnail in userEditoonThumbnails" :key="thumnail.id">
      <v-card hover class="ma-3" min-height="300" @click="reviewBook(thumnail._id)">
        <v-layout column align-center fill-height class="text-center">
          <v-img :src="'https://j3b308.p.ssafy.io/image/editoon/' + `${thumnail._id}/` + `${thumnail.thumbnail}`"></v-img>
          <!-- <v-img src="https://j3b308.p.ssafy.io/image/profileImg/200927_c1cb4f6611.JPG"></v-img> -->
          <!-- <v-img :src="book.thumbnail"></v-img> -->
          
          <v-card-title class="font-weight-light">{{ thumnail.subject }} | {{ thumnail.createDate }} </v-card-title>
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
      ...mapState(['userEditoonThumbnails'])
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
        // this.$router.push(`reviewbook/${bookId}`)
        this.$store.dispatch('getUserEditoonImages', bookId)
      },
    }
  };
</script>

<style scoped>
  .v-card:hover {
    background: #FFA000;
  }
</style>
