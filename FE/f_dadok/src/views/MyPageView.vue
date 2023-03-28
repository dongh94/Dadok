<template>
  <div>
    <b-row style="margin: 0">
      <b-col class="d-flex justify-content-center" cols="3">
        <side-nav></side-nav>
      </b-col>
      <b-col cols="9">
        <my-info></my-info>
        <br />
        <target-list></target-list>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import SideNav from "@/components/common/SideNav.vue";
import TargetList from "@/components/mypage/TargetList.vue";

import MyInfo from "@/components/mypage/MyInfo.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "MakeGroupView",
  components: {
    TargetList,
    SideNav,
    MyInfo,
  },
  computed: {
    ...mapState(["loginUser"]),
  },
  methods: {
    ...mapActions(["checkUserInfo"]),
  },
  created() {
    this.checkUserInfo();
    // console.log(this.loginUser);
  },
};
</script>

<style></style>
