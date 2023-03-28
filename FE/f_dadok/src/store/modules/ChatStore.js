import http from "@/util/http-common.js";
const chatStore = {
  namespaced: true.valueOf,
  state: {
    UserName: "dd",
    meethostid: "",
    sessionstate: 0,
  },
  getters: {
    changesession(state) {
      return state.sessionstate;
    },
  },
  mutations: {
    OPEN_SESSION: (state) => {
      state.sessionstate = 1;
    },
    CLOSE_SESSION: (state) => {
      state.sessionstate = 0;
    },
    SET_SESSION: (state, val) => {
      state.sessionstate = val;
    },
    CHAT_SET_USERNAME: (state, name) => {
      // console.log(state.UserName);
      state.UserName = name;
    },
    SET_HOSTID: (state, id) => {
      // console.log(state.meethostid);
      state.meethostid = id;
    },
  },
  actions: {
    async getsession({ commit }, id) {
      // console.log("get:" + id);
      await http
        .get("/meeting/session/" + id)
        .then((response) => {
          commit("SET_SESSION", response.data);
          // console.log("getsession : " + response.data);
          return response.data;
        })
        .catch();
    },
    opensession({ commit }) {
      commit("OPEN_SESSION");
    },
    closesession({ commit }) {
      commit("CLOSE_SESSION");
    },
    setusername: ({ commit }, data) => {
      // console.log(data);
      commit("CHAT_SET_USERNAME", data);
      //this.$store.dispatch("CREAT_SET_USERNAME", data);
    },
    sethostid: ({ commit }, data) => {
      // console.log(data);
      commit("SET_HOSTID", data);
      //this.$store.dispatch("CREAT_SET_USERNAME", data);
    },
  },
};

export default chatStore;
