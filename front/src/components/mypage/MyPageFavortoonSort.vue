<template>
  <v-layout justify-end row class="mt-3 mr-4">
    <v-flex class="ml-5" align-self-center>
      <h1 class="display-1 font-weight-light">{{ categoryTitle }}</h1>
      <h5 class="font-weight-light">{{ sortCriteria }}</h5>
    </v-flex>
    <v-spacer></v-spacer>

    <v-flex align-self-center class="my-3">
      <div class="text-sm-end">
        <v-tooltip top>
          <template v-slot:activator="{on}">
            <v-btn
              v-on="on"
              small
              color="primary"
              class="criteriaSelector mx-3 mt-3"
              @click="sortBy('all_book')"
            >
              <v-icon left>mdi-account-heart</v-icon>
              <span class="caption text-lowercase">전체</span>
            </v-btn>
          </template>
          <span>전체 책</span>
        </v-tooltip>

        <v-tooltip top>
          <template v-slot:activator="{on}">
            <v-btn
              v-on="on"
              small
              color="primary"
              class="criteriaSelector mx-3 mt-3"
              @click="sortBy('someone_book')"
            >
              <v-icon left>mdi-star</v-icon>
              <span class="caption text-lowercase">여행</span>
            </v-btn>
          </template>
          <span>여행 책</span>
        </v-tooltip>
        <v-tooltip top>
          <template v-slot:activator="{on}">
            <v-btn
              v-on="on"
              small
              color="primary"
              class="criteriaSelector mx-3 mt-3"
              @click="sortBy('release_date')"
            >
              <v-icon left>mdi-calendar</v-icon>
              <span class="caption text-lowercase">시간</span>
            </v-btn>
          </template>
          <span>시간 순</span>
        </v-tooltip>
      </div>
    </v-flex>
  </v-layout>
</template>

<script>
  export default {
    name: "MyPageFavortoonSort",
    props: {
      categoryTitle: String,
      sortCriteria: String,
    },
    data() {
      return {};
    },
    methods: {
      sortBy(prop) {
        this.$emit(prop);
      }
    }
  };
</script>

<style scoped>
</style>
