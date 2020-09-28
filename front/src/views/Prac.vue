<template>

  <div class="mt-16">
    <div class="oval-speech" style="z-index:999">
      
                <v-row>
                  <v-col>
                    텍스트영역
                    <!-- stopPropagation 통해서 상위 이벤트로 못넘어가게해줘야함 -->
                    <v-text-field
                      @focus.stop
                      @mouseup.stop
                      @mousedown.stop
                      label="Regular"
                      placeholder="Placeholder"
                      style="color: white;"
                      dark
                      v-model="image.bubbleOption.text.content"
                    >
                    </v-text-field>
                    <div>
                      font-size vislide
                    </div>
                    <v-slider
                      v-model="image.bubbleOption.text.fontSize"
                      class="align-center"
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
                          type="number"
                          style="width: 60px"
                        ></v-text-field>
                      </template>
                    </v-slider>
                    <div>
                      font-weight
                    </div>
                    <v-slider
                      v-model="image.bubbleOption.text.fontWeight"
                      class="align-center"
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
                          type="number"
                          style="width: 60px"
                        ></v-text-field>
                      </template>
                    </v-slider>
                    <div>
                      font-color
                    </div>

                    <!-- <div
                      @click="isClickTextColor = !isClickTextColor"
                      style="width: 50px; height: 50px; background-color: white; border-radius: 70px;"

                    ></div> -->
                    <v-color-picker
                      hide-mode-switch
                      v-model="image.bubbleOption.text.color"
                      mode='hexa'
                      class="my-2"
                      :value="image.bubbleOption.text.color"
                      
                    >
                    </v-color-picker>


                  </v-col>
                </v-row>

    </div>
    <div style="z-index: 999;" class="oval-speech">abadcaef</div>
  </div>

</template>

<script>

export default {
  name: 'Prac',
  components: {
  },
  data() {
    return {

    }
  }
}
</script>

<style scoped>

.oval-speech {
  position:relative;
  width:270px;
  padding:50px 40px;
  margin:1em auto 50px;
  text-align:center;
  color:#000;
  /* background:#5a8f00; */
  /* css3 */
  /* background:-webkit-gradient(linear, 0 0, 0 100%, from(#b8db29), to(#5a8f00));
  background:-moz-linear-gradient(#b8db29, #5a8f00);
  background:-o-linear-gradient(#b8db29, #5a8f00);
  background:linear-gradient(#b8db29, #5a8f00); */
  /*
  NOTES:
  -webkit-border-radius:220px 120px; // produces oval in safari 4 and chrome 4
  -webkit-border-radius:220px / 120px; // produces oval in chrome 4 (again!) but not supported in safari 4
  Not correct application of the current spec, therefore, using longhand to avoid future problems with webkit corrects this
  */
  /* -webkit-border-top-left-radius:220px 120px;
  -webkit-border-top-right-radius:220px 120px;
  -webkit-border-bottom-right-radius:220px 120px;
  -webkit-border-bottom-left-radius:220px 120px;
  -moz-border-radius:220px / 120px; */
  border-radius:220px / 120px;
  border: 0.1px solid black;
}

/* .oval-speech p {font-size:1.25em;} */

/* creates part of the curve */
.oval-speech:before {
  content:"";
  position:absolute;
  z-index:-1;
  bottom:-33px;
  right:50%;
  height:30px;
  border-right:60px solid #000;
  /* border: 1px solid black; */
  background:#fff;
  border-bottom-right-radius:80px 50px;
  transform:translate(0, -2px);
}

/* creates part of the curved pointy bit */
.oval-speech:after {
  content:"";
  position:absolute;
  z-index:-1;
  bottom:-33px;
  right:50%;
  width:60px;
  height:30px;
  background:#fff;
  /* css3 */
  border-bottom-right-radius:40px 50px;
  /* using translate to avoid undesired appearance in CSS2.1-capabable but CSS3-incapable browsers */
  transform:translate(-30px, -2px);
}




</style>