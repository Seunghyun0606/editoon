<template>
  <v-container fluid style="height: 100%; background-color: rgba(0, 0,0, 0.88)">
    <v-row
      style="justify-content: space-between; top: 20px; position: relative; z-index: 999;"
    >
      <v-col cols="5" class="mx-auto" style="text-align: end;">
        <v-btn @click="$store.state.saveCanvasDialog = true" dark>
          <v-icon class="pr-2">
            mdi-cloud-download-outline
          </v-icon>
          SAVE online
        </v-btn>
        <v-btn @click="downloadCanvas" dark class="mx-2">
          <v-icon class="pr-2">
            mdi-file-download-outline
          </v-icon>
          download canvas
        </v-btn>
        <v-btn @click="downloadImage" dark>
          <v-icon class="pr-2">
            mdi-download
          </v-icon>
          download image
        </v-btn>
      </v-col>
      <v-col
        cols="6"
        class="mr-5"
        :style="{ position: 'relative', top: `${currentScrollPlace}` + 'px' }"
      >
        <v-btn
          @click="isShowWebtoonImages = !isShowWebtoonImages"
          class="mr-2"
          color=""
          dark
        >
          <v-icon class="pr-2">
            mdi-image-multiple-outline
          </v-icon>
          Images
          <Loading
            :loading="checkLoading.isConvertedLoading"
            :color="'white'"
            :size="'35px'"
            class="ml-2"
          />
          <v-row
            class="showConvertedImage pa-10"
            style="z-index: 999;"
            v-show="isShowWebtoonImages"
          >
            <v-col v-for="(convertedImage, idx) in convertedImages" :key="idx">
              <img
                @click="moveConvertedImage(convertedImage)"
                class="my-2"
                style="width: 100px; height: 100px;"
                :src="'data:image/png;base64,' + `${convertedImage}`"
                alt="transformed image"
              />
            </v-col>
            <div style="align-self: center;">
              <Loading
                class="my-2"
                :loading="checkLoading.isConvertedLoading"
                :color="'white'"
                :size="'50px'"
              />
            </div>
          </v-row>
        </v-btn>

        <v-btn @click="btnAddBubble1" dark>
          <v-icon class="pr-2">
            mdi-chat-plus-outline
          </v-icon>
          Chat
        </v-btn>
        <v-btn @click="btnAddBackground" dark class="mx-2">
          <v-icon class="pr-2">
            mdi-card-plus
          </v-icon>
          Background
        </v-btn>
        <v-btn @click="btnAddCanvasHeight" dark class="">
          <v-icon class="pr-2">
            mdi-table-column-plus-after
          </v-icon>
          Add Page
        </v-btn>
        <v-btn @click="btnEditorImageToCanvas" class="mx-2" dark>
          <v-icon class="pr-2">
            mdi-send
          </v-icon>
          paint image
        </v-btn>
      </v-col>
    </v-row>
    <v-row style="justify-content: space-between;">
      <v-col
        v-resize="resizeCanvasWidth"
        cols="5"
        id="webtoonCanvas"
        class="mx-auto my-16 webtoon-canvas-css"
        :style="{ height: webtoonCanvasHeight + 'px' }"
      >
        <VueDragResize
          v-for="(image, idx) in images"
          :key="idx"
          :isActive="image.isActive"
          :parentH="webtoonCanvasHeight"
          :parentW="webtoonCanvasWidth"
          :w="image.w"
          :h="image.h"
          :x="image.x"
          :y="image.y"
          :z="image.isActive ? 999 : image.zIndex"
          :isDraggable="image.isDraggable"
          :parentLimitation="true"
          @activated="canvasImageOnActivated(idx)"
          @deactivated="canvasImageOffActivated(idx)"
          :style="[
            !image.isBubble && !image.isBackground ? '' : objectStyle(idx),
          ]"
          class="d-flex"
          @dragstop="dragstop($event, idx)"
          @resizestop="resizestop($event, idx)"
        >
          <img
            v-if="!image.isBubble && !image.isBackground"
            :src="image.image"
            :style="[objectStyle(idx)]"
            style="position: absolute; height: 100%; width: 100%;"
            alt=""
          />

          <div v-if="image.isBubble" :style="[bubbleArrowStyleSub(idx)]"></div>
          <!-- 옵션창 -->
          <div
            v-show="image.isActive"
            style="position: absolute; top: -35px; right: 0; z-index: 999"
          >
            <v-btn icon color="#0D47A1" style="cursor: default;">
              {{ image.zIndex }}
            </v-btn>
            <v-btn icon color="#0D47A1" @click="btnUpZindex(idx)">
              <v-icon>
                mdi-arrow-up-circle
              </v-icon>
            </v-btn>
            <v-btn icon color="#0D47A1" @click="btnDownZindex(idx)">
              <v-icon>
                mdi-arrow-down-circle
              </v-icon>
            </v-btn>
            <v-btn icon color="#0D47A1" @click="btnDelete(idx)">
              <v-icon>
                mdi-delete
              </v-icon>
            </v-btn>
            <v-btn icon color="#0D47A1" @click="btnOption(idx)">
              <v-icon>
                mdi-cog
              </v-icon>
            </v-btn>
          </div>

          <!-- background의 경우 -->
          <div
            @mousedown="onOptionPreventDrag(idx)"
            @mouseup="onOptionAllowDrag(idx)"
            v-if="image.isClickOption && image.isBackground"
            class="pa-3"
            style="position: absolute; left: calc(100% + 50px);"
          >
            <v-btn
              dark
              color="#0D47A1"
              @click="image.backgroundOption.gradientCheck = 1"
              style="text-transform: none;"
            >
              Background Uppper
            </v-btn>
            <v-btn
              dark
              color="#0D47A1"
              class="my-2"
              @click="image.backgroundOption.gradientCheck = 0"
              style="text-transform: none;"
            >
              Background Center
            </v-btn>
            <v-btn
              dark
              color="#0D47A1"
              @click="image.backgroundOption.gradientCheck = 2"
              style="text-transform: none;"
            >
              Background Lower
            </v-btn>
          </div>
          <!-- 말풍선의 경우 -->
          <div
            @mousedown="onOptionPreventDrag(idx)"
            @mouseup="onOptionAllowDrag(idx)"
            v-if="image.isClickOption && image.isBubble"
            :style="{
              height: 'fit-content',
              backgroundColor: 'rgba(0,0,20,0.9)',
              position: 'relative',
              bottom: image.bubbleOption.main.borderWidth + 'px',
              left:
                'calc(100% + 50px + ' +
                `${image.bubbleOption.main.borderWidth}` +
                'px )',
            }"
          >
            <v-container class="mx-auto my-8 object-option" id="optionSlider">
              <div class="mb-6">
                <v-btn
                  dark
                  color="#0D47A1"
                  class="mr-5"
                  @click.stop="clickBubbleOptionText"
                  >Text</v-btn
                >
                <v-btn dark color="#0D47A1" @click.stop="clickBubbleOption"
                  >Speech Bubble</v-btn
                >
              </div>

              <!-- 텍스트 옵션 -->
              <v-row v-show="isClickBubbleOptionText">
                <v-col>
                  <!-- stopPropagation 통해서 상위 이벤트로 못넘어가게해줘야함 -->
                  <div>
                    Your Line
                  </div>
                  <v-text-field
                    @focus.stop
                    @mouseup.stop
                    @mousedown.stop
                    placeholder="Say anything for your Character"
                    class="pt-0"
                    dark
                    v-model="image.bubbleOption.text.content"
                  >
                  </v-text-field>
                  <div>
                    Font Size
                  </div>
                  <v-slider
                    @mousedown.stop
                    v-model="image.bubbleOption.text.fontSize"
                    :max="100"
                    :min="10"
                    hide-details
                    dark
                  >
                    <template v-slot:append>
                      <v-text-field
                        dark
                        v-model="image.bubbleOption.text.fontSize"
                        class="mt-0 pt-0"
                        hide-details
                        single-line
                        style="width: 60px"
                      ></v-text-field>
                    </template>
                  </v-slider>
                  <div>
                    Font Weight
                  </div>
                  <v-slider
                    @mousedown.stop
                    v-model="image.bubbleOption.text.fontWeight"
                    :max="9"
                    :min="1"
                    hide-details
                    dark
                  >
                    <template v-slot:append>
                      <v-text-field
                        dark
                        v-model="image.bubbleOption.text.fontWeight"
                        class="mt-0 pt-0"
                        hide-details
                        single-line
                        style="width: 60px"
                      ></v-text-field>
                    </template>
                  </v-slider>
                  <div>
                    Font Color
                  </div>
                  <v-color-picker
                    @update:color.once="
                      image.bubbleOption.text.color = '#000000F0'
                    "
                    v-model="image.bubbleOption.text.color"
                    hide-mode-switch
                    mode="hexa"
                    class="my-2"
                    width="250"
                  >
                  </v-color-picker>
                </v-col>
              </v-row>

              <!-- 말풍선 옵션 -->
              <div v-show="isClickBubbleOption">
                <v-row>
                  <v-col>
                    <div class="mb-2">
                      <div>
                        Place
                      </div>

                      <v-btn @click="btnBubbleArrowUp(idx)" dark icon>
                        <v-icon>
                          mdi-arrow-up-bold-circle-outline
                        </v-icon>
                      </v-btn>
                      <v-btn @click="btnBubbleArrowDown(idx)" dark icon>
                        <v-icon>
                          mdi-arrow-down-bold-circle-outline
                        </v-icon>
                      </v-btn>
                      <v-btn @click="btnBubbleArrowLeft(idx)" dark icon>
                        <v-icon>
                          mdi-arrow-left-bold-circle-outline
                        </v-icon>
                      </v-btn>
                      <v-btn @click="btnBubbleArrowRight(idx)" dark icon>
                        <v-icon>
                          mdi-arrow-right-bold-circle-outline
                        </v-icon>
                      </v-btn>
                    </div>

                    <div>
                      Arrow Size
                    </div>
                    <v-slider
                      @mousedown.stop
                      v-model="image.bubbleOption.sub.width"
                      dark
                      :max="100"
                      :min="10"
                    >
                      <template v-slot:append>
                        <v-text-field
                          dark
                          v-model="image.bubbleOption.sub.width"
                          class="mt-0 pt-0"
                          hide-details
                          single-line
                          style="width: 60px"
                        ></v-text-field>
                      </template>
                    </v-slider>
                    <div>
                      Arrow Horizontal adjust
                    </div>
                    <v-slider
                      @mousedown.stop
                      v-model="image.bubbleOption.sub.left"
                      dark
                      :max="95"
                      :min="-10"
                    >
                      <template v-slot:append>
                        <v-text-field
                          dark
                          v-model="image.bubbleOption.sub.left"
                          class="mt-0 pt-0"
                          hide-details
                          single-line
                          style="width: 60px"
                        ></v-text-field>
                      </template>
                    </v-slider>
                    <div>
                      Arrow Vertical adjust
                    </div>
                    <v-slider
                      @mousedown.stop
                      v-model="image.bubbleOption.sub.bottom"
                      dark
                      :max="95"
                      :min="-10"
                    >
                      <template v-slot:append>
                        <v-text-field
                          dark
                          v-model="image.bubbleOption.sub.bottom"
                          class="mt-0 pt-0"
                          hide-details
                          single-line
                          style="width: 60px"
                        ></v-text-field>
                      </template>
                    </v-slider>
                    <div>
                      Border Size
                    </div>
                    <v-slider
                      @mousedown.stop
                      v-model="image.bubbleOption.main.borderWidth"
                      dark
                      :max="10"
                      :min="0"
                    >
                      <template v-slot:append>
                        <v-text-field
                          dark
                          v-model="image.bubbleOption.main.borderWidth"
                          class="mt-0 pt-0"
                          hide-details
                          single-line
                          style="width: 60px"
                        ></v-text-field>
                      </template>
                    </v-slider>
                    <div>
                      borderRadius
                    </div>
                    <v-slider
                      @mousedown.stop
                      v-model="image.bubbleOption.main.borderRadius"
                      dark
                      :max="70"
                      :min="0"
                    >
                      <template v-slot:append>
                        <v-text-field
                          dark
                          v-model="image.bubbleOption.main.borderRadius"
                          class="mt-0 pt-0"
                          hide-details
                          single-line
                          style="width: 60px"
                        ></v-text-field>
                      </template>
                    </v-slider>
                  </v-col>
                </v-row>
                <v-row class="">
                  <v-col class="mr-5">
                    <div>
                      Speech bubble Color
                    </div>
                    <v-color-picker
                      hide-mode-switch
                      v-model="image.bubbleOption.main.backgroundColor"
                      mode="hexa"
                      class="my-2"
                      width="200"
                    >
                    </v-color-picker>
                  </v-col>
                  <v-col>
                    <div>
                      Border line Color
                    </div>
                    <v-color-picker
                      @update:color.once="
                        image.bubbleOption.main.borderColor = '#000000F0'
                      "
                      hide-mode-switch
                      v-model="image.bubbleOption.main.borderColor"
                      mode="hexa"
                      class="my-2"
                      width="200"
                    >
                    </v-color-picker>
                  </v-col>
                </v-row>
              </div>
            </v-container>
          </div>
          <!-- 이미지의 경우 -->
          <div
            @mousedown="onOptionPreventDrag(idx)"
            @mouseup="onOptionAllowDrag(idx)"
            v-if="image.isClickOption && !image.isBubble && !image.isBackground"
            :style="{
              height: 'fit-content',
              backgroundColor: 'rgba(0,0,20,0.9)',
              position: 'relative',
              bottom: image.imageOption.borderSlider + 'px',
              left:
                'calc(100% + 50px + ' +
                `${image.imageOption.borderSlider}` +
                'px )',
            }"
          >
            <v-container
              fluid
              class="mx-auto my-8 object-option"
              id="optionSlider"
            >
              <div>
                Border Size
              </div>
              <v-slider
                @mousedown.stop
                v-model="image.imageOption.borderSlider"
                dark
                :max="10"
                :min="0"
              >
                <template v-slot:append>
                  <v-text-field
                    dark
                    v-model="image.imageOption.borderSlider"
                    class="mt-0 pt-0"
                    hide-details
                    single-line
                    style="width: 60px"
                  ></v-text-field>
                </template>
              </v-slider>
              <div>
                Border Radius
              </div>
              <v-slider
                @mousedown.stop
                v-model="image.imageOption.borderRadius"
                dark
                :max="70"
                :min="0"
              >
                <template v-slot:append>
                  <v-text-field
                    dark
                    v-model="image.imageOption.borderRadius"
                    class="mt-0 pt-0"
                    hide-details
                    single-line
                    style="width: 60px"
                  ></v-text-field>
                </template>
              </v-slider>

              <div>
                Border Color
              </div>
              <v-color-picker
                v-model="image.imageOption.borderColor"
                hide-mode-switch
                class="my-2"
                mode="hexa"
              >
              </v-color-picker>
            </v-container>
          </div>

          <!-- 대사영역 -->
          <p
            v-if="image.isBubble"
            class="my-0"
            style="width: 100%; padding: 20%; position: absolute; align-self: center; text-align: center; overflow-wrap: anywhere;"
            :style="[contentStyle(idx)]"
          >
            {{ image.bubbleOption.text.content }}
          </p>
        </VueDragResize>
      </v-col>

      <v-col
        cols="6"
        class="mr-5"
        :style="{ position: 'relative', top: `${currentScrollPlace}` + 'px' }"
      >
        <v-row style="height: 70vh; width: 100%;" class="">
          <tui-image-editor
            ref="imageEditor"
            :include-ui="useDefaultUI"
            :options="options"
          ></tui-image-editor>
        </v-row>

        <v-row>
          <vue2Dropzone
            @vdropzone-files-added="dropZoneImageMoveToEditor"
            id="dropZone"
            ref="myVueDropzone"
            style="
                width: 100%;
                position: relative;
                height: 25vh;
                overflow-y: auto;
                border: 1px solid white;
                text-align: center;
                background: border-box;
                border-bottom-right-radius: 20px;
                border-bottom-left-radius: 20px;
                border-top-left-radius: 5px;
                border-top-right-radius: 5px;
                "
            class=""
            :options="dropzoneOptions"
            :useCustomSlot="true"
          >
            <div class="dropzone-custom-content">
              <h3 class="dropzone-custom-title">
                Drag and drop to upload content First!
              </h3>
              <div class="subtitle">
                ...or click to select a file from your computer
              </div>
            </div>
          </vue2Dropzone>
        </v-row>
      </v-col>
    </v-row>
    <PhotoEditorSaveOnlineModal @thumbnailAndSubject="canvasImageToSpring" />
    <PhotoEditorHelpDial />
    <v-overlay
      :value="checkLoading.isMoveImageToCanvasLoading"
      style="z-index: 999;"
    >
      <v-progress-circular indeterminate size="64"></v-progress-circular>
    </v-overlay>
  </v-container>
</template>

<script>
import VueDragResize from "vue-drag-resize";
import vue2Dropzone from "vue2-dropzone";
import "vue2-dropzone/dist/vue2Dropzone.min.css";
import html2canvas from "html2canvas";

import { ImageEditor } from "@toast-ui/vue-image-editor";
import "tui-image-editor/dist/svg/icon-a.svg";
import "tui-image-editor/dist/svg/icon-b.svg";
import "tui-image-editor/dist/svg/icon-c.svg";
import "tui-image-editor/dist/svg/icon-d.svg";
import "tui-image-editor/dist/tui-image-editor.css";
import { mapState } from "vuex";

import Loading from "@/components/Loading";
import PhotoEditorSaveOnlineModal from "@/components/photoeditor/PhotoEditorSaveOnlineModal";
import PhotoEditorHelpDial from "@/components/photoeditor/PhotoEditorHelpDial";

export default {
  components: {
    "tui-image-editor": ImageEditor,
    VueDragResize,
    vue2Dropzone,
    PhotoEditorSaveOnlineModal,
    PhotoEditorHelpDial,
    Loading,
  },
  watch: {
    objectCount(newVal) {
      if (newVal >= 10) {
        this.objectCount = 0;
      }
    },
  },
  data() {
    return {
      saveDialog: false,
      isShowWebtoonImages: false,
      currentScrollPlace: 0,
      isClickBubbleOptionText: true,
      isClickBubbleOption: false,
      previewCount: 0,
      objectCount: 0,
      images: [
        {
          x: 0,
          y: 0,
          w: 200,
          h: 200,
          image: require(`@/assets/default.png`), // 맨처음 테스트용으로 넣은것
          isActive: false, // 나중에 중복 선택 제거를 위함.
          isBackground: false, // 배경인지 확인하기위함.
          isBubble: false, // 말풍선인지 확인
          zIndex: 102,
          isClickOption: false,
          isDraggable: true,
          imageOption: {
            borderSlider: 5,
            borderRadius: 0,
            borderColor: "#000",
          },
          backgroundOption: {
            gradientCheck: 0, // 0 없음, 1 upper, 2 lower
          },
          bubbleOption: {
            text: {
              content: "",
              fontSize: 16,
              fontFamily: "",
              fontWeight: 5,
              color: "black",
            },
            main: {
              position: "absolute",
              backgroundColor: "#fff",
              borderRadius: 1,
              border: "1px solid",
              borderColor: "black",
              borderWidth: 1,
            },
            sub: {
              position: "absolute",
              zIndex: -1,
              bottom: -5,
              left: 70,
              width: 30,
              height: 30,
              backgroundColor: "white",
              borderWidth: "1px 1px 0 0",
              borderStyle: "solid",
              borderColor: "black",
              transform: "rotate(135deg)",
            },
          },
        },
      ],
      // 배경, 이미지 변경시
      objectStyle: function(idx) {
        let image = this.images[idx];
        let imageStyle = {};

        if (image.isBackground) {
          let gradientCheck = image.backgroundOption.gradientCheck;
          if (gradientCheck === 1) {
            imageStyle.background = "linear-gradient( white , black )";
          } else if (gradientCheck === 2) {
            imageStyle.background = "linear-gradient( black , white )";
          } else {
            imageStyle.backgroundColor = "black";
          }
        } else if (image.isBubble) {
          imageStyle.position = image.bubbleOption.main.position;
          imageStyle.backgroundColor = image.bubbleOption.main.backgroundColor;
          imageStyle.border = image.bubbleOption.main.border;
          imageStyle.borderRadius = image.bubbleOption.main.borderRadius + "%";
          imageStyle.borderColor = image.bubbleOption.main.borderColor;
          imageStyle.borderWidth = image.bubbleOption.main.borderWidth + "px";
        } else {
          imageStyle.border = image.imageOption.borderSlider + "px" + " solid";
          imageStyle.borderColor = image.imageOption.borderColor;
          imageStyle.borderRadius = image.imageOption.borderRadius + "%";
          // imageStyle.backgroundImage = 'url(' + `${image.image}` + ')'
          // imageStyle.backgroundRepeat= 'round'
        }
        return imageStyle;
      },
      bubbleArrowStyleSub: function(idx) {
        let style = this.images[idx].bubbleOption.sub;
        let mainStyle = this.images[idx].bubbleOption.main;
        let arrowStyle = {};

        arrowStyle.position = style.position;
        arrowStyle.zIndex = -1;
        arrowStyle.bottom = style.bottom + "%";
        arrowStyle.left = style.left + "%";
        arrowStyle.width = style.width + "px";
        arrowStyle.height = style.width + "px"; // width === height
        arrowStyle.borderStyle = style.borderStyle;

        arrowStyle.backgroundColor = mainStyle.backgroundColor;
        arrowStyle.borderWidth =
          mainStyle.borderWidth + "px " + mainStyle.borderWidth + "px " + "0 0";
        arrowStyle.borderColor = mainStyle.borderColor;

        arrowStyle.transform = style.transform;

        return arrowStyle;
      },
      contentStyle: function(idx) {
        let content = this.images[idx].bubbleOption.text;
        let contentStyle = {};

        contentStyle.fontSize = content.fontSize + "px";
        contentStyle.fontFamily = content.fontFamily;
        contentStyle.fontWeight = content.fontWeight * 100;
        contentStyle.color = content.color;

        return contentStyle;
      },
      useDefaultUI: true,
      initWebtoonCanvasHeight: window.innerHeight * 0.87,
      webtoonCanvasHeight: window.innerHeight * 0.87,
      webtoonCanvasWidth: 0,
      webtoonCanvasCount: 1,
      options: {
        cssMaxWidth: 400,
        cssMaxHeight: 400,
        includeUI: {
          loadImage: {
            path: require(`@/assets/init.png`),
            name: "test5442",
          },
          uiSize: {
            width: String,
            height: String,
          },
          menuBarPosition: "bottom",
          // initMenu: 'filter',
          // menu: ['shape', 'filter',],
          menu: ["crop", "flip", "rotate", "shape", "draw", "icon", "text"],
        },
        selectionStyle: {
          cornerSize: 10,
          // borderColor: 'red',
          cornerStrokeColor: "white",
          cornerColor: "white",
          rotatingPointOffset: 40,
        },
      },
      dropzoneOptions: {
        url: "https://httpbin.org/post",
        thumbnailWidth: 100,
        thumbnailHeight: 100,
        // addRemoveLinks: true
        // clickable: false,
      },
    };
  },
  computed: {
    ...mapState(["convertedImages", "userInfo", "checkLoading"]),
    // img :src="'data:image/png;base64,' + `${test123}`" 나중에 이미지 base64파일 형식으로 넣어주면된다.
  },
  methods: {
    dragstop(payload, idx) {
      this.images[idx].x = payload.left;
      this.images[idx].y = payload.top;
    },
    resizestop(payload, idx) {
      this.images[idx].w = payload.width;
      this.images[idx].h = payload.height;
    },
    btnDelete(idx) {
      this.images.splice(idx, 1);
    },
    getCurrentScrollPlace() {
      const checkScrollPlace = document.documentElement.scrollTop;
      if (checkScrollPlace >= 200) {
        this.currentScrollPlace = checkScrollPlace - 70;
      } else {
        this.currentScrollPlace = 0;
      }
    },
    onOptionAllowDrag(idx) {
      this.images[idx].isDraggable = true;
    },
    onOptionPreventDrag(idx) {
      this.images[idx].isDraggable = false;
    },

    clickBubbleOptionText() {
      this.isClickBubbleOptionText = true;
      this.isClickBubbleOption = false;
    },
    clickBubbleOption() {
      this.isClickBubbleOptionText = false;
      this.isClickBubbleOption = true;
    },

    btnBubbleArrowUp(idx) {
      const arrowStyle = this.images[idx].bubbleOption.sub;
      arrowStyle.bottom = 90;
      arrowStyle.left = 30;
      arrowStyle.transform = "rotate(315deg)";
    },
    btnBubbleArrowDown(idx) {
      const arrowStyle = this.images[idx].bubbleOption.sub;
      arrowStyle.bottom = -5;
      arrowStyle.left = 70;
      arrowStyle.transform = "rotate(135deg)";
    },
    btnBubbleArrowLeft(idx) {
      const arrowStyle = this.images[idx].bubbleOption.sub;
      arrowStyle.bottom = 50;
      arrowStyle.left = -5;
      arrowStyle.transform = "rotate(225deg)";
    },
    btnBubbleArrowRight(idx) {
      const arrowStyle = this.images[idx].bubbleOption.sub;
      arrowStyle.bottom = 50;
      arrowStyle.left = 90;
      arrowStyle.transform = "rotate(45deg)";
    },
    btnOption(idx) {
      this.images[idx].isClickOption = !this.images[idx].isClickOption;
    },

    // 캔버스 추가
    btnAddCanvasHeight() {
      this.webtoonCanvasHeight += this.initWebtoonCanvasHeight;
      this.webtoonCanvasCount++;
    },
    // 에디터 이미지를 캔버스로 이동
    btnEditorImageToCanvas() {
      // 그냥 base64 string이 너무길어서 오래걸리는 것, 그래서 로딩도 못걸어준다. ( 변환중에 애초에 다른 작업 수행이안됨. )
      // this.$store.state.checkLoading.isMoveImageToCanvasLoading = true
      // setTimeout(() => {
      // this.$store.state.checkLoading.isMoveImageToCanvasLoading = false
      // }, 10000);

      const dataURL = this.$refs.imageEditor.invoke("toDataURL"); // base64 data
      const imageData = {
        x: this.objectCount * 10,
        y: this.currentScrollPlace,
        w: 200,
        h: 200,
        image: dataURL,
        isActive: false,
        isBackground: false,
        isBubble: false,
        zIndex: 100,
        isClickOption: false,
        isDraggable: true,
        imageOption: {
          borderSlider: 5,
          borderColor: "#000",
          borderRadius: 0,
        },
      };
      this.images.push(imageData);
      this.objectCount++;
    },
    // 말풍선 추가
    btnAddBubble1() {
      const addBubble = {
        x: this.objectCount * 10,
        y: this.currentScrollPlace,
        w: 200,
        h: 200,
        image: "",
        isActive: false,
        isBackground: false,
        isBubble: true,
        zIndex: 100,
        isClickOption: false,
        isDraggable: true,
        bubbleOption: {
          text: {
            content: "",
            fontSize: 16,
            fontFamily: "",
            fontWeight: 5,
            color: "black",
          },
          main: {
            position: "absolute",
            backgroundColor: "#fff",
            borderRadius: 10,
            border: "1px solid",
            borderColor: "black",
            borderWidth: 1,
          },
          sub: {
            position: "absolute",
            zIndex: -1,
            bottom: -5,
            left: 70,
            width: 30,
            height: 30,
            backgroundColor: "#fff",
            borderWidth: "1px 1px 0 0",
            borderStyle: "solid",
            borderColor: "black",
            transform: "rotate(135deg)",
          },
        },
      };
      this.images.push(addBubble);
      this.objectCount++;
    },
    // background 추가.
    btnAddBackground() {
      const backgroundWidth = document.querySelector("#webtoonCanvas")
        .offsetWidth;
      console.log(backgroundWidth);
      const addBackground = {
        x: 0,
        y: this.currentScrollPlace,
        w: backgroundWidth,
        h: 200,
        image: "",
        isActive: false,
        isBackground: true,
        isBubble: false,
        zIndex: 100,
        isClickOption: false,
        isDraggable: true,
        backgroundOption: {
          gradientCheck: 0, // 0 없음, 1 upper, 2 lower
        },
      };
      this.images.push(addBackground);
      this.objectCount++;
    },

    isIndex() {
      this.$store.commit("isIndex", false);
    },
    isNotEditor() {
      this.$store.commit("isNotEditor", false);
    },
    btnUpZindex(idx) {
      // console.log(idx)
      this.images[idx].zIndex += 1;
    },
    btnDownZindex(idx) {
      this.images[idx].zIndex -= 1;
    },
    downloadImage() {
      const base64Data = this.$refs.imageEditor.invoke("toDataURL");
      // 굳이 IE 10+ 지원하지말자. 지원할거면 canvas와 같이 한다
      let a = document.createElement("a");
      a.style = "display: none";
      a.href = base64Data;
      a.download = "new_image.png";
      document.body.appendChild(a);
      a.click();

      setTimeout(function() {
        document.body.removeChild(a);
      }, 100);
    },
    downloadCanvas() {
      const downCanvas = document.querySelector("#webtoonCanvas");
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
            window.navigator.msSaveOrOpenBlob(file, "new_file.png");
          } else {
            let a = document.createElement("a");
            a.style = "display: none";
            a.href = base64Data;
            a.download = "new_file.png";
            document.body.appendChild(a);
            a.click();

            setTimeout(function() {
              document.body.removeChild(a);
            }, 100);
          }
        });
    },
    // 스프링으로 이미지 전달.
    async canvasImageToSpring(thumbnail, subject) {
      const ctxTest = document.querySelector("#webtoonCanvas");
      const offsetY = ctxTest.offsetTop + 64;
      this.$store.state.checkLoading.isSaveOnlineLoading = true;

      let canvasFormData = new FormData();
      //let canvasFormArray = new Array()

      for (let count = 0; count < this.webtoonCanvasCount; count++) {
        await html2canvas(ctxTest, {
          height: this.initWebtoonCanvasHeight,
          y: offsetY + this.initWebtoonCanvasHeight * count, // 아래위 마진때문
        }).then((canvas) => {
          // document.body.appendChild(canvas)

          const base64Data = canvas.toDataURL("image/png");

          // base64 데이터 디코딩
          let blobBin = atob(base64Data.split(",")[1]);
          let array = [];
          for (var i = 0; i < blobBin.length; i++) {
            array.push(blobBin.charCodeAt(i));
          }

          let file = new Blob([new Uint8Array(array)], { type: "image/png" }); // Blob 생성
          // canvasFormData.append(count, file);	// file data 추가
          // canvasFormArray.push(file);	// file data 추가
          // 같은 이름으로 넣어줘야, Array list 로 스프링에서 받을수 있다.
          canvasFormData.append("image", file);
          // return canvasFormData
        });
        // .catch( e => {
        //   console.log(e)
        //   alert('캔버스 전송실패')
        // })
        // .then( canvasFormData => {
        //   console.log(5)
        //   this.$store.dispatch('canvasImageToSpring', canvasFormData)
        // })
      }
      canvasFormData.append("no", this.userInfo.no);
      canvasFormData.append("subject", subject);
      canvasFormData.append("thumbnail", thumbnail); // 여기에 섬네일 파일 넣어주면 됨.
      // canvasFormData.append('createDate', 'check')
      // canvasFormData.append('image', canvasFormArray)
      await this.$store.dispatch("canvasImageToSpring", canvasFormData);
      this.$store.state.checkLoading.isSaveOnlineLoading = false;
    },
    // 업로드된 파일 클릭시 preview생성
    moveConvertedImage(baseData) {
      let formData = new FormData();

      let blobBin = atob(baseData);
      let array = [];
      for (var i = 0; i < blobBin.length; i++) {
        array.push(blobBin.charCodeAt(i));
      }

      let file = new Blob([new Uint8Array(array)], { type: "image/png" }); // Blob 생성

      formData.append("file", file, "file1");
      this.$refs.imageEditor.invoke("loadImageFromFile", formData.get("file"));
    },

    // 파일 업로드시, preview만 클릭하면 올라갈 수 있도록 만듬.
    dropZoneImageMoveToEditor(file_list) {
      // console.log(file_list)

      const dz_preview = document.querySelectorAll(".dz-preview");

      this.dropZoneImageToDjango(file_list);

      // const temp2 = document.querySelectorAll('.dz-image > img')

      // temp1[0].addEventListener('click', function(e) {
      //   console.log(e)
      // })

      for (let idx = 0; idx < file_list.length; idx++) {
        dz_preview[idx + this.previewCount].addEventListener("click", (e) => {
          this.$refs.imageEditor.invoke("loadImageFromFile", file_list[idx]);
          e;
        });
      }
      // 여러번 올렸을경우, dz_preview의 인덱스가 달라지기때문
      this.previewCount += file_list.length;
    },

    // django로 이미지 보내기.
    async dropZoneImageToDjango(file_list) {
      this.$store.state.checkLoading.isConvertedLoading = true;
      const djangoImageForm = new FormData();

      // function appendImage(name, file) {
      //   console.log('resolve')
      //   return new Promise(function (resolve) {
      //     console.log(name, file)
      //     djangoImageForm.append(name, file)
      //     resolve(name)
      //   })
      // }
      // console.log(file_list)
      // let cnt = 1

      for (let file of file_list) {
        // var a = await appendImage('img' + `${cnt}`, file)
        // console.log(a)
        djangoImageForm.append("img1", file);
        // cnt += 1
        await this.$store.dispatch("dropZoneImageToDjango", djangoImageForm);
      }

      this.$store.state.checkLoading.isConvertedLoading = false;
    },

    // 캔버스의 이미지가 눌러졌을때, 다른 이미지는 활성화 취소
    canvasImageOnActivated(idx) {
      for (let i = 0; i < this.images.length; i++) {
        if (i === idx) {
          this.images[i].isActive = true;
          continue;
        }
        this.images[i].isActive = false;
      }
    },
    canvasImageOffActivated(idx) {
      this.images[idx].isActive = false;
      this.images[idx].isClickOption = false;
      this.images[idx].isDraggable = true;

      // 여기서 세팅 init 하면될듯.
      this.isClickBubbleOptionText = true;
      this.isClickBubbleOption = false;
    },

    //캔버스 사이즈 변경시, 캔버스 안의 이미지가 안옮겨지는 버그 수정
    resizeCanvasWidth(e) {
      if (e) {
        const canvas_width = e.target.document.querySelector("#webtoonCanvas")
          .clientWidth;
        this.webtoonCanvasWidth = canvas_width;
      }
    },
  },
  created() {
    this.isIndex();
    this.isNotEditor();
  },
  mounted() {
    // scroll 추적기 붙이기
    window.addEventListener("scroll", this.getCurrentScrollPlace);

    this.isShowWebtoonImages = false;
  },
  destroy() {
    window.removeEventListener("scroll", this.getCurrentScrollPlace);
  },
};
</script>

<style>
.showConvertedImage {
  top: 60px;
  position: absolute;
  width: 400px;
  /* height: 300px; */
  background-color: rgba(0, 0, 20, 0.9);
  border-radius: 10px;
}

.showConvertedImage::after {
  content: "";
  position: absolute;
  bottom: 100%;
  left: 50%;
  border-bottom: 20px solid rgba(0, 0, 20, 0.9);
  border-right: 20px solid transparent;
  border-left: 20px solid transparent;
}

.showConvertedImage:hover {
  background-color: rgba(0, 0, 20, 0.5);
}

.tri {
  width: 0px;
  height: 0px;
}

.object-option {
  width: 500px;
  color: white;
  padding-left: 40px;
  padding-right: 40px;
}

#optionSlider .v-input__append-outer {
  position: relative;
  bottom: 20px;
}

.v-slider {
  margin-left: 0 !important;
}

.tui-image-editor-header > .tui-image-editor-header-logo,
.tui-image-editor-header-buttons {
  display: none;
}

.tui-image-editor-container .tui-image-editor-wrap {
  overflow: initial;
}

.webtoon-canvas-css {
  position: relative;
  background-color: white;
  /* border: 1px solid black; */
}

.dropzone-custom-content {
  margin-top: 45px;
}
.dropzone-custom-title {
  color: rgba(255, 255, 255, 1);
  font-weight: bold;
  margin: 0;
  font-size: 1.1rem;
}

.vdr:hover {
  cursor: pointer;
}

.subtitle {
  /* color: #314b5f; */
  color: inherit;
}
</style>
