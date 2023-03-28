<template>
  <div>
    <b-navbar class="shadow" toggleable="lg" type="light" v-if="checkUserInfo" style="background-color: #f4ece1; padding: 0">
      <b-navbar-brand :to="{ name: 'HomeView' }" id="font" style="font-size: 2rem"
        >&nbsp;&nbsp;&nbsp;
        <img src="@/assets/real_logo.png" alt="main_logo" style="width: 4rem" class="mb-2" />
        <strong> 다독 </strong>
      </b-navbar-brand>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto" style="font-size: 1.3rem">
          <!-- <b-nav-item :to="{ name: 'HomeView' }"> <b-icon icon="journal-bookmark-fill"></b-icon>&nbsp;&nbsp; 표지&nbsp;&nbsp; </b-nav-item> -->

          <b-nav-item :to="{ name: 'MakeGroupView' }"> <b-icon icon="people"></b-icon>&nbsp;&nbsp;모임 만들기&nbsp;&nbsp;</b-nav-item>
          <b-nav-item :to="{ name: 'SearchGroupView' }"> <b-icon icon="search"></b-icon>&nbsp;&nbsp;모임 찾기&nbsp;&nbsp;</b-nav-item>
          <b-nav-item-dropdown right>
            <!-- Using 'button-content' slot -->

            <template #button-content> <b-icon icon="info" font-scale="1.5"></b-icon>내 정보&nbsp;&nbsp; </template>
            <b-dropdown-item :to="{ name: 'MyPageView' }"><b-icon icon="pencil-fill" font-scale="1"></b-icon>&nbsp;&nbsp;내 기록 </b-dropdown-item>
            <b-dropdown-item :to="{ name: 'MypageLibraryEx' }"><b-icon icon="book" font-scale="1"></b-icon>&nbsp;&nbsp;내 서재 </b-dropdown-item>
            <b-dropdown-item :to="{ name: 'LeadGroupView' }"> <b-icon icon="chat-right-text-fill" font-scale="1"></b-icon>&nbsp;&nbsp;개최한 모임 </b-dropdown-item>
            <b-dropdown-item :to="{ name: 'FollowGroupView' }"><b-icon icon="chat-left-text" font-scale="1"></b-icon>&nbsp;&nbsp;참여한 모임</b-dropdown-item>
            <b-dropdown-item @click="pushlogout"><b-icon icon="box-arrow-right" font-scale="1"></b-icon>&nbsp;&nbsp;로그아웃</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapState } from "vuex";
export default {
  methods: {
    ...mapActions(["logout"]),
    pushlogout() {
      // if (confirm("정말 로그아웃 하시겠습니까?")) {
      //   this.logout();
      // }
      this.$swal({
        title: "로그아웃",
        text: "정말 로그아웃 하시겠습니까 ?",
        icon: "info",
        showCancelButton: true,
        confirmButtonText: "네",
        confirmButtonColor: "#1A4D2E",
        cancelButtonText: "아니오",
      }).then((result) => {
        if (result.isConfirmed) {
          this.logout();
        }
      });
    },
  },
  computed: {
    ...mapGetters(["checkUserInfo"]),
    ...mapState(["UserName"]),
  },
};
</script>

<style></style>
