<template>
  <div>
    <b-row style="margin: 0">
      <b-col class="d-flex justify-content-center" cols="3">
        <side-nav></side-nav>
      </b-col>
      <b-col cols="9" v-if="loginUser.isCreate === 1">
        <made-group></made-group>

        <waiting-list></waiting-list>

        <member-list></member-list>
      </b-col>
      <b-col cols="9" v-else>
        <div class="container" style="margin-top: 10rem">
          <div class="d-flex justify-content-center" style="font-size: 5rem">
            <b-icon
              icon="emoji-dizzy
"
            ></b-icon>
          </div>
          <br />
          <div class="d-flex justify-content-center" style="font-size: 2rem">아직 개최한 모임이 없습니다..</div>
        </div>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import SideNav from "@/components/common/SideNav.vue";
import { mapState, mapActions, mapGetters } from "vuex";
import MadeGroup from "@/components/leadgroup/MadeGroup.vue";
import WaitingList from "@/components/leadgroup/WaitingList.vue";
import MemberList from "@/components/leadgroup/MemberList.vue";
export default {
  name: "LeadGroupView",
  components: { SideNav, MadeGroup, WaitingList, MemberList },

  computed: {
    ...mapState(["loginUser", "meeting"]),
  },
  mounted() {
    if (this.$route.params.sense == 0) {
      // console.log("세션 : " + this.$route.params.sense);
      this.$route.params.sense = 1;
      this.$router.go();
    }
  },
  methods: {
    ...mapActions(["getApplies", "getMembers", "getUserInfo"]),
    ...mapGetters(["checkUserInfo"]),
    test11() {
      // console.log(this.meeting);
      // console.log(this.loginUser);
    },
  },
  created() {
    const token = sessionStorage.getItem("access-token");
    this.getUserInfo(token);
    this.getApplies(this.loginUser.id);
    this.getMembers(this.loginUser.id);
  },
};
</script>

<style></style>
