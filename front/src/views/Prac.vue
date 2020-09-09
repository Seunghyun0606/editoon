<template>
  <VueCompareImage :leftImage="require('@/assets/1-1.jpg')" :rightImage="require('@/assets/1-2.jpg')" />

</template>

<script>
import VueCompareImage from 'vue-compare-image';

export default {
  name: 'Prac',
  components: {
    VueCompareImage 
  },
  data() {
    return {

    }
  }
}
</script>

<style>

</style>