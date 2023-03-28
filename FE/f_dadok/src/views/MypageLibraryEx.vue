<template>
  <div id="librarybox">
    <b-row style="margin: 0">
      <b-col class="d-flex justify-content-center" cols="3">
        <side-nav></side-nav>
      </b-col>
      <b-col cols="9" id="back">
        <div><library-v-2></library-v-2></div>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import SideNav from "@/components/common/SideNav.vue";
import LibraryV2 from "@/components/mypage/LibraryV2.vue";

export default {
  name: "MypageLibraryEx",
  components: { SideNav, LibraryV2 },
  created() {
    // console.log("시작세션값 : " + this.$route.params.session);
  },
  mounted() {
    if (this.$route.params.session == 0) {
      this.$route.params.session = 1;
      this.$router.go();
      // this.$swal({
      //   title: "모임세션이 종료되었습니다.",
      //   icon: "info",
      //   confirmButtonText: "확인",
      //   confirmButtonColor: "#1A4D2E",
      // }).then(() => {
      //   console.log("확인");
      //   this.$router.go();
      // });
    }
  },
};
</script>

<style>
#librarybox {
  min-width: 1400px;
  max-width: 1400px;
}
#back2 {
  background-image: url(@/images/tree3.jpg);
}
</style>
