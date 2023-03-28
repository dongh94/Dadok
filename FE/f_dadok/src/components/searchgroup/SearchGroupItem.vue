<template>
  <div class="item">
    <b-row>
      <div class="hover14">
        <div>
          <router-link
            :to="{
              name: 'SearchGroupDetail',
              params: { meetingId: meeting.id },
            }"
            ><figure class="image-box">
              <!-- <img alt="기획전 이미지" style="display: inline" src="@/assets/advert1-2.jpg" class="image-thumbnail" /> -->
              <img :src="`${meeting.img}`" alt="" style="display: inline" height="300" width="200" class="image-thumbnail" v-if="`${meeting.img}`" />
              <img :src="require(`@/assets/${meeting.interest}.jpg`)" style="width: 100%; height: 100%" v-else />
            </figure>
          </router-link>
        </div>
        <div class="mt-3" style="height: 3rem; font-size: 20px">
          {{ meeting.name }}
        </div>

        <div class="item-summary">
          <p>
            <strong><span style="background-color: #f4ece1; color: black">&nbsp;모임 유형&nbsp;</span></strong>
            {{ meeting.theme }}
          </p>
        </div>
        <div class="item-summary">
          <p>
            <strong><span style="background-color: #f4ece1; color: black">&nbsp;관심 분야&nbsp;</span></strong>
            {{ meeting.interest }}
          </p>
        </div>
        <div class="item-summary">
          <p>
            <strong><span style="background-color: #f4ece1; color: black">&nbsp;시작일&nbsp;</span></strong>
            {{ meeting.startDay }}
          </p>
        </div>
      </div>
    </b-row>
  </div>
</template>

<script>
export default {
  name: "SearchGroupItem",
  props: {
    meeting: {
      type: Object,
    },
  },
};
</script>

<style>
/* #itemback {
  background-color: white;
  padding: 5%;
  border-radius: 5%;
  width: 400px;
  height: 100%;
  margin: 20px;
  font-size: 0.5rem;
} */
.item {
  width: 350px;
  height: 480px;
  margin-bottom: 1rem;
}
.item-overlay {
  opacity: 0;
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  background-color: rgba(255, 255, 255, 0.7);
  color: #333;
  transition: 0.2s;
}
.shop-item {
  text-align: center;
  float: left;
}
.item-summary {
  font-size: 17px;
}
.item-summary > p {
  margin-bottom: 12px;
}
.image-box {
  width: 310px;
  height: 300px;
  overflow: hidden;
  margin: 0 auto;
}

.image-thumbnail {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

/* Shine */
.hover14 figure {
  position: relative;
}
.hover14 figure::before {
  position: absolute;
  top: 0;
  left: -75%;
  z-index: 2;
  display: block;
  content: "";
  width: 50%;
  height: 100%;
  background: -webkit-linear-gradient(left, rgba(255, 255, 255, 0) 0%, rgba(255, 255, 255, 0.3) 100%);
  background: linear-gradient(to right, rgba(255, 255, 255, 0) 0%, rgba(255, 255, 255, 0.3) 100%);
  -webkit-transform: skewX(-25deg);
  transform: skewX(-25deg);
}
.hover14 figure:hover::before {
  -webkit-animation: shine 0.75s;
  animation: shine 0.75s;
}
@-webkit-keyframes shine {
  100% {
    left: 125%;
  }
}
@keyframes shine {
  100% {
    left: 125%;
  }
}
</style>
