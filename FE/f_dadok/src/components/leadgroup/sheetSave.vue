<template>
  <div style="display: inline">
    &nbsp;
    <b-button v-b-tooltip.hover.top title="내서재로 보내기" class="m-btn m-btn-simple mx-3" style="background-color: #1a4d2e" @click="sheetToBook">
      <img src="@/assets/real_logo.png" style="width: 3rem" alt="" />
    </b-button>
  </div>
</template>

<script>
import { mapState, mapActions, mapGetters, mapMutations } from "vuex";
import http from "@/util/http-common.js";
export default {
  computed: {
    ...mapState(["meeting", "loginUser", "sheet"]),
  },
  methods: {
    ...mapGetters(["checkUserInfo"]),
    ...mapActions(["getMeetingInfo", "getUserInfo", "getSheetInfo"]),
    ...mapMutations(["CLEAR_SHEET"]),

    async sheetToBook() {
      await http.post("/sheet/complete/" + this.loginUser.id).then(() => {
        // console.log(res);
      });
      this.CLEAR_SHEET;
      this.createSheet(this.loginUser.id);
      this.$swal({
        title: "책 저장 완료 !",
        text: "서재에서 열람하세요",
        icon: "info",
        confirmButtonText: "확인",
        confirmButtonColor: "#1A4D2E",
      }).then(() => {
        this.$router.push({ name: "MyLibrary2View" });
      });
    },
    async createSheet(id) {
      await http.post("/sheet/" + id).then((response) => {
        // console.log(response);
        if (response.data === "success") {
          // console.log("성공");
          // this.$router.go();
        } else {
          // console.log(response);
          this.$swal({
            title: response.data,
            icon: "warning",
            confirmButtonText: "확인",
            confirmButtonColor: "#1A4D2E",
          });
        }
      });
    },
  },
};
</script>

<style>
.m-btn {
  position: relative;
  border: none;
  display: inline-block;
  padding: 10px 20px;
  border-radius: 15px;
  /* font-family: "paybooc-Light", sans-serif; */
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
  text-decoration: none;
  font-weight: 600;
  transition: 0.25s;
}
.m-btn:hover {
  letter-spacing: 2px;
  transform: scale(1.2);
  cursor: pointer;
  background-color: #f4ece1;
  color: black;
}
.m-btn:active {
  transform: scale(1.5);
}
.m-btn-simple {
  background-color: #f4ece1;
  color: black;
}
.m-btn-simple:hover {
  letter-spacing: 2px;
  transform: scale(1.2);
  cursor: pointer;
  background-color: #f4ece1;
  color: black;
}
</style>
