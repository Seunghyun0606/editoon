<template>

  <tui-image-editor :include-ui="useDefaultUI" :options="options"></tui-image-editor>

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
}
</script>

<style>

/* .tui-image-editor-container {
  width: 50% !important;
  float: right;
} */

.tui-image-editor-header-logo {
  display: none !important;
}



</style>