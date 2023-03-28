import axios from "axios";

export default axios.create({
  baseURL: "https://i7b109.p.ssafy.io/api",
  // baseURL: "http://localhost:8080",
  headers: {
    "Content-type": "application/json",
  },
});
