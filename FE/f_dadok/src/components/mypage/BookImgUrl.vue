<template>
  <span><img :src="bookimgurl" alt="" width="100%" height="100%" /></span>
</template>

<script>
export default {
  name: "BookImgUrl",
  props: {
    bookimgurl: {
      type: String,
    },
  },
  created() {
    // console.log(this.bookimgurl);
  },
};
</script>

<style></style>
