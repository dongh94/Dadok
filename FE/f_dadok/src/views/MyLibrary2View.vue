<template>
  <div>
    <b-row style="margin: 0">
      <b-col class="d-flex justify-content-center" cols="3">
        <side-nav></side-nav>
      </b-col>
      <b-col cols="9">
        <library-v-2></library-v-2>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import SideNav from "@/components/common/SideNav.vue";
import LibraryV2 from "@/components/mypage/LibraryV2.vue";

export default {
  name: "MyLibrary2View",
  components: { SideNav, LibraryV2 },
};
</script>

<style></style>
