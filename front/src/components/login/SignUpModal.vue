<template>
  <v-row justify="center">
    <v-dialog v-model="signUpDialog" max-width="60%">
      <v-container fluid style="height: 80vh;">
        <v-row style="height: 100%; text-align: center; background-color: rgba(0, 0, 0, 0.8)">
          <v-col cols="7">
            <img
              style="height: 100%; width: 100%;"
              :src="require('@/assets/account_signup.png')"
              alt
            />
          </v-col>
          <v-col cols="5" class="main-index-txt align-self-center">
            <div style="color: white;">회원가입</div>
            <v-form
              ref="form"
              style="margin-top: 0.2vh;"
            >

              <!-- 이메일 확인 -->
              <v-text-field
                v-model="signUpData.email"
                label="e-mail"
                :rules="[rules.email]"
                :readonly="validation.validateCode"
                required
                clearable                
                dark
                style="width: 70%;"
                class="mx-auto"
              ></v-text-field>

              <!-- 이메일 코드인증 -->
              <v-text-field
                v-if="validation.sendEmail"
                v-model="signUpData.code"
                label="Security Code"
                :rules="[rules.lengthCheck(6)]"
                :readonly="validation.validateCode"
                required
                clearable
                dark
                style="width: 70%;"
                class="mx-auto"
                
              ></v-text-field>
              
              <!-- 패스워드 검증 -->
              <v-text-field
                v-if="validation.validateCode"
                v-model="signUpData.password"
                label="password"
                type="password"
                :rules="[rules.password, rules.lengthCheck(10)]"
                required
                clearable
                dark
                style="width: 70%;"
                class="mx-auto"
              ></v-text-field>
              <v-text-field
                v-if="validation.validateCode"
                v-model="validation.passwordCheck"
                label="password 확인"
                type="password"
                :rules="[rules.passwordCheck]"
                required
                clearable
                dark
                style="width: 70%;"
                class="mx-auto"
              ></v-text-field>
              <div v-if="!validation.validateCode">
                <v-btn v-if="!validation.sendEmail" slot="" class="primary" @click="sendEmail()" style="" >
                  <strong>인증 메일 보내기!</strong>
                </v-btn>
                <div v-else>
                  <v-btn class="primary" @click="sendEmail()" style="">
                    <strong>재전송</strong>
                  </v-btn>
                  <v-btn class="primary ml-5" @click="verificateCode()" style="">
                    <strong>확인</strong>
                  </v-btn>

                </div>

              </div>
              <div v-else>
                <v-btn class="primary " @click="signUp()" style="">
                  <strong>가입하기!</strong>
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
// import axios from 'axios'

export default {
  name: "SignUpModal",
  computed: {
    // modal을 이용할때, v-model을 사용하는 것이 편하기도하고, 실제로 v-dialog에 v-model에
    // emit 이벤트로 click.outside 의 경우 false를 보내서 v-model에 보내준다.
    // 그런데 나같은 경우는 지금 store를 이용하다보니, setter가 없다. v-model을 걸어서 반환해줄부분이없다.
    // 따라서, computed에서 getter는 store에서 가져오고,
    // 새롭게 변하는 값은 setter에서 받아서 store의 값을바꿔준다.
    // https://vuex.vuejs.org/kr/guide/forms.html 참고.
    signUpDialog: {
      get() {
        return this.$store.state.signUpDialog;
      },
      set(val) {
        this.$store.state.signUpDialog = val;
        this.init()
      },
    },

  },
  data() {
    return {
      signUpData: {
        email: "",
        password: "",
        code: "",
      },
      validation: {
        passwordCheck: "",
        sendEmail: false,
        validateCode: false,
      },
      verificationCode: "",
      rules: {
        email: v => !!(v || '').match(/@/) || '이메일 형식이 아닙니다.',
        lengthCheck: len => v => (v || '').length >= len || `${len}자 이상이어야합니다. 현재 ${v.length}자 입니다.`,
        password: v => !!(v || '').match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*(_|[^\w])).+$/) ||
          '숫자, 영어 대소문자, 특수문자가 포함되어야합니다.',
        // required: v => !!v || 'This field is required',
        passwordCheck: v => !!( v === this.validation.password ) || '비밀번호가 맞지 않습니다.',
      },  
    };
  },
  methods: {
    signUp() {
      this.$store.state.signUpDialog = false;
      // this.$store.dispatch('signUp', this.signUpData)
    },
    resetValidation () {
      this.$refs.form.reset()
    },
    verificateCode() {
      if ( this.verificateCode === this.signUpData.code ) {
        this.validation.validateCode = true
      }
      else {
        // validation할까?
        alert('틀립니다')
        this.validation.validateCode = false
      }
      this.validation.validateCode = true

    },
    sendEmail() {

      this.validation.sendEmail = true
      // this.$store.dispatch('sendValidationEmail', this.signUpData.email)
      // 여기서 처리할지 store에서 처리할지 고민해봐야할듯.
      // axios.get('backend/email', '?')
      //   .then(res => {
      //     return res
      //   })
      //   .catch(err => {
      //     return err
      //   })
    },
    init() {
      this.resetValidation()
      this.validation.sendEmail = false
      this.validation.validateCode = false
      
    }
  },

};
</script>

<style>
</style>