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
          <div id="mypageCanvas">
            <!-- <img :src="userEditoonImage.image" alt=""> -->
            <v-img v-for="(userEditoonImage, id) in userEditoonImages.image" :key="id" :src="'https://j3b308.p.ssafy.io/image/editoonImg/' + `${userInfo.no}/` + `${userEditoonImage}`"></v-img>

          </div>
        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="primary"
              gray
              large
              @click="downloadCanvas"
            >
            Download
            </v-btn>
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
import html2canvas from "html2canvas"

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
      downloadCanvas() {
        const downCanvas = document.querySelector("#mypageCanvas");
        html2canvas(downCanvas)
          .then((canvas) => {
            const base64Data = canvas.toDataURL("image/png");

            // base64 데이터 디코딩
            let blobBin = atob(base64Data.split(",")[1]);
            let array = [];
            for (var i = 0; i < blobBin.length; i++) {
              array.push(blobBin.charCodeAt(i));
            }
            let file = new Blob([new Uint8Array(array)], { type: "image/png" }); // Blob 생성

            let downloadArray = new Array();
            downloadArray.push(file);
            downloadArray.push(base64Data);
            return downloadArray;
          })
          .then((downloadArray) => {
            let file = downloadArray[0];
            let base64Data = downloadArray[1];

            // blob 생성해서 msSaveOrOpenBlob 사용하면 IE 10+ 에서 다운로드를 지원한다.
            // IE 10+ 를 지원할 생각이없다면 그냥 a 태그의 download쓰면됨.
            if (window.navigator.msSaveOrOpenBlob) {
              window.navigator.msSaveOrOpenBlob(file, "new_file.png")
            } else {
              let a = document.createElement("a")
              a.style = "display: none"
              a.href = base64Data;
              a.download = "new_file.png"
              document.body.appendChild(a)
              a.click()

              setTimeout(function() {
                document.body.removeChild(a)
              }, 100)
            }
          })
      },
    },
  }
</script>

<style>

</style>