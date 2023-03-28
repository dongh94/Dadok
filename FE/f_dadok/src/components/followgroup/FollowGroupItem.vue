<template>
  <div id="member" class="accordion" role="tablist">
    <ul>
      <li v-for="fmeeting in fmeetings" :key="fmeeting.id">
        <b-card no-body class="mb-1">
          <b-card-header header-tag="header" class="p-1" role="tab">
            <b-button block v-b-toggle="'' + fmeeting.id" style="background-color: #f4ece1; border: 0">
              <div class="">
                <b-row class="d-flex align-items-center" style="margin: 0">
                  <b-col cols="5" class="d-flex justify-content-start">
                    <h4 class="m-0" style="color: black">{{ fmeeting.name }}</h4>
                  </b-col>

                  <b-col cols="7" style="padding: 0" class="d-flex justify-content-end">
                    <div>
                      <button
                        class="m-btn m-btn-simple mx-4"
                        @click="sheetInfo($event)"
                        v-b-modal="`${fmeeting.id}`"
                        v-b-toggle="'' + fmeeting.id"
                        style="background-color: smokewhite; border: 0; color: black"
                      >
                        <b-icon icon="file-earmark-spreadsheet"></b-icon>&nbsp;&nbsp;활동지 작성
                      </button>

                      <button
                        class="m-btn m-btn-simple mx-2"
                        v-b-toggle="'' + fmeeting.id"
                        style="background-color: #1a4d2e; border: 0; color: white; opacity: 0.9"
                        @click="videoIn(fmeeting.id, fmeeting.name, fmeeting.host)"
                      >
                        <b-icon icon="box-arrow-in-right"></b-icon>&nbsp;&nbsp;입장하기
                      </button>
                    </div>
                  </b-col>
                </b-row>
                <!-- 활동지 조회 모달 -->
                <b-modal :id="`${fmeeting.id}`" scrollable size="lg" :title="meeting.name + ' 활동지'">
                  <div style="text-align: center">
                    <img :src="sheet.img_path" height="60%" width="40%" v-if="sheet.img_path" />
                  </div>

                  <b-form>
                    <b-table>
                      <b-row></b-row>
                      <b-row></b-row>
                      <b-row></b-row>
                    </b-table>
                    <b-row>
                      <b-col sm="8"></b-col>
                      <b-col sm="4" style="text-align: center; font-weight: 700"> 모임 날짜: {{ sheet.date }}<br /></b-col>
                    </b-row>
                    <br />
                    <hr />
                    <br />
                    <b-row>
                      <b-col sm="3"></b-col>
                      <b-col sm="6">
                        <div style="font-size: 18px; text-align: center; border-style: inset; border-color: #1a4d2e; font-weight: 800; border-radius: 20px" v-if="sheetOpen === false">
                          {{ sheet.topic }}
                        </div>
                        <b-form-input class="shadow" style="border-radius: 10px" v-if="sheetOpen === true" v-model="sheet.topic"></b-form-input>
                      </b-col>
                      <b-col sm="3"></b-col>
                    </b-row>

                    <br />
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
            </b-button>
          </b-card-header>
          <b-collapse :id="'' + fmeeting.id" visible accordion="my-accordion2" role="tabpanel">
            <b-card-body class="mb-3">
              <b-card-text>
                <b-row>
                  <b-col cols="4">
                    <img :src="`${fmeeting.img}`" alt="" style="width: 300px; height: 300px" v-if="`${fmeeting.img}`" />
                    <img :src="require(`@/assets/${fmeeting.interest}.jpg`)" style="width: 100%; height: 100%" v-else />
                    <p style="font-size: 20px">#{{ fmeeting.interest }}#{{ fmeeting.theme }}</p>
                  </b-col>
                  <b-col style="font-size: 25px">
                    <div><b-icon icon="calendar2-week"></b-icon>&nbsp;모임시작: {{ fmeeting.startDay }}</div>
                    <div><b-icon icon="flower2"></b-icon>&nbsp;모임요일: {{ fmeeting.schedule }}</div>
                    <div><b-icon icon="people"></b-icon>&nbsp;인원현황: {{ fmeeting.capacity }}/{{ fmeeting.people }}</div>
                    <div><b-icon icon="receipt"></b-icon>&nbsp;모임소개</div>
                    <div>{{ fmeeting.introduce }}</div>
                  </b-col>
                </b-row>
              </b-card-text>
            </b-card-body>
          </b-collapse>
        </b-card>
      </li>
    </ul>
  </div>
</template>

<script>
import { mapState, mapActions, mapGetters } from "vuex";
import http from "@/util/http-common.js";
const chatStore = "chatStore";
export default {
  data() {
    return {
      host: "",
      sheetOpen: false,
      meeting: {},
    };
  },
  computed: {
    ...mapState(["loginUser", "fmeetings", "sheet", "bookimgurl"]),
    ...mapState(chatStore, ["sessionstate"]),
  },
  methods: {
    ...mapActions(chatStore, ["getsession"]),
    ...mapActions(["getFMeetings", "getSheetInfo", "updateSheet"]),
    ...mapGetters(["checkUserInfo", "checkBookImg"]),
    sheetInfo: async function (event) {
      const meetingid = event.target.getAttribute("aria-controls");

      await http.get("/meeting/detail/" + meetingid).then((response) => {
        this.meeting = response.data;
        this.host = response.data.host;

        this.getSheetInfo(this.meeting);
      });
      await http.get("/sheet/" + meetingid).then(() => {});
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
    async videoIn(id, name, host) {
      // console.log("입장");
      // console.log("아이디 보낼게 " + id);
      // console.log("이름 보낼게 " + name);
      await this.getsession(host);
      // console.log("현재 세션정보" + this.sessionstate);
      if (this.sessionstate === 1) {
        this.$router.push({
          name: "VideoView",
          params: { meetid: id, meetname: name },
        });
      } else {
        this.$swal({
          title: "모임세션이 닫혀있습니다.",
          icon: "info",
          confirmButtonText: "확인",
          confirmButtonColor: "#1A4D2E",
        });
      }
    },
  },
  mounted() {
    this.getFMeetings(this.loginUser.id);
    this.getSheetInfo(this.meeting);
  },
};
</script>

<style>
ul {
  list-style: none;
  padding-left: 0px;
}
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
</style>
