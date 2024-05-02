template
<template>
    <div id="AIVoice" @click="start">
        <!--     录音-->

        <i v-if="!isload" class="iconfont icon-luyinyuyin"/>
        <div v-if="istart" class="loader"/>
        <!--    <button @click="getVoice">显示文件</button>-->
        <!--    <button @click="play">播放</button>-->
        <!--    &lt;!&ndash; 添加发送音频至后端的按钮 &ndash;&gt;-->
        <!--    <button @click="sendAudioToBackend">发送至后端</button>-->
    </div>
</template>

<script>
import Recorder from 'js-audio-recorder'

export default {
    name: 'AIConversations',
    props: {
        IconSize: {

            require: true
        },
        isload: {
            require: true

        }
    },
    data() {
        return {
            recorder: new Recorder({
                sampleBits: 16,
                sampleRate: 16000,
                numChannels: 1
            }),
            wavBlob: null,
            pcmBlob: null,
            istart: false,
            name: null,
            UserId: null,

        }
    },
    mounted() {
        this.updateSize(this.IconSize)
        // 获取用户id
        const user = JSON.parse(localStorage.getItem('xm-user'))
        this.UserId = user.id
    },

    methods: {
        start() {
            this.istart = !this.istart
            if (this.istart) {
                console.log('开始录音')
                this.recorder.start()
            } else {
                console.log('停止录音')
                this.recorder.stop()

                this.sendAudioToBackend().then(
                    res1 => console.log(res1)
                )
            }
        },
        updateSize(newSize) {
            const aiVoiceElement = document.querySelector('#AIVoice')
            aiVoiceElement.style.setProperty('--size', newSize)
        },
        getVoice() {
            this.wavBlob = this.recorder.getWAVBlob()

            // 创建FormData对象
            this.voice = new FormData()

            // 处理文件信息
            this.voice.append('file', this.wavBlob, 'recording.wav')
            console.log(this.voice)
            // 将音频数据从后段获取

            // 使用fetch

            // 处理文件信息
            console.log('这是文件信息')
            console.log(this.wavBlob)
            this.pcmBlob = this.recorder.getPCMBlob()
            console.log('这是pcm文件信息')
            console.log(this.pcmBlob)
        },
        play() {
            this.recorder.play()
        },

        // 新增方法：发送音频文件到后端
        async sendAudioToBackend() {
            this.wavBlob = this.recorder.getWAVBlob()

            // 创建FormData对象
            this.voice = new FormData()

            // 处理文件信息
            this.voice.append('file', this.wavBlob, 'recording.wav')
            if (!this.wavBlob) {
                alert('请先录制并获取语音文件')
                return
            }
            this.updateParentValue(true)
            // 定义请求 URL 和音频元素
            const audioUrl = `http://localhost:9090/Ai/AiConversations?id=${this.UserId}`
            const audioElement = new Audio()

            // 使用 fetch API 发起请求
            await fetch(audioUrl, {
                method: 'POST',
                body: this.voice,
               // 确保发送 cookies
            })
                .then(response => response.blob()) // 假设你希望以 blob 形式接收响应
                .then(blob => {
                    console.log('音频文件上传成功')
                    // 创建音频播放

                    audioElement.src = URL.createObjectURL(blob)

                    // 可选：立即播放音频
                    audioElement.play()
                    // 这里可以添加代码来处理 blob，例如使用 HTML5 的 audio 元素播放它
                    this.updateParentValue(false)
                })
                .catch(error => {
                    console.error('请求失败:', error)
                })
            // 处理后端返回的数据或状态
        },
        //   更新父组件中的isload状态
        updateParentValue(newValue) {
            this.$emit('update-load', newValue)
        }
    }
}
</script>

<style lang="scss" scoped>

#AIVoice {
  --size: 3vw;
  width: fit-content;
  height: fit-content;
  position: relative;
  border: 2px solid #fffdfd;

  &:before {
    content: '';
    display: block;
    width: var(--size);
    height: var(--size);
  }

  .icon-luyinyuyin {
    display: block;
    font-size: var(--size) !important;
    color: green;
    position: absolute;
    top: 0;
  }

  .loader {
    position: absolute;
    top: calc(var(--size) / 2);
    left: calc(var(--size) / 4 * 1.5);
    width: calc(var(--size) / 4);
    aspect-ratio: 1;
    --c: no-repeat linear-gradient(45deg, #7b00ff, green);

    background: var(--c) 0% 50%,
    var(--c) 50% 50%,
    var(--c) 100% 50%;
    background-size: 20% 100%;
    animation: l1 1s infinite linear;
  }

  @keyframes l1 {
    0% {
      background-size: 20% 100%, 20% 100%, 20% 100%
    }
    33% {
      background-size: 20% 10%, 20% 100%, 20% 100%
    }
    50% {
      background-size: 20% 100%, 20% 10%, 20% 100%
    }
    66% {
      background-size: 20% 100%, 20% 100%, 20% 10%
    }
    100% {
      background-size: 20% 100%, 20% 100%, 20% 100%
    }
  }

}

</style>
