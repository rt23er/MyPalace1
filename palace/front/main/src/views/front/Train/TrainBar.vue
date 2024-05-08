<template>
  <div>
      <h1>
          选择你的训练项目

      </h1>

      <div class="container">
          <div class="card">
              <router-link to="/front/train/trainUP">3D训练</router-link>
          </div>


          <div class="card">
              <router-link to="/front/train/trainNumber">数字训练</router-link>

          </div>
      </div>
  </div>
</template>

<script>
export default {
    name: "TrainBar"
}
</script>

<style scoped>
  .container{
      display: flex;

  }
</style>