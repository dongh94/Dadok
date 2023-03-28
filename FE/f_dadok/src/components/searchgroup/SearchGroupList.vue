<template>
  <div class="row">
    <search-group-item v-for="meeting in meetings" :key="meeting.id" :meeting="meeting" class="col-lg-4 col-md-5"></search-group-item>
  </div>
</template>

<script>
import SearchGroupItem from "@/components/searchgroup/SearchGroupItem.vue";
import { mapState } from "vuex";
export default {
  name: "SearchGroupList",

  components: { SearchGroupItem },
  computed: {
    ...mapState(["meetings"]),
  },
};
</script>

<style></style>
