<template>
  <v-row justify="center">

    <v-dialog
      v-model="deleteUserDialog"
      max-width="30%"
    >

      <v-container fluid style="height: 50vh;">
        <v-row style="height: 100%; text-align: center; background-color: rgba(0, 0, 0, 0.8)">
          <v-col class="align-self-center">
            <div style="color: white; font-size: 1em; font-weight: 900;" class="mb-10">
              ※ 한번 지우면 되돌릴 수 없습니다.
            </div>

            <v-form
              v-model="valid"
              action=""
              style="margin-top: 0.2vh;"
            >
              <v-text-field
                v-model="userInfo.email"
                label="Email 입력"
                type="email"
                :rules="[rules.email]"
                required
                clearable
                dark
                style="width: 70%;"
                class="mx-auto"
              ></v-text-field>
              <v-text-field
                v-model="userInfo.password"
                label="Password 입력"
                type="password"
                :rules="[rules.password, rules.lengthCheck(10)]"
                required
                clearable
                dark
                style="width: 70%;"
                class="mx-auto"
              ></v-text-field>
              <v-btn
                :disabled="!valid"
                class='error mr-4'
                @click="deleteUser()"
              >
                <strong>삭제하기</strong>
              </v-btn>
              <v-btn class='' @click="clickOut()" >
                <strong>돌아가기</strong>
              </v-btn>
            </v-form>
          </v-col>
        </v-row>

      </v-container>
    </v-dialog>

  </v-row>
</template>

<script>

export default {
  name: "MyPageDeleteUser",
  computed: {
    // modal을 이용할때, v-model을 사용하는 것이 편하기도하고, 실제로 v-dialog에 v-model에
    // emit 이벤트로 click.outside 의 경우 false를 보내서 v-model에 보내준다.
    // 그런데 나같은 경우는 지금 store를 이용하다보니, setter가 없다. v-model을 걸어서 반환해줄부분이없다.
    // 따라서, computed에서 getter는 store에서 가져오고,
    // 새롭게 변하는 값은 setter에서 받아서 store의 값을바꿔준다.
    // https://vuex.vuejs.org/kr/guide/forms.html 참고.
    deleteUserDialog: {
      get() {
        return this.$store.state.deleteUserDialog
      },
      set (val) {
        this.$store.state.deleteUserDialog = val
        this.resetValidation()
      }
    },

  },
  data() {
    return {
      valid: true,
      userInfo: {
        email: '',
        password: '',
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
      }
    }
  },
  methods: {
    deleteUser() {
      const check = this.$store.dispatch('deleteUser', this.userInfo)
      if (check) {
        this.$router.push('/')
      }

    },
    clickOut() {
      this.$store.state.deleteUserDialog = false
      this.resetValidation()
    },
    resetValidation() {
      this.userInfo.password = ""
      this.userInfo.email = ""
    }
  },
}
</script>

<style>

</style>