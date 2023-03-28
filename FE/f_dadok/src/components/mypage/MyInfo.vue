<template>
  <div class="mt-4 container">
    <b-card class="shadow-lg p-3 mb-5 rounded" style="border-radius: 50px">
      <div class="d-flex">
        <div class="">
          <h2>내정보</h2>
          <br />
          <h3>{{ loginUser.name }}님, 안녕하세요:)</h3>
        </div>
      </div>
      <br />
      <!-- 프로필 사진 -->

      <b-row>
        <b-col cols="4">
          <div>
            <!-- <img src="https://picsum.photos/id/151/200/300" alt="" /> -->
            <label for="upload">
              <img class="meme" src="@/assets/real_person.png" alt="프로필 이미지를 등록해주세요" height="400" width="300" v-if="`${loginUser.img_path}` === 'asdf'" />
              <img :src="`${loginUser.img_path}`" alt="" height="400" width="300" v-else />
            </label>
            <input type="file" style="visibility: hidden" id="upload" @change="imageUpload" />
          </div>
        </b-col>
        <b-col cols="8">
          <div>
            <h5><b-icon icon="person"></b-icon>&nbsp;{{ loginUser.name }}({{ loginUser.sex }})</h5>
            <h5><b-icon icon="calendar2-check"></b-icon>&nbsp;&nbsp;{{ loginUser.birthday }}</h5>
            <h5><b-icon icon="envelope"></b-icon>&nbsp;&nbsp;{{ loginUser.email }}&nbsp;<b-icon icon="patch-check-fill" style="color: #1a4d2e"></b-icon></h5>
          </div>
          <hr />
          <div>
            <h5>
              <b-icon icon="bookmark-star"></b-icon>&nbsp;&nbsp;관심분야(장르) -
              {{ loginUser.interest }}
            </h5>
            <h5><b-icon icon="graph-up"></b-icon>&nbsp;&nbsp;올해 읽은 책 - {{ loginUser.book_count }}권</h5>
            <h5><b-icon icon="people"></b-icon>&nbsp;&nbsp;참여한 모임 - {{ loginUser.meeting_count }}개</h5>
          </div>
          <hr />
          <div>
            <h5><b-icon icon="file-earmark-text"></b-icon>&nbsp;&nbsp;짧은 소개</h5>
            <div v-if="textopen === false" style="font-size: 1.2rem">
              {{ loginUser.introduce }}
            </div>
            <div class="d-flex justify-content-end mt-2">
              <b-button class="w-btn2 w-btn-simple" v-if="textopen === false" @click="textopen = true">수정하기</b-button>
            </div>
            <b-form-textarea v-if="textopen === true" id="textarea-rows" v-model="loginUser.introduce" style="height: 100px; border-radius: 10px" class="mt-1" rows="8"></b-form-textarea>

            <!-- <b-form-textarea
              id="me"
              rows="8"
              :v-model="introduceobj.introduce"
              disabled
            ></b-form-textarea> -->

            <div class="d-flex justify-content-end mt-3">
              <b-button class="w-btn2 w-btn-simple" @click="update" v-if="textopen === true">저장하기</b-button>
            </div>
          </div>
        </b-col>
      </b-row>
    </b-card>
    <b-card class="shadow-lg p-3 mb-5 rounded">
      <div class="d-flex justify-content-between">
        <div>
          <h3>실시간 베스트셀러</h3>
        </div>
        <div>(제공-인터파크도서)</div>
      </div>
      <b-container>
        <hr />
        <b-row>
          <div v-for="book in books" :key="book.isbn">
            <b-col>
              <img class="shadow-lg" :src="book.coverSmallUrl" style="" />
            </b-col>
          </div>
        </b-row>
      </b-container>
    </b-card>
  </div>
</template>

<script>
import { mapGetters, mapActions, mapState } from "vuex";
import { getStorage, ref, uploadBytes, getDownloadURL } from "firebase/storage";
import http from "@/util/http-common";
export default {
  name: "MyInfo",

  data() {
    return {
      introduceobj: {
        introduce: "",
      },
      textopen: false,
      books: [],
    };
  },
  created() {
    this.getBestSeller();
  },

  computed: {
    ...mapState(["loginUser"]),
    ...mapGetters(["checkUserInfo"]),
  },

  methods: {
    ...mapActions(["updateIntro", "updateUserImg"]),

    update() {
      const userId = this.loginUser.id;
      const updateuser = this.loginUser;
      this.updateIntro({ userId, updateuser });
      this.textopen = false;
      // console.log(updateuser);
    },
    getData() {
      // Create a root reference
      const storage = getStorage();
      // Create a reference to 'mountains.jpg'
      const profileRef = ref(storage, this.loginUser.id);
      // console.log(profileRef);
      const urlObj = getDownloadURL(profileRef);
      // console.log(urlObj);
      urlObj.then((appData) => {
        // console.log(appData);
        this.loginUser.img_path = appData;
        this.updateUserImg(this.loginUser);
      });
    },

    imageUpload() {
      const img = document.getElementById("upload").files[0];
      const storage = getStorage();
      const profileRef = ref(storage, this.loginUser.id);

      uploadBytes(profileRef, img).then(() => {
        this.getData();
      });
    },
    async getBestSeller() {
      await http.get("/search/popular").then((response) => {
        for (let index = 0; index < 8; index++) {
          this.books.push(response.data[index]);
        }
        // console.log(this.books);
      });
    },
  },
};
</script>

<style scoped>
.w-btn2 {
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
/* .w-btn2:hover {
  letter-spacing: 2px;
  transform: scale(1.2);
  cursor: pointer;
  background-color: #f4ece1;
  color: grey;
} */
.w-btn2:active {
  transform: scale(1.5);
}
.w-btn-simple {
  background-color: #f4ece1;
  color: grey;
}
.w-btn-simple:hover {
  letter-spacing: 2px;
  transform: scale(1.2);
  cursor: pointer;
  background-color: #f4ece1;
  color: grey;
}
.meme:hover {
  transform: scale(1.021);
  cursor: pointer;
}
.meme {
  border-radius: 15px;
}
</style>
