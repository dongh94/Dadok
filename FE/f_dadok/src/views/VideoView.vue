<template>
  <div id="main-container">
    <div style="margin: 0; padding: 0">
      <div id="headerbackimgadd">
        <br />
        <div class="justify-content-center">
          <div style="text-align: center; font-size: 40px; font-weight: 600" sm="8">
            <div class="d-flex justify-content-center">
              <img src="@/assets/real_logo.png" alt="main_logo" style="width: 4rem; margin-top: 5px" class="" />
              <div style="color: white; margin-bottom: 7px" class="mx-1">
                {{ meetingName }}
              </div>
              <img src="@/assets/logout.png" alt="" style="width: 2.4rem; height: 2.5rem; margin-top: 1rem" class="exitmoon mx-2" id="buttonLeaveSession" @click="Session_leave" />
            </div>
          </div>
        </div>
        <b-row>
          <b-col cols="2"> </b-col>
          <b-col cols="8" class="d-flex justify-content-center">
            <b-button style="background-color: black; color: white; border: 0; border-radius: 20px" v-b-toggle.sidebar-right class="mx-1 m-btn m-btn-simple"
              ><img class="" src="@/assets/chat.png" alt="" style="width: 1.6rem"
            /></b-button>

            <button style="background-color: black; color: white; border: 0; border-radius: 20px" v-if="isCamera == 1" class="mx-1 m-btn m-btn-simple" @click="cameraoff()">
              <img class="" src="@/assets/no-video.png" alt="" style="width: 1.6rem" />
            </button>
            <button style="background-color: black; color: white; border: 0; border-radius: 20px" v-if="isCamera == 0" class="mx-1 m-btn m-btn-simple" @click="cameraoff()">
              <img class="" src="@/assets/video-camera.png" alt="" style="width: 1.6rem" />
            </button>
            <button style="background-color: black; color: white; border: 0; border-radius: 20px" v-if="isSpeake == 1" class="mx-1 m-btn m-btn-simple" @click="spkoff()">
              <img class="" src="@/assets/volume-mute.png" alt="" style="width: 1.6rem" />
            </button>
            <button style="background-color: black; color: white; border: 0; border-radius: 20px" v-if="isSpeake == 0" class="mx-1 m-btn m-btn-simple" @click="spkoff()">
              <img class="" src="@/assets/speaker-filled-audio-tool.png" alt="" style="width: 1.6rem" />
            </button>
            <div class="dropdown">
              <button style="background-color: black; color: white; border: 0; border-radius: 20px; height: 3.5rem" class="mx-1 m-btn m-btn-simple">
                <img class="" src="@/assets/happy.png" alt="" style="width: 1.6rem" />
              </button>
              <div class="dropdown-content">
                <div class="d-flex">
                  <a href="#"> <img @click="face2()" class="" src="@/assets/heartemoji.png" alt="" style="width: 1.6rem" /></a>
                  <a href="#"> <img @click="face()" class="" src="@/assets/youyou.png" alt="" style="width: 1.6rem" /></a>
                  <a href="#"> <img @click="face2()" class="" src="@/assets/party.png" alt="" style="width: 1.6rem" /></a>
                </div>

                <div class="d-flex">
                  <a href="#"> <img @click="face3()" class="" src="@/assets/crying.png" alt="" style="width: 1.6rem" /></a>
                  <a href="#"> <img @click="face()" class="" src="@/assets/zzzz.png" alt="" style="width: 1.6rem" /></a>
                  <a href="#"> <img @click="face()" class="" src="@/assets/angry.png" alt="" style="width: 1.6rem" /></a>
                </div>
              </div>
            </div>
            <button style="background-color: black; color: white; border: 0; border-radius: 20px" class="mx-1 m-btn m-btn-simple" @click="changecam()">
              <img class="" src="@/assets/updown.png" alt="" style="width: 1.6rem" />
            </button>
            <button style="background-color: black; color: white; border: 0; border-radius: 20px" class="mx-1 m-btn m-btn-simple" @click="RLCH()">
              <img class="" src="@/assets/transfer.png" alt="" style="width: 1.6rem" />
            </button>
            <button style="background-color: black; color: white; border: 0; border-radius: 20px" class="mx-1 m-btn m-btn-simple" @click="dis()">
              <img class="" src="@/assets/reload.png" alt="" style="width: 1.6rem" />
            </button>
          </b-col>
          <b-col cols="2">
            <b-button v-if="meeting.host == loginUser.id" class="m-btn m-btn-simple" style="background-color: black; color: white; border: 0" @click="Session_Close">
              <img class="mb-2" src="@/assets/crown.png" alt="" style="width: 1.6rem" />&nbsp;세션 종료</b-button
            >
          </b-col>
        </b-row>

        <br />
      </div>

      <b-row>
        <div id="uservideo_left">
          <b-row>
            <user-video :stream-manager="mainStreamManager" :key="componentkey" />
          </b-row>
          <b-row>
            <user-video :stream-manager="this.subscribers[1]" @click="updateMainVideoStreamManager(this.subscribers[0])" />
          </b-row>
          <b-row>
            <user-video :stream-manager="this.subscribers[3]" @click="updateMainVideoStreamManager(this.subscribers[1])" />
          </b-row>
        </div>

        <b-col><rtc-sheet class="width: 40%;" /> </b-col>

        <div id="uservideo_right">
          <b-row> <user-video :stream-manager="this.subscribers[0]" @click="updateMainVideoStreamManager(this.subscribers[2])" /></b-row>
          <b-row> <user-video :stream-manager="this.subscribers[2]" @click="updateMainVideoStreamManager(this.subscribers[3])" /></b-row>
          <b-row> <user-video :stream-manager="this.subscribers[4]" @click="updateMainVideoStreamManager(this.subscribers[4])" /></b-row>
        </div>
      </b-row>
      <br />
      <!-- <b-row>
        <b-col sm="10"></b-col>
        <b-col sm="1"> <input class="btn btn-large btn-danger" type="button" id="buttonCloseSession" @click="Session_Close" value="세션종료하기" /></b-col>
        <b-col sm="1"> <input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="Session_leave" value="퇴실하기" /></b-col>
      </b-row> -->
      <!-- <hr /> -->
      <!-- <b-row class="justify-content-center">
        <b-col sm="1.5"><b-button v-b-toggle.sidebar-right class="btn-small btn-warning">채팅on/off</b-button></b-col
        >&nbsp;&nbsp; <b-col sm="1.5" class="d-flex justify-content-center"><button class="btn btn-small btn-info" @click="cameraoff()">카메라on/off</button></b-col>&nbsp;&nbsp;
        <b-col sm="1.5" class="d-flex justify-content-center"><button class="btn btn-small btn-primary disabled" @click="spkoff()">스피커on/off</button></b-col>&nbsp;&nbsp;
        <b-col sm="1.5" class="d-flex justify-content-center"><button class="btn btn-small btn-primary" @click="face()">얼굴감지</button></b-col>&nbsp;&nbsp;
        <b-col sm="1.5" class="d-flex justify-content-center"><button class="btn btn-small btn-primary active" @click="changecam()">카메라반전</button></b-col>&nbsp;&nbsp;
        <b-col sm="1.5" class="d-flex justify-content-center"><button class="btn btn-small btn-secondary" @click="dis()">해제</button></b-col>&nbsp;&nbsp;
<<<<<<< HEAD

=======
        <div>
          <button @click="face()">얼굴감지</button>
          <button @click="face2()">얼굴이모티콘</button>
          <button @click="face3()">우는이모티콘</button>
        </div>
        <b-sidebar id="sidebar-right" right shadow padding="10px">
          <Chat />
        </b-sidebar>
>>>>>>> kang
      </b-row>
      <hr /> -->
      <b-sidebar id="sidebar-right" right shadow padding="10px">
        <Chat />
      </b-sidebar>

      <!-- <div id="main-video" class="col-md-6">
        <user-video :stream-manager="mainStreamManager" :key="componentkey" />
      </div> -->
      <!-- <div id="video-container" class="col-md-6"> -->
      <!-- <user-video :stream-manager="publisher" @click="updateMainVideoStreamManager(publisher)" :key="componentkey" /> -->
      <!-- <user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click="updateMainVideoStreamManager(sub)" /> -->
      <!-- </div> -->
      <!-- <input id="textstat" v-model="textstat" /> -->
      <!-- <div><button @click="textcam()">상태메시지</button></div> -->
      <!-- <div>
        <input type="text" />
        <textarea name="" id="" cols="30" rows="10" v-model="textval"></textarea>
      </div> -->

      <!-- <Chat />
      <div><button @click="cameraoff()">카메라on/off</button></div>
      <div><button @click="spkoff()">스피커on/off</button></div>
      <div><button @click="micoff()">마이크on/off</button></div>
      <div><button @click="emoticon()">이모티콘</button></div>
      <div>
        <button @click="face()">얼굴감지</button>
        <button @click="face2()">얼굴이모티콘</button>
        <button @click="face3()">우는이모티콘</button>
      </div>
      <div><button @click="changecam()">카메라반전</button></div>
      <div><button @click="RLCH()">카메라좌우</button></div>
      <input id="textstat" v-model="textstat" />
      <div><button @click="textcam()">상태메시지</button></div>
      <div><button @click="dis()">해제</button></div>
      <div>
        <input type="text" />
        <textarea name="" id="" cols="30" rows="10" v-model="textval"></textarea>
      </div> -->
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "@/components/videodir/UserVideo.vue";
import Chat from "@/components/videodir/Chat.vue";
import { mapState, mapActions, mapGetters } from "vuex";
import RtcSheet from "@/components/rtc/RtcSheet.vue";
import io from "socket.io-client";
const socket = io("http://i7b109.p.ssafy.io:3001", { transports: ["websocket"] });

axios.defaults.headers.post["Content-Type"] = "application/json";

//const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_URL = "https://i7b109.p.ssafy.io:4443";
const OPENVIDU_SERVER_SECRET = "Dadok";

const chatStore = "chatStore";

export default {
  name: "App",
  components: {
    UserVideo,
    Chat,
    RtcSheet,
  },

  data() {
    return {
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      isCamera: false,
      isSpeake: false,
      componentkey: 0,
      filterstate: false,

      textstat: "",
      meetid: "",
      meetname: "",

      // mySessionId: "SessionA",
      // myUserName: "Participant" + Math.floor(Math.random() * 100),
      meetingName: "",
      mySessionId: "",
      myUserName: "",
    };
  },
  watch: {
    change(val) {
      console.log("change : " + val);
    },
  },
  created() {
    this.getUserInfo();
    this.sethostid(this.$route.params.meetid);
    console.log(this.loginUser);
    console.log(this.$route.params.meetid);
    console.log(this.$route.params.meetname);
    this.mySessionId = `Session${this.$route.params.meetid}`;
    this.meetingName = this.$route.params.meetname;
    this.myUserName = this.loginUser.name;
    console.log("현재 세션 아이디: " + this.mySessionId);
    this.joinSession();
  },
  mounted() {
    socket.on("session", (val) => {
      console.log(val);
      if (val == 0) {
        this.$swal({
          title: "모임세션이 종료되었습니다.",
          icon: "info",
          confirmButtonText: "확인",
          confirmButtonColor: "#1A4D2E",
        }).then(() => {
          this.leaveSession();
          //console.log("확인");
          //this.$router.go();
        });
      }
    });
  },
  computed: {
    ...mapState(["meeting", "loginUser", "sheet"]),
    ...mapState(chatStore, ["sessionstate"]),
    ...mapGetters(chatStore, ["changesession"]),
    change() {
      return this.changesession;
    },
  },
  methods: {
    ...mapGetters(["checkUserInfo"]),
    //...mapMutations(ChatStore, ["CREATE_SET_USERNAME"]),
    ...mapActions(chatStore, ["setusername", "getUserInfo", "sethostid", "closesession"]),
    Session_Close() {
      console.log("Ccc" + this.changesession);
      http
        .post("/session/close/" + this.loginUser.id)
        .then((response) => {
          console.log(response);
          // this.$swal({
          //   title: "모임세션이 종료되었습니다.",
          //   icon: "success",
          //   confirmButtonText: "확인",
          //   confirmButtonColor: "#1A4D2E",
          // });
          this.closesession();

          var val = 0;
          socket.emit("session", val);
          this.$router.push({
            name: "LeadGroupView",
          });
        })
        .catch((error) => {
          console.log(error);
        });
    },
    Session_leave() {
      this.$swal({
        title: "퇴실하였습니다.",
        icon: "success",
        confirmButtonText: "확인",
        confirmButtonColor: "#1A4D2E",
      }).then(() => {
        this.leaveSession();
      });
    },
    RLCH() {
      if (this.filterstate) {
        this.dis();
      }
      let command = `videoflip method=horizontal-flip`;
      this.publisher.stream
        .applyFilter("GStreamerFilter", { command: command })
        .then(() => {
          console.log("success!!!");
        })
        .catch((e) => {
          console.log("error :: ", e);
        });
      this.filterstate = true;
    },
    textcam() {
      if (this.filterstate) {
        this.dis();
      }
      // setTimeout(function () {
      //   console.log("Works!");
      // }, 5000);
      let command = `textoverlay text="${this.textstat}" valignment=top halignment=right font-desc="Cantarell 25"`;
      this.publisher.stream
        .applyFilter("GStreamerFilter", { command: command })
        .then(() => {
          console.log("success!!!");
          console.log(this.textstat);
        })
        .catch((e) => {
          console.log("error :: ", e);
        });
      this.filterstate = true;
    },
    changecam() {
      if (this.filterstate) {
        this.dis();
      }
      let command = `videoflip method=vertical-flip`;
      this.publisher.stream
        .applyFilter("GStreamerFilter", { command: command })
        .then(() => {
          console.log("success!!!");
        })
        .catch((e) => {
          console.log("error :: ", e);
        });
      this.filterstate = true;
    },
    async face() {
      if (this.filterstate) {
        await this.dis();
      }
      await this.publisher.stream.applyFilter("FaceOverlayFilter").then((filter) => {
        var offsetX = "-0.2F";
        var offsetY = "-0.8F";
        var width = "1.3F";
        var height = "1.0F";
        filter.execMethod("setOverlayedImage", {
          //uri: "https://kuku-keke.com/wp-content/uploads/2020/04/2496_6.png",
          //uri: "https://cdn-icons.flaticon.com/png/512/4160/premium/4160763.png?token=exp=1660566185~hmac=e92ce6886b5ffa6d584c798caa979891",
          uri: "https://firebasestorage.googleapis.com/v0/b/dadok-c9f63.appspot.com/o/images%2Fhat.png?alt=media&token=083104c2-7aec-4ba9-9366-022fb79360bd",
          offsetXPercent: offsetX,
          offsetYPercent: offsetY,
          widthPercent: width,
          heightPercent: height,
        });
      });
      this.filterstate = true;
    },
    async face2() {
      if (this.filterstate) {
        await this.dis();
      }
      await this.publisher.stream.applyFilter("FaceOverlayFilter").then((filter) => {
        var offsetX = "-0.7F";
        var offsetY = "-0.7F";
        var width = "2.3F";
        var height = "2.3F";
        filter.execMethod("setOverlayedImage", {
          //uri: "https://e7.pngegg.com/pngimages/341/929/png-clipart-emoji-emoticon-smiley-wink-facebook-emoji-smiley-emoticon.png",
          //uri: "https://cdn-icons.flaticon.com/png/512/4160/premium/4160731.png?token=exp=1660565643~hmac=c8cab1c858e56f8cc6e79f02e96eb7ad",
          uri: "https://firebasestorage.googleapis.com/v0/b/dadok-c9f63.appspot.com/o/images%2Fheart.png?alt=media&token=06efe5c6-43fa-4753-8b13-ec69341affd8",
          offsetXPercent: offsetX,
          offsetYPercent: offsetY,
          widthPercent: width,
          heightPercent: height,
        });
      });
      this.filterstate = true;
    },
    async face3() {
      if (this.filterstate) {
        await this.dis();
      }
      await this.publisher.stream.applyFilter("FaceOverlayFilter").then((filter) => {
        var offsetX = "-0.7F";
        var offsetY = "-0.7F";
        var width = "2.3F";
        var height = "2.3F";
        filter.execMethod("setOverlayedImage", {
          //uri: "https://cdn-icons.flaticon.com/png/512/4160/premium/4160755.png?token=exp=1660566191~hmac=62d1c2798a1805b6aed3cae4db510ae7",
          uri: "https://firebasestorage.googleapis.com/v0/b/dadok-c9f63.appspot.com/o/images%2Fsad.png?alt=media&token=35d96278-7edf-470a-9f72-6e61af008abd",
          offsetXPercent: offsetX,
          offsetYPercent: offsetY,
          widthPercent: width,
          heightPercent: height,
        });
      });
      this.filterstate = true;
    },
    async dis() {
      await this.publisher.stream
        .removeFilter()
        .then(() => {
          console.log("-- Filter removed --");
        })
        .catch((error) => {
          console.error(error);
        });
      this.filterstate = false;
    },
    emoticon() {
      if (this.filterstate) {
        this.dis();
      }
      let command = `gdkpixbufoverlay location=https://cdn-icons.flaticon.com/png/512/4160/premium/4160731.png?token=exp=1660565643~hmac=c8cab1c858e56f8cc6e79f02e96eb7adoffset-x=10 offset-y=10 overlay-height=200 overlay-width=200`;
      //let command = `textoverlay text="Embedded text" valignment=top halignment=right font-desc="Cantarell 25"`;
      this.publisher.stream
        .applyFilter("GStreamerFilter", { command: command })
        .then(() => {
          console.log("success!!!");
        })
        .catch((e) => {
          console.log("error :: ", e);
        });
      this.filterstate = true;
    },
    spkoff() {
      this.publisher.publishAudio(this.isSpeake);
      this.isSpeake = !this.isSpeake;
    },
    micoff() {
      // console.log(document.querySelector("video").volume);
      // document.querySelector("video").volume = 0.0;
      // console.log(document.querySelector("video").volume);
      var timestamp = this.publisher.stream.creationTime;
      var date = new Date(timestamp);
      console.log(date.getTime());
      console.log(date);
    },
    cameraoff() {
      this.publisher.publishVideo(this.isCamera);
      this.isCamera = !this.isCamera;
    },
    joinSession() {
      this.setusername(this.myUserName);
      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu();

      // --- Init a session ---
      this.session = this.OV.initSession();

      // --- Specify the actions when events take place in the session ---

      // On every new Stream received...
      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
      });

      // On every Stream destroyed...
      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });

      // On every asynchronous exception...
      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      // --- Connect to the session with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      this.getToken(this.mySessionId).then((token) => {
        this.session
          .connect(token, { clientData: this.myUserName })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---

            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "350x260", // The resolution of your video "275x270",
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            //publisher.stream.userName = "abc";
            // Kurento
            publisher.subscribeToRemote(true);

            this.mainStreamManager = publisher;
            this.publisher = publisher;

            // --- Publish your stream ---

            this.session.publish(this.publisher);
          })
          .catch((error) => {
            console.log("There was an error connecting to the session:", error.code, error.message);
          });
      });

      window.addEventListener("beforeunload", this.leaveSession);
    },

    leaveSession() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();

      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;

      window.removeEventListener("beforeunload", this.leaveSession);
      this.$router.push({
        name: "MypageLibraryEx",
        params: { session: 0 },
      });
      //this.$router.go(this.$router.push({ name: "MypageLibraryEx" }));
      //this.$router.push({ name: "HomeView" });
    },

    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },

    /**
     * --------------------------
     * SERVER-SIDE RESPONSIBILITY
     * --------------------------
     * These methods retrieve the mandatory user token from OpenVidu Server.
     * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
     * the API REST, openvidu-java-client or openvidu-node-client):
     *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
     *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
     *   3) The Connection.token must be consumed in Session.connect() method
     */

    getToken(mySessionId) {
      return this.createSession(mySessionId).then((sessionId) => this.createToken(sessionId));
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-session
    createSession(sessionId) {
      console.log(sessionId);
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
            JSON.stringify({
              customSessionId: sessionId,
            }),
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.id))
          .catch((error) => {
            if (error.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`
                )
              ) {
                location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
              }
              reject(error.response);
            }
          });
      });
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-connection
    createToken(sessionId) {
      console.log(sessionId);
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
            {
              // filter
              type: "WEBRTC",
              role: "PUBLISHER",
              kurentoOptions: {
                allowedFilters: ["GStreamerFilter", "FaceOverlayFilter"],
              },
            },
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.token))
          .catch((error) => reject(error.response));
      });
    },
  },
};
</script>
<style scoped>
#main-container {
  min-width: 1480px;
  max-width: 1500px;
  justify-content: center;
}
.row {
  margin: 0px;
  padding: 0px;
}
.exitmoon:hover {
  cursor: pointer;
  transform: scale(1.1);
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
  transform: scale(1.2);
  cursor: pointer;
  background-color: #f4ece1;
  color: black;
}

.dropdown {
  position: relative;
  display: inline-block;
  z-index: 10;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;

  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 10;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  width: 3.8rem;
}

.dropdown-content a:hover {
  background-color: #ddd;
}

.dropdown:hover .dropdown-content {
  display: block;
}
#headerbackimgadd {
  background-image: url(@/assets/space.png);
  opacity: 0.85;
  margin-bottom: 2rem;
  z-index: 999;
  position: relative;
}
</style>
