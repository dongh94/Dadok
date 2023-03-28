<template>
  <div>
    <h3 class="m-5 p-5">
      <strong> 독서모임을 단 하나의 웹으로 </strong>
    </h3>
    <div class="m-5 p-5">
      <div class="d-flex justify-content-end">
        <h4>화상회의와 독서모임 기록을 동시에</h4>
      </div>
      <div class="d-flex justify-content-end">
        <h4>내가 읽은 책을 책장에 꽂는 재미까지</h4>
      </div>
    </div>

    <div>
      <b-img src="https://picsum.photos/1200/400/?image=119" alt="Responsive image" style="height: auto; width: 100%"></b-img>
    </div>
    <div>
      <div style="margin-top: 8rem">
        <h3 class="m-4 justify-content-center d-flex">
          <vue-typer
            class="display-3"
            :text="['당신의 독서 모임을..', '지금 시작하세요']"
            :repeat="Infinity"
            initial-action="typing"
            :pre-type-delay="100"
            :type-delay="200"
            :pre-erase-delay="1500"
            :erase-delay="250"
            erase-style="backspace"
            :erase-on-complete="false"
            caret-animation="blink"
          ></vue-typer>
        </h3>
      </div>
      <div style="margin-bottom: 8rem" class="justify-content-center d-flex">
        <b-button class="w-btn-neon2" :to="{ name: 'MakeGroupView' }">모임 만들기</b-button>
      </div>
      <b-row>
        <b-col cols="6" style="background-color: #e6ddc6">
          <b-img src="https://picsum.photos/1200/400/?image=1073" alt="Responsive image" style="height: 100%; width: 100%"></b-img>
        </b-col>
        <b-col cols="6" style="background-color: #e6ddc6">
          <div style="margin-top: 8rem; color: #e6ddc6">
            <h1 class="m-4 justify-content-center d-flex" style="text-align: center; text-shadow: 1px 1px 3px grey; font-weight: 600; color: black">
              나에게 꼭 맞는 모임을<br />
              찾아보세요 !
              <!-- <vue-typer
                class="display-3"
                :text="['나에게 꼭 맞는 모임을', '찾아보세요 !']"
                :repeat="Infinity"
                initial-action="typing"
                :pre-type-delay="100"
                :type-delay="200"
                :pre-erase-delay="1500"
                :erase-delay="250"
                erase-style="clear"
                :erase-on-complete="false"
                caret-animation="blink"
              ></vue-typer> -->
            </h1>
          </div>
          <div style="margin-bottom: 8rem" class="justify-content-center d-flex">
            <b-button class="w-btn-neon3" :to="{ name: 'SearchGroupView' }">모임 찾기</b-button>
          </div>
          <!-- <div class="row" id="learn1">
        
        <div id="advertwrite1">
          <div style="margin-left: 8rem">독서와 모임을 한번에

          </div>
        </div>
      </div> -->
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
export default {
  // data() {
  //   return {
  //     slide: 0,
  //     sliding: null,
  //   };
  // },
  // methods: {
  //   onSlideStart() {
  //     this.sliding = true;
  //   },
  //   onSlideEnd() {
  //     this.sliding = false;
  //   },
  // },
};
</script>
<style scoped>
h1 {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, "Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol";
}
@keyframes ring {
  0% {
    width: 30px;
    height: 30px;
    opacity: 1;
  }
  100% {
    width: 300px;
    height: 300px;
    opacity: 0;
  }
}
.w-btn-neon2 {
  position: relative;
  border: none;
  min-width: 200px;
  min-height: 50px;
  background: linear-gradient(90deg, #1a4d2e 0%, #1a4d2e 100%);
  border-radius: 1000px;
  color: whitesmoke !important;
  cursor: pointer;
  box-shadow: 12px 12px 24px #1a4d2e;
  font-weight: 700;
  font-size: 20px;
  transition: 0.3s;
  margin-top: 8rem;
  margin-bottom: 4rem;
}

.w-btn-neon2:hover {
  transform: scale(1.2);
}

.w-btn-neon2:hover::after {
  content: "";
  width: 30px;
  height: 30px;
  border-radius: 100%;
  border: 6px solid #1a4d2e;
  position: absolute;
  z-index: -1;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  animation: ring 1.5s infinite;
}
.w-btn-neon3 {
  position: relative;
  border: none;
  min-width: 200px;
  min-height: 50px;
  background: linear-gradient(90deg, #1a4d2e 0%, #1a4d2e 100%);
  border-radius: 1000px;
  color: whitesmoke !important;
  cursor: pointer;
  box-shadow: 12px 12px 24px #1a4d2e;
  font-weight: 700;
  font-size: 20px;
  transition: 0.3s;
  margin-top: 8rem;
  margin-bottom: 4rem;
}

.w-btn-neon3:hover {
  transform: scale(1.2);
}
</style>
