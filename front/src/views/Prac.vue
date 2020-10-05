<template>
  <div class="mt-16">
    <div class="oval-speech" style="z-index: 999"></div>
    <div style="z-index: 999" class="oval-speech">abadcaef</div>

    <div>
      <label>사진★:</label>
      <input type="file" id="imgselector" multiple />
      <v-btn @click="sendimg">test</v-btn>
    </div>

    <div>
      test
      <img
        :src="'data:image/png;base64,' + `${test123}`"
        alt=""
        style="
          width: 100px;
          height: 100px;
          background-color: black;
          z-index: 9999;
        "
      />
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Prac",
  components: {},
  data() {
    return {
      test123: "",
    };
  },
  methods: {
    sendimg() {
      const formData = new FormData();
      const file = document.getElementById("imgselector");
      formData.append("img1", file.files[0]);
      formData.append("img2", file.files[1]);

      axios
        .post("https://j3b308.p.ssafy.io/ai/ImgtoAnime/", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((res) => {
          console.log(1, res.data);
          console.log(2, res);
          this.test123 = res.data;
          console.log(this.test123);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style scoped>
.oval-speech {
  position: relative;
  width: 270px;
  padding: 50px 40px;
  margin: 1em auto 50px;
  text-align: center;
  color: #000;
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
  border-radius: 220px / 120px;
  border: 0.1px solid black;
}

/* .oval-speech p {font-size:1.25em;} */

/* creates part of the curve */
.oval-speech:before {
  content: "";
  position: absolute;
  z-index: -1;
  bottom: -33px;
  right: 50%;
  height: 30px;
  border-right: 60px solid #000;
  /* border: 1px solid black; */
  background: #fff;
  border-bottom-right-radius: 80px 50px;
  transform: translate(0, -2px);
}

/* creates part of the curved pointy bit */
.oval-speech:after {
  content: "";
  position: absolute;
  z-index: -1;
  bottom: -33px;
  right: 50%;
  width: 60px;
  height: 30px;
  background: #fff;
  /* css3 */
  border-bottom-right-radius: 40px 50px;
  /* using translate to avoid undesired appearance in CSS2.1-capabable but CSS3-incapable browsers */
  transform: translate(-30px, -2px);
}
</style>