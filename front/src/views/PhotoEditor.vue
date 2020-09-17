<template>
    <v-container fluid>
      <v-row style="height: calc(100vh - 64px); border-top: 1px solid black;">
        <v-col cols="2" style="border-right: 1px solid black; ">
          프레임
        </v-col>
        <v-col cols="4">
          webtoon 들어갈 공간.
        </v-col>
        <v-col cols="6">
          <tui-image-editor :include-ui="useDefaultUI" :options="options"></tui-image-editor>

        </v-col>

      </v-row>

    </v-container>




</template>

<script>
import { ImageEditor } from '@toast-ui/vue-image-editor';
import 'tui-image-editor/dist/svg/icon-a.svg';
import 'tui-image-editor/dist/svg/icon-b.svg';
import 'tui-image-editor/dist/svg/icon-c.svg';
import 'tui-image-editor/dist/svg/icon-d.svg';
import 'tui-image-editor/dist/tui-image-editor.css';

export default {
  components: {
      'tui-image-editor': ImageEditor
  },
  data() {
    return {
      useDefaultUI: true,
      options: { // for tui-image-editor component's "options" prop
        includeUI: {
          cssMaxWidth: 700,
          cssMaxHeight: 500
        }
      }
    }
  },
  methods: {
    isIndex() {
      this.$store.commit('isIndex', false)
    },
    isNotEditor() {
      this.$store.commit("isNotEditor", false);
    },
  },
  created() {
    this.isIndex()
    this.isNotEditor()
  }
}
</script>

<style>

.tui-image-editor-container {
  /* width: 50% !important; */

}

.tui-image-editor-header-logo {
  display: none !important;
}



</style>