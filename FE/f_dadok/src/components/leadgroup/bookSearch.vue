<template>
  <div style="display: inline">
    <b-button class="m-btn m-btn-simple mx-3" v-b-modal.bookModal>
      <b-icon icon="book"></b-icon>
      도서 검색</b-button
    >
    <b-modal id="bookModal" size="lg" title="모임 책 찾기">
      <input class="shadow" type="text" style="margin-top: 1rem; border-radius: 10px; height: 2.4rem; width: 25rem" v-model="keyword" />
      <b-button class="search-btn mb-2 mx-3" style="background-color: #1a4d2e; color: white; border: 0; border-radius: 20px" @click="bookSearch"><b-icon icon="search"></b-icon></b-button>
      <b-button style="color: black; margin-top: 1rem" size="sm" class="float-right m-btn m-btn-simple" @click="save"> 저장하기 </b-button>
      <h4 class="mt-4 mb-4"><img v-if="bookName !== ''" src="@/assets/real_logo.png" style="width: 2.5rem" alt="" />{{ bookName }}</h4>

      <ul>
        <li v-for="book in books" :key="book.isbn" :book="book">
          <b-card style="border-radius: 10px" class="shadow mb-1">
            <b-row>
              <b-col cols="2"> <img :src="`${book.image}`" v-if="book.image" height="130" width="100" /> <br /> </b-col>
              <b-col class="mt-3">
                <div>제목: {{ book.title }}</div>
                <div>저자: {{ book.author }}</div>
                <div>출판사: {{ book.publisher }}</div>
                <div class="d-flex justify-content-between">
                  <div>출판일: {{ book.pubdate }}</div>
                  <div>
                    <button
                      style="border: 0; color: white; background-color: #1a4d2e; border-radius: 20px; padding: 10px 20px; border-radius: 20px"
                      :id="`${book.isbn}`"
                      :class="`${book.title}`"
                      :aria-controls="`${book.image}`"
                      @click="getIsbn($event)"
                    >
                      선택
                    </button>
                  </div>
                </div>
              </b-col>
            </b-row>
          </b-card>
        </li>
      </ul>
      <template #modal-footer>
        <div class="검색">Dadok</div>
      </template>
    </b-modal>
  </div>
</template>

<script>
import { mapState, mapActions, mapGetters } from "vuex";
import http from "@/util/http-common.js";
export default {
  data() {
    return {
      keyword: "",
      books: [],
      imgURL: "",
      bookName: "",
      isbn: 0,
    };
  },
  computed: {
    ...mapState(["meeting", "loginUser", "sheet"]),
  },
  methods: {
    ...mapGetters(["checkUserInfo"]),

    ...mapActions(["getMeetingInfo", "getUserInfo", "getSheetInfo"]),

    async bookSearch() {
      await http.get("/search/" + this.keyword).then((response) => {
        this.books.length = 0;
        for (let index = 0; index < 10; index++) {
          this.books.push(response.data.items[index]);
        }
      });
    },
    getIsbn: function (event) {
      // console.log(event.target);
      this.isbn = event.target.getAttribute("id");
      this.bookName = event.target.getAttribute("class");
      this.imgURL = event.target.getAttribute("aria-controls");
    },
    async save() {
      const urlObj = { img_path: this.imgURL };
      await http.patch("sheet/img/" + this.meeting.id, urlObj).then(() => {
        // console.log("url 변경");
        // console.log(res);
      });
      await http.patch("/sheet/" + this.meeting.id + "/" + this.isbn).then(() => {
        this.$swal({
          title: "책 저장 완료",
          text: "활동지에서 책을 확인하세요",
          icon: "info",
          confirmButtonText: "확인",
          confirmButtonColor: "#1A4D2E",
        }).then(() => {
          this.$nextTick(() => {
            this.$bvModal.hide("bookModal");
          });
          // this.$router.push({
          //   name: "LeadGroupView",
          //   params: { sense: 0 },
          // });
        });
        this.getSheetInfo(this.meeting);
      });
    },
  },
};
</script>

<style scoped>
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
.search-btn:hover {
  transform: scale(1.2);
  cursor: pointer;
}
.search-btn {
  box-shadow: 0 10px 15px #1a4d2e;
  transition: 0.25s;
  /* box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
  transition: 0.25s; */
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
.card:hover {
  /* transform: scale(1.025); */
  background-color: #f4ece1;
  opacity: 0.9;
}
.card:active {
  /* transform: scale(1.05); */
}
</style>
