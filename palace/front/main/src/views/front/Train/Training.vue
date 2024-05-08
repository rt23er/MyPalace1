<template>
   <div>
<!--   设计测试训练    -->

       <router-view></router-view>
       <i class="iconfont myI  icon-zhuxiaoguanji" v-if="$route.name==='TrainUP' ||$route.name==='TrainNumber'" @click="back"></i>

   </div>
</template>

<script>

export default {
    name: "Training" ,
    mounted() {
        this.$router.push({ name:'TrainBar' })
    //      获取路由名字
        console.log(this.$route.name)
    },
    methods:{
        // 通过编程式路由跳转到主页
        back() {
            this.$router.push({ name:'TrainBar' })
        }
    }
}
</script>

<style scoped>


.myI {
    position: absolute;
    z-index: 100;
    width: 30px;
    font-size: larger;
    bottom: -2vw;
    right: 10px;
    color: rgba(66, 106, 145, 0.5);
}
.icon-zhuxiaoguanji:hover {
    color: yellow;
}
</style>