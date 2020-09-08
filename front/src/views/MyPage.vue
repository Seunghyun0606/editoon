<template>

  <v-container>
    <v-row>
      <v-col>
        <MyPageLeftNav/>

      </v-col>
      <v-col cols=8>

        abcd

      </v-col>
    </v-row>
  </v-container>

</template>

<script>
import MyPageLeftNav from '@/components/mypage/MyPageLeftNav'


export default {
  name: "MyPage",
  components: {
    MyPageLeftNav,
  },
  data() {
    return {
    }
  },
  computed: {
    titleNums() {
      return this.title.length
    }
  },

}
</script>

<style>

</style>