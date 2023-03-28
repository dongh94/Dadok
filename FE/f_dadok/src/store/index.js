import Vue from "vue";
import Vuex from "vuex";
import http from "@/util/http-common.js";
import jwt_decode from "jwt-decode";
import createPersistedState from "vuex-persistedstate";
import router from "@/router";
import Swal from "sweetalert2";

Vue.use(Vuex);

import chatStore from "./modules/ChatStore.js";

export default new Vuex.Store({
  state: {
    // token: "",
    ////////////////// 유저 //////////////////
    isLogin: false,
    loginUser: null,

    ////////////////// 모임 //////////////////
    meeting: {}, // 내가 만든 미팅
    meetings: [], // 모든 미팅 (서치)
    detailmeeting: {},
    fmeetings: [], // 내가 참여한 미팅들
    ////////////////// 신청 //////////////////
    applies: [], //내가 만든 미팅에 신청한 신청서들
    members: [], //내가 만든 미팅 멤버들
    isapply: false,
    ////////////////// 활동 //////////////////
    sheet: {},
    ///////////////// 북 /////////////////////
    books: [],
    bookimgurl: "",
  },
  getters: {
    ////////////////// 유저 //////////////////
    checkUserInfo: function (state) {
      return state.loginUser;
    },
    checkMeetingInfo: (state) => state.meeting,
    checkBookImg: (state) => state.bookimgurl,
  },
  mutations: {
    //////////////////////////////////// 유저쪽 ////////////////////////////////////
    // SET_USER_TOKEN(state, token) {
    //   state.token = token;
    // },
    SET_USER_INFO(state, user) {
      state.isLogin = true;
      state.loginUser = user;
    },
    CLEAR_USER(state) {
      state.loginUser = null;
      state.isLogin = false;
      sessionStorage.removeItem("access-token");
    },
    LOG_OUT(state) {
      state.loginUser = null;
      state.isLogin = false;
      sessionStorage.removeItem("access-token");
    },
    SET_IS_LOGIN: (state, isLogin) => (state.isLogin = isLogin),
    SET_MEETING: (state, meeting) => (state.meeting = meeting),

    SET_MEETINGS: (state, meetings) => (state.meetings = meetings),
    SET_FMEETINGS: (state, fmeetings) => (state.fmeetings = fmeetings),
    SET_DETAIL_MEETING: (state, detailmeeting) => (state.detailmeeting = detailmeeting),
    SET_IS_APPLY: (state, isapply) => (state.isapply = isapply),
    SET_APPLIES: (state, applies) => (state.applies = applies),
    SET_MEMBERS: (state, members) => (state.members = members),

    SET_SHEET: (state, sheet) => (state.sheet = sheet),
    CLEAR_SHEET: (state) => (state.sheet = {}),
    SET_BOOKS: (state, books) => (state.books = books),
    SET_BOOK_IMGURL: (state, bookimgurl) => (state.bookimgurl = bookimgurl),
  },
  actions: {
    async userConfirm({ commit, dispatch }, user) {
      await http
        .post("/user/login", user)
        .then((response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            sessionStorage.setItem("access-token", token);
            commit("SET_IS_LOGIN", true);
          } else if (response.data.message === "noauth") {
            Swal.fire({
              title: "해당 아이디는 \n이메일 인증이 필요합니다. \n다시, 확인해주세요",
              showDenyButton: true,
              showCancelButton: true,
              confirmButtonText: "이메일 재전송",
              denyButtonText: `아이디 삭제`,
              cancelButtonText: "취소",
            }).then((result) => {
              /* Read more about isConfirmed, isDenied below */
              if (result.isConfirmed) {
                Swal.fire("재전송 완료", "", "success");
                dispatch("REmail", user.id);
              } else if (result.isDenied) {
                Swal.fire("아이디 삭제 완료", "", "info");
                dispatch("dellogin", user.id);
              }
            });
          } else {
            Swal.fire({
              title: "비밀번호를 확인해주세요.",
              icon: "error",
              confirmButtonText: "확인",
              confirmButtonColor: "#1A4D2E",
            });
          }
        })
        .catch((err) => {
          console.log(err);
          Swal.fire({
            title: "존재하지 않는 아이디입니다. \n확인해주세요",
            icon: "question",
            confirmButtonText: "확인",
            confirmButtonColor: "#1A4D2E",
          });
        });
    },
    async REmail({ commit }, userId) {
      await http
        .post("/user/remail/" + userId)
        .then(() => {
          // console.log(res);
          commit;
          router.push({ name: "HomeView" });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    async dellogin({ commit }, userId) {
      await http
        .delete("/user/" + userId)
        .then(() => {
          // console.log(res);
          commit;
          router.push({ name: "HomeView" });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    async getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      const temp_http = http;
      temp_http.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
      await temp_http
        .get("/user/login/" + decode_token.userid)
        .then((response) => {
          if (response.data.message === "success") {
            // console.log("유저정보 획득");

            commit("SET_USER_INFO", response.data.userInfo);
            // commit("SET_USER_TOKEN", token);
          } else {
            // console.log("유저 정보 없음!!");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    logout({ commit }) {
      // this.$swal({
      //   title: "로그아웃 완료",

      //   icon: "info",
      //   confirmButtonText: "Cool",
      // });
      // alert("로그아웃 완료");
      // Swal.fire({
      //   title: "로그아웃 완료",

      //   icon: "success",
      //   confirmButtonText: "확인",
      //   confirmButtonColor: "#1A4D2E",
      // });
      commit("LOG_OUT");
      router.push({ name: "StartView" });
    },
    async updateUserImg({ commit }, user) {
      const updateuserimg = { img_path: user.img_path };
      await http
        .patch("/user/img/" + user.id, updateuserimg)
        .then(({ data }) => {
          if (data == "success") {
            Swal.fire({
              title: "수정 완료",

              icon: "success",
              confirmButtonText: "확인",
              confirmButtonColor: "#1A4D2E",
            });
            commit("SET_USER_INFO", user);
          }
        })
        .catch();
    },
    async deleteUser({ commit }, id) {
      await http
        .delete("/user/" + id)
        .then(({ data }) => {
          if (data == "success") {
            Swal.fire({
              title: "회원탈퇴 성공",

              icon: "success",
              confirmButtonText: "확인",
              confirmButtonColor: "#1A4D2E",
            });
            commit("CLEAR_USER");
          }
        })
        .catch();
    },

    async updateIntro({ commit }, { userId, updateuser }) {
      const updateuserobj = { introduce: updateuser.introduce };
      await http
        .patch("/user/introduce/" + userId, updateuserobj)
        .then(({ data }) => {
          if (data == "success") {
            Swal.fire({
              title: "수정 완료",

              icon: "success",
              confirmButtonText: "확인",
              confirmButtonColor: "#1A4D2E",
            });
            commit("SET_USER_INFO", updateuser);
          }
        })
        .catch();
    },
    //////////////////////////////////// 미팅 ////////////////////////////////////

    async getMeetingInfo({ commit }, userid) {
      await http
        .get("/meeting/myMeet/" + userid)
        .then((response) => {
          if (response.status === 200) {
            commit("SET_MEETING", response.data);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async getFMeetings({ commit }, userid) {
      await http
        .get("/meeting/list/" + userid)
        .then((response) => {
          if (response.status === 200) {
            commit("SET_FMEETINGS", response.data);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },

    async createmeeting({ commit }, meeting) {
      await http
        .post("/meeting", meeting)
        .then(({ data }) => {
          let msg = "모임을 만드는데 문제가 발생했습니다.";
          if (data == "success") {
            msg = "모임 생성을 완료했습니다.";
            Swal.fire({
              title: msg,
              icon: "success",
              confirmButtonText: "확인",
              confirmButtonColor: "#1A4D2E",
            });
            commit("SET_MEETING", meeting);
            router.push({ name: "LeadGroupView" });
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 내 미팅 정보 수정1(소개)
    async updateMeetingInroduce({ commit }, { userId, updatemeeting }) {
      const updatemeetingobj = { introduce: updatemeeting.introduce };

      await http
        .patch("/meeting/introduce/" + userId, updatemeetingobj)
        .then(({ data }) => {
          if (data == "success") {
            Swal.fire({
              title: "모임 소개 수정완료",

              icon: "success",
              confirmButtonText: "확인",
              confirmButtonColor: "#1A4D2E",
            });

            commit("SET_MEETING", updatemeeting);
          }
        })
        .catch();
    },
    // 내 미팅 정보 수정2(진행방식)
    async updateMeetingFormat({ commit }, { userId, updatemeeting }) {
      const updatemeetingobj = { format: updatemeeting.format };
      await http
        .patch("/meeting/format/" + userId, updatemeetingobj)
        .then(({ data }) => {
          if (data == "success") {
            Swal.fire({
              title: "진행 방식 수정완료",
              confirmButtonColor: "#1A4D2E",
              icon: "success",
              confirmButtonText: "확인",
            });
            commit("SET_MEETING", updatemeeting);
          }
        })
        .catch();
    },
    // 내 미팅 정보 수정3(추천대상)
    async updateMeetingTarget({ commit }, { userId, updatemeeting }) {
      const updatemeetingobj = { target: updatemeeting.targetUser };
      await http
        .patch("/meeting/target/" + userId, updatemeetingobj)
        .then(({ data }) => {
          if (data == "success") {
            Swal.fire({
              title: "수정 완료",
              confirmButtonColor: "#1A4D2E",
              icon: "success",
              confirmButtonText: "확인",
            });
            commit("SET_MEETING", updatemeeting);
          }
        })
        .catch();
    },
    // 내 미팅 정보 수정4(요일)
    async updateMeetingDay({ commit }, { userId, updatemeeting }) {
      const updatemeetingobj = { schedule: updatemeeting.schedule };
      await http
        .patch("/meeting/schedule/" + userId, updatemeetingobj)
        .then(({ data }) => {
          if (data == "success") {
            Swal.fire({
              title: "수정 완료",
              confirmButtonColor: "#1A4D2E",
              icon: "success",
              confirmButtonText: "확인",
            });
            commit("SET_MEETING", updatemeeting);
          }
        })
        .catch();
    },
    // 모든 미팅들 가져오기
    async SearchMeetings({ commit }) {
      await http.get("/meeting").then(({ data }) => {
        commit("SET_MEETINGS", data);
      });
    },
    // 미팅 필터 검색
    async SearchMeetingsName({ commit }, { name, schedule, theme, interest }) {
      await http.get("/meeting").then((res) => {
        let filtered_meeting = [];
        let before_meetings = res.data;
        let index = 0;
        for (index = 0; index < before_meetings.length; index++) {
          let meetings = before_meetings[index];
          if (name && meetings.name.includes(name)) {
            filtered_meeting.push(meetings);
          }
          let arr1 = schedule.values();
          for (let schedule1 of arr1) {
            if (meetings.schedule.includes(schedule1)) {
              filtered_meeting.push(meetings);
            }
          }
          let arr2 = theme.values();
          for (let theme1 of arr2) {
            if (meetings.theme.includes(theme1)) {
              filtered_meeting.push(meetings);
            }
          }
          let arr3 = interest.values();
          for (let interest1 of arr3) {
            if (meetings.interest.includes(interest1)) {
              filtered_meeting.push(meetings);
            }
          }
        }
        let set = new Set(filtered_meeting);

        let filter_meetings = [...set];

        commit("SET_MEETINGS", filter_meetings);
      });
    },
    //미팅 디테일
    async SearchMeetingDetail({ commit }, meetingId) {
      await http.get("/meeting/detail/" + meetingId).then(({ data }) => {
        commit("SET_DETAIL_MEETING", data);
      });
    },
    //미팅 삭제
    async deleteMeeting({ commit }, meetingId) {
      await http
        .delete("/meeting/" + meetingId)
        .then(() => {
          commit("SET_MEETING", {});
          //apply 삭제 등등
          commit("SET_APPLIES", []);
          commit("SET_MEMBERS", []);
          commit("SET_SHEET", {});

          // router.push({ name: "LeadGroupView" }); //추후 참여한 모임 ㄱㄱ
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 내가 주최한 모임의 현재 등록된 멤버를 확인
    async getMembers({ commit }, userid) {
      await http
        .get("/meeting/myMeet/member/" + userid)
        .then((response) => {
          if (response.status === 200) {
            commit("SET_MEMBERS", response.data);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async banMember({ commit }, { userId, memberId, meetingId }) {
      await http
        .post(`/meeting/ban/+${meetingId}/${memberId}`)
        .then(() => {
          // console.log(res.data);
          commit;
          this.getMembers(userId);
          router.push({ name: "LeadGroupView" });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    ////////////////// 신청 //////////////////////
    // 미팅신청하기
    async ApplyMeeting({ commit }, apply) {
      await http.post("/apply", apply).then(({ data }) => {
        if (data == "success") {
          Swal.fire({
            title: "신청 완료",
            icon: "success",
            confirmButtonText: "확인",
            confirmButtonColor: "#1A4D2E",
          });
          commit;
          router.push({ name: "SearchGroupView" }); //추후 참여한 모임 ㄱㄱ
        } else {
          // alert(data);
          Swal.fire({
            title: data,
            icon: "warning",
            confirmButtonText: "확인",
            confirmButtonColor: "#1A4D2E",
          });
          setTimeout(router.push({ name: "SearchGroupView" }), 1000);
        }
      });
    },
    async getApplies({ commit }, userid) {
      await http
        .get("/meeting/myMeet/apply/" + userid)
        .then((response) => {
          if (response.status === 200) {
            commit("SET_APPLIES", response.data);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async applyChecking({ commit }, { appmId, logId }) {
      await http.get("/meeting/meetUser/" + appmId).then((response) => {
        commit;
        const applyidarr = response.data;
        for (let id of applyidarr) {
          if (logId === Object.values(id)[0]) {
            commit("SET_IS_APPLY", true);
            break;
          } else {
            commit("SET_IS_APPLY", false);
          }
        }
      });
    },
    //////////////////////////////////// 활동 ////////////////////////////////////
    async getSheetInfo({ commit }, meeting) {
      await http
        .get("/sheet/" + meeting.id)
        .then((response) => {
          if (response.status === 200) {
            commit("SET_SHEET", response.data);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async getSheetInfo_id({ commit }, meetingid) {
      await http
        .get("/sheet/" + meetingid)
        .then((response) => {
          if (response.status === 200) {
            commit("SET_SHEET", response.data);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async updateSheet({ commit }, afterSheet) {
      const sheetobj = {
        date: afterSheet.date,
        hostId: afterSheet.hostId,
        isbn: afterSheet.isbn,
        img_path: afterSheet.img_path,
        people: afterSheet.people,
        member1: afterSheet.member1,
        member2: afterSheet.member2,
        member3: afterSheet.member3,
        member4: afterSheet.member4,
        member5: afterSheet.member5,
        member6: afterSheet.member6,
        opinion1: afterSheet.opinion1,
        opinion2: afterSheet.opinion2,
        opinion3: afterSheet.opinion3,
        opinion4: afterSheet.opinion4,
        opinion5: afterSheet.opinion5,
        opinion6: afterSheet.opinion6,
        topic: afterSheet.topic,
      };
      await http
        .patch("/sheet", sheetobj)
        .then(({ data }) => {
          if (data == "success") {
            Swal.fire({
              title: "수정 완료",
              confirmButtonColor: "#1A4D2E",
              icon: "success",
              confirmButtonText: "확인",
            });
            commit("SET_SHEET", sheetobj);
          }
        })
        .catch();
    },

    //////////////////////// 북 ////////////////////////
    async getBooks({ commit }, userId) {
      await http.get("/sheet/book/" + userId).then(({ data }) => {
        commit("SET_BOOKS", data);
        // console.log(data);
      });
    },
    async getBookImg({ commit }, isbn) {
      await http.get("/search/isbn/" + isbn).then((res) => {
        const imgurl = res.data.items[0].image;
        commit("SET_BOOK_IMGURL", imgurl);
      });
    },
  },
  modules: {
    chatStore: chatStore,
  },
  plugins: [
    createPersistedState({
      storage: sessionStorage,
    }),
  ],
});
