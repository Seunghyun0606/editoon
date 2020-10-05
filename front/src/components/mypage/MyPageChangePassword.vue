<template>
  <v-row justify="center">

    <v-dialog
      v-model="changePasswordDialog"
      max-width="30%"
    >

      <v-container fluid style="height: 50vh;">
        <v-row style="height: 100%; text-align: center; background-color: rgba(0, 0, 0, 0.8)">
          <v-col class="main-index-txt align-self-center">

            <form action="" style="margin-top: 0.2vh;">
              <v-text-field
                v-model="passwordSet.password"
                label="Password"
                type="password"
                :rules="[passwordRules.password, passwordRules.lengthCheck(10)]"
                required
                clearable
                dark
                style="width: 70%;"
                class="mx-auto"
              ></v-text-field>
              <v-text-field
                v-model="passwordCheck"
                label="Password 확인"
                type="password"
                :rules="[passwordRules.passwordCheck]"
                required
                clearable
                dark
                style="width: 70%;"
                class="mx-auto"
              ></v-text-field>
              <v-text-field
                v-if="canShowNewPassword && passwordCheck === passwordSet.password"
                v-model="passwordSet.newPassword"
                label="새 비밀번호 입력"
                type="password"
                :rules="[passwordRules.newPasswordCheck]"
                required
                clearable
                dark
                style="width: 70%;"
                class="mx-auto"
              ></v-text-field>
              <v-btn v-if="canShowNewPassword && passwordCheck === passwordSet.password"
                class='warning mr-4'
                @click="changePassword()"
              >
                <strong>비밀번호변경하기</strong>
              </v-btn>
              <v-btn class='' @click="clickOut()" >
                <strong>돌아가기</strong>
              </v-btn>
            </form>
          </v-col>
        </v-row>

      </v-container>
    </v-dialog>

  </v-row>
</template>

<script>
// import { mapState } from 'vuex'

export default {
  name: "MyPageChangePassword",
  computed: {
    // modal을 이용할때, v-model을 사용하는 것이 편하기도하고, 실제로 v-dialog에 v-model에
    // emit 이벤트로 click.outside 의 경우 false를 보내서 v-model에 보내준다.
    // 그런데 나같은 경우는 지금 store를 이용하다보니, setter가 없다. v-model을 걸어서 반환해줄부분이없다.
    // 따라서, computed에서 getter는 store에서 가져오고,
    // 새롭게 변하는 값은 setter에서 받아서 store의 값을바꿔준다.
    // https://vuex.vuejs.org/kr/guide/forms.html 참고.
    changePasswordDialog: {
      get() {
        return this.$store.state.changePasswordDialog
      },
      set (val) {
        this.$store.state.changePasswordDialog = val
        this.resetValidation()
      }
    },
    canShowNewPassword() {
      return this.passwordSet.password.length > 9 ? true : false
    },

  },
  data() {
    return {
      passwordSet: {
        password: "",
        newPassword: "",
      },
      passwordCheck: "",
      passwordRules: {
        password: v => !!(v || '').match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*(_|[^\w])).+$/) || '숫자, 영어 대소문자, 특수문자가 포함되어야합니다.',
        passwordCheck: v => !!( v === this.passwordSet.password ) || '비밀번호가 맞지 않습니다.',
        newPasswordCheck: v => !!(v || '').match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*(_|[^\w])).+$/) || '숫자, 영어 대소문자, 특수문자가 포함되어야합니다.',
        lengthCheck: len => v => (v || '').length >= len || `${len}자 이상이어야합니다. 현재 ${v.length}자 입니다.`,
      },

    }
  },
  methods: {
    changePassword() {
      let changeInfo = {}
      changeInfo['email'] = this.$store.state.userInfo.email
      Object.assign(changeInfo, this.passwordSet)
      this.$store.dispatch('changePassword', changeInfo)
    },
    clickOut() {
      this.$store.state.changePasswordDialog = false
      this.resetValidation()
    },
    resetValidation() {
      this.passwordSet.password = ""
      this.passwordSet.newPassword = ""
      this.passwordCheck = ""
    }
  },
}
</script>

<style>

</style>