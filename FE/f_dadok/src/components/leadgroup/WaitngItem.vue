<template>
  <div id="waiting" class="accordion" role="tablist">
    <ul>
      <li v-for="apply in applies" :key="apply.applicantId">
        <b-card no-body class="mb-1">
          <b-card-header header-tag="header" class="p-1" role="tab">
            <b-button block v-b-toggle="apply.applicantId" style="background-color: #f4ece1; border: 0">
              <div class="d-flex justify-content-between">
                <div class="d-flex align-items-center">
                  <h4 class="m-0" style="color: black">{{ apply.applicantName }}( {{ apply.applicantId }})</h4>
                </div>
                <div>
                  <b-button @click="accept($event)" v-b-toggle="apply.applicantId" style="background-color: white; border: 0; color: black; border-radius: 15px" class="person mx-1"
                    ><b-icon icon="person-plus"></b-icon
                  ></b-button>
                  <b-button @click="reject($event)" v-b-toggle="apply.applicantId" style="background-color: black; border: 0; color: white; border-radius: 15px" class="person mx-1"
                    ><b-icon icon="person-dash"></b-icon
                  ></b-button>
                </div>
              </div>
            </b-button>
          </b-card-header>
          <b-collapse :id="apply.applicantId" visible accordion="my-accordion" role="tabpanel">
            <b-card-body>
              <b-card-text>
                <b-row>
                  <b-col cols="3">
                    <img src="@/assets/real_person.png" alt="" height="200" width="150" v-if="`${apply.profileImgPath}` === 'asdf'" />
                    <img :src="`${apply.profileImgPath}`" alt="" height="200" width="150" v-else /> <br />
                  </b-col>
                  <b-col cols="6" style="font-size: 1.2rem">
                    <p>
                      <strong> <b-icon icon="file-earmark-text"></b-icon>&nbsp;소개 </strong>
                    </p>
                    <p>{{ apply.applicantIntroduce }}</p>
                  </b-col>
                  <b-col cols="3" style="font-size: 1.2rem">
                    <p>
                      <strong><b-icon icon="bookmark-star"></b-icon>&nbsp;관심분야(장르) </strong>
                    </p>
                    <p>&nbsp;&nbsp;&nbsp;&nbsp;{{ apply.applicantInterest }}</p>
                    <p>
                      <strong><b-icon icon="graph-up"></b-icon>&nbsp;독서력 </strong>
                    </p>
                    <p>&nbsp;&nbsp;&nbsp;&nbsp;{{ apply.applicantReadBooks }} 권</p>
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
import http from "@/util/http-common.js";
import { mapState, mapActions, mapGetters } from "vuex";
export default {
  data() {
    return {
      info: {
        applicantId: "",
        hostId: "",
      },
      test: "",
    };
  },

  computed: {
    ...mapState(["loginUser", "applies"]),
  },
  methods: {
    ...mapActions(["getApplies"]),
    ...mapGetters(["checkUserInfo"]),
    accept: async function (event) {
      this.test = event.target.getAttribute("id");
      this.info.applicantId = event.currentTarget.getAttribute("aria-controls");
      this.info.hostId = this.loginUser.id;

      await http.post("/apply/accept", this.info).then(() => {
        this.$swal({
          title: "멤버로 추가했습니다",
          icon: "success",
          confirmButtonText: "확인",
          confirmButtonColor: "#1A4D2E",
        });
        setTimeout(this.$router.go(), 1000);
      });
    },
    reject: async function (event) {
      this.test = event.target.getAttribute("id");
      this.info.applicantId = event.currentTarget.getAttribute("aria-controls");
      this.info.hostId = this.loginUser.id;

      await http.post("/apply/reject", this.info).then(() => {});
      this.$router.go();
    },
  },
};
</script>

<style>
ul {
  list-style: none;
  padding-left: 0px;
}
.person:hover {
  transform: scale(1.2);
}
.person:active {
  transform: scale(1.5);
}
.person {
  transition: 0.25s;
}
</style>
