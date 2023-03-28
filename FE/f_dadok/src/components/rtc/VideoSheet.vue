<template>
  <div class="mt-4 container">
    <b-card class="shadow-lg p-3 mb-5 rounded" style="border-radius: 50px">
      <div class="m-3">
        <b-form>
          <b-row>
            <b-col sm="4"></b-col>
            <b-col sm="4" style="text-align: center; font-size: 40px; font-weight: 600">{{ meeting.name }}</b-col>
            <b-col sm="4"></b-col>
            <br />
          </b-row>
          <br />
          <b-row>
            <b-col sm="3"></b-col>
            <b-col sm="6"><b-form-input v-model="sheet.topic"></b-form-input></b-col>
            <b-col sm="3"></b-col>
          </b-row>
          <br />
          <b-row>
            <b-col sm="2"><b-form-input v-model="sheet.member1"></b-form-input></b-col>
            <b-col sm="10"><b-form-textarea v-model="sheet.opinion1"></b-form-textarea></b-col>
          </b-row>
          <br />
          <b-row>
            <b-col sm="2"><b-form-input v-model="sheet.member2"></b-form-input></b-col>
            <b-col sm="10"><b-form-textarea v-model="sheet.opinion2"></b-form-textarea></b-col>
          </b-row>
          <br />
          <b-row>
            <b-col sm="2"><b-form-input v-model="sheet.member3"></b-form-input></b-col>
            <b-col sm="10"><b-form-textarea v-model="sheet.opinion3"></b-form-textarea></b-col>
          </b-row>
          <br />
          <b-row>
            <b-col sm="2"><b-form-input v-model="sheet.member4"></b-form-input></b-col>
            <b-col sm="10"> <b-form-textarea v-model="sheet.opinion4"></b-form-textarea></b-col>
          </b-row>
          <br />
          <b-row>
            <b-col sm="2"><b-form-input v-model="sheet.member5"></b-form-input></b-col>
            <b-col sm="10"><b-form-textarea v-model="sheet.opinion5"></b-form-textarea></b-col>
          </b-row>
          <br />
          <b-row>
            <b-col sm="2"><b-form-input v-model="sheet.member6"></b-form-input></b-col>
            <b-col sm="10"><b-form-textarea v-model="sheet.opinion6"></b-form-textarea></b-col>
          </b-row>
          <br />
        </b-form>
        <br />
        <b-row>
          <b-col sm="5"></b-col>
          <b-col sm="2" class="d-flex justify-content-center"> <button @click="syncUpdate">수정하기</button></b-col>
          <b-col sm="5"></b-col>
        </b-row>
      </div>
    </b-card>
  </div>
</template>

<script>
import { mapState, mapActions, mapGetters } from "vuex";
export default {
  computed: {
    ...mapState(["meeting", "loginUser", "sheet"]),
  },
  methods: {
    ...mapActions(["getMeetingInfo", "getSheetInfo", "updateSheet"]),
    ...mapGetters(["checkUserInfo"]),

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
  mounted() {
    this.getMeetingInfo(this.loginUser.id);
    this.getSheetInfo(this.meeting);
  },
};
</script>

<style></style>
