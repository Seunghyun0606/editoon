<template>
  <v-app-bar
    absolute
    color="transparent"
    class="px-10"
    flat
  >

    <v-tabs align-with-title>
      <v-tab router-link to="/">Index</v-tab>
      <v-tab router-link to="/abs">Get started</v-tab>
      <v-tab router-link to="/webtoonindex">Best arts</v-tab>


    </v-tabs>

    <v-spacer></v-spacer>
    <v-tabs right hide-slider optional>
      <v-tab >Sign Up</v-tab>
      <v-tab @click="clickLogin()">Log in</v-tab>

    </v-tabs>

    <LoginModal /> 
  </v-app-bar>
</template>


<script>
import LoginModal from '@/components/login/LoginModal'
import { mapState } from 'vuex'


export default {
  name: 'NavBar',
  components: {
    LoginModal
  },
  computed: {
    ...mapState([
      'loginDialog'
    ]),
  },
  data() {
    return {

    }
  },
  methods: {
    clickLogin() {
      this.$store.state.loginDialog = true
    }
  }

}
</script>

<style>

</style>