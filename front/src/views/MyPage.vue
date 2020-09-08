<template>

  <v-container class="ma-10 pa-10">
    <v-row>
      <v-col>
        <MyPageLeftNav/>

      </v-col>
      <v-col cols=8>

        <MyPageInfo v-if="false"/>

        <MyPageFavortoonSort
          :categoryTitle="categoryTitle"
          :sortCriteria="sortCriteria"
          @all_book="sortBy('all_book')"
          @someone_book="sortBy('someone_book')"
          @release_date="sortBy('release_date')"
        
        />

        <MyPageFavortoon :books="books"/>
        <MyPageMytoon v-if="false"/>

      </v-col>
    </v-row>
  </v-container>

</template>

<script>
import MyPageLeftNav from '@/components/mypage/MyPageLeftNav'
import MyPageInfo from '@/components/mypage/MyPageInfo'
import MyPageFavortoon from '@/components/mypage/MyPageFavortoon'
import MyPageMytoon from '@/components/mypage/MyPageMytoon'
import MyPageFavortoonSort from '@/components/mypage/MyPageFavortoonSort'


export default {
  name: "MyPage",
  components: {
    MyPageLeftNav,
    MyPageInfo,
    MyPageFavortoon,
    MyPageMytoon,
    MyPageFavortoonSort,
  },
  data() {
    return {
      books: [
        {
          title: 'abc',
          time: '1234',
        },
        {
          title: 'abc',
          time: '1234',
        },
        {
          title: 'abc',
          time: '1234',
        },
        {
          title: 'abc',
          time: '1234',
        }
      ],
      categoryTitle: '카테고리타이틀',
      sortCriteria: '조건',
    }
  },
  methods: {
    sortBy(prop) {
      if (prop === "all_book") {
        this.sortCriteria = "전체 책";
      } else if (prop === "someone_book") {
        this.sortCriteria = "여행 장르";
      } else if (prop === "release_date") {
        this.sortCriteria = "시간 순서";
      }
      this.sortedBy = prop;
      this.books.sort((a, b) => (a[prop] > b[prop] ? -1 : 1));
    },
  }


}
</script>

<style>

</style>