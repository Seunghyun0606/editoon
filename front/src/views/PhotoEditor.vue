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
            :isDraggable='image.isDraggable'
            :parentLimitation="true"
            @activated="canvasImageOnActivated(idx)"
            @deactivated="canvasImageOffActivated(idx)"
            :style="[objectStyle(idx)]"
          >
            <!-- :style="[ image.isBackground ? addBackground : addImage(image.image), { border: image.imageOption.borderSlider +'px' + ' solid' + ' black'} ]" -->
            <!-- @clicked="check2('bubble' + idx)" -->
            <!-- :style="{ backgroundImage:  'url('+ `${image.image}` + ')', backgroundRepeat: 'round' }" -->
            <!-- <img :src="image.image" style="height: inherit; width: inherit;" alt=""> -->
            <div v-show="image.isActive" style="position: absolute;">
              <v-btn @click="btnUpZindex(idx)">zindex올리기</v-btn>
              <v-btn @click="btnDownZindex(idx)">zindex내리기</v-btn>
              <v-btn @click="btnOption(idx)">옵션</v-btn>

            </div>
            <!-- <input v-if="image.isBubble" :id="'bubble' + idx" @click='check' style="width: inherit; height: inherit" type="text" class="triangle-isosceles" value='대사란' > -->
            <!-- <p v-if="image.isBubble" >abcdefawefawefawefawefawefaabcdefawefawefawefawefawefaabcdefawefawefawefawefawefaabcdefawefawefawefawefawefa</p> -->

            <!-- background의 경우 -->
            <div v-if="image.isClickOption && image.isBackground" style="width: 500px; height: 500px; background-color: black; position: relative; z-index: 999; left: calc(100% + 50px);">

              <v-btn @click="image.backgroundOption.gradientCheck = 0">
                일반
              </v-btn>
              <v-btn @click="image.backgroundOption.gradientCheck = 1">
                회상 윗배경
              </v-btn>
              <v-btn @click="image.backgroundOption.gradientCheck = 2">
                회상 아랫배경
              </v-btn>

            </div>
            <!-- 말풍선의 경우 -->
            <div v-if="image.isClickOption && image.isBubble" style="width: 500px; height: 500px; background-color: black; position: relative; z-index: 999; left: calc(100% + 50px);">
              <v-container>
                <v-row>
                  <v-col>
                    
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>

                  </v-col>
                </v-row>
                <v-row>
                  <v-col>

                  </v-col>
                </v-row>
              </v-container>
            </div>
            <!-- 이미지의 경우 -->
            <div v-if="image.isClickOption && !image.isBubble && !image.isBackground"
              :style="{ width: '500px', height: '500px', backgroundColor: 'black', position: 'relative', zIndex: 999, bottom: image.imageOption.borderSlider + 'px', left: 'calc(100% + 50px + ' + `${image.imageOption.borderSlider}` + 'px )' }">
              <v-container fluid style="color:white;">
                <v-row>
                  <v-col>
                    <v-color-picker v-model="image.imageOption.borderColor" class="ma-2" hide-inputs></v-color-picker>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <v-subheader class="" style="color: white;">테두리 두께</v-subheader>
                    <v-slider
                      v-model="image.imageOption.borderSlider"
                      thumb-label
                    >
                    </v-slider>
                  </v-col>
                </v-row>

              </v-container>
            </div>
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
              <v-btn @click="canvasImageToSpring">캔버스 이미지변환 테스트</v-btn>
              <v-btn @click="btnDropZoneImageMoveToEditor">에디터로보내기</v-btn>
              <v-btn @click="btnEditorImageToCanvas">캔버스로보내기</v-btn>
              <v-btn @click="btnAddCanvasHeight">캔버스늘리기</v-btn>
              <v-btn @click="btnAddBackground">회상배경추가하기</v-btn>
              <v-btn @click="btnAddBubble1">말풍선1</v-btn>
              <v-btn @click="btnAddBubble2">말풍선2</v-btn>


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
import html2canvas from 'html2canvas';


import { ImageEditor } from '@toast-ui/vue-image-editor'
import 'tui-image-editor/dist/svg/icon-a.svg'
import 'tui-image-editor/dist/svg/icon-b.svg'
import 'tui-image-editor/dist/svg/icon-c.svg'
import 'tui-image-editor/dist/svg/icon-d.svg'
import 'tui-image-editor/dist/tui-image-editor.css'
import { mapState } from 'vuex'

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
          isBubble: false,  // 말풍선인지 확인
          zIndex: 102,
          isClickOption: false,
          isDraggable: true,
          imageOption: {
            borderSlider: 5,
            borderColor: '#000',
          },
          backgroundOption: {
            gradientCheck: 0  // 0 없음, 1 upper, 2 lower
          },
          bubbleOption: {

          }
        },
        // {
        //   image: require(`@/assets/account_signup.png`),  // 맨처음 테스트용으로 넣은것
        //   isActive: false,  // 나중에 중복 선택 제거를 위함.
        //   isBackground: true, // 배경인지 확인하기위함.
        //   isBubble: false,  // 말풍선인지 확인
        //   zIndex: 100,
        //   isClickOption: false,
        //   isDraggable: false,
        // },
        // {
        //   image: require(`@/assets/webtoon.png`),  // 맨처음 테스트용으로 넣은것
        //   isActive: false,  // 나중에 중복 선택 제거를 위함.
        //   isBackground: false, // 배경인지 확인하기위함.
        //   isBubble: true,  // 말풍선인지 확인
        //   zIndex: 100,
        //   isClickOption: false,
        //   isDraggable: false,
        // },
      ],
      objectStyle: function(idx) {
        let image = this.images[idx]
        let imageStyle = {}

        if ( image.isBackground ) {
          let gradientCheck = image.backgroundOption.gradientCheck
          if ( gradientCheck === 1 ) {
            imageStyle.background = "linear-gradient( white , black )"
          }
          else if ( gradientCheck === 2) {
            imageStyle.background = "linear-gradient( black , white )"
          }
          else {
            imageStyle.backgroundColor = 'black'
          }
        }
        else if ( image.isBubble ) {
          imageStyle.backgroundColor = 'blue'
        }
        else {
          imageStyle.color = image.imageOption.color
          imageStyle.border = image.imageOption.borderSlider + 'px' + " solid"
          imageStyle.borderColor = image.imageOption.borderColor
          imageStyle.backgroundImage = 'url(' + `${image.image}` + ')',
          imageStyle.backgroundRepeat= 'round'
        }
        return imageStyle
      },
      useDefaultUI: true,
      initWebtoonCanvasHeight: window.innerHeight*0.87,
      webtoonCanvasHeight: window.innerHeight*0.87,
      webtoonCanvasWidth: 0,
      webtoonCanvasCount: 1,
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
          menu: ['crop', 'flip', 'rotate', 'shape', 'draw', 'icon', 'text']
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
  computed: {
    ...mapState(['convertedImages'])
    // img :src="'data:image/png;base64,' + `${test123}`" 나중에 이미지 base64파일 형식으로 넣어주면된다.
  },
  methods: {
    btnOption(idx) {
      this.images[idx].isClickOption = !this.images[idx].isClickOption
    },
    btnAddBubble1() {
      
    },
    btnAddBubble2() {

    },
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
        isBubble: false,
        zIndex: 100,
        isClickOption: false,
        isDraggable: true,
        backgroundOption: {
          gradientCheck: 0  // 0 없음, 1 upper, 2 lower
        },
      }
      this.images.push(addBackground)
    },

    async canvasImageToSpring() {
      const ctxTest = document.querySelector("#webtoonCanvas")
      // console.log(ctxTest)

      let canvasFormData = new FormData()
      let canvasFormArray = new Array()

      for ( let count = 0; count < this.webtoonCanvasCount; count++ ) {
        await html2canvas(ctxTest, {
          height: this.initWebtoonCanvasHeight,
          y: 128 + this.initWebtoonCanvasHeight*count,  // 아래위 마진때문에 128.
        })
          .then( canvas => {
            // document.body.appendChild(canvas)
  
            const base64Data = canvas.toDataURL('image/png')
            
            // base64 데이터 디코딩
            let blobBin = atob(base64Data.split(',')[1])
            let array = [];
            for (var i = 0; i < blobBin.length; i++) {
                array.push(blobBin.charCodeAt(i));
            }
  
            let file = new Blob([new Uint8Array(array)], {type: 'image/png'});	// Blob 생성
            // canvasFormData.append(count, file);	// file data 추가
            // canvasFormData.append("one", file);	// file data 추가
            // canvasFormData.append("two", file);	// file data 추가
            // canvasFormData.append("three", file);	// file data 추가
            canvasFormArray.push(file);	// file data 추가
            // return canvasFormData
        })
          // .catch( e => {
          //   console.log(e)
          //   alert('캔버스 전송실패')
          // })
          // .then( canvasFormData => {
          //   console.log(5)
          //   this.$store.dispatch('canvasImageToSpring', canvasFormData)
          // })
      }
      canvasFormData.append('_id', 0)
      canvasFormData.append('subject', 'check')
      canvasFormData.append('thumbnail', 'check')
      // canvasFormData.append('createDate', 'check')

      canvasFormData.append('image', canvasFormArray)
      console.log(canvasFormArray)
      this.$store.dispatch('canvasImageToSpring', canvasFormData)


    },
    
    // 파일 업로드시, preview만 클릭하면 올라갈 수 있도록 만듬.
    dropZoneImageMoveToEditor(file_list) {
      // console.log(file_list)

      const dz_preview = document.querySelectorAll('.dz-preview')

      this.dropZoneImageToDjango(file_list)

      for ( let idx = 0; idx < file_list.length; idx++) {
        dz_preview[idx + this.previewCount].addEventListener('click', e => {
          this.$refs.imageEditor.invoke('loadImageFromFile', file_list[idx])
          e
        })
      }
      // 여러번 올렸을경우, dz_preview의 인덱스가 달라지기때문
      this.previewCount += file_list.length
    },
    // django로 이미지 보내기.
    dropZoneImageToDjango(file_list) {

      const djangoImageForm = new FormData()
      
      for ( let file of file_list ) {
        djangoImageForm.append(file.name ,file)
      }

      this.$store.dispatch("dropZoneImageToDjango", djangoImageForm)
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
        isBubble: false, 
        zIndex: 100,
        isClickOption: false,
        isDraggable: true,
        imageOption: {
          borderSlider: 5,
          borderColor: '#000',
        },
      }
      this.images.push(imageData)
    },
    
    // 캔버스 추가
    btnAddCanvasHeight() {
      this.webtoonCanvasHeight = this.webtoonCanvasHeight*2
      this.webtoonCanvasCount++
    },

    // 캔버스의 이미지가 눌러졌을때, 다른 이미지는 활성화 취소
    canvasImageOnActivated(idx) {
      this.images[idx].isDraggable = false
      for ( let i = 0; i < this.images.length; i++ ) {
        if ( i === idx ) {
          this.images[i].isActive = true
          continue
        }
        this.images[i].isActive = false
      }
    },
    canvasImageOffActivated(idx) {
      this.images[idx].isActive = false
      this.images[idx].isClickOption = false
      this.images[idx].isDraggable = true
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

.vdr:hover {
  cursor: pointer;
}

.subtitle {
  /* color: #314b5f; */
  color: inherit
}



</style>