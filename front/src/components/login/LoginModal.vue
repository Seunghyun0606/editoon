<template>
  <v-row justify="center">
    <v-dialog v-model="loginDialog" max-width="60%">
      <v-container fluid style="height: 80vh;">
        <v-row
          style="height: 100%; text-align: center; background-color: rgba(0, 0, 0, 0.8)"
        >
          <v-col cols="7">
            <img
              style="height: 100%; width: 100%;"
              :src="require('@/assets/red.gif')"
              alt
            />
          </v-col>
          <v-col cols="5" class="main-index-txt align-self-center">
            <div style="color: white;">
              로그인
            </div>
            <v-form
              action
              style="margin-top: 0.2vh;"
              v-model="valid"
            >
              <v-text-field
                v-model="loginData.email"
                label="E-mail"
                :rules="[rules.email]"
                required
                clearable
                dark
                style="width: 70%;"
                class="mx-auto"
              ></v-text-field>
              <v-text-field
                v-model="loginData.password"
                label="password"
                type="password"
                :rules="[rules.password, rules.lengthCheck(10)]"
                required
                clearable
                dark
                style="width: 70%;"
                class="mx-auto"
              ></v-text-field>
              <div>
                <v-btn :disabled="!valid" class="primary" @click="login()" style="width: 30%;">
                  <strong>접속하기!</strong>
                </v-btn>
              </div>
            </v-form>
          </v-col>
        </v-row>
      </v-container>
    </v-dialog>
  </v-row>
</template>

<script>
// import { mapState } from 'vuex'

export default {
  name: "LoginModal",
  computed: {
    // modal을 이용할때, v-model을 사용하는 것이 편하기도하고, 실제로 v-dialog에 v-model에
    // emit 이벤트로 click.outside 의 경우 false를 보내서 v-model에 보내준다.
    // 그런데 나같은 경우는 지금 store를 이용하다보니, setter가 없다. v-model을 걸어서 반환해줄부분이없다.
    // 따라서, computed에서 getter는 store에서 가져오고,
    // 새롭게 변하는 값은 setter에서 받아서 store의 값을바꿔준다.
    // https://vuex.vuejs.org/kr/guide/forms.html 참고.
    loginDialog: {
      get() {
        return this.$store.state.loginDialog;
      },
      set(val) {
        this.$store.state.loginDialog = val;
        this.reset();
      },
    },
  },
  data() {
    return {
      valid: true,

      loginData: {
        email: "",
        password: "",
      },
      rules: {
        email: (v) => !!(v || "").match(/@/) || "이메일 형식이 아닙니다.",
        lengthCheck: (len) => (v) =>
          (v || "").length >= len ||
          `${len}자 이상이어야합니다. 현재 ${v.length}자 입니다.`,
        password: (v) =>
          !!(v || "").match(
            /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*(_|[^\w])).+$/
          ) || "숫자, 영어 대소문자, 특수문자가 포함되어야합니다.",
      },
    };
  },
  methods: {
    login() {
      // 받아온 값이랑, 데이터 비교해야함.
      this.$store.state.loginDialog = false;
      this.$store.dispatch("login", this.loginData);
      this.reset();
    },
    reset() {
      this.loginData.email = "";
      this.loginData.password = "";
    },
  },
};
</script>

<style></style>
