<template>
  <div class="d-flex justify-content-center">
    <p v-b-modal.modal-login class="green-text-shadow">로그인</p>
    <b-modal
      id="modal-login"
      ref="modal"
      title-html="<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 로그인</h2>"
      @show="resetModal"
      @hidden="resetModal"
      @ok="handleOk"
      centered
    >
      <form ref="form" @submit.stop.prevent="handleSubmit" style="margin-top: 1rem; margin-bottom: 1.2rem">
        <!-- <b-form-group label="아이디" label-for="id-input" invalid-feedback="ID is required" :state="idState">
          <b-form-input class="shadow" style="border-radius: 10px" id="id-input" v-model="id" :state="idState" required></b-form-input>
        </b-form-group> -->
        <div>
          <div class="d-flex justify-content-center" style="font-size: 1.3rem">
            <label for="id-input">아이디</label>
          </div>
          <div class="d-flex justify-content-center">
            <b-form-input class="shadow" style="border-radius: 10px; height: 2.4rem; width: 60%" id="id-input" v-model="id" :state="idState" required></b-form-input>
          </div>
        </div>
        <div style="margin-top: 1rem">
          <div class="d-flex justify-content-center" style="font-size: 1.3rem">
            <label for="password">&nbsp;&nbsp;&nbsp;비밀번호</label>
            <div class="d-flex justify-content-end mx-2">
              <a v-b-modal.modal-search-password class="center" @click="show = false"><b-icon icon="question-circle"></b-icon></a>
              <b-modal id="modal-search-password">가입 시 입력한 이메일로 비밀번호 찾기가 발송되었습니다.</b-modal>
            </div>
          </div>
          <div class="d-flex justify-content-center">
            <b-form-input class="shadow" style="border-radius: 10px; height: 2.4rem; width: 60%" id="password" type="password" @keyup="enterkey" v-model="pw"></b-form-input>
          </div>
        </div>
      </form>

      <template #modal-footer>
        <div class="w-100">
          <b-button block class="j-btn j-btn-simple" style="background-color: #1a4d2e; color: white; border-radius: 20px; opacity: 0.9; font-size: 1.2rem" size="sm" @click="login">
            <img src="@/assets/real_logo.png" alt="" style="height: 2.5rem" /> 로그인
          </b-button>
        </div>
        <div class="w-100">
          <!-- <b-button block class="j-btn j-btn-simple" style="color: #000000; background-color: #fee500; border-radius: 20px; opacity: 0.9; font-size: 1.2rem" size="sm" @click="show = false">
            카카오 계정으로 로그인
          </b-button> -->
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
// import http from "@/util/http-common.js";
export default {
  data() {
    return {
      id: "",
      idState: null,
      submittedIDs: [],
      pw: "",
    };
  },
  computed: {
    ...mapState(["isLogin", "isAuth"]),
  },
  methods: {
    enterkey() {
      if (window.event.keyCode == 13) {
        this.login();
      }
    },
    checkFormValidity() {
      const valid = this.$refs.form.checkValidity();
      this.idState = valid;
      return valid;
    },
    resetModal() {
      this.id = "";
      this.idState = null;
    },
    handleOk(bvModalEvent) {
      // Prevent modal from closing
      bvModalEvent.preventDefault();
      // Trigger submit handler
      this.handleSubmit();
    },
    handleSubmit() {
      // Exit when the form isn't valid
      if (!this.checkFormValidity()) {
        return;
      }
      // Push the name to submitted names
      this.submittedIDs.push(this.id);
      // Hide the modal manually
      this.$nextTick(() => {
        this.$bvModal.hide("modal-login");
      });
    },
    // 유저 로그인( JWT ).
    ...mapActions(["userConfirm", "getUserInfo"]),

    async login() {
      let user = {
        id: this.id,
        pw: this.pw,
      };
      await this.userConfirm(user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "HomeView" });
      }
    },
  },
};
</script>
<style>
#modal-login {
  background-image: url(@/assets/modal_back.jpg);

  background-size: cover;
}

.green-text-shadow:hover {
  text-shadow: 1px 2px 4px #1a4d2e;
}
.green-text-shadow {
  font-size: 1.5rem;
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
.modal-title {
  font-size: 2rem;
  text-shadow: 1px 2px 3px #1a4d2e;
  color: #1a4d2e;
}
</style>
