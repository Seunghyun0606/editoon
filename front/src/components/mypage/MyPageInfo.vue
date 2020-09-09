<template>

  <v-container>
    <v-row>
      <v-col class="text-h3">
        내 정보 수정
      </v-col>
    </v-row>

    <v-row class="mt-10">
      <v-col>
        <v-avatar>
          <img
            src="https://cdn.vuetifyjs.com/images/john.jpg"
            alt="John"
          >
        </v-avatar>
      </v-col>
      <v-col cols=10>
        <v-file-input
          accept="image/png, image/jpeg, image/bmp"
          placeholder="Pick an avatar"
          prepend-icon="mdi-camera"
          label="Avatar"
        ></v-file-input>
      </v-col>
    </v-row>

    <v-text-field
      value="abcdedf@gamil.com"
      label="e-mail"
      readonly
    ></v-text-field>


    <v-form
      ref="form"
      v-model="valid"
      lazy-validation
    >
      <v-text-field
        v-model="name"
        :counter="10"
        :rules="nameRules"
        label="Name"
        required
      ></v-text-field>

      <!-- <v-text-field
        v-model="password"
        :rules="passwordRules"
        label="Password"
        type="password"
        required
      ></v-text-field> -->


      <v-btn
        :disabled="!valid"
        color="success"
        class="mr-4"
        @click="validate"
      >
        변경하기
      </v-btn>

      <v-btn
        color="warning"
        class="mr-4"
        @click="changePassword"
      >
        비밀번호 변경
      </v-btn>

      <v-btn
        color="error"
        class="mr-4"
        
      >
        탈퇴하기
      </v-btn>


    </v-form>
    <MyPageChangePassword/>
  
  </v-container>  

</template>

<script>
import MyPageChangePassword from '@/components/mypage/MyPageChangePassword'

export default {
  name: "MyPageInfo",
  components: {
    MyPageChangePassword
  },
  data() {
    return {
      valid: true,
      name: '',
      nameRules: [
        v => !!v || 'Name is required',
        v => (v && v.length <= 10) || 'Name must be less than 10 characters',
      ],
      email: '',
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
      ],
      password: '',
      passwordRules: [
        v => !!v || 'Password is required',
        v => (v && v.length <= 10) || 'Password must be less than 10 characters',
      ],

    }

  },
  methods: {
    validate () {
      this.$refs.form.validate()
    },
    reset () {
      this.$refs.form.reset()
    },
    resetValidation () {
      this.$refs.form.resetValidation()
    },
    changePassword() {
      this.$store.state.changePasswordDialog = true
    }
  },

}
</script>

<style scoped>

.text-h5 {
  margin: 40px 0px;
}

</style>