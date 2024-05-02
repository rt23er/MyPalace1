<!--视频播放页面demo-->
<template>
    <div class="district-page">
        <!--    头部的轮播图-->
        <div class="my">
            <div class="swiper-list-card swiper-list">
                <div class="swiper-main-card swiper-main"></div>
                <img id="prev-card" class="btn leftBtn" src="./img/left.png" alt="">
                <img id="next-card" class="btn rightBtn" src="./img/right.png" alt="">
            </div>
        </div>

        <div class="district-text">
            <div v-for="item in videoList" :key="item.id" style="margin-top:15px">
                <div class="test_two_box">
                    <div class="title"> {{ item.videoName }}</div>
                    <video :id="'wVideo'+item.id" class="video-js" style="width:100%;height:100%">
                        <source :src="item.videoPath" type="video/mp4"/>

                    </video>
                    <div class="V_des">{{ item.description }}</div>
                </div>
            </div>
        </div>
    </div>
</template>
<script src="./slider_card.js"></script>
<script>
import {getAll} from "@/api/video";
import {Swiper} from "@/utils/slider_card";

export default {
    name: "video1",
    data() {
        return {
            videoList: [],
            videoHtmlList: '',
        }
    },
    mounted() {
        this.GetVideo();
        this.$nextTick(() => {
            this.sliderinit();
        })
    },
    methods: {
        GetVideo() {
            getAll().then(res => {
                this.videoList = res.data;
                if (this.videoList.length > 0) {
                    this.$nextTick(() => {
                        this.initVideo();
                    })
                }
            })
        },
        initVideo() {
            this.videoList.forEach((element, i) => {
                let myPlayer = this.$video('wVideo' + element.id, {
                    controls: true,
                    autoplay: false,
                    preload: "auto",
                    width: "300px",
                    height: "150px",
                    poster: element.videoPng
                });

                myPlayer.ready(() => {

                    let seekBar = myPlayer.controlBar.progressControl.seekBar;
                    seekBar.on('mousedown', (e) => {
                        let duration = myPlayer.duration();
                        let offsetX = e.offsetX;
                        let percentage = offsetX / seekBar.width();
                        myPlayer.currentTime(duration * percentage);
                    });
                });
                myPlayer.on("play", () => {
                    this.videoList.forEach((el, index) => {
                        let elsePlayer = this.$video('wVideo' + el.id);
                        if (elsePlayer.id_ !== myPlayer.id_) {

                            elsePlayer.pause();
                            elsePlayer.currentTime(0);
                            elsePlayer.poster.show();
                        }
                    });
                });
            });
        },


        sliderinit() {
            let imgArr = [{
                url: '#',
                imgPath: require('./img/i.jpg')
            },
                {
                    url: '#',
                    imgPath: require('./img/swiper5.png')
                },
                {
                    url: '#',
                    imgPath: require('./img/swiper3.jpg')
                },
                {
                    url: '#',
                    imgPath: require('./img/swiper4.jpg')
                },
                {
                    url: '#',
                    imgPath: require('./img/z.png')
                }
            ];
            // let imgArr = ['i.jpg', 'o.jpg', 'q.jpeg'];
            // let imgArr = ['i.jpg', 'o.jpg'];
            // let imgArr = ['i.jpg'];
            new Swiper({
                imgArr: imgArr,
                imgWidth: 420,
                aniTime: 1000,
                intervalTime: 1500,
                scale: 0.8,
                autoplay: true,
                gap: 0,
                clsSuffix: '-card'
            }).init();


            new Swiper({
                imgArr: imgArr,
                imgWidth: 320,
                aniTime: 1000,
                intervalTime: 1500,
                scale: 0.8,
                autoplay: false,
                gap: -200,
                clsSuffix: '-stack'
            }).init();
        }
    },
    beforeDestroy() {
        this.videoList.forEach((element, i) => {
            let myPlayer = this.$video('wVideo' + element.id);
            myPlayer.dispose();
        });
    },
};

</script>

<style lang="scss" scoped>
.district-page {
  text-align: left;

  .district-text {
    display: flex;
    flex-wrap: wrap;
    margin: 0 auto;
    margin-top: 16vh;
    //border: 1px  solid pink;
    width: fit-content;

    .test_two_box {
      position: relative;
      width: 25vw;
      height: 30vh;
      margin: 2vw;
      margin-top: 4vh;

      &:hover .V_des {
        z-index: 10000;
      }

      .title {
        width: 100%;
        height: 4vh;
        background: rgba(12, 2, 3, .4);
        position: absolute;
        top: -4vh;
        color: white;
        font-size: 1vw;
        text-shadow: 1px 3px saddlebrown;
      }

      //添加一个伪类元素用于实现当鼠标悬浮显示视频简介
      .V_des {
        z-index: 100;
        position: absolute;
        width: 50%;
        min-height: 50%;
        top: 0;
        left: 0;
        background: rgba(147, 122, 122, 0.5);
        color: #ffffff;
        font-size: 2vw;
        padding: 1vw;
        text-align: center;
        border-radius: 0.2vw;
      }

      .video-js {
        position: relative;
        z-index: 1001;
        background: transparent;
        border-radius: 2px;
      }

      .video-js {
        width: 100%;
        height: 100%;
        background-color: #f8f8f8;

        ::v-deep .vjs-big-play-button {
          width: 60px;
          height: 60px;
          border-radius: 50%;
          line-height: 60px;
          left: 50%;
          top: 50%;
          transform: translate(-30px, -30px);
          border: 1px solid #fff;
        }

        ::v-deep .vjs-control-bar {
          width: 100%;
          height: 30px;
          line-height: 30px;

          .vjs-play-control,
          .vjs-volume-panel,
          .vjs-fullscreen-control {
            width: 30px;

            .vjs-button {
              width: 100%;
            }
          }

          .vjs-time-control {
            padding: 0;
            line-height: 30px;
          }

          .vjs-picture-in-picture-control {
            display: none !important;
          }

          .vjs-progress-control {
            .vjs-play-progress:before {
              top: -13px;
              right: -6px;
            }
          }

          .vjs-volume-control {
            display: none !important;
          }
        }
      }
    }

  }
}

.my {

  .swiper-list {
    //background: pink;
    width: 70vw !important;
    height: 300px;
    position: relative;
    overflow: hidden;
    border: 1px solid #eee;
    padding: 30px 0;
    margin: 0 auto;
  }

  .swiper-main {
    height: 100%;
    position: relative;
  }

  .swiper-main img {
    height: 100%;
    display: block;
    position: absolute;
    top: 0px;
    border-radius: 4px;
    display: inline-block;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  }

  .btn {
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    width: 30px;
    height: 30px;
    z-index: 1002;
  }

  .leftBtn {
    left: 0px;
  }

  .rightBtn {
    right: 0px;
  }
}
</style>
