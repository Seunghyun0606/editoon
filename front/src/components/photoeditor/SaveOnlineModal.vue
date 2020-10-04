<template>
  <div class="text-center" style="background-color: white;">
    <v-dialog
      v-model="checkDialog"
      width="500"
      style="z-index: 999;"
    >
      <v-container
        v-show="!checkLoading.isSaveOnlineLoading"
        class="pa-10 object-option"
        style="background-color: rgba(0,0,20,0.9);"
      >

        <div>
          Put the Thumbnail
        </div>
        <input type="file" id="thumbnail" class="my-2" style="background-color: white; color: black;">


        <div class="mt-5">
          Create your content name
        </div>
        <v-text-field
          placeholder="Beautiful trip in the Seoul Han river"
          class="pt-0"
          dark
          v-model='subject'
        >

        </v-text-field>
        <div style="text-align: right">
          <v-btn
            color="#0D47A1"
            dark
            @click="saveCanvas"
          >
            Save the webtoon
          </v-btn>
        </div>
      </v-container>
      <div
        class="pa-10 object-option"
        style="background-color: rgba(0,0,20,0.9)"
        v-show="checkLoading.isSaveOnlineLoading"  
      >
      <Loading
        :loading="checkLoading.isSaveOnlineLoading"
        :color="'white'"
        :size="'100px'"
      />
      <div style="text-align: center" class="mt-16">
        업로드 중입니다. 잠시만기다려주세요
      </div>

      </div>

    </v-dialog>
  </div>
</template>

<script>
import Loading from '@/components/Loading'
import { mapState } from 'vuex'

export default {
  name: 'SaveOnlineModal',
  components: {
    Loading,
  },
  computed: {
    checkDialog: {
      get() {
        return this.$store.state.saveCanvasDialog
      },
      set(val) {
        this.$store.commit("saveCanvasDialogInit",  val)
      }
    },
    ...mapState(['checkLoading'])
  },
  data () {
    return {
      subject: '',
    }
  },
  methods: {
    saveCanvas() {
      const thumbnail = document.querySelector("#thumbnail").files[0]
      this.$emit('thumbnailAndSubject', thumbnail, this.subject)
    }
  },
}
</script>

<style>

</style>