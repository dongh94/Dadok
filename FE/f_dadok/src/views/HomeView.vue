<template>
  <div class="back">
    <!-- <main-test></main-test> -->
    <home-banner></home-banner>
    <home-book></home-book>
  </div>
</template>

<script>
// @ is an alias to /src
import HomeBanner from "@/components/home/HomeBanner.vue";
import HomeBook from "@/components/home/HomeBook.vue";
import { mapGetters, mapState } from "vuex";
import router from "@/router";
// import axios from "axios";

// import MainTest from "@/components/start/MainTest.vue";

export default {
  name: "HomeView",
  components: {
    HomeBanner,
    HomeBook,

    // MainTest,
  },
  computed: {
    ...mapState(["isLogin", "loginUser"]),
  },
  methods: {
    ...mapGetters(["checkUserInfo"]),
    // onTest() {
    //   axios({
    //     url: `https://openapi.naver.com/v1/search/book.json`,
    //     method: "get",
    //     headers: {
    //       "X-Naver-Client-Id": "Dt_0v7XCqxdpyz3ITJiX",
    //       "X-Naver-Client-Secret": "cofYo5OHiT",
    //     },
    //     params: { query: "abc" },
    //   }).then((res) => {
    //     console.log(res.data);
    //   });
    // },
  },
  created() {
    this.checkUserInfo();
    if (!this.isLogin) {
      router.push({ name: "StartView" });
      sessionStorage.clear();
    }
  },
};
</script>
<style>
/* .back {
  background-color: #fef7dc;
} */
</style>
