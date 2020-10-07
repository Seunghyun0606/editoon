<template>
  <div class="text-center">
    <v-dialog
      v-model="checkDialog"
      width="500"
    >
      <v-container
        class="pa-10 object-option"
        style="background-color: rgba(0,0,20,0.9);"
      >
        <div>
          Check your Tip
        </div>

        <v-carousel :show-arrows="false">
          <v-carousel-item
            v-for="(item,i) in items"
            :key="i"
            :src="item.src"
          ></v-carousel-item>
        </v-carousel>



      </v-container>

    </v-dialog>
  </div>
</template>

<script>

export default {
  name: 'PhotoEditorToolTipModal',
  components: {
  },
  computed: {
    checkDialog: {
      get() {
        return this.$store.state.photoToolTipDialog
      },
      set(val) {
        this.$store.commit("photoToolTipDialogInit",  val)
        // this.reset()
      }
    },
  },
  data () {
    return {
      items: [
        {
          src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
        },
        {
          src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
        },
        {
          src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',
        },
        {
          src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
        },
      ],

    }
  },
  methods: {
  },
}
</script>

<style>

</style>