<template>
  <section>
    <WebtoonIndexCarro/>
    <v-container class="mx-auto">
      <!-- <WebtoonSort
        :categoryTitle="categoryTitle"
        :sortCriteria="sortCriteria"
        @all_book="sortBy('all_book')"
        @someone_book="sortBy('someone_book')"
        @release_date="sortBy('release_date')"
      ></WebtoonSort> -->
      <div style="float: right">
        대충 버튼?
      </div>
      <div class="text-h4 mt-16">
        오늘의 웹툰
      </div>
      <WebtoonGrid :books="books" :imageURL="imageURL" />
      <!-- <div class="text-h4 mt-16">
        인기 웹툰
      </div>
      <WebtoonGrid :books="books" :imageURL="imageURL" />
      <div class="text-h4 mt-16">
        여행 웹툰
      </div>
      <WebtoonGrid :books="books" :imageURL="imageURL" /> -->

    </v-container>
  </section>
</template>

<script>
import WebtoonGrid from '@/components/webtoonindex/WebtoonGrid'
// import WebtoonSort from '@/components/webtoonindex/WebtoonSort'
import WebtoonIndexCarro from '@/components/webtoonindex/WebtoonIndexCarro'


export default {
  name: 'WebtoonIndex',
  components: {
    WebtoonGrid,
    // WebtoonSort,
    WebtoonIndexCarro,
  },
  data() {
    return {
      imageURL: "abc",
      categoryTitle: '카테고리타이틀',
      sortCriteria: '조건',
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
        },
        {
          title: 'abc',
          time: '1234',
        },
        {
          title: 'abc',
          time: '1234',
        },
      ]

    }
  },
  methods: {
    isIndex() {
      this.$store.commit('isIndex', false)
    },

    sortBy(prop) {
      if (prop === "all_book") {
        this.sortCriteria = "전체 책";
      } else if (prop === "someone_book") {
        this.sortCriteria = "누군가의 책";
      } else if (prop === "release_date") {
        this.sortCriteria = "시간 순서";
      }
      this.sortedBy = prop;
      this.books.sort((a, b) => (a[prop] > b[prop] ? -1 : 1));
    },
  },
  created() {
    this.isIndex()
  }

}
</script>

<style>

</style>