<template>

  <v-container class="ma-10 pa-10">
    <v-row>
      <v-col>
        <MyPageLeftNav/>

      </v-col>
      <v-col cols=8>

        <MyPageInfo/>

      </v-col>
    </v-row>
  </v-container>

</template>

<script>
import MyPageLeftNav from '@/components/mypage/MyPageLeftNav'
import MyPageInfo from '@/components/mypage/MyPageInfo'


export default {
  name: "MyPage",
  components: {
    MyPageLeftNav,
    MyPageInfo,
  },
  data() {
    return {
    }
  },


}
</script>

<style>

</style>