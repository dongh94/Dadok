<template>
  <div class="row" id="logbtns">
    <div class="col-8"></div>
    <!-- <button id="logbtn" type="button" class="btn btn-dark">로그인</button> -->
    <!-- <button id="logbtn" type="button" class="btn btn-dark">회원가입</button> -->
    <div class="row d-flex mt-2 m-0">
      <login-modal id="logbtn"></login-modal>
      <join-modal id="logbtn"></join-modal>
    </div>
  </div>
</template>

<script>
import Matter from "matter-js";

import JoinModal from "@/components/modals/JoinModal.vue";
import LoginModal from "@/components/modals/LoginModal.vue";
export default {
  name: "DropBooks",
  components: { JoinModal, LoginModal },

  mounted() {
    var Engine = Matter.Engine,
      Render = Matter.Render,
      World = Matter.World,
      Bodies = Matter.Bodies,
      Mouse = Matter.Mouse,
      // Vertices = Matter.Vertices,
      Common = Matter.Common,
      MouseConstraint = Matter.MouseConstraint;

    var engine = Engine.create({
      // positionIterations: 20
    });

    var render = Render.create({
      element: this.$el,
      engine: engine,
      options: {
        width: 920,
        height: 700,
        wireframes: false,
        background: "#FFFFFF",
      },
    });

    let arr = new Array();
    // let book3 = Bodies.rectangle(300, 100, 500, 300, {
    //   render: {
    //     fillStyle: "#000000",
    //     sprite: {
    //       texture: require("@/assets/bigbook.png"),
    //     },
    //   },
    //   restitution: 0.5,
    // });
    // arr.push(book3);
    // let book4 = Bodies.rectangle(0, 300, 400, 400, {
    //   render: {
    //     fillStyle: "#000000",
    //     sprite: {
    //       texture: require("@/assets/bigbook2.png"),
    //     },
    //   },
    //   restitution: 0.2,
    // });
    // arr.push(book4);

    let i = 0;
    for (i; i < 25; i++) {
      const rand1 = Math.floor(Math.random() * 500) + 300;
      const rand2 = Math.floor(Math.random() * 200) + 50;
      const img1 = Math.floor(Math.random() * 3) + 1;
      Math.random();
      let book1 = Bodies.rectangle(rand1, rand2, 100, 100, {
        render: {
          fillStyle: "#000000",
          sprite: {
            texture: require(`@/assets/logoremove${img1}.png`),
          },
        },
        restitution: 0.5,
      });
      // let book3 = Bodies.rectangle(rand1, rand2, 100, 100, {
      //   render: {
      //     fillStyle: "#000000",
      //     sprite: {
      //       texture: require("@/assets/logoremove2.png"),
      //     },
      //   },
      //   restitution: 0.5,
      // });
      // let book2 = Bodies.rectangle(rand1, rand2, 100, 100, {
      //   render: {
      //     fillStyle: "#000000",
      //     sprite: {
      //       texture: require("@/assets/logoremove3.png"),
      //     },
      //   },
      //   restitution: 0.5,
      // });
      // let book4 = Bodies.rectangle(rand1, rand2, 100, 100, {
      //   render: {
      //     fillStyle: "#000000",
      //     sprite: {
      //       texture: require("@/assets/logoremove4.png"),
      //     },
      //   },
      //   restitution: 0.5,
      // });
      arr.push(book1);
      // arr.push(book2);
      // arr.push(book3);
      // arr.push(book4);
    }

    // console.log(arr);
    World.add(engine.world, [
      // walls
      // Bodies.rectangle(200, 0, 1500, 100, { isStatic: true }), // 상단
      Bodies.rectangle(700, 650, 1400, 50, {
        render: { fillStyle: "#ffffff" },
        isStatic: true,
      }), // 하단
      Bodies.rectangle(950, 200, 100, 1000, {
        render: { fillStyle: "#Ffffff" },
        isStatic: true,
      }), // 우측
      Bodies.rectangle(0, 300, 100, 1000, {
        render: { fillStyle: "#Ffffff" },
        isStatic: true,
      }), // 좌측
      // Bodies.rectangle(300,150, 600, 50, { isStatic: true })
    ]);

    World.add(engine.world, arr);

    // add mouse control
    var mouse = Mouse.create(render.canvas),
      mouseConstraint = MouseConstraint.create(engine, {
        mouse: mouse,
        constraint: {
          stiffness: 0.2,
          render: {
            visible: false,
          },
        },
      });

    World.add(engine.world, mouseConstraint);

    Matter.Events.on(mouseConstraint, "mousedown", function () {
      var color = Common.choose(["#Ffffff", "#000000"]);
      var booknum = Common.choose([1, 2, 3, 4]);
      var num1 = Common.choose([200, 550, 400, 600, 770, 800, 1000]);
      var num2 = Common.choose([20, 55, 40, 60, 77, 80, 100]);
      World.add(
        engine.world,
        Bodies.rectangle(num1, num2, 100, 100, {
          render: {
            fillStyle: color,
            sprite: {
              texture: require(`@/assets/logoremove${booknum}.png`),
            },
          },
          restitution: 0.5,
        })
      );
    });

    Engine.run(engine);

    Render.run(render);
  },
};
</script>

<style>
/* #logbtns {
} */
#logbtn {
  margin: 0.8rem;
}
</style>
