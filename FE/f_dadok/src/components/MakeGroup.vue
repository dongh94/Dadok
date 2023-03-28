<template>
  <div>
    <div class="container mb-5">
      <!-- <div class="wavebox">
        <svg
          class="loading"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          xmlns:xlink="http://www.w3.org/1999/xlink"
          x="0px"
          y="0px"
          width="400.558px"
          height="180px"
          viewBox="0 0 574.558 120"
          enable-background="new 0 0 200 120"
          xml:space="preserve"
        >
          <defs>
            <pattern id="water" width=".25" height="1.1" patternContentUnits="objectBoundingBox">
              <path fill="#fff" d="M0.25,1H0c0,0,0-0.659,0-0.916c0.083-0.303,0.158,0.334,0.25,0C0.25,0.327,0.25,1,0.25,1z" />
            </pattern>

            <text id="text" transform="matrix(1 0 0 1 -8.0684 100.7852)" font-size="70">내 독서 모임 만들기</text>

            <mask id="text_mask">
              <use x="0" y="0" xlink:href="#text" fill="#23a6d5" />
            </mask>
          </defs>

          <use x="0" y="0" xlink:href="#text" fill="#1a4d2e" />

          <rect class="water-fill" mask="url(#text_mask)" fill="url(#water)" x="-400" y="0" width="1600" height="120" />
        </svg>
      </div> -->
      <h2 class="row justify-content-center" id="makewrite"></h2>
      <br />
      <div class="card shadow-lg" id="makeform">
        <div class="underline1 d-flex justify-content-center" style="margin-bottom: 2rem">내 독서모임 만들기</div>
        <b-row>
          <b-col cols="2"> </b-col>
          <b-col class="mt-4">
            <b-form @submit="onSubmit">
              <b-form-group style="font-size: 20px" id="input-group-1" label="독서모임 명" label-for="input-1">
                <b-form-input class="shadow" style="border-radius: 10px; height: 2.4rem" id="input-1" v-model="meeting.name" type="text" placeholder="예시) 다독" required autofocus></b-form-input>
              </b-form-group>

              <b-form-group id="input-group-2" label="독서모임 테마" label-for="input-2" label-cols-sm="3" style="width: 145%; font-size: 20px">
                <b-form-select class="shadow" id="input-2" v-model="meeting.theme" :options="themes" required style="border-radius: 10px; height: 2.4rem; width: 59.5%"></b-form-select>
              </b-form-group>

              <b-form-group id="input-group-3" label="관심 분야" label-for="input-3" label-cols-sm="3" style="width: 145%; font-size: 20px">
                <b-form-select class="shadow" id="input-3" v-model="meeting.interest" :options="genres" required style="border-radius: 10px; height: 2.4rem; width: 59.5%"></b-form-select>
              </b-form-group>
              <b-row>
                <b-col cols="8">
                  <b-form-group label="모임 대표 사진" style="font-size: 20px">
                    <!-- <b-form-file id="file-default" v-model="meeting.img" :state="Boolean(meeting.img)" style="width: 48.5%"></b-form-file> -->

                    <input type="file" id="upload" @change="imageUpload" style="width: 48.5%" />
                  </b-form-group>
                </b-col>
                <b-col>
                  <b-form-group label="모임 정원" for="sb-wrap" style="font-size: 20px">
                    <b-form-spinbutton class="shadow" id="sb-wrap" v-model="meeting.people" min="1" max="6" style="border-radius: 10px; height: 2.4rem"></b-form-spinbutton>
                  </b-form-group>
                </b-col>
              </b-row>
              <b-form-group label="모임 소개" style="font-size: 20px">
                <b-form-textarea
                  style="border-radius: 10px; height: 170px"
                  class="shadow"
                  wrap="hard"
                  id="textarea-row1"
                  placeholder="모임의 특성이 나타나는 소개말을 작성해 주세요"
                  rows="8"
                  v-model="meeting.introduce"
                ></b-form-textarea>
              </b-form-group>
              <b-form-group label="진행 방식" style="font-size: 20px">
                <b-form-textarea
                  class="shadow"
                  style="border-radius: 10px; height: 170px"
                  wrap="hard"
                  id="textarea-row2"
                  placeholder="예시)
카톡방 -시작일 전 초대를 통해 입장 홈페이지
게시판 -게시판을 통해 기사공유, 댓글로 감상 인증 기사 큐레이션(주 3회)
- 화, 목 : 리더가 기사 제공
- 토 : 멤버 각자가 경제 기사 링크 올리기 감상 인증 -화, 목에는 리더 추천기사에, 토요일에는 자신이 올린 기사에 감상 인증"
                  rows="8"
                  v-model="meeting.format"
                ></b-form-textarea>
              </b-form-group>
              <b-form-group label="추천 대상" style="font-size: 20px">
                <b-form-textarea
                  class="shadow"
                  style="border-radius: 10px; height: 170px"
                  wrap="hard"
                  id="textarea-row3"
                  placeholder="예시)
경제 기사를 읽고 싶은데 시작이 어려운 분
어떤 경제 이슈부터 알아봐야할지 고민인 분
투자 등 경제 활동을 할 때 객관적인 정보를 수집하고 싶은 분

"
                  rows="8"
                  v-model="meeting.targetUser"
                ></b-form-textarea>
              </b-form-group>
              <b-row>
                <b-col>
                  <b-form-group label="모임 시작일" style="font-size: 20px">
                    <b-form-input id="date" v-model="meeting.startDay" class="mb-2 shadow" type="date" style="border-radius: 10px; height: 2.4rem"></b-form-input>
                  </b-form-group>
                </b-col>
                <b-col>
                  <b-form-group label="모임 요일" v-slot="{ ariaDescribedby }" style="font-size: 20px">
                    <b-form-checkbox-group
                      class="shadow"
                      style="background-color: #f4ece1; width: 100%"
                      v-model="meeting.schedule"
                      :options="options"
                      :aria-describedby="ariaDescribedby"
                      name="buttons-1"
                      buttons
                    ></b-form-checkbox-group>
                  </b-form-group>
                </b-col>
              </b-row>
              <div class="d-flex justify-content-center" id="makesend">
                <b-button type="submit" class="g-btn w-btn-green">모임 만들기</b-button>
              </div>
            </b-form>
          </b-col>
          <b-col cols="2"> </b-col>
        </b-row>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import { getStorage, ref, uploadBytes, getDownloadURL } from "firebase/storage";
import http from "@/util/http-common.js";
export default {
  data() {
    return {
      meeting: {
        name: "",
        theme: "",
        img: "",
        interest: "",
        people: 0,
        introduce: "",
        format: "",
        targetUser: "",
        startDay: "",
        schedule: "",
        host: "",
      },
      options: [
        { text: "월", value: "월" },
        { text: "화", value: "화" },
        { text: "수", value: "수" },
        { text: "목", value: "목" },
        { text: "금", value: "금" },
        { text: "토", value: "토" },
        { text: "일", value: "일" },
      ],
      themes: [{ text: "선택", value: null }, "독서토론형", "책수다형", "덕후형", "스터디형", "낭독형"],
      genres: [{ text: "선택", value: null }, "소설", "인문", "수필", "고전", "과학"],
    };
  },
  computed: {
    ...mapState(["loginUser"]),
  },
  methods: {
    ...mapActions(["createmeeting", "getUserInfo"]),

    imageUpload() {
      const img = document.getElementById("upload").files[0];

      // Create a root reference
      const storage = getStorage();

      // Create a reference to 'mountains.jpg'
      const profileRef = ref(storage, this.loginUser.id + "'s meeting");
      // console.log(profileRef);

      uploadBytes(profileRef, img).then((snapshot) => {
        snapshot;
        // console.log(snapshot);
        // console.log("파이어베이스에 업로드 완료");

        const urlObj = getDownloadURL(profileRef);

        const getData = () => {
          urlObj.then((appData) => {
            // console.log(appData);
            this.meeting.img = appData;
          });
        };
        getData();
        // console.log(img);
      });
    },
    async createSheet(id) {
      await http.post("/sheet/" + id).then((response) => {
        // console.log(response);
        if (response.data === "success") {
          // console.log("성공");
          // this.$router.go();
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

    async onSubmit(event) {
      event.preventDefault();
      this.meeting.host = this.loginUser.id;
      // console.log(this.meeting);
      // console.log("미팅만들기 전", this.loginUser);
      if (this.loginUser.isCreate == 0) {
        await this.createmeeting(this.meeting);
        const token = sessionStorage.getItem("access-token");
        this.getUserInfo(token);
        this.createSheet(this.meeting.host);
      } else {
        window.scrollTo(0, 0);
        // alert("모임은 하나만 만들수 있어요.");
        this.$swal({
          title: "모임은 하나만 만들 수 있어요.",
          icon: "warning",
          confirmButtonText: "확인",
          confirmButtonColor: "#1A4D2E",
        });
      }
      // console.log("미팅만들었을 때, 로그인 유저 : ", this.loginUser);
    },
  },
};
</script>
<style scoped>
.underline1 {
  font-size: 2rem;
  text-decoration: underline;
  text-underline-offset: 10px;
  text-decoration-color: #1a4d2e;
}
/* .custom-file-input ~ .custom-file-label[data-browse]::after {
  content: "찾아보기";
  background-color: #f4ece1;

  border-color: #1a4d2e;
} */

.btn-secondary {
  color: black;
  background-color: #f4ece1;
  border-color: #f4ece1;
  border-color: black;
}
.btn-secondary.active {
  background-color: #1a4d2e;
  border-color: #f4ece1;
}
.btn-secondary:hover {
  background-color: #1a4d2e;
}
/* #makewrite {
  padding-top: 100px;
  padding-bottom: 50px;
  font-size: 2rem;
  font-weight: 700;
} */

#makesend {
  padding-top: 50px;
  font-size: 2rem;
  font-weight: 700;
}

#makeform {
  /* border: thick double #1a4d2e; */
  padding-top: 80px;
  padding-bottom: 100px;
  margin: auto;
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
@import "https://fonts.googleapis.com/css?family=Oswald";

.wavebox {
  background: white;
  background: white;
  height: 100%;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: "Oswald", sans-serif !important;
  letter-spacing: 2px;
  overflow: hidden;
  font-weight: bold;
}

.water-fill {
  animation: wave 0.7s infinite linear, fill-up 10s infinite ease-out alternate;
}

@keyframes wave {
  0% {
    x: -400px;
  }
  100% {
    x: 0;
  }
}

@keyframes fill-up {
  0% {
    height: 0;
    y: 130px;
  }
  100% {
    height: 160px;
    y: -30px;
  }
}
input[type="file"]::file-selector-button {
  width: 110px;
  height: 30px;
  background: #f4ece1;
  border: none;
  color: grey;

  border-radius: 10px;
  cursor: pointer;
}
input[type="file"]::file-selector-button:hover {
  background: #1a4d2e;
  color: white;
}
</style>
