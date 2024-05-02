<template>
    <div class="AiPaint hide_scrollbar">
        <!--AI绘画-->
        <div class="AIHeader">
            <i class="iconfont icon-ai"/>绘图
        </div>
        <div class="des">
            <textarea v-model="des" placeholder="请输入描述信息"/>
            <i v-if="!isload" class="iconfont icon-send" @click="getImgs"/>
            <i v-if="isload" class="iconfont icon-shuyi_jiazai"/>
        </div>
        <div class="main hide_scrollbar">
            <div v-for="(item,index) in imgs" :key="index" class="item">
                <img :src="`${item}?id=${UserId}`" alt="">
                <div class="download" @click="download(`${item}?id=${UserId}`)"><i class="iconfont icon-xiazai"></i></div>
            </div>
        </div>
    </div>

</template>

<script>


import {AIPaint} from "@/api/ai";



export default {
    name: 'AiChat',
    data() {
        return {
            imgs: null,
            des: '',
            // 是否处于加载
            isload: false,
            UserId: null,

        }
    },
    methods: {
        // 图片下载
        download(url){
        //
            let a = document.createElement('a')
            a.href = url
            // 根据资源路径切割文件名
            a.download = url.split('/').pop()
            // 模拟点击
            a.click()
            // 释放内存
            a = null

        },
        getImgs() {
            console.log(this.des)
            if (this.des === '') {
                this.$message('请输入描述信息')
                return
            }
            this.isload = true
            AIPaint(this.des, this.UserId).then(res => {
                this.imgs = res.data
                this.isload = false
                console.log(res.data)
            }).catch(err => {
                this.$message(err)
                this.isload = false
            })
        }

    },
    mounted() {
        const user = JSON.parse(localStorage.getItem('xm-user'))
        this.UserId = user.id
        console.log(user.id)
    }

}
</script>

<style scoped lang="scss">
.AiPaint {
  display: flex;
  height: 100vh;
  width: 100vw;
  background: rgba(111, 146, 55, 0.9);
  flex-direction: column;
  overflow: scroll;
}

.icon-shuyi_jiazai {
  font-size: 4vw;
  color: rgba(177, 83, 83, 0.5);
  animation: load 1s linear infinite;
  transform: translateX(-5vw);
  right: 14vw;
}

.AIHeader {
  width: 30vw;
  height: 20vh;
  //background: rgba(134, 83, 83, 0.3);
  //flex: 1;
  margin: 0 auto;
  font-size: 4vw;
  text-align: center;
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5),
  -2px -1px 2px rgba(151, 49, 49, 0.5);

  .icon-ai {
    font-size: 4vw;

  }
}

.des {
  width: 80vw;
  height: 12vh;
  background: rgba(235, 239, 239, 0.4);
  margin: 0 auto;
  display: flex;
  line-height: 12vh;

  textarea {
    resize: none;
    width: 50vw;
    min-height: 10vh;
    margin: 0 auto;
    font-size: 2vw;
    outline: none;
  }

  .icon-send {
    font-size: 4vw;
    color: #52b337;
    transform: translateX(-10vw);
  }

}

.main {
  width: 80vw;
  min-height: 80vh;
  background: rgba(157, 173, 165, 0.4);
  //flex: 1;
  margin: 0 auto;
  display: flex;
  flex-wrap: wrap;
  overflow: scroll;
  align-items: center;

  .item {
    width: 50vw;
    height: 40vh;
    margin: 1vw;
    flex-wrap: wrap;
    flex: 1;
    background: #ced0d9;
    position: relative;
    .download
    {
        width: 2vw;
        height: 2vw;
        font-size: 1.5vw;
        text-align: center;
        line-height: 2vw;
        position: absolute;
        bottom: 0;
        //移动到父元素的底部居中
        background: #89a2d2;
        display: none;
        transform: translate(-50%,-50%);
        left: 50%;

    }
      &:hover .download{
          background: #52b337;
          display: block;
      }
    img {
      display: block;
      width: 100%;
      height: 100%;
    }
  }
}

@keyframes load {
  to {
    transform: rotate(0deg);
  }
  from {
    transform: rotate(360deg);
  }
}

.hide_scrollbar {
  overflow: auto;
  /* 兼容ie */
  -ms-overflow-style: none;
  /* 兼容firfox */
  scrollbar-width: none;
}

.hide_scrollbar::-webkit-scrollbar {
  display: none;
}
</style>
