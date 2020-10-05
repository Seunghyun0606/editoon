<template>
  <div class="text-center" style="background-color: white; overflow-x: hidden;">
    <v-dialog
      v-model="checkDialog"
      width="500"
    >
      <v-container 
        class="pa-10 object-option"
        style="background-color: rgba(0,0,20,0.9)"  
      >

        <div v-for="(userEditoonImage, id) in userEditoonImages.image" :key="id">
          <!-- <img :src="userEditoonImage.image" alt=""> -->
          <v-img :src="'https://j3b308.p.ssafy.io/image/editoonImg/' + `${userInfo.no}/` + `${userEditoonImage}`"></v-img>

        </div>

      </v-container>
    </v-dialog>
  </div>
</template>

<script>
import { mapState } from 'vuex'

  export default {
    name: 'MyPageShowMytoon',
    computed: {
      checkDialog: {
        get() {
          return this.$store.state.showMytoonDialog
        },
        set(val) {
          this.$store.commit("showMytoonDialogInit",  val)
        }
      },
      ...mapState(['userEditoonImages', 'userInfo'])
    },
    data () {
      return {
      }
    },
    methods: {
    },
  }
</script>

<style>

</style>