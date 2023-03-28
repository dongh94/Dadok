<template>
  <div id="member" class="accordion" role="tablist">
    <ul>
      <li v-for="member in members" :key="member.id">
        <b-card no-body class="mb-1">
          <b-card-header header-tag="header" class="p-1" role="tab">
            <b-button block v-b-toggle="'test-' + member.id" style="background-color: #f4ece1; border: 0">
              <div class="d-flex justify-content-between">
                <div class="d-flex align-items-center">
                  <h4 class="m-0" id="memberId" style="color: black">{{ member.name }}({{ member.id }})</h4>
                  <img v-if="loginUser.id === member.id" src="@/assets/crown.png" alt="" style="width: 2rem" />
                </div>
                <div>
                  <b-button v-if="loginUser.id != member.id" @click="banMemberBtn" :id="member.id" class="person" style="background-color: black; color: white; border: 0">강퇴</b-button>
                </div>
              </div>
            </b-button>
          </b-card-header>
          <!-- <b-collapse :id="'test-' + member.id" visible accordion="my-accordion2" role="tabpanel">
            <b-card-body>
              <b-card-text>{{ member }}</b-card-text>
            </b-card-body>
          </b-collapse> -->
        </b-card>
      </li>
    </ul>
  </div>
</template>

<script>
import { mapState, mapActions, mapGetters } from "vuex";
export default {
  computed: {
    ...mapState(["loginUser", "members", "meeting"]),
  },
  methods: {
    ...mapActions(["getMembers", "banMember"]),
    ...mapGetters(["checkUserInfo"]),
    banMemberBtn(event) {
      event.preventDefault();
      const userId = this.loginUser.id;
      const memberId = event.target.id;
      const meetingId = this.meeting.id;
      if (memberId == userId) {
        this.$swal({
          title: "본인은 나갈 수 없습니다.",
          icon: "error",
          confirmButtonText: "확인",
          confirmButtonColor: "#1A4D2E",
        });
      } else {
        this.banMember({ userId, memberId, meetingId });
        setTimeout(this.$router.go(), 1000);
      }
    },
  },
};
</script>

<style>
.person {
  transition: 0.25s;
}
.person:hover {
  transform: scale(1.2);
}
.person:active {
  transform: scale(1.5);
}
</style>
