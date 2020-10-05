<template>

  <v-container>
    <v-row>
      <v-col class="text-h3">
        내 정보 수정
      </v-col>
    </v-row>

    <v-row class="mt-10">
      <v-col cols=12 style="text-algin: center;">
        <v-avatar style="width: 200px; height: 200px;">
          <img
            style="width: 200px; height: 200px;"
            :src="'https://j3b308.p.ssafy.io/image/profileImg/' + `${userInfo.image}`"
            alt="프로필사진"
          >
        </v-avatar>
      </v-col>
      <v-col>
        <v-file-input
          id="putAvatar"
          accept="image/png, image/jpeg, image/bmp"
          placeholder="Pick an avatar"
          prepend-icon="mdi-camera"
          label="Avatar"
        ></v-file-input>
      </v-col>
    </v-row>

    <v-text-field
      :value="userInfo.email"
      label="e-mail"
      readonly
    ></v-text-field>


    <v-form
      ref="form"
      v-model="valid"
      lazy-validation
    >
      <v-text-field
        v-model="userInfo.name"
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
        @click="changeInfo"
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
        @click="deleteUser"
      >
        탈퇴하기
      </v-btn>


    </v-form>
    <MyPageChangePassword/>
    <MyPageDeleteUser/>
  
  </v-container>  

</template>

<script>
import MyPageChangePassword from '@/components/mypage/MyPageChangePassword'
import MyPageDeleteUser from '@/components/mypage/MyPageDeleteUser'
import { mapState } from 'vuex'


export default {
  name: "MyPageInfo",
  components: {
    MyPageChangePassword,
    MyPageDeleteUser,
  },
  computed: {
    ...mapState(['userInfo'])
  },
  data() {
    return {
      valid: true,
      nameRules: [
        v => !!v || '이름이 필요합니다',
        v => (v && v.length <= 10) || '10글자 미만이어야합니다.',
      ],

    }

  },
  methods: {
    changeInfo() {
      const avatar = document.querySelector("#putAvatar")
      let changeInfo = {}
      console.log(avatar.files)
      changeInfo.name = this.userInfo.name
      changeInfo.email = this.userInfo.email
      changeInfo.image = this.userInfo.image
      if ( this.userInfo.image === 'default.jpg' ) {
        if ( avatar.files.length > 0 ) {
          changeInfo.multipartFile = avatar.files[0]
          changeInfo.isChange = 'yes'  
        }
        else {
          changeInfo.isChange = 'no'
        }
      }
      else {
        // default 값이 아니면서 아바타를 수정하는 경우
        if ( avatar.files.length > 0 ) {
          changeInfo.multipartFile = avatar.files[0]
          changeInfo.isChange = 'yes'  
        }
        else {
          changeInfo.isChange = 'yes'
        }
      }
      console.log(4, changeInfo)
      this.$store.dispatch('changeUserInfo', changeInfo)

    },
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
    },
    deleteUser() {
      this.$store.state.deleteUserDialog = true
    }
  },

}
</script>

<style scoped>

.text-h5 {
  margin: 40px 0px;
}

</style>