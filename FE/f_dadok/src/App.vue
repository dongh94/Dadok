<template>
  <div id="app" class="background" style="padding: 0">
    <header-nav v-if="$route.name !== 'VideoView'" />

    <router-view />
    <footer-nav v-if="$route.name !== 'VideoView'"></footer-nav>
  </div>
</template>

<script>
import HeaderNav from "@/components/common/HeaderNav.vue";
import FooterNav from "@/components/common/FooterNav.vue";
// import { mapGetters, mapState } from "vuex";
// import router from "@/router";
export default {
  components: { HeaderNav, FooterNav },
  // computed: {
  //   ...mapState(["isLogin"]),
  // },
  // methods: {
  //   ...mapGetters(["checkUserInfo"]),
  // },
  // created() {
  //   this.checkUserInfo();
  //   if (!this.isLogin) {
  //     router.push({ name: "StartView" });
  //   }
  // },
};
</script>

<style>
#app {
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-use-select: none;
  user-select: none;
}
.background {
  height: 100%;
  /* background-color: #fef7dc; */
}
/* @import url("https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800,900&display=swap"); */
@import url("https://fonts.googleapis.com/css2?family=Gugi&family=Hahmlet:wght@200&display=swap");
* {
  font-family: "Hahmlet", serif;
  letter-spacing: 1px;
}
</style>
