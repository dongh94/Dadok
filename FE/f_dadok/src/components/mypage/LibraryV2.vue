<template>
  <div class="row">
    <Book class="col-3" v-for="book in books" :key="book.sheet_Id" :book="book" />
  </div>
</template>

<script>
import { mapActions, mapGetters, mapState } from "vuex";
// import Matter from "matter-js";
import Book from "@/components/mypage/Book.vue";

export default {
  name: "LibraryV2",
  components: {
    Book,
  },
  computed: {
    ...mapState(["loginUser", "books"]),
  },
  methods: {
    ...mapGetters(["checkUserInfo"]),
    ...mapActions(["getUserInfo", "getBooks"]),
  },
  mounted() {
    this.getBooks(this.loginUser.id);
  },
  created() {
    let token = sessionStorage.getItem("access-token");
    this.getUserInfo(token);
    this.checkUserInfo();
  },

  // mounted() {
  //   var Engine = Matter.Engine,
  //     Render = Matter.Render,
  //     World = Matter.World,
  //     Bodies = Matter.Bodies,
  //     Mouse = Matter.Mouse,
  //     // Vertices = Matter.Vertices,
  //     // Common = Matter.Common,
  //     MouseConstraint = Matter.MouseConstraint;

  //   var engine = Engine.create({
  //     // positionIterations: 20
  //   });

  //   var render = Render.create({
  //     element: this.$el,
  //     engine: engine,
  //     options: {
  //       width: 950,
  //       height: 500,
  //       wireframes: false,
  //       background: "#fef7dc",
  //     },
  //   });
  //   var bookA = Bodies.rectangle(100, 40, 30, 200, {
  //     render: {
  //       sprite: {
  //         texture: require("@/assets/book1.png"),
  //       },
  //     },
  //     restitution: 0.2,
  //   });
  //   var bookB = Bodies.rectangle(120, 150, 30, 200, {
  //     render: {
  //       sprite: {
  //         texture: require("@/assets/book2.png"),
  //       },
  //     },
  //     restitution: 0.2,
  //   });
  //   var bookC = Bodies.rectangle(510, 60, 30, 200, {
  //     render: {
  //       sprite: {
  //         texture: require("@/assets/book3.png"),
  //       },
  //     },
  //     restitution: 0.2,
  //   });
  //   var bookD = Bodies.rectangle(450, 50, 30, 200, {
  //     render: {
  //       sprite: {
  //         texture: require("@/assets/book4.png"),
  //       },
  //     },
  //     restitution: 0.2,
  //   });
  //   var bookE = Bodies.rectangle(410, 100, 30, 200, {
  //     render: {
  //       sprite: {
  //         texture: require("@/assets/book5.png"),
  //       },
  //     },
  //     restitution: 0.2,
  //   });
  //   var bookF = Bodies.rectangle(210, 150, 30, 200, {
  //     render: {
  //       sprite: {
  //         texture: require("@/assets/book6.png"),
  //       },
  //     },
  //     restitution: 0.2,
  //   });
  //   var bookG = Bodies.rectangle(440, 100, 30, 200, {
  //     render: {
  //       sprite: {
  //         texture: require("@/assets/book7.png"),
  //       },
  //     },
  //     restitution: 0.2,
  //   });
  //   var bookI = Bodies.rectangle(390, 150, 30, 200, {
  //     render: {
  //       sprite: {
  //         texture: require("@/assets/book8.png"),
  //       },
  //     },
  //     restitution: 0.2,
  //   });
  //   var bookJ = Bodies.rectangle(110, 100, 30, 200, {
  //     render: {
  //       sprite: {
  //         texture: require("@/assets/book9.png"),
  //       },
  //     },
  //     restitution: 0.2,
  //   });
  //   var bookK = Bodies.rectangle(210, 50, 30, 200, {
  //     render: {
  //       sprite: {
  //         texture: require("@/assets/book10.png"),
  //       },
  //     },
  //     restitution: 0.2,
  //   });
  //   // var bookL = Bodies.rectangle(210, 100, 30, 200, {
  //   //   render: {
  //   //     sprite: {
  //   //       texture: require("@/assets/book1.png"),
  //   //     },
  //   //   },
  //   //   restitution: 0.2,
  //   // });
  //   // var bookM = Bodies.rectangle(110, 50, 30, 200, {
  //   //   render: {
  //   //     sprite: {
  //   //       texture: require("@/assets/book1.png"),
  //   //     },
  //   //   },
  //   //   restitution: 0.2,
  //   // });
  //   World.add(engine.world, [
  //     // walls
  //     // Bodies.rectangle(200, 0, 1500, 100, { isStatic: true }), // 상단
  //     Bodies.rectangle(200, 0, 2000, 50, {
  //       render: { fillStyle: "#5C4A31" },
  //       isStatic: true,
  //     }), // 상단
  //     Bodies.rectangle(200, 250, 2000, 30, {
  //       render: { fillStyle: "#5C4A31" },
  //       isStatic: true,
  //     }), // 중단
  //     Bodies.rectangle(200, 500, 2500, 50, {
  //       render: { fillStyle: "#5C4A31" },
  //       isStatic: true,
  //     }), // 하단
  //     Bodies.rectangle(950, 300, 100, 1000, {
  //       render: { fillStyle: "#5C4A31" },
  //       isStatic: true,
  //     }), // 우측
  //     Bodies.rectangle(600, 500, 30, 500, {
  //       render: { fillStyle: "#5C4A31" },
  //       isStatic: true,
  //     }), // 가운데 우측
  //     Bodies.rectangle(320, 120, 30, 250, {
  //       render: { fillStyle: "#5C4A31" },
  //       isStatic: true,
  //     }), // 가운데 좌측
  //     Bodies.rectangle(0, 300, 100, 1000, {
  //       render: { fillStyle: "#5C4A31" },
  //       isStatic: true,
  //     }), // 좌측
  //     // Bodies.rectangle(300,150, 600, 50, { isStatic: true })
  //   ]);

  //   World.add(engine.world, [
  //     bookA,
  //     bookB,
  //     bookC,
  //     bookD,
  //     bookE,
  //     bookF,
  //     bookG,
  //     bookI,
  //     bookJ,
  //     bookK,
  //     // bookL,
  //     // bookM,
  //   ]);

  //   // add mouse control
  //   var mouse = Mouse.create(render.canvas),
  //     mouseConstraint = MouseConstraint.create(engine, {
  //       mouse: mouse,
  //       constraint: {
  //         stiffness: 0.2,
  //         render: {
  //           visible: false,
  //         },
  //       },
  //     });

  //   World.add(engine.world, mouseConstraint);
  //   // 누를 때마다 생성 >> API로 생성가능
  //   // Matter.Events.on(mouseConstraint, "mousedown", function () {
  //   //   var color = Common.choose([
  //   //     "#654E2A",
  //   //     "#000000",
  //   //     "#A6576F",
  //   //     "#FF6B6B",
  //   //     "#082857",
  //   //     "#45584C",
  //   //   ]);
  //   //   var num1 = Common.choose([200, 550, 400, 600, 770, 800, 1000]);
  //   //   var num2 = Common.choose([
  //   //     20, 55, 40, 60, 77, 80, 100, 200, 550, 400, 600, 770, 800, 1000, 300,
  //   //     500, 700,
  //   //   ]);

  //   //   World.add(
  //   //     engine.world,
  //   //     Bodies.rectangle(num1, num2, 30, 200, {
  //   //       render: {
  //   //         fillStyle: color,
  //   //         // sprite: {
  //   //         //   texture: "../assets/logo.png",
  //   //         // },
  //   //       },
  //   //       restitution: 0.2,
  //   //     })
  //   //   );
  //   // });

  //   Engine.run(engine);

  //   Render.run(render);
  // },
};
</script>
<style scoped></style>
