<template>
  <div>
    <b-row style="margin: 0">
      <b-col class="d-flex justify-content-center" cols="3">
        <side-nav></side-nav>
      </b-col>
      <b-col cols="9">
        <follow-group-list></follow-group-list>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import SideNav from "@/components/common/SideNav.vue";
import { mapState, mapActions, mapGetters } from "vuex";
import FollowGroupList from "@/components/followgroup/FollowGroupList.vue";
export default {
  name: "FollowGroupView",
  components: { SideNav, FollowGroupList },
  computed: {
    ...mapState(["loginUser"]),
  },

  methods: {
    ...mapActions(["getFMeetings"]),
    ...mapGetters(["checkUserInfo"]),
  },
};
</script>

<style></style>
