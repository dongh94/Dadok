<template>
  <div class="mt-4 container">
    <b-card class="shadow-lg p-3 mb-5 rounded" style="border-radius: 50px">
      <div class="d-flex my-2">
        <h2 class="me-2">대기중</h2>
        <b-spinner class="mx-1 mt-1"></b-spinner>
      </div>
      <waitng-item></waitng-item>
    </b-card>
  </div>
</template>

<script>
import WaitngItem from "./WaitngItem.vue";
export default {
  name: "WaitingList",
  components: { WaitngItem },
};
</script>

<style></style>
