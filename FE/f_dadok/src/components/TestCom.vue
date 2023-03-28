<template>
  <div>
    <h1 class="fromLeft">왼쪽부터</h1>
  </div>
</template>

<script>
export default {};
</script>

<style>
h1 {
  color: #000;
  display: inline-block;
  margin: 0;
  text-transform: uppercase;
}
h1:after {
  display: block;
  content: "";
  border-bottom: thick double 3.5px #1a4d2e;
  transform: scaleX(0);
  transition: transform 250ms ease-in-out;
}
h1:hover:after {
  transform: scaleX(1);
}

h1.fromLeft:after {
  transform-origin: 0% 50%;
}
</style>
