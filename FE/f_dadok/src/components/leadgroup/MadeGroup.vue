<template>
  <div class="mt-4 container">
    <b-card class="shadow-lg p-3 mb-5 rounded" style="border-radius: 50px">
      <div class="m-3">
        <div class="d-flex">
          <h2 style="margin: 0">개최한 모임</h2>
          <div class="mx-2 mt-1">
            <div class="justify-content-end" style="height: 3rem">
              <b-button class="m-btn m-btn-simple mx-2" style="" @click="roomOpen">모임 열기</b-button>
              <b-button class="m-btn m-btn-simple mx-2" style="" @click="roomClose">모임 닫기</b-button>
            </div>

            <!-- <b-button class="w-btn2 w-btn-simple mx-2">
                <img src="@/assets/화상회의시작.pngs" style="width: 5rem; height: 10px" alt="" />
              </b-button> -->
          </div>
        </div>
        <div class="mt-4">
          <h2 style="color: #1a4d2e; font-weight: bold; margin-bottom: 2rem">[ {{ meeting.name }} ]</h2>
        </div>
        <b-row>
          <b-col cols="6">
            <!-- <img class="img-fluid mt-2" src="@/assets/geul2.jpg" alt="" /> -->

            <img :src="`${meeting.img}`" style="width: 100%; height: 100%" v-if="`${meeting.img}`" />
            <img :src="require(`@/assets/${meeting.interest}.jpg`)" style="width: 100%; height: 100%" v-else />

            <h4 class="mx-2 my-2" style="color: #125688">#{{ meeting.theme }} #{{ meeting.interest }}</h4>
          </b-col>
          <b-col class="mt-4">
            <div>
              <p class="zoom"><b-icon icon="calendar2-week"></b-icon>&nbsp;모임 시작&nbsp;</p>

              <div style="font-size: 1.5rem">&nbsp; {{ meeting.startDay }}</div>
            </div>
            <br />
            <!-- 4. 요일수정 -->
            <div class="mt-2">
              <div class="d-flex">
                <p class="zoom"><b-icon icon="flower2"></b-icon>&nbsp;모임 요일&nbsp;</p>

                <h3 v-if="dayOpen === false" class="mx-2 mt-1" style="font-size: 25px; color: grey" @click="dayOpen = true"><b-icon icon="pencil-square"></b-icon></h3>
                <h3 v-if="dayOpen === true" class="mx-2 mt-1" style="font-size: 25px; color: grey" @click="update4"><b-icon icon="check2-square"></b-icon></h3>
              </div>
              <div style="font-size: 1.5rem" v-if="dayOpen === false">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ meeting.schedule }}요일</div>
              <!-- <b-form-textarea
            v-if="dayOpen === true"
            id="textarea-rows"
            v-model="meeting.schedule"
            style="height: 170px"
            rows="8"
          ></b-form-textarea> -->
              <div class="mx-4">
                <b-form-checkbox-group
                  v-if="dayOpen === true"
                  v-model="meeting.schedule"
                  :options="options"
                  :aria-describedby="ariaDescribedby"
                  name="buttons-1"
                  buttons
                  style="background-color: #f4ece1 !important"
                ></b-form-checkbox-group>
              </div>
            </div>
            <br />
            <div class="mt-2">
              <p class="zoom"><b-icon icon="people"></b-icon>&nbsp;총 인원&nbsp;</p>

              <div style="font-size: 1.5rem">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ meeting.people }}명</div>
            </div>
          </b-col>
        </b-row>

        <br />
        <br />

        <hr />

        <div class="">
          <!-- 1.모임소개 -->
          <div class="d-flex" style="margin-top: 2rem">
            <p class="zoom"><b-icon icon="receipt"></b-icon>&nbsp;모임 소개&nbsp;</p>

            <h3 v-if="infoOpen === false" class="mx-2 mt-1" style="font-size: 25px; color: grey" @click="infoOpen = true">
              <b-icon icon="pencil-square"></b-icon>
            </h3>

            <h3 v-if="infoOpen === true" class="mx-2 mt-1" style="font-size: 25px; color: grey" @click="update1"><b-icon icon="check2-square"></b-icon></h3>
          </div>

          <div style="white-space: pre-line; font-size: 1.5rem" v-if="infoOpen === false">
            {{ meeting.introduce }}
          </div>
          <b-form-textarea v-if="infoOpen === true" id="textarea-rows" v-model="meeting.introduce" style="height: 340px" rows="8"></b-form-textarea>
          <br />
          <!-- 2. 모임진행방식 -->
          <div class="d-flex" style="margin-top: 4rem">
            <p class="zoom"><b-icon icon="diagram3-fill"></b-icon>&nbsp;진행 방식&nbsp;</p>

            <h3 v-if="formatOpen === false" class="mx-2 mt-1" style="font-size: 25px; color: grey" @click="formatOpen = true"><b-icon icon="pencil-square"></b-icon></h3>
            <h3 v-if="formatOpen === true" class="mx-2 mt-1" style="font-size: 25px; color: grey" @click="update2"><b-icon icon="check2-square"></b-icon></h3>
          </div>

          <div style="white-space: pre-line; font-size: 1.5rem" v-if="formatOpen === false">
            {{ meeting.format }}
          </div>
          <b-form-textarea v-if="formatOpen === true" id="textarea-rows" v-model="meeting.format" style="height: 170px" rows="8"></b-form-textarea>
          <br />
          <!-- 3. 추천대상 -->
          <div class="d-flex" style="margin-top: 4rem">
            <p class="zoom"><b-icon icon="gem"></b-icon>&nbsp;이런 분께 추천드려요&nbsp;</p>

            <h3 v-if="targetOpen === false" class="mx-2 mt-1" style="font-size: 25px; color: grey" @click="targetOpen = true"><b-icon icon="pencil-square"></b-icon></h3>
            <h3 v-if="targetOpen === true" class="mx-2 mt-1" style="font-size: 25px; color: grey" @click="update3"><b-icon icon="check2-square"></b-icon></h3>
          </div>
          <div style="white-space: pre-line; font-size: 1.5rem" v-if="targetOpen === false">
            {{ meeting.targetUser }}
          </div>
          <b-form-textarea v-if="targetOpen === true" id="textarea-rows" v-model="meeting.targetUser" style="height: 170px" rows="8"></b-form-textarea>
        </div>
        <br />
        <hr />
        <br />
        <div style="margin-bottom: 2rem">
          <div class="d-flex justify-content-between">
            <div style="margin: 0">
              <h2>
                <strong> {{ meeting.name }} 활동지 </strong>
              </h2>
            </div>
            <!-- 
            <div>
              <sheet-save></sheet-save>
            </div> -->
          </div>

          <div class="sheetModal">
            <!-- {{ meeting }} -->
            <br />
            <book-search></book-search>
            <b-button class="m-btn m-btn-simple mx-3" style="border-radius: 20px; display: inline" v-b-modal.modal-sheet @click="change"> <b-icon icon="clipboard-check"></b-icon>&nbsp;조회</b-button>
            <div class="d-flex justify-content-end">
              <sheet-save></sheet-save>
            </div>
          </div>
        </div>
        <hr />
        <div class="d-flex justify-content-end" style="margin-top: 2rem">
          <b-button class="m-btn m-btn-simple" style="background-color: black; color: white; border: 0" @click="deleteMeetingButton"> <b-icon icon="trash"></b-icon>&nbsp;모임 삭제하기</b-button>
          <!-- 활동지 조회 모달 -->
          <b-modal id="modal-sheet" scrollable size="lg" :title="meeting.name + ' 활동지'">
            <div style="text-align: center">
              <img :src="sheet.img_path" height="45%" width="35%" v-if="sheet.img_path" />
            </div>

            <b-form>
              <b-table>
                <b-row></b-row>
                <b-row></b-row>
                <b-row></b-row>
              </b-table>

              <b-row>
                <b-col sm="8"></b-col>
                <b-col sm="4" style="text-align: center; font-weight: 700">
                  <b-icon icon="calendar2-date"></b-icon>
                  모임 날짜: {{ sheet.date }}<br
                /></b-col>
              </b-row>
              <br style="height: 3px" />
              <hr />

              <b-row>
                <b-col sm="3"></b-col>
                <b-col sm="6">
                  <div style="font-size: 18px; text-align: center; border-style: inset; border-color: #1a4d2e; font-weight: 800; border-radius: 20px" v-if="sheetOpen === false">
                    {{ sheet.topic }}
                  </div>
                  <b-form-input v-if="sheetOpen === true" v-model="sheet.topic" class="shadow" style="border-radius: 10px"></b-form-input>
                </b-col>
                <b-col sm="3"></b-col>
              </b-row>
              <br style="height: 3px" />

              <b-row>
                <b-col sm="2"
                  ><div style="font-size: 18px; text-align: center; border-style: inset; border-color: #1a4d2e; font-weight: 800; border-radius: 20px" v-if="sheetOpen === false">
                    {{ sheet.member1 }}
                  </div>
                  <b-form-input class="shadow" style="border-radius: 10px" v-if="sheetOpen === true" v-model="sheet.member1"></b-form-input
                ></b-col>
                <b-col sm="10">
                  <div style="font-size: 18px; text-align: center; border-style: inset; border-color: #1a4d2e; font-weight: 800; border-radius: 20px" v-if="sheetOpen === false">
                    {{ sheet.opinion1 }}
                  </div>
                  <b-form-textarea class="shadow" style="border-radius: 10px" v-if="sheetOpen === true" v-model="sheet.opinion1"></b-form-textarea
                ></b-col>
              </b-row>
              <br />

              <b-row>
                <b-col sm="2"
                  ><div style="font-size: 18px; text-align: center; border-style: inset; border-color: #1a4d2e; font-weight: 800; border-radius: 20px" v-if="sheetOpen === false">
                    {{ sheet.member2 }}
                  </div>
                  <b-form-input class="shadow" style="border-radius: 10px" v-if="sheetOpen === true" v-model="sheet.member2"></b-form-input
                ></b-col>
                <b-col sm="10">
                  <div style="font-size: 18px; text-align: center; border-style: inset; border-color: #1a4d2e; font-weight: 800; border-radius: 20px" v-if="sheetOpen === false">
                    {{ sheet.opinion2 }}
                  </div>
                  <b-form-textarea class="shadow" style="border-radius: 10px" v-if="sheetOpen === true" v-model="sheet.opinion2"></b-form-textarea
                ></b-col>
              </b-row>
              <br />

              <b-row>
                <b-col sm="2"
                  ><div style="font-size: 18px; text-align: center; border-style: inset; border-color: #1a4d2e; font-weight: 800; border-radius: 20px" v-if="sheetOpen === false">
                    {{ sheet.member3 }}
                  </div>
                  <b-form-input class="shadow" style="border-radius: 10px" v-if="sheetOpen === true" v-model="sheet.member3"></b-form-input
                ></b-col>
                <b-col sm="10">
                  <div style="font-size: 18px; text-align: center; border-style: inset; border-color: #1a4d2e; font-weight: 800; border-radius: 20px" v-if="sheetOpen === false">
                    {{ sheet.opinion3 }}
                  </div>
                  <b-form-textarea class="shadow" style="border-radius: 10px" v-if="sheetOpen === true" v-model="sheet.opinion3"></b-form-textarea
                ></b-col>
              </b-row>
              <br />

              <b-row>
                <b-col sm="2"
                  ><div style="font-size: 18px; text-align: center; border-style: inset; border-color: #1a4d2e; font-weight: 800; border-radius: 20px" v-if="sheetOpen === false">
                    {{ sheet.member4 }}
                  </div>
                  <b-form-input class="shadow" style="border-radius: 10px" v-if="sheetOpen === true" v-model="sheet.member4"></b-form-input
                ></b-col>
                <b-col sm="10">
                  <div style="font-size: 18px; text-align: center; border-style: inset; border-color: #1a4d2e; font-weight: 800; border-radius: 20px" v-if="sheetOpen === false">
                    {{ sheet.opinion4 }}
                  </div>
                  <b-form-textarea class="shadow" style="border-radius: 10px" v-if="sheetOpen === true" v-model="sheet.opinion4"></b-form-textarea
                ></b-col>
              </b-row>
              <br />

              <b-row>
                <b-col sm="2"
                  ><div style="font-size: 18px; text-align: center; border-style: inset; border-color: #1a4d2e; font-weight: 800; border-radius: 20px" v-if="sheetOpen === false">
                    {{ sheet.member5 }}
                  </div>
                  <b-form-input class="shadow" style="border-radius: 10px" v-if="sheetOpen === true" v-model="sheet.member5"></b-form-input
                ></b-col>
                <b-col sm="10">
                  <div style="font-size: 18px; text-align: center; border-style: inset; border-color: #1a4d2e; font-weight: 800; border-radius: 20px" v-if="sheetOpen === false">
                    {{ sheet.opinion5 }}
                  </div>
                  <b-form-textarea class="shadow" style="border-radius: 10px" v-if="sheetOpen === true" v-model="sheet.opinion5"></b-form-textarea
                ></b-col>
              </b-row>
              <br />

              <b-row>
                <b-col sm="2"
                  ><div style="font-size: 18px; text-align: center; border-style: inset; border-color: #1a4d2e; font-weight: 800; border-radius: 20px" v-if="sheetOpen === false">
                    {{ sheet.member6 }}
                  </div>
                  <b-form-input class="shadow" style="border-radius: 10px" v-if="sheetOpen === true" v-model="sheet.member6"></b-form-input
                ></b-col>
                <b-col sm="10">
                  <div style="font-size: 18px; text-align: center; border-style: inset; border-color: #1a4d2e; font-weight: 800; border-radius: 20px" v-if="sheetOpen === false">
                    {{ sheet.opinion6 }}
                  </div>
                  <b-form-textarea class="shadow" style="border-radius: 10px" v-if="sheetOpen === true" v-model="sheet.opinion6"></b-form-textarea
                ></b-col>
              </b-row>
              <br />
            </b-form>

            <template #modal-footer>
              <div class="">
                <b-button v-if="sheetOpen === false" style="background-color: #f4ece1; color: black" class="m-btn m-btn-simple mx-3" @click="sheetOpen = true"> 수정하기 </b-button>
                <b-button v-if="sheetOpen === true" style="background-color: #1a4d2e; color: white" class="m-btn m-btn-simple mx-3" @click="update"> 수정완료 </b-button>
              </div>
            </template>
          </b-modal>
        </div>
      </div>
    </b-card>
  </div>
</template>

<script>
import { mapState, mapActions, mapGetters } from "vuex";
import http from "@/util/http-common.js";
import BookSearch from "./bookSearch.vue";
import io from "socket.io-client";
import SheetSave from "./sheetSave.vue";
const socket = io("http://i7b109.p.ssafy.io:3001", { transports: ["websocket"] });

const chatStore = "chatStore";

export default {
  components: { BookSearch, SheetSave },
  data() {
    return {
      infoOpen: false,
      formatOpen: false,
      targetOpen: false,
      dayOpen: false,
      options: [
        { text: "월", value: "월" },
        { text: "화", value: "화" },
        { text: "수", value: "수" },
        { text: "목", value: "목" },
        { text: "금", value: "금" },
        { text: "토", value: "토" },
        { text: "일", value: "일" },
      ],

      sheetOpen: false,
    };
  },

  name: "MadeGroup",
  computed: {
    ...mapState(["meeting", "loginUser", "sheet", "bookimgurl"]),
    ...mapState(chatStore, ["sessionstate"]),
  },
  methods: {
    ...mapGetters(["checkUserInfo", "checkBookImg"]),

    ...mapActions([
      "getMeetingInfo",
      "updateMeetingInroduce",
      "updateMeetingFormat",
      "updateMeetingTarget",
      "updateMeetingDay",
      "deleteMeeting",
      "getUserInfo",
      "getSheetInfo",
      "updateSheet",
      "getBookImg",
    ]),
    ...mapActions(chatStore, ["opensession", "closesession"]),
    async roomOpen() {
      await http
        .post("/session/open/" + this.loginUser.id)
        .then((response) => {
          // console.log(response);
          if (response.data === "success") {
            //alert("모임세션이 생성되었습니다.");
            this.$swal({
              title: "모임세션이 생성되었습니다.",
              //text: "이메일 인증 후 완료 처리 됩니다.",
              icon: "success",
              confirmButtonText: "확인",
              confirmButtonColor: "#1A4D2E",
            });
            this.opensession();
          } else {
            //alert(response.data);
            this.$swal({
              title: "이미 생성되어 있습니다.",
              //text: "이메일 인증 후 완료 처리 됩니다.",
              icon: "info",
              confirmButtonText: "확인",
              confirmButtonColor: "#1A4D2E",
            });
          }
        })
        .catch((error) => {
          console.log(error);
        });
      // console.log(this.sessionstate);
    },
    async roomClose() {
      await http
        .post("/session/close/" + this.loginUser.id)
        .then((response) => {
          // console.log(response);
          if (response.data === "success") {
            //alert("모임세션이 종료되었습니다.");
            this.$swal({
              title: "모임세션이 종료되었습니다.",
              //text: "이메일 인증 후 완료 처리 됩니다.",
              icon: "success",
              confirmButtonText: "확인",
              confirmButtonColor: "#1A4D2E",
            });
            this.closesession();
            var val = 0;
            socket.emit("session", val);
          } else {
            //alert(response.data);
            this.$swal({
              title: "이미 종료되어 있습니다.",
              //text: "이메일 인증 후 완료 처리 됩니다.",
              icon: "info",
              confirmButtonText: "확인",
              confirmButtonColor: "#1A4D2E",
            });
          }
        })
        .catch((error) => {
          console.log(error);
        });
      // console.log(this.sessionstate);
    },
    change() {
      this.getSheetInfo(this.meeting);
      this.getBookImg(this.sheet.isbn);
    },
    async createSheet() {
      await http.post("/sheet/" + this.loginUser.id).then((response) => {
        // console.log(response);
        if (response.data === "success") {
          this.$swal({
            title: "활동지 생성이 완료되었습니다 !",
            text: "활동지를 조회하고 수정해보세요",
            icon: "info",
            confirmButtonText: "확인",
            confirmButtonColor: "#1A4D2E",
          });
          this.$router.go();
        } else {
          // console.log(response);
          this.$swal({
            title: response.data,
            icon: "warning",
            confirmButtonText: "확인",
            confirmButtonColor: "#1A4D2E",
          });
        }
      });
    },

    async deleteMeetingButton() {
      // console.log(this.loginUser);
      await this.deleteMeeting(this.meeting.id);
      const token = sessionStorage.getItem("access-token");
      this.getUserInfo(token);
      // console.log(this.loginUser);
      setTimeout(this.$router.go({ name: "LeadGroupView" }), 2000);
    },

    update() {
      const date = this.sheet.date;
      const hostId = this.sheet.hostId;
      const people = this.sheet.people;
      this.updateSheet(this.sheet);
      this.sheet.hostId = hostId;
      this.sheet.date = date;
      this.sheet.people = people;
      this.sheetOpen = false;
    },

    update1() {
      const userId = this.loginUser.id;
      const updatemeeting = this.meeting;
      this.updateMeetingInroduce({ userId, updatemeeting });
      this.infoOpen = false;
    },
    update2() {
      const userId = this.loginUser.id;
      const updatemeeting = this.meeting;
      this.updateMeetingFormat({ userId, updatemeeting });
      this.formatOpen = false;
    },
    update3() {
      const userId = this.loginUser.id;
      const updatemeeting = this.meeting;
      this.updateMeetingTarget({ userId, updatemeeting });
      this.targetOpen = false;
    },
    update4() {
      const userId = this.loginUser.id;
      const updatemeeting = this.meeting;
      this.updateMeetingDay({ userId, updatemeeting });
      this.dayOpen = false;
    },
    refresh() {
      this.getSheetInfo(this.meeting);
    },
    // getBook(isbn) {
    //   http.get("/search/isbn/" + isbn).then((res) => {
    //     this.bookImgURL = res.data.items[0].image;
    //   });
    // },
  },
  created() {
    this.getMeetingInfo(this.loginUser.id);
    this.getSheetInfo(this.meeting); // 활동없을때 오류 무시함. -> 활동는 항상 있는 상태로 변경
    // console.log(this.meeting);
    // this.getBook(this.sheet.isbn);
  },
};
</script>
<style>
.btn-secondary {
  color: black;
  background-color: #f4ece1;
  border-color: #f4ece1;
  border-color: black;
}
.btn-secondary.active {
  background-color: #1a4d2e;
  border-color: #f4ece1;
  color: black;
}
.btn-secondary:hover {
  background-color: #1a4d2e;
}
</style>
<style scoped>
.color {
  height: 100%;
  background-color: black;
}
h3 {
  color: #000;
  display: inline-block;
  margin: 0;
  text-transform: uppercase;
  cursor: pointer;
}
h3:after {
  display: block;
  content: "";
  border-bottom: double 3.5px #1a4d2e;
  transform: scaleX(0);
  transition: transform 250ms ease-in-out;
}

h3:hover {
  transform: scale(1.15);
  transition-duration: 0.5s;
}
.action {
  transform: scale(1.15);
  transition-duration: 0.5s;
}
span {
  transform: scale(1.15);
  transition-duration: 0.5s;
}
/* ///////////////////// */
.m-btn {
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
.m-btn:hover {
  letter-spacing: 2px;
  transform: scale(1.2);
  cursor: pointer;
  background-color: #f4ece1;
  color: black;
}
.m-btn:active {
  transform: scale(1.5);
}
.m-btn-simple {
  background-color: #f4ece1;
  color: black;
}
.m-btn-simple:hover {
  letter-spacing: 2px;
  transform: scale(1.2);
  cursor: pointer;
  background-color: #f4ece1;
  color: black;
}
p {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, "Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol";
  color: black;
  font-size: 1.7rem;
  font-weight: 550;
}
</style>
