import Vue from "vue";
import  Vuex from "vuex"
Vue.use(Vuex)
 let  BOARD_TEXTURES = [
    new URL("./assets/boards/蒙娜丽莎.png", import.meta.url).href,
    new URL("./assets/boards/2.png", import.meta.url).href,
    new URL("./assets/boards/3.jpg", import.meta.url).href,
    new URL("./assets/boards/4.jpg", import.meta.url).href,
    new URL("./assets/boards/5.png", import.meta.url).href,
    new URL("./assets/boards/6.png", import.meta.url).href,
    new URL("./assets/boards/7.png", import.meta.url).href,
    new URL("./assets/boards/8.jpg", import.meta.url).href,
    new URL("./assets/boards/9.jpg", import.meta.url).href,
    new URL("./assets/boards/10.png", import.meta.url).href
];

export  default  new Vuex.Store({
    state:{
        BOARD_TEXTURES ,
    } ,
    mutations:{

    },
    actions:{

    }

})