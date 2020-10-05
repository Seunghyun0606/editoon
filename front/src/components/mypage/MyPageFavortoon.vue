<template>
  <v-layout row wrap>
    <div class="text-h3" style="width: 100%;">
        내가 쓴 글

    </div>
    <div class="text-h5 my-10" v-if="!userEditoonThumbnails.length">
        정보가 없습니다
    </div>
    <v-flex xs12 sm6 md4 lg3 v-for="( thumbnail, idx ) in userEditoonThumbnails" :key="idx">
      <v-card hover class="ma-3" min-height="300" @click="reviewBook(thumbnail._id)">
        <v-layout column align-center fill-height class="text-center">
          <v-img :src="'https://j3b308.p.ssafy.io/image/editoonImg/' + `${userInfo.no}/` + `${thumbnail.thumbnail}`"></v-img>
          <!-- <v-img src="https://j3b308.p.ssafy.io/image/profileImg/200927_c1cb4f6611.JPG"></v-img> -->
          <!-- <v-img :src="book.thumbnail"></v-img> -->
          <v-card-title class="font-weight-light">{{ thumbnail.subject }} | {{ thumbnail.createDate }} </v-card-title>
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
      ...mapState(['userEditoonThumbnails', 'userInfo'])
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
