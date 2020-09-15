<template>
  <section class="">
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
      <div style="background-color: blue; height: 200px;" class="my-16">
      </div>
      <div class="d-flex">
        <div class="mr-auto text-h5">
          장르별 보기
        </div>
        <v-btn
          color='primary'
          tile
          class="mr-4"
        >
          좋아요 순
        </v-btn>
        <v-btn
          color="primary"
          tile
          class="mr-4"
        >
          시간 순
        </v-btn>
        <div style="width: 300px;">
          <v-text-field
            class="pa-0"
            label="Search"
            placeholder="작품 검색하기"
            append-icon="mdi-magnify"
          ></v-text-field>

        </div>

      </div>
      <v-tabs class="my-2">
        <v-tab>
          일상
        </v-tab>
        <v-tab>
          여행
        </v-tab>
        <v-tab>
          공포
        </v-tab>
      </v-tabs>

      <WebtoonGrid :books="books" :imageURL="imageURL" />
      <WebtoonGrid :books="books" :imageURL="imageURL" />

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
    isNotEditor() {
      this.$store.commit('isNotEditor', true)
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
    this.isNotEditor()
  }

}
</script>

<style>

</style>