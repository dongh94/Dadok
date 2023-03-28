<template>
  <div id="rtcsheetbox">
    <b-card id="rtcsheetcard" class="shadow-lg rounded" style="border-radius: 50px">
      <!-- <div class="mt-4 card container-md shadow m-3" style="width: 700px">  
    <div class="m-3"> -->
      <b-form>
        <!-- <b-row>
          <b-col sm="4"></b-col>
          <b-col sm="4" style="text-align: center; font-size: 40px; font-weight: 600">{{ meeting.name }}</b-col>
          <b-col sm="4"></b-col>
          <br />
        </b-row> -->
        <br />
        <b-row></b-row>
        <b-row>
          <b-col sm="3"></b-col>
          <b-col sm="6"><b-form-input class="shadow" style="border-radius: 10px" v-model="sheettopic"></b-form-input></b-col>
          <b-col sm="3"></b-col>
        </b-row>
        <br />
        <b-row>
          <b-col sm="2"><b-form-input class="shadow" style="border-radius: 10px" v-model="sheetmembers.member1"></b-form-input></b-col>
          <b-col sm="10"><b-form-textarea class="shadow" style="border-radius: 10px" v-model="sheetcontext.opinion1"></b-form-textarea></b-col>
        </b-row>
        <br />
        <b-row>
          <b-col sm="2"><b-form-input class="shadow" style="border-radius: 10px" v-model="sheetmembers.member2"></b-form-input></b-col>
          <b-col sm="10"><b-form-textarea class="shadow" style="border-radius: 10px" v-model="sheetcontext.opinion2"></b-form-textarea></b-col>
        </b-row>
        <br />
        <b-row>
          <b-col sm="2"><b-form-input class="shadow" style="border-radius: 10px" v-model="sheetmembers.member3"></b-form-input></b-col>
          <b-col sm="10"><b-form-textarea class="shadow" style="border-radius: 10px" v-model="sheetcontext.opinion3"></b-form-textarea></b-col>
        </b-row>
        <br />
        <b-row>
          <b-col sm="2"><b-form-input class="shadow" style="border-radius: 10px" v-model="sheetmembers.member4"></b-form-input></b-col>
          <b-col sm="10"> <b-form-textarea class="shadow" style="border-radius: 10px" v-model="sheetcontext.opinion4"></b-form-textarea></b-col>
        </b-row>
        <br />
        <b-row>
          <b-col sm="2"><b-form-input class="shadow" style="border-radius: 10px" v-model="sheetmembers.member5"></b-form-input></b-col>
          <b-col sm="10"><b-form-textarea class="shadow" style="border-radius: 10px" v-model="sheetcontext.opinion5"></b-form-textarea></b-col>
        </b-row>
        <br />
        <b-row>
          <b-col sm="2"><b-form-input class="shadow" style="border-radius: 10px" v-model="sheetmembers.member6"></b-form-input></b-col>
          <b-col sm="10"><b-form-textarea class="shadow" style="border-radius: 10px" v-model="sheetcontext.opinion6"></b-form-textarea></b-col>
        </b-row>
        <br />
      </b-form>
      <br />
      <div class="d-flex justify-content-end">
        <button class="m-btn" @click="sheetset" style="background-color: black; color: white">활동지불러오기</button>
        <button class="m-btn mx-3" @click="sheetup" style="background-color: black; color: white">전달하기</button>
        <button class="m-btn" @click="sheetsave" style="background-color: black; color: white">저장</button>
      </div>
      <br />
    </b-card>
  </div>
</template>

<script>
import io from "socket.io-client";

import { mapActions, mapState } from "vuex";
const socket = io("http://i7b109.p.ssafy.io:3001", { transports: ["websocket"] });
const chatStore = "chatStore";

export default {
  data() {
    return {
      sheettopic: "",
      sheetmembers: {},
      sheetcontext: {},
    };
  },
  computed: {
    ...mapState(chatStore, ["UserName", "meethostid"]),
    ...mapState(["sheet", "meeting", "loginUser"]),
  },
  methods: {
    ...mapActions(["getSheetInfo", "getMeetingInfo", "updateSheet", "getSheetInfo_id"]),
    setting() {
      this.sheettopic = this.sheet.topic;
      this.sheetmembers.member1 = this.sheet.member1;
      this.sheetmembers.member2 = this.sheet.member2;
      this.sheetmembers.member3 = this.sheet.member3;
      this.sheetmembers.member4 = this.sheet.member4;
      this.sheetmembers.member5 = this.sheet.member5;
      this.sheetmembers.member6 = this.sheet.member6;
      this.sheetcontext.opinion1 = this.sheet.opinion1;
      this.sheetcontext.opinion2 = this.sheet.opinion2;
      this.sheetcontext.opinion3 = this.sheet.opinion3;
      this.sheetcontext.opinion4 = this.sheet.opinion4;
      this.sheetcontext.opinion5 = this.sheet.opinion5;
      this.sheetcontext.opinion6 = this.sheet.opinion6;
    },
    async sheetset() {
      //console.log(this.loginUser.id);
      //this.getMeetingInfo(this.loginUser.id);
      //console.log(this.meeting);
      //this.getSheetInfo(this.meeting);
      // async function sh() {
      //   await this.getSheetInfo_id(this.meethostid);
      // }
      // sh();
      // console.log(this.meethostid);
      await this.getSheetInfo_id(this.meethostid);
      // console.log(this.sheet);
      setTimeout(function () {
        // console.log("log");
      }, 3000);
      await this.setting();
    },
    sheetsave() {
      this.sheet.topic = this.sheettopic;
      this.sheet.member1 = this.sheetmembers.member1;
      this.sheet.member2 = this.sheetmembers.member2;
      this.sheet.member3 = this.sheetmembers.member3;
      this.sheet.member4 = this.sheetmembers.member4;
      this.sheet.member5 = this.sheetmembers.member5;
      this.sheet.member6 = this.sheetmembers.member6;
      this.sheet.opinion1 = this.sheetcontext.opinion1;
      this.sheet.opinion2 = this.sheetcontext.opinion2;
      this.sheet.opinion3 = this.sheetcontext.opinion3;
      this.sheet.opinion4 = this.sheetcontext.opinion4;
      this.sheet.opinion5 = this.sheetcontext.opinion5;
      this.sheet.opinion6 = this.sheetcontext.opinion6;
      this.updateSheet(this.sheet);
    },
    sheetup() {
      var val = [this.sheettopic, this.sheetmembers, this.sheetcontext];
      socket.emit("sheet", val);
    },
    syncUpdate() {
      const date = this.sheet.date;
      const hostId = this.sheet.hostId;
      const people = this.sheet.people;
      this.updateSheet(this.sheet);
      this.sheet.hostId = hostId;
      this.sheet.date = date;
      this.sheet.people = people;
    },
  },
  created() {
    socket.on("sheet", (val) => {
      this.sheettopic = val[0];
      this.sheetmembers = val[1];
      this.sheetcontext = val[2];
      // console.log("sheetupdate");
    });
  },
};
</script>

<style>
#rtcsheetbox {
  height: 100%;
  z-index: 3;
}

#rtcsheetcard {
  /* height: 100%; */
  z-index: 3;
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
  /* letter-spacing: 2px; */
  /* transform: scale(1.2); */
  cursor: pointer;
  background-color: #f4ece1;
  color: black;
}
.m-btn:active {
  /* transform: scale(1.5); */
}
.m-btn-simple {
  background-color: #f4ece1;
  color: black;
}
.m-btn-simple:hover {
  /* letter-spacing: 2px; */
  transform: scale(1.05);
  cursor: pointer;
  background-color: #f4ece1;
  color: black;
}
</style>
