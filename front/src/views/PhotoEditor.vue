<template>
    <v-container fluid style="height: 100%; background-color: rgba(0, 0,0, 0.88)">
      <v-row style="justify-content: space-between;">
        <v-col v-resize="resizeCanvasWidth" cols="5" id="webtoonCanvas" class="mx-auto my-16 webtoon-canvas-css" :style="{ height: webtoonCanvasHeight+'px' }">
          <VueDragResize
            v-for="(image, idx) in images"
            :key="idx"
            :isActive="image.isActive"
            :parentH="webtoonCanvasHeight"
            :parentW="webtoonCanvasWidth"
            :w="200"
            :h="200"
            :z="image.zIndex"
            :parentLimitation="true"
            @activated="canvasImageOnActivated(idx)"
            :style="[ image.isBackground ? addBackground : addImage(image.image) ]"
          >
            <!-- :style="{ backgroundImage:  'url('+ `${image.image}` + ')', backgroundRepeat: 'round' }" -->
            <!-- <img :src="image.image" style="height: inherit; width: inherit;" alt=""> -->
            <v-btn @click="btnUpZindex(idx)">zindex올리기</v-btn>
            <v-btn @click="btnDownZindex(idx)">zindex내리기</v-btn>

          </VueDragResize>
        </v-col>


        <v-col cols="6">
          <v-row style="height: 70vh; width: 90%;" class="mx-auto">
            <tui-image-editor ref="imageEditor" :include-ui="useDefaultUI" :options="options"></tui-image-editor>

          </v-row>

          <v-row>
            <vue2Dropzone
              @vdropzone-files-added="dropZoneImageMoveToEditor"
              id="dropZone"
              ref="myVueDropzone"
              style=" width: 90%; position: relative;
                height: 25vh;
                overflow-y: auto;
                border: 1px solid white;
                border-radius: 20px;
                text-align: center;
                background: border-box"
              class="mx-auto"
              :options="dropzoneOptions"
              :useCustomSlot='true'>
              <div class="dropzone-custom-content">
                <h3 class="dropzone-custom-title">Drag and drop to upload content!</h3>
                <div class="subtitle">...or click to select a file from your computer</div>
              </div>
            </vue2Dropzone>
            <div id='editorBtnSet'>
              <v-btn @click="btnDropZoneImageMoveToEditor">에디터로보내기</v-btn>
              <v-btn @click="btnEditorImageToCanvas">캔버스로보내기</v-btn>
              <v-btn @click="btnAddCanvasHeight">캔버스늘리기</v-btn>
              <v-btn @click="btnAddBackground">회상배경추가하기</v-btn>


            </div>
          </v-row>
        </v-col>
      </v-row>
    </v-container>




</template>

<script>
import VueDragResize from 'vue-drag-resize'
import vue2Dropzone from 'vue2-dropzone'
import 'vue2-dropzone/dist/vue2Dropzone.min.css'

import { ImageEditor } from '@toast-ui/vue-image-editor';
import 'tui-image-editor/dist/svg/icon-a.svg';
import 'tui-image-editor/dist/svg/icon-b.svg';
import 'tui-image-editor/dist/svg/icon-c.svg';
import 'tui-image-editor/dist/svg/icon-d.svg';
import 'tui-image-editor/dist/tui-image-editor.css';

export default {
  components: {
    'tui-image-editor': ImageEditor,
    VueDragResize,
    vue2Dropzone,
  },
  data() {
    return {
      previewCount: 0,

      images: [
        {
          image: require(`@/assets/account_signup.png`),  // 맨처음 테스트용으로 넣은것
          isActive: false,  // 나중에 중복 선택 제거를 위함.
          isBackground: false, // 배경인지 확인하기위함.
          zIndex: 100,
        }
      ],
      addBackground: {
        backgroundColor: 'black',
      },
      addImage: function(imageURL) {
        let imageStyle = {
          backgroundImage:  'url('+ `${imageURL}` + ')',
          backgroundRepeat: 'round' 
        }
        return imageStyle
      },

      useDefaultUI: true,
      webtoonCanvasHeight: window.innerHeight*0.87,
      webtoonCanvasWidth: 0,
      options: {
        cssMaxWidth: 400,
        cssMaxHeight: 400,
        includeUI: {
          loadImage: {
            path: require(`@/assets/init.png`),
            name: 'test5442'
          },
          uiSize: {
            width: String,
            height: String,
          },
          menuBarPosition: 'bottom',
          // initMenu: 'filter',
          // menu: ['shape', 'filter',],
          // menu: ['crop']
        },
        selectionStyle: {
          cornerSize: 10,
          // borderColor: 'red',
          cornerStrokeColor: 'white',
          cornerColor: 'white',
          rotatingPointOffset: 40,
        },
      },
      dropzoneOptions: {          
        url: 'https://httpbin.org/post',
        thumbnailWidth: 100,
        thumbnailHeight: 100,
        // addRemoveLinks: true
        // clickable: false,
      },
    }
  },
  methods: {
    isIndex() {
      this.$store.commit('isIndex', false)
    },
    isNotEditor() {
      this.$store.commit("isNotEditor", false);
    },
    btnUpZindex(idx) {
      console.log(idx)
      this.images[idx].zIndex += 1
    },
    btnDownZindex(idx) {
      this.images[idx].zIndex -= 1
    },
    btnAddBackground() {
      const addBackground = {
        image: "",
        isActive: false,
        isBackground: true,
        zIndex: 100,
      }
      this.images.push(addBackground)
    },
    // 파일 업로드시, preview만 클릭하면 올라갈 수 있도록 만듬.
    dropZoneImageMoveToEditor(file_list) {
      console.log(file_list)

      const dz_preview = document.querySelectorAll('.dz-preview')

      // const temp2 = document.querySelectorAll('.dz-image > img')

      // temp1[0].addEventListener('click', function(e) {
        //   console.log(e)
      // })

      for ( let idx = 0; idx < file_list.length; idx++) {
        dz_preview[idx + this.previewCount].addEventListener('click', e => {
          this.$refs.imageEditor.invoke('loadImageFromFile', file_list[idx])
          e
        })
      }
      // 여러번 올렸을경우, dz_preview의 인덱스가 달라지기때문
      this.previewCount += file_list.length
    },

    // 사실상 preview에서 클릭해서 넣을 수 있기 때문에 필요없음.
    btnDropZoneImageMoveToEditor() {
      const files = this.$refs.myVueDropzone.getAcceptedFiles()
      // console.log(1)
      // console.log(a)
      // this.image = files[0].dataURL
      const file = files[0]
      this.$refs.imageEditor.invoke('loadImageFromFile', file)

    },

    // 에디터 이미지를 캔버스로 이동
    btnEditorImageToCanvas() {
      const dataURL = this.$refs.imageEditor.invoke('toDataURL')  // base64 data
      const imageData = {
        image: dataURL,
        isActive: false,
        isBackground: false,
        zIndex: 100,
      }
      this.images.push(imageData)

    },
    
    // 캔버스 추가
    btnAddCanvasHeight() {
      this.webtoonCanvasHeight = this.webtoonCanvasHeight*2
    },

    // 캔버스의 이미지가 눌러졌을때, 다른 이미지는 활성화 취소
    canvasImageOnActivated(idx) {
      for ( let i = 0; i < this.images.length; i++ ) {
        if ( i === idx ) {
          this.images[i].isActive = true
          continue
        }
        this.images[i].isActive = false
      }
    },

    //캔버스 사이즈 변경시, 캔버스 안의 이미지가 안옮겨지는 버그 수정
    resizeCanvasWidth(e) {
      if (e) {
        const canvas_width = e.target.document.querySelector('#webtoonCanvas').clientWidth
        // console.log(canvas_width)
        this.webtoonCanvasWidth = canvas_width

      }

    }

  },
  created() {
    this.isIndex()
    this.isNotEditor()
  },
  mounted() {
    // 버튼위치 변경
    const editorHeader = document.querySelector('.tui-image-editor-header')
    const editorBtnSet = document.querySelector('#editorBtnSet')
    editorHeader.appendChild(editorBtnSet)

  }
}
</script>

<style>

.tui-image-editor-header > .tui-image-editor-header-logo, .tui-image-editor-header-buttons {
  display: none;
}

.tui-image-editor-container .tui-image-editor-wrap {
  overflow: initial;
}

.webtoon-canvas-css {
  position: relative;
  background-color: white;
  border: 1px solid black;
}

.dropzone-custom-content {
  margin-top: 45px;
}
.dropzone-custom-title {
  color: rgba(255, 255, 255, 1);
  font-weight: bold;
  margin: 0;
  font-size: 1.1rem
  
}

.subtitle {
  /* color: #314b5f; */
  color: inherit
}



</style>