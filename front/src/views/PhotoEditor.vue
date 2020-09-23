<template>
    <v-container fluid style="height: calc(100%-64px); background-color: rgba(0, 0,0, 0.88)">
      <v-row style="height: calc(100vh - 64px);  justify-content: space-between;">
        <v-col cols="5" id="webtoonCanvas" class="mx-auto my-16 webtoon-canvas-css" :style="{ height: webtoonCanvasHeight+'px' }">
          <VueDragResize
            :parentH="webtoonCanvasHeight"
            :parentW="webtoonCanvasWidth"
            :w="200"
            :h="200"
            v-on:resizing="resize"
            v-on:dragging="resize"
            :parentLimitation="true"
          >
          </VueDragResize>
        </v-col>


        <v-col cols="6">
          <v-row style="height: 70vh; width: 90%;" class="mx-auto">
            <tui-image-editor :include-ui="useDefaultUI" :options="options"></tui-image-editor>

          </v-row>

          <v-row>
            <vue2Dropzone
              @vdropzone-files-added="urlCheck"
              id="a"
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
      useDefaultUI: true,
      webtoonCanvasHeight: window.innerHeight*0.87,
      webtoonCanvasWidth: 0,
      options: { // for tui-image-editor component's "options" prop
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


      }
    }
  },
  methods: {
    isIndex() {
      this.$store.commit('isIndex', false)
    },
    isNotEditor() {
      this.$store.commit("isNotEditor", false);
    },
  },
  created() {
    this.isIndex()
    this.isNotEditor()
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

.dropzone-custom-title {
  margin-top: 0;
  /* color: #00b782; */
  color: rgba(255, 255, 255, 1);
}

.subtitle {
  /* color: #314b5f; */
  color: inherit
}



</style>