import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/css/global.css'
import '@/assets/css/theme/index.css'
import '@/assets/css/iconfont/iconfont.css'
import 'highlight.js/styles/monokai-sublime.css'
import store from './store'
import request from "@/utils/request";
import Video from 'video.js'
import 'video.js/dist/video-js.css'
Vue.prototype.$video = Video
import Wujie from "wujie-vue2";
const {preloadApp} = Wujie;
Vue.config.productionTip = false

Vue.prototype.$request = request
Vue.prototype.$baseUrl = process.env.VUE_APP_BASEURL

Vue.use(ElementUI, {size: "small"}).use(Wujie)

new Vue({
    store,
    router,
    render: h => h(App)
}).$mount('#app')

// preloadApp({name:'train3D' ,url:'http://localhost:8003' ,exect:true})
// preloadApp({name:'trainNumber' ,url:'http://localhost:8002' ,exect:true})