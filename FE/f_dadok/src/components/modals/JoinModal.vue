<template>
  <div>
    <!-- <b-button v-b-modal.modal-join1 class="w-btn-outline1 w-btn-bright-outline">회원가입</b-button> -->
    <p v-b-modal.modal-join1 class="green-text-shadow">회원가입</p>
    <b-modal id="modal-join1" ref="modal" title-html="<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;회원가입</h2>" centered>
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <div class="d-flex mt-2 justify-content-end" style="margin-right: 2rem">
          <div class="" style="font-size: 1.3rem; margin-right: 6.8rem">
            <label for="password">아이디</label>
          </div>
          <div class="mx-2">
            <b-form-input class="shadow" style="border-radius: 10px; height: 2.4rem" id="id-input" v-model="regist_user.id" required></b-form-input>
          </div>
        </div>
        <div class="d-flex mt-2 justify-content-end" style="margin-right: 2rem">
          <div class="" style="font-size: 1.3rem; margin-right: 5.5rem">
            <label for="password">비밀번호</label>
          </div>
          <div class="mx-2">
            <b-form-input class="shadow" style="border-radius: 10px; height: 2.4rem" id="password" type="password" v-model="regist_user.pw"></b-form-input>
          </div>
        </div>
        <div class="d-flex mt-2 justify-content-end" style="margin-right: 2rem">
          <div class="" style="font-size: 1.3rem; margin-right: 2.6rem">
            <label for="passwordChk">비밀번호 확인</label>
          </div>
          <div class="mx-2">
            <b-form-input class="shadow" style="border-radius: 10px; height: 2.4rem; width: 233.2px" id="passwordChk" type="password" v-model="regist_user.pwchk" :state="pwState"></b-form-input>
          </div>
        </div>
        <div class="d-flex mt-2 justify-content-end" style="margin-right: 2rem">
          <div class="" style="font-size: 1.3rem; margin-right: 8rem">
            <label for="name">이름</label>
          </div>
          <div class="mx-2">
            <b-form-input class="shadow" style="border-radius: 10px; height: 2.4rem; width: 233.2px" id="name" v-model="regist_user.name"></b-form-input>
          </div>
        </div>
        <div class="d-flex mt-2 justify-content-end" style="margin-right: 2rem">
          <div class="" style="font-size: 1.3rem; margin-right: 5.5rem">
            <label for="name">생년월일</label>
          </div>
          <div class="mx-2">
            <b-form-input class="shadow" style="border-radius: 10px; height: 2.4rem; width: 233.2px" id="birth" type="date" v-model="regist_user.birthday"></b-form-input>
          </div>
        </div>
        <div class="d-flex mt-2 justify-content-end" style="margin-right: 2rem">
          <div class="" style="font-size: 1.3rem; margin-right: 8rem">
            <label for="sex">성별</label>
          </div>
          <div class="mx-2">
            <b-form-select class="shadow" style="border-radius: 10px; height: 2.4rem; width: 233.2px" id="sex" v-model="regist_user.sex" :options="sOptions"> </b-form-select>
          </div>
        </div>
        <div class="d-flex mt-2 justify-content-end" style="margin-right: 2rem">
          <div class="" style="font-size: 1.3rem; margin-right: 6.8rem">
            <label for="email">이메일</label>
          </div>
          <div class="mx-2">
            <b-form-input class="shadow" style="border-radius: 10px; height: 2.4rem; width: 233.2px" id="email" type="email" v-model="regist_user.email"></b-form-input>
          </div>
        </div>
        <div class="d-flex mt-2 justify-content-end" style="margin-right: 2rem">
          <div class="" style="font-size: 1.3rem; margin-right: 1.9rem">
            <label for="interest">관심분야(장르)</label>
          </div>
          <div class="mx-2">
            <b-form-select class="shadow" style="border-radius: 10px; height: 2.4rem; width: 233.2px" id="interest" v-model="regist_user.interest" :options="iOptions"> </b-form-select>
          </div>
        </div>
      </form>
      <template #modal-footer>
        <div class="w-100">
          <b-button block class="j-btn j-btn-simple" style="background-color: #1a4d2e; color: white; border-radius: 20px; opacity: 0.9; font-size: 1.2rem" size="sm" @click="handleSubmit">
            <img src="@/assets/real_logo.png" alt="" style="height: 2.5rem" /> 가입하기
          </b-button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
export default {
  computed: {
    pwState() {
      return this.regist_user.pw == this.regist_user.pwchk ? true : false;
    },
  },
  data() {
    return {
      regist_user: {
        id: "",
        pw: "",
        name: "",
        birthday: "",
        img_path: "asdf",
        interest: "",
        email: "",
        sex: "",
        introduce: "",
        isCreate: 0,
        Auth: 0,
        meeting_count: 0,
        meeting_time: 0,
        book_count: 0,
      },
      sOptions: [
        { value: "남자", text: "남자" },
        { value: "여자", text: "여자" },
      ],
      iOptions: [
        { value: "소설", text: "소설" },
        { value: "인문", text: "인문" },
        { value: "수필", text: "수필" },
        { value: "고전", text: "고전" },
        { value: "과학", text: "과학" },
      ],
    };
  },

  methods: {
    async handleSubmit() {
      // console.log(this.regist_user.id);
      // console.log(this.regist_user.pw);
      // console.log(this.regist_user.name);
      // console.log(this.regist_user.sex);
      // console.log(this.regist_user.email);
      // console.log(this.regist_user.interest);
      await http.post("/user", this.regist_user).then(() => {
        this.$swal({
          title: "회원가입 신청완료 !",
          text: "이메일 인증 후 완료 처리 됩니다.",
          icon: "info",
          confirmButtonText: "확인",
          confirmButtonColor: "#1A4D2E",
        });

        // alert("회원가입 신청완료 ! \n이메일 인증 후 완료 처리 됩니다.");
        this.$nextTick(() => {
          this.$bvModal.hide("modal-join1");
        });
      });
    },
  },
};
</script>
<style>
#modal-join1 {
  background-image: url(@/assets/modal_back2.jpg);

  background-size: cover;
  /* top: 50%;
  margin-top: -50px; */
}
.w-btn-outline1 {
  position: relative;
  padding: 10px 25px;
  border-radius: 15px;
  /* font-family: "paybooc-Light", sans-serif; */
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
  text-decoration: none;
  font-weight: 600;
  transition: 0.25s;
  background-color: #1a4d2e;

  color: whitesmoke;
  font-size: 1.2rem;
}
.w-btn-outline1:hover {
  letter-spacing: 2px;
  transform: scale(1.2);
  cursor: pointer;
}
.w-btn-outline1:active {
  transform: scale(1.5);
}
.w-btn-bright-outline {
  border: 3px solid #1a4d2e;
  color: #e6ddc6;
}
.w-btn-bright-outline:hover {
  background-color: #e6ddc6;
  border: 3px solid #e6ddc6;
  color: black;
}
.modal-content {
  -webkit-border-radius: 0px !important;
  -moz-border-radius: 0px !important;
  border-radius: 10px !important;
  border-color: #f4ece1;
}
.j-btn {
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
.j-btn:hover {
  letter-spacing: 2px;
  transform: scale(1.05);
  cursor: pointer;
  background-color: #f4ece1;
  color: black;
}
.j-btn:active {
  transform: scale(1.1);
}
.j-btn-simple {
  background-color: #f4ece1;
  color: black;
}
.j-btn-simple:hover {
  letter-spacing: 2px;
  transform: scale(1.05);
  cursor: pointer;
  background-color: #f4ece1;
  color: black;
}
</style>
