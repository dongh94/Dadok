<template>
  <div class="mt-4 container">
    <b-card class="shadow-lg p-3 mb-5 rounded" style="border-radius: 50px">
      <div class="d-flex my-2">
        <h2 class="me-2">회원현황 [{{ meeting.capacity }}/{{ meeting.people }}]</h2>
      </div>
      <!-- {{ meeting }} -->
      <member-item></member-item>
    </b-card>
  </div>
</template>

<script>
import MemberItem from "./MemberItem.vue";
import { mapState } from "vuex";
export default {
  name: "MemberList",
  components: { MemberItem },
  computed: {
    ...mapState(["members", "meeting"]),
  },
};
</script>

<style></style>
