<template>
  <div class="sidediv">
    <b-card class="shadow-lg p-3 mb-5 bg-body rounded">
      <div class="itemdiv">
        <router-link :to="{ name: 'MyPageView' }" style="font-size: 1.5rem; color: black">
          <strong> <b-icon icon="pencil-fill" font-scale="1"></b-icon>&nbsp;&nbsp;내기록</strong></router-link
        >
      </div>
      <div class="itemdiv">
        <router-link :to="{ name: 'MypageLibraryEx' }" style="font-size: 1.5rem; color: black">
          <strong> <b-icon icon="book" font-scale="1"></b-icon>&nbsp;&nbsp;내서재</strong></router-link
        >
      </div>
      <div class="itemdiv">
        <router-link :to="{ name: 'LeadGroupView' }" style="font-size: 1.5rem; color: black">
          <strong> <b-icon icon="chat-right-text-fill" font-scale="1"></b-icon>&nbsp;&nbsp;개최한 모임</strong>
        </router-link>
      </div>
      <div class="itemdiv">
        <router-link :to="{ name: 'FollowGroupView' }" style="font-size: 1.5rem; color: black">
          <strong> <b-icon icon="chat-left-text" font-scale="1"></b-icon>&nbsp;&nbsp;참여한 모임</strong>
        </router-link>
      </div>
    </b-card>
  </div>
</template>

<script>
export default {};
</script>

<style>
.sidediv {
  position: sticky;
  margin: 1.5rem;
  min-width: 240px;
}
.itemdiv {
  margin-bottom: 3rem;
}
</style>
