<template>
  <v-app-bar
    absolute
    color="transparent"
    class="px-10"
    flat
  >

    <v-tabs align-with-title>
      <v-tab router-link to="/">Index</v-tab>
      <v-tab router-link to="/photoeditor">Get started</v-tab>
      <v-tab router-link to="/webtoonindex">Best arts</v-tab>


    </v-tabs>

    <!-- <v-spacer></v-spacer> -->
    <v-tabs
      right
      hide-slider
      optional
      color="rgba(0, 0, 0, 0.54)"
    >
    <!-- v-tab--active 효과대매 color 는 그냥 rgba 박아넣음. signup이랑 login은 클릭했을대 변하길 원치않음. -->
      <v-tab router-link to='/mypage'>
        <v-avatar color="black" size="40">
          <v-icon color="white">mdi-account-circle</v-icon>
        </v-avatar>

      </v-tab>
      <v-tab @click="clickSingUp()">Sign Up</v-tab>
      <v-tab @click="clickLogin()">Log in</v-tab>

    </v-tabs>

    <LoginModal style="z-index: 999;"/> 
    <SignUpModal style="z-index: 999;"/>
  </v-app-bar>
</template>


<script>
import LoginModal from '@/components/login/LoginModal'
import SignUpModal from '@/components/login/SignUpModal'


export default {
  name: 'NavBar',
  components: {
    LoginModal,
    SignUpModal,
  },
  computed: {
  },
  data() {
    return {

    }
  },
  methods: {
    clickLogin() {
      this.$store.state.loginDialog = true
    },
    clickSingUp() {
      this.$store.state.signUpDialog = true
    }
  }

}
</script>

<style>

</style>