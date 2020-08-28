<template>
  <v-container class="mx-auto">
    <MainLibraryListSort
      :categoryTitle="categoryTitle"
      :sortCriteria="sortCriteria"
      @all_book="sortBy('all_book')"
      @someone_book="sortBy('someone_book')"
      @release_date="sortBy('release_date')"
    ></MainLibraryListSort>

    <MainLibraryListGrid :books="books" :imageURL="imageURL" />

  </v-container>
</template>

<script>
import MainLibraryListGrid from '@/components/mainlibrary/MainLibraryListGrid'
import MainLibraryListSort from '@/components/mainlibrary/MainLibraryListSort'


export default {
  name: 'MainLibrary',
  components: {
    MainLibraryListGrid,
    MainLibraryListSort,
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
  }

}
</script>

<style>

</style>