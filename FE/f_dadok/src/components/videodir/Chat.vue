<template>
  <div>
    <b-row>
      <b-col sm="2"></b-col>
      <b-col style="text-align: center; font-size: 40px; font-weight: 600; color: black" sm="8"> 채팅방 </b-col>
      <b-col sm="2"></b-col>
    </b-row>
    <br />
    <textarea
      style="height: 400px; margin-start: 0.5rem; border: 0; border-radius: 20px; background-color: black; width: 19.2rem; color: white"
      id="tarea"
      v-model="textarea"
      cols="50"
      rows="12"
      readonly
    ></textarea>

    <!-- <form id="form" action=""> -->
    <div class="d-flex">
      <div>
        <input class="shadow" size="35" id="input" autocomplete="off" type="text" v-model="message" @keyup="sendkeypress($event)" />
      </div>
      <div>
        <button class="shadow btn btn-small mt-2" style="background-color: black; color: white; border-radius: 8px" type="button" @click="sendMessage()">
          <b-icon icon="chevron-double-right"></b-icon>
        </button>
      </div>
    </div>

    <!-- </form> -->

    <b-row>
      <b-col sm="1"></b-col>
      <b-col sm="10" class="d-flex justify-content-center">
        <!-- <form id="form" action="">
          
        </form> -->
      </b-col>
      <b-col sm="1"></b-col>
    </b-row>
  </div>
</template>

<script>
import io from "socket.io-client";
import { mapState } from "vuex";
const socket = io("http://i7b109.p.ssafy.io:3001", { transports: ["websocket"] });
const chatStore = "chatStore";

export default {
  name: "Chat-vue",
  data() {
    return {
      userid: "",
      message: "",
      textarea: "",
    };
  },
  computed: {
    ...mapState(chatStore, ["UserName"]),
  },
  methods: {
    // press(form) {
    //   if(form.keyCode == 13){
    //     formname.submit();
    //   }
    // },
    sendkeypress(event) {
      if (event.keyCode == 13) {
        this.sendMessage();
      }
    },
    sendMessage() {
      var val = [this.UserName, this.message];
      // console.log(val);
      socket.emit("chat", val);
      // this.textarea += "[" + val[0] + "]: " + val[1] + "\n";
      // this.message = "";
      this.message = "";
      let area = document.getElementById("tarea");
      // console.log(area.scrollTop);
      // console.log(area.scrollHeight);
      area.scrollTop = area.scrollHeight;
    },
  },
  created() {
    socket.on("chat", (val) => {
      this.textarea += "[" + val[0] + "]: " + val[1] + "\n";
      let area = document.getElementById("tarea");
      // console.log(area.scrollTop);
      // console.log(area.scrollHeight);
      area.scrollTop = area.scrollHeight;
    });
  },
  // created() {
  //   socket.on("chat message", (val) => {
  //     var item = document.createElement("li");
  //     item.textContent = "[" + val[0] + "]: " + val[1];
  //     var me = document.getElementById("messages");
  //     me.appendChild(item);
  //     window.scrollTo(0, document.body.scrollHeight);
  //   });
  // },
};
</script>

<style>
body {
  margin: 0;
  padding-bottom: 3rem;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
}

#form2 {
  background: rgba(0, 0, 0, 0.15);
  padding: 0.25rem;
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  display: flex;
  height: 3rem;
  box-sizing: border-box;
  backdrop-filter: blur(10px);
}
#input {
  padding: 0 1rem;
  flex-grow: 1;
  border-radius: 8px;

  height: 2.3rem;
  width: 15.6rem;
  margin: 0.5rem;
}
#input:focus {
  outline: none;
}
#form > button {
  background: #333;
  border: none;
  padding: 0 1rem;
  margin: 0.25rem;
  border-radius: 3px;
  outline: none;
  color: #fff;
}

#messages {
  list-style-type: none;
  margin: 0;
  padding: 0;
}
#messages > li {
  padding: 0.5rem 1rem;
}
#messages > li:nth-child(odd) {
  background: #efefef;
}
</style>
