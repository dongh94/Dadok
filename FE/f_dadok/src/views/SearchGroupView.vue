<template>
  <div id="searchpage">
    <div class="row" style="margin: 0">
      <div class="col-2 sideleft"></div>

      <div id="searchwrite" class="col-8">
        <!-- 검색창 -->
        <div class="mt-5">
          <div class="input-group mt-3 my-3 justify-content-center">
            <!-- <input type="search" v-model="filter_name" class="form-control rounded" placeholder="모임 이름" aria-label="Search" aria-describedby="search-addon" /> -->
            <b-form-group
              ><b-form-input type="text" v-model="filter_name" class="input" placeholder="   당신의 모임을 검색하세요." aria-label="Search" aria-describedby="search-addon" autofocus /> </b-form-group
            ><button
              type="button"
              class="search-btn btn mx-3"
              @click="
                SearchMeetingsName({
                  name: filter_name,
                  schedule: selected1,
                  theme: selected2,
                  interest: selected3,
                })
              "
              style="background-color: #1a4d2e; color: white; border-radius: 20px; width: 70px; height: 65px; font-size: 1.2rem"
            >
              <b-icon icon="search"></b-icon>
            </button>
            <button
              type="button"
              class="search-btn btn"
              @click="filterOn()"
              style="background-color: #1a4d2e; color: white; opacity: 0.8; border-radius: 20px; width: 70px; height: 65px; font-size: 1.2rem"
            >
              <b-icon icon="filter-right"></b-icon>
            </button>
            <button
              type="button"
              class="search-btn2 btn mx-3"
              @click="SearchMeetings()"
              style="background-color: #f4ece1; color: #1a4d2e; border-radius: 20px; width: 70px; height: 65px; font-size: 1.2rem"
            >
              <b-icon icon="arrow-counterclockwise"></b-icon>
            </button>
          </div>
        </div>
        <!-- <p>나에게 맞는 독서 모임</p>
        <p>필터와 모임 이름 검색으로 찾아보세요.</p> -->
        <div class="container d-flex justify-content-center mt-5" v-if="isFilter == true">
          <div id="searchfilter" class="row d-flex justify-content-center">
            <div>
              <b-form-group label="요일" v-slot="{ ariaDescribedby }">
                <b-form-checkbox-group v-model="selected1" :options="options1" :aria-describedby="ariaDescribedby" size="lg" name="buttons-1" buttons></b-form-checkbox-group>
              </b-form-group>

              <b-form-group label="독서모임 테마" v-slot="{ ariaDescribedby }">
                <b-form-checkbox-group v-model="selected2" :options="options2" :aria-describedby="ariaDescribedby" buttons size="lg" name="buttons-3"></b-form-checkbox-group>
              </b-form-group>

              <b-form-group label="관심분야 (장르)" v-slot="{ ariaDescribedby }">
                <b-form-checkbox-group v-model="selected3" :options="options3" :aria-describedby="ariaDescribedby" buttons size="lg" name="buttons-2"></b-form-checkbox-group>
                <!-- <button
                  type="button"
                  class="search-btn btn mx-3"
                  @click="
                    SearchMeetingsName({
                      name: filter_name,
                      schedule: selected1,
                      theme: selected2,
                      interest: selected3,
                    })
                  "
                  style="background-color: #fef7dc; color: #1a4d2e; border-radius: 20px; width: 70px; height: 65px; font-size: 1.2rem"
                >
                  <b-icon icon="search"></b-icon>
                </button> -->
              </b-form-group>
              <div class="mt-5 d-flex justify-content-center">
                <button
                  type="button"
                  class="a-btn a-btn-gra1 a-btn-gra-anim"
                  @click="
                    SearchMeetingsName({
                      name: filter_name,
                      schedule: selected1,
                      theme: selected2,
                      interest: selected3,
                    })
                  "
                  style="background-color: #000; color: black"
                >
                  <b-icon icon="search"></b-icon>&nbsp;&nbsp;찾기
                </button>
              </div>
            </div>

            <!-- <div class="d-flex justify-content-center mt-5" style="">
              <button type="button" class="a-btn a-btn-gra1 a-btn-gra-anim" @click="SearchMeetings()" style="background-color: #000; color: black">진행 중인 전체 모임</button>
            </div> -->
          </div>
        </div>
      </div>
    </div>
    <div class="row" style="margin: 0">
      <div class="col-2 sideleft"></div>

      <div id="searchwrite" class="col-8">
        <!-- <p>진행 중인 모임을 보고 골라보세요.</p> -->

        <!-- <div class="container" id="searchfilter"> -->
        <div class="mt-5" style="margin-start: 1.2rem">
          <search-group-list></search-group-list>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SearchGroupList from "@/components/searchgroup/SearchGroupList.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "SearchGroupView",
  components: { SearchGroupList },

  data() {
    return {
      isFilter: false,
      filter_name: "",
      selected1: [], // Must be an array reference!
      selected2: [], // Must be an array reference!
      selected3: [], // Must be an array reference!
      options1: [
        { text: "월", value: "월" },
        { text: "화", value: "화" },
        { text: "수", value: "수" },
        { text: "목", value: "목" },
        { text: "금", value: "금" },
        { text: "토", value: "토" },
        { text: "일", value: "일" },
      ],
      options2: [
        { text: "독서토론형", value: "독서토론형" },
        { text: "책수다형", value: "책수다형" },
        { text: "덕후형", value: "덕후형" },
        { text: "스터디형", value: "스터디형" },
        // { text: "생활습관 개선형", value: "생활습관 개선형" },
        { text: "낭독형", value: "낭독형" },
        // { text: "챌린지형", value: "챌린지형" },
      ],
      options3: [
        { text: "소설", value: "소설" },
        { text: "인문", value: "인문" },
        { text: "수필", value: "수필" },
        { text: "고전", value: "고전" },
        { text: "과학", value: "과학" },
      ],
    };
  },
  computed: {
    ...mapState(["meetings"]),
  },
  methods: {
    ...mapActions(["SearchMeetings", "SearchMeetingsName"]),
    filterOn() {
      // console.log(this);
      this.isFilter = !this.isFilter;
      // console.log(this.isFilter);
    },
  },
  created() {
    this.SearchMeetings();
  },
};
</script>

<style scoped>
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
#searchpage {
  /* background-color: #fef7dc; */
  padding-bottom: 5rem;
  /* padding-left: 20px; */
}

#searchwrite {
  padding-top: 10px;
  font-size: 1.4rem;
  font-weight: 700;
}
#mainsearch {
  border: 1px solid #bebebe;
}
#searchfilter {
  border-radius: 20px;
  border: 1px solid #bebebe;
  /* width: 75%; */
  height: 85%;
  width: 1100px;
  padding: 5%;
  box-shadow: 1px 4px 10px #1a4d2e;
}
.search {
  width: 100%;
  position: relative;
  display: flex;
}

.input {
  background-color: transparent;
  border: none;
  border-radius: 10px;
  border-bottom: 2px solid #1a4d2e;
  border-top: 2px solid #1a4d2e;
  border-right: 2px solid #1a4d2e;
  border-left: 2px solid #1a4d2e;
  color: #555;
  box-sizing: border-box;
  /* font-family: "Arvo"; */
  font-size: 20px;
  height: 65px;
  /* left: 50%; */
  /* margin: -25px 0 0 -100px; */
  padding: 10px 0px;
  position: flex;
  top: 50%;
  width: 650px;
  box-shadow: 1px 2px 4px #1a4d2e;
}
/* .input:hover {
  transform: scale(1.2);
} */
.input:focus {
  outline: none;
  border: none;
  border-radius: 10px;
  border-bottom: 2px solid #1a4d2e;
  border-top: 2px solid #1a4d2e;
  border-right: 2px solid #1a4d2e;
  border-left: 2px solid #1a4d2e;
}

input::-webkit-input-placeholder {
  color: #aaa;
}

input:focus::-webkit-input-placeholder {
  color: grey;
}

.input:focus + .underline {
  transform: scale(1);
  border: none;
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
.search-btn2 {
  box-shadow: 0 10px 15px #f4ece1;
  /* box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
  transition: 0.25s; */
}
.search-btn2:hover {
  transform: scale(1.2);
  cursor: pointer;
}

.search-btn:active {
  transform: scale(1.5);
}

.a-btn:active {
  transform: scale(1.5);
}
.a-btn:hover {
  letter-spacing: 2px;
  transform: scale(1.2);
  cursor: pointer;
}
.a-btn {
  position: flex;
  border: none;
  justify-content: center;
  /* display: inline-block; */
  padding: 15px 30px;
  border-radius: 30px;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
  text-decoration: none;
  font-weight: 600;
  transition: 0.25s;
  width: 200px;
}
.a-btn-gra1 {
  background: linear-gradient(-45deg, #f0ded7, #d4cac8, #f4ece1, #9e8d83);
  color: rgb(3, 2, 2);
}
.a-btn-gra-anim {
  background-size: 400% 400%;
  animation: gradient2 5s ease infinite;
}
@keyframes gradient2 {
  0% {
    background-position: 100% 50%;
  }
  50% {
    background-position: 0% 50%;
  }
  100% {
    background-position: 100% 50%;
  }
}
</style>
