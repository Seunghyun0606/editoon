<template>
  <v-card
    max-width="200"
    outlined
  >
    <div style="height: 80px;">
      임의 상자
    </div>
    <v-divider class="ma-0"></v-divider>
    <v-list
      style="padding: 0"
    >
      <!-- v-model 로 걸면 v-list-item 클릭했을때, 그 값을 바꿔준다. -->
      <v-list-item-group
        color="red"
      >
        <div v-for="(title, index) in title" :key="index">
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title> {{ title }} </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-divider style="margin: 0;"
            v-if="index + 1 < titleNums"
            :key="index"
          >
          </v-divider>
        </div>
      </v-list-item-group>

    </v-list>

  </v-card>

</template>

<script>
export default {
  name: "MyPageLeftNav",
  data() {
    return {
      title: [
        '내가 좋아하는 툰',
        '내가 쓴 글',
        '내 정보 수정',
      ],

    }
  }


}
</script>

<style>

</style>