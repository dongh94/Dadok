<template>
  <div id="startpage" class="row justify-content-center">
    <div class="col-sm-4 col-md-4" id="startmargin">
      <b-navbar-brand :to="{ name: 'StartView' }" style="font-size: 3rem; color: black">
        <img src="@/assets/main_logo_w.png" alt="main_logo" style="width: 4.5rem" class="mb-2" />
        <strong class=""> 다독 </strong>
      </b-navbar-brand>

      <div id="startbinbox" style="border-radius: 20% 0% / 20% 40%"></div>
      <div style="margin-left: 2rem text-shadow: 2px 2px 4px #000000" data-sal="zoom-in" data-sal-delay="0" data-sal-easing="easeOutCirc" data-sal-duration="1000">
        <h1 style="text-shadow: 1px 1px 2px #000000">많은 독서</h1>
        <h1 style="text-shadow: 1px 1px 2px #000000">다같이 독서</h1>
        <h1 style="text-shadow: 1px 1px 2px #000000">독서를 다독</h1>
        <h1 style="text-shadow: 1px 1px 2px #000000">다양한 독서모임</h1>
        <br />
        <b-button size="lg" onclick="location.href = '#learn1' " id="learnmorebtn1" type="button" class="w-btn w-btn-gra2 w-btn-gra-anim" style="color: white"> 더 알아보기 </b-button>
      </div>
    </div>
    <drop-books class="col-sm-8 col-md-8" id="books"></drop-books>
    <div class="row" id="learn1">
      <div id="advertwrite1" style="text-shadow: 1px 1px 2px #000000">
        <!-- &nbsp;&nbsp;&nbsp; -->
        <div data-sal="slide-right" data-sal-delay="0" data-sal-easing="easeOutCirc" data-sal-duration="1200" width="300px">독서와 모임을 한번에</div>
      </div>
    </div>
    <div class="row justify-content-center" id="learn1-2">
      <div class="col-12" id="advertwrite1-2" style="text-shadow: 1px 1px 2px #000000">
        <div data-sal="slide-up" data-sal-delay="50" data-sal-easing="easeOutCirc" data-sal-duration="1200">다양한 독서 모임에 참여하세요</div>
        <img id="img1-2" :src="images1" style="text-shadow: 1px 1px 2px #000000" data-sal="slide-up" data-sal-delay="0" data-sal-easing="easeOutCirc" data-sal-duration="1200" />
      </div>
    </div>
    <div style="height: 160px"></div>

    <div class="row align-items-center" id="learn2" style="border-radius: 20% 0% / 20% 40%; box-shadow: 10px 5px 5px #a19882">
      <div id="advertwrite2" style="text-shadow: 1px 1px 2px #000000; margin-right: 2rem" data-sal="slide-left" data-sal-delay="0" data-sal-easing="easeOutCirc" data-sal-duration="1200">
        자신의 모임을
        <br />
        기록합니다.
        <!-- <p class="zoom">자신의 모임을&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
        <div>기록합니다.&nbsp;&nbsp;&nbsp;&nbsp;</div> -->
      </div>
    </div>
    <div style="height: 160px"></div>
    <div style="margin-top: 3rem; margin-bottom: 3rem; text-decoration-color: hsl(70, 100%, 40%)">
      <join-modal2 id="joinbtn" data-sal="fade" data-sal-delay="0" data-sal-easing="easeOutCirc" data-sal-duration="1200"></join-modal2>
    </div>
    <br />
  </div>
</template>

<script>
import images1 from "@/assets/advert1-2.jpg";
import JoinModal2 from "@/components/modals/JoinModal2.vue";
import DropBooks from "@/components/start/DropBooks.vue";
import { mapGetters, mapState } from "vuex";
import router from "@/router";
import sal from "sal.js";

export default {
  name: "StartView",
  components: { DropBooks, JoinModal2 },
  data() {
    return {
      images1: images1,
    };
  },
  mounted() {
    sal({
      threshold: 0.5,
      once: false,
    });
  },

  computed: {
    ...mapState(["isLogin"]),
  },
  methods: {
    ...mapGetters(["checkUserInfo"]),
  },
  created() {
    this.checkUserInfo();
    if (this.isLogin) {
      // alert("홈페이지로 이동됩니다.");
      this.$swal({
        title: "홈페이지로 이동됩니다.",
        icon: "info",
        confirmButtonText: "확인",
        confirmButtonColor: "#1A4D2E",
      });
      router.push({ name: "HomeView" });
    }
  },
};
</script>

<style>
#startpage {
  margin: auto;
  max-width: 1400px;
  text-align: center;
}
#startmargin {
  margin-left: 20px;
}
#learnmorebtn1 {
  margin: 3%;
}
#learnmorebtn2 {
  width: 10%;
  margin-top: 2%;
  margin-bottom: 2%;
}
#startbinbox {
  height: 20%;
}
#books {
  background-color: #fff;
  padding-right: 0px;
  padding-left: 0px;
  /* background-color: #fef7dc; */
}
#learn1 {
  /* background-color: #fef7dc; */
  background-image: url(@/assets/advert1.jpg);
  background-size: 1700px;
  width: 100%;
  height: 800px;
  color: black;
  box-shadow: 3px 3px #f4ece1, -1em 0 0.4em #a19882;
}
#advertwrite1 {
  width: 100%;
  height: 800px;
  font-size: 3rem;
  font-weight: 400;
  color: black;
  line-height: 500px;
  text-align: left;
  margin-left: 2rem;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, "Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol";
}

#learn1-2 {
  margin-top: 2rem;
  margin-bottom: 2rem;
  background-size: 1700px;
  width: 100%;
  height: 600px;
  padding: 5%;
}

#img1-2 {
  width: 450px;
  height: 300px;
  border-radius: 10%;
  margin-top: 3rem;
}

#advertwrite1-2 {
  font-size: 3rem;
  font-weight: 400;
  color: black;
  text-align: center;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, "Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol";
}

#learn2 {
  /* background-color: #fef7dc; */
  background-image: url(@/assets/advert2.jpg);
  background-size: 1700px;
  width: 100%;
  height: 800px;
  color: black;
}
#advertwrite2 {
  width: 100%;
  font-size: 3rem;
  font-weight: 400;
  color: white;
  text-align: right;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, "Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol";
}
#joinbtn {
  display: flex;
  justify-content: center;
}

.w-btn:active {
  transform: scale(1.5);
}
.w-btn:hover {
  letter-spacing: 2px;
  transform: scale(1.2);
  cursor: pointer;
}
.w-btn {
  position: relative;
  border: none;
  display: inline-block;
  padding: 15px 30px;
  border-radius: 15px;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
  text-decoration: none;
  font-weight: 600;
  transition: 0.25s;
}
.w-btn-gra2 {
  background: linear-gradient(-45deg, #23a6d5, #23d5ab, #1a4d2e, #23d5ab);
  /* background: linear-gradient(-45deg, #23a6d5, #1a4d2e, #23d5ab, #046d76, #46b9ae); */
  color: white;
}
.w-btn-gra-anim {
  background-size: 400% 400%;
  animation: gradient2 5s ease infinite;
}
@keyframes gradient2 {
  0% {
    background-position: 100% 50%;
  }
  50% {
    background-position: 0% 50%;
  }
  100% {
    background-position: 100% 50%;
  }
}
</style>
