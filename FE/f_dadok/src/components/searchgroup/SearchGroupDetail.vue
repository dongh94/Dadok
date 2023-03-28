<template>
  <div class="row" style="margin: 0; margin-top: 2rem">
    <!-- <test-com></test-com> -->
    <div class="col-2 sideleft"></div>

    <div class="col-8" style="margin-top: 2rem">
      <div class="my-2 d-flex justify-content-between">
        <h2 class="mname space1">{{ detailmeeting.name }}</h2>
        <b-button size="lg" type="button" class="g-btn w-btn-green" style="color: white; margin-bottom: 0.2rem; font-size: 1.1rem" @click="onApply"> 지금 바로 신청하기 </b-button>
      </div>

      <div class="row">
        <!-- {{ meeting }} -->
        <!-- <img class="img-fluid mt-2" src="@/assets/geul2.jpg" alt="" /> -->
        <div>
          <h4 class="d-flex justify-content-end">#{{ detailmeeting.theme }} #{{ detailmeeting.interest }}</h4>
          <img id="imgbox" class="img-fluid mt-2" :src="`${detailmeeting.img}`" v-if="`${detailmeeting.img}`" />
          <img id="imgbox" :src="require(`@/assets/${detailmeeting.interest}.jpg`)" v-else />
        </div>
        <div class="col" id="searchdetail">
          <div class="mx-5">
            <div class="mt-5 my-3">
              <span class="green underline"> 참여 안내 </span>
            </div>
            <div style="white-space: pre-line; font-size: 20px">
              <p>시작하는 날 : {{ detailmeeting.startDay }}</p>
              <p>모이는 날 : 매주 {{ detailmeeting.schedule }}요일</p>
              <p>모임 정원 : {{ detailmeeting.people }} 명</p>
            </div>
            <div class="mt-5 my-3">
              <span class="green underline"> 진행 방식 </span>
            </div>

            <div style="white-space: pre-line; font-size: 20px">
              {{ detailmeeting.format }}
            </div>
          </div>
        </div>
      </div>
      <hr />
      <div class="row">
        <div class="col-12">
          <div class="mt-5 my-3">
            <span class="green underline space1">모임 소개</span>
          </div>

          <div style="white-space: pre-line; font-size: 20px">
            {{ detailmeeting.introduce }}
          </div>
          <br />
          <br />
          <span class="mname space1" style="font-size: 1.6rem">이런 분에게 추천해요</span>
          <br />
          <div style="white-space: pre-line; font-size: 20px">
            {{ detailmeeting.targetUser }}
          </div>
        </div>
      </div>
      <div>
        <div class="d-flex justify-content-center" style="margin-top: 5rem; margin-bottom: 5rem">
          <button type="button" class="g-btn w-btn-green" style="background-color: #1a4d2e; color: white; width: 13rem; font-size: 1.4rem" @click="onApply">신청하기</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import TestCom from "@/components/TestCom.vue";
import { mapActions, mapState } from "vuex";
export default {
  name: "SearchGroupDetail",
  // components: { TestCom },
  data() {
    return {
      applyInfo: {
        meetingId: this.$route.params.meetingId,
        applicantId: "",
      },
    };
  },
  computed: {
    ...mapState(["detailmeeting", "loginUser", "isapply"]),
  },
  methods: {
    ...mapActions(["SearchMeetingDetail", "ApplyMeeting", "applyChecking"]),
    onApply() {
      const appmId = this.applyInfo.meetingId;
      const logId = this.loginUser.id;
      this.applyChecking({ appmId, logId });
      // if (!this.isapply) {
      this.applyInfo.applicantId = this.loginUser.id;
      // console.log(this.applyInfo);
      const apply = this.applyInfo;
      this.ApplyMeeting(apply);
      window.scrollTo(0, 0);
      // } else {
      //   // alert("이미 신청되었습니다.");
      //   this.$swal({
      //     title: "이미 신청된 모임입니다.",
      //     icon: "warning",
      //     confirmButtonText: "확인",
      //   });
      //   window.scrollTo(0, 0);
    },
    // },
  },
  created() {
    this.SearchMeetingDetail(this.applyInfo.meetingId);
    window.scrollTo(0, 0);
  },
};
</script>

<style scoped>
.mname {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, "Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol";
  font-size: 2.7rem;
  font-weight: 700;
}
#imgbox {
  width: 600px;
  height: 600px;
}
.underline {
  line-height: 1.2;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, "Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol";
  font-size: 3em;
  font-weight: 700;
  background-image: linear-gradient(transparent calc(100% - 3px), #000 3px);
  background-repeat: no-repeat;
  background-size: 0% 100%;
  transition: background-size 0.8s;
  color: #000;
  cursor: pointer;
}
@media (min-width: 1000px) {
  .underline {
    font-size: 2.5rem;
  }
}
.underline.green {
  background-image: linear-gradient(transparent 60%, #1e663a 30%);
}
.underline:hover {
  background-size: 100% 100%;
}
.font {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, "Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol";
  font-size: 2em;
}
.w-btn-green {
  background-color: #1a4d2e !important;
  color: whitesmoke !important;
}
.g-btn:hover {
  letter-spacing: 2px;
  transform: scale(1.2);
  cursor: pointer;
  background-color: #1a4d2e;
}
.g-btn:active {
  transform: scale(1.5);
}
.g-btn {
  position: relative;
  border: none;
  display: inline-block;
  padding: 15px 30px;
  border-radius: 30px;
  /* font-family: "paybooc-Light", sans-serif; */
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
  text-decoration: none;
  font-weight: 600;
  font-size: 1.1rem;
  background-color: #1a4d2e !important;
  transition: 0.25s;
}
</style>
