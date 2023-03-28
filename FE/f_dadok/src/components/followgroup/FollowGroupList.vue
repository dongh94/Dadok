<template>
  <div class="mt-4 container">
    <b-card class="shadow-lg p-3 mb-5 rounded" style="border-radius: 50px">
      <div class="d-flex my-2">
        <h2 class="me-2">가입한 모임 현황</h2>
      </div>
      <follow-group-item></follow-group-item>
    </b-card>
  </div>
</template>

<script>
import FollowGroupItem from "./FollowGroupItem.vue";

export default {
  components: { FollowGroupItem },
  name: "MemberList",
};
</script>

<style></style>
