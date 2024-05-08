<template>


   <div>

       <MySwiper></MySwiper>

   </div>

</template>

<script>
import MySwiper from "@/Swiper/demo/MySwiper.vue";
export default {
    name: "test" ,
    methods: {
        getName:()=>{
          name =  this.$store.state.activeMenuName ;
          alert(name) ;
        }
    },
    components:{MySwiper}

}



</script>

<style scoped>

</style>