<template>
  <v-row justify="center">

    <v-dialog
      v-model="loginDialog"
      max-width="60%"
    >

      <v-container fluid style="height: 80vh;">
        <v-row style="height: 100%; text-align: center; background-color: rgba(0, 0, 0, 0.8)">
          <v-col cols=7>
            <img style="height: 100%; width: 100%;" :src="require('@/assets/test_main_right.jpg')" alt="">
          </v-col >
          <v-col cols=5 class="main-index-txt align-self-center">

            <form action="" style="margin-top: 0.2vh;">
              <v-text-field
                v-model="loginData.username"
                label="USER NAME"
                required
                clearable
                dark
                style="width: 70%;"
                class="mx-auto"
              ></v-text-field>
              <v-text-field
                v-model="loginData.password"
                label="PASSWORD"
                type="password"
                required
                clearable
                dark
                style="width: 70%;"
                class="mx-auto"
              ></v-text-field>
              <div>
                <v-btn class='primary pull-right' @click="clickOut()" style="width: 30%;">
                  <strong>접속하기!</strong></v-btn>
              </div>
            </form>
          </v-col>
        </v-row>

      </v-container>
    </v-dialog>

    <!-- <v-dialog
      v-model="loginDialog"
      max-width="290"
    >
      <v-card>
        <v-card-title class="headline">Use Google's location service?</v-card-title>

        <v-card-text>
          Let Google help apps determine location. This means sending anonymous location data to Google, even when no apps are running.
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn
            color="green darken-1"
            text
            @click="clickOut()"
          >
            Disagree
          </v-btn>

          <v-btn
            color="green darken-1"
            text
            @click="clickOut()"
          >
            Agree
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog> -->
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
        return this.$store.state.loginDialog
      },
      set (val) {
        this.$store.state.loginDialog = val
      }
    }

  },
  data() {
    return {
      loginData: {
        username: "",
        email: "",
        password: "",
      },

    }
  },
  methods: {
    clickOut() {
      this.$store.state.loginDialog = false
    }
  },
}
</script>

<style>

</style>