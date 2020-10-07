<template style="overflow-x: hidden; background-color: rgba(0,0,20,0.9);">
  <div class="text-center mask" style="background-color: black">
    <v-dialog
      v-model="checkDialog"
      width="35%"
      transition="dialog-bottom-transition"
    >
    <!-- class="pa-10 object-option" -->
      <v-container 
        style="background-color: rgba(0,0,20,0.9);"  
        width="100%"
      >
        <div v-for="(userEditoonImage, id) in userEditoonImages.image" :key="id">
          <!-- <img :src="userEditoonImage.image" alt=""> -->
          <v-img :src="'https://j3b308.p.ssafy.io/image/editoonImg/' + `${userInfo.no}/` + `${userEditoonImage}`"></v-img>

        </div>
        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="error"
              gray
              large
              @click="checkDialog = false"
            >
            Close My Editoon
            </v-btn>
        </v-card-actions>    
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
        },
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