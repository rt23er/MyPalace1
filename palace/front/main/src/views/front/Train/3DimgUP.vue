<template>
    <div>
        <h1>3D训练</h1>
        <el-steps :active="active">
            <el-step title="步骤 1" description="上传训练图片"></el-step>
            <el-step title="步骤 2" description="进入3D场景记忆图片位置"></el-step>
            <el-step title="步骤 3" description="答题测试"></el-step>
        </el-steps>

        <el-button style="margin-top: 12px;" @click="next">下一步</el-button>
        <div class="imgUp" v-if="active===1">
            <li v-for="(item ,index) in 10" :key="index">
                <!--               {{index}}-->
                <i class="el-icon-upload2"></i>
                <i class="mask1" @click="dialog(index)"> </i>
                上传图片{{ item }}
                <i class="mask" v-if="visiblelsit[index]"> </i>
                <img :src="Imgs[index].imageUrl" alt="">
            </li>
        </div>
<!--        <button @click="upload"> dddddd</button>-->

        <el-dialog
                title="提示"
                :visible.sync="visable"
                width="30%"
                :before-close="handleClose">
            <form @submit.prevent="uploadFile" >
                <input type="file" @change="onFileChange" accept=".jpg,.jpeg,.png"/><br>
                <input type="text" v-model="description" :placeholder="description"><br>
                <button type="submit">Upload</button>
            </form>
            <span slot="footer" class="dialog-footer">
    <el-button @click="visable = false">取 消</el-button>
    <el-button type="primary" @click="visable = false">确 定</el-button>
  </span>
        </el-dialog>
        <iframe v-if="active===2" src=" http://localhost:8083/" frameborder="0"></iframe>

        <div class="question" v-if="active===3">
            <li v-for="(item , index) in questions">
                <span> {{ item.content }}</span>
                <select>
                    <option value="" v-for="it  in    item.questions">
                        {{ it }}
                    </option>
                </select>
                <button @click="success">提交</button>

            </li>

        </div>
    </div>
</template>

<script>
// import {upload} from "@/api/3D";
import {getImg} from "@/api/3D";

export default {
    name: "3DimgUP",
    data() {
        return {
            Imgs:[],
            active: 1,
            questions: [
                {
                    content: '第一张图片是什么',
                    questions: ['加菲猫', '米老鼠', '灰太狼']

                },
                {
                    content: '第二张图片是什么',
                    questions: ['胡歌', '米老鼠', '灰太狼']

                }
                ,
                {
                    content: '第三张图片是什么',
                    questions: ['加菲猫', '米老鼠', '灰太狼']

                }
                ,
                {
                    content: '第四张图片是什么',
                    questions: ['比萨斜塔', '米老鼠', '灰太狼']
                }
                ,
                {
                    content: '第五张图片是什么',
                    questions: ['熊大', '米老鼠', '灰太狼']

                }
                ,
                {
                    content: '第六张图片是什么',
                    questions: ['刘亦菲', '米老鼠', '灰太狼']

                }

            ],
            file: null,
            userId: '',
            groupId: '',
            description: '',
            visable: false,
            visiblelsit: [false, false, false, false, false, false, false, false, false, false],
            count: 0,
        //     需要当十张图片都有上传才能下一步
        };
    },
    mounted() {
        const user = JSON.parse(localStorage.getItem('xm-user'))
        this.userId = user.id
        getImg(this.userId).then(res=>{
            console.log(res.data)
            this.Imgs = res.data
        })
    },
    methods: {
        // 限制图片全部上传
        success(){
                this.$message(
                    {
                        message: '恭喜答对了',
                        type: 'success'
                    }
                )
        },
        next() {
            // if(!this.isComplete()) {
            //     this.$message({
            //         message: '请上传所有图片',
            //         type: 'warning'
            //     });
            //     return
            // }
            // this.active++
            if (this.active++ > 2) this.active = 1;
        },
        isComplete(){
            this.count =0
            this.visiblelsit.forEach((item,index) => {
                if (item){
                    this.count++
                }
            })
            return this.count === 10
        },
        post() {
            // upload().then(res => {
            //     console.log(res)
            // })
        },
        dialog(groupID) {
            this.visable = true
            this.groupId = groupID
            if(this.Imgs[groupID].description && this.Imgs ) {
                this.description = this.Imgs[groupID].description
            }
        },
        upload() {
            var formData = new FormData();

// 添加文件到 FormData
            formData.append("file", file);

// 添加其他参数到 FormData
            formData.append("UserId", '4');
            formData.append("GroupId", '4');
            formData.append("Description", '33333');

// 发起 POST 请求
            fetch('http://localhost:9090/images/upload', {
                method: 'POST',
                body: formData
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => {
                    console.log(data);
                    getImg(this.userId).then(res=>{
                        console.log(res.data)
                        this.Imgs = res.data
                    })
                })
                .catch(error => {
                    console.error('There was a problem with your fetch operation:', error);
                });
        },
        async uploadFile() {
            const formData = new FormData();
            formData.append("file", this.file);
            formData.append("UserId", this.userId);
            formData.append("GroupId", this.groupId);
            formData.append("Description", this.description);

            try {
                const response = await fetch('http://localhost:9090/images/upload', {
                    method: 'POST',
                    body: formData
                }).then(res => {
                    this.visable = false
                    this.visiblelsit[this.groupId] = true
                });
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                const data = await response.json();
                console.log(data);
            } catch (error) {
                console.error('There was a problem with your fetch operation:', error);
            }
        },
        onFileChange(event) {
            this.file = event.target.files[0];
        },

    }

}
</script>

<style scoped>
iframe {
    width: 100%;
    height: 100vh;
    display: block;
    /*position: absolute;*/
    /*z-index: 1000;*/
}


.el-steps {
    width: 80%;
    margin-left: 2vw;
}

.imgUp {
    width: 80%;
    margin-left: 2vw;
    display: flex;
    flex-wrap: wrap;
}

li {
    width: 100px;
    height: 100px;
    background-color: rgba(212, 227, 227, 0.2);
    margin-right: 10px;
    margin-bottom: 10px;
    list-style: none;
    text-align: center;
    cursor: pointer;
    position: relative;
    img {
        display: block;
        width: 100%;
        height: 100%;
        background: #13ce66;
        position: absolute;
        top: 0;
        z-index: -1;
    }
    .mask, .mask1 {
        display: block;
        width: 100%;
        height: 100%;
        background: #13ce66;
        position: absolute;
        top: 0;
        z-index: 100;
    }

    .mask1 {
        background: transparent!important;
    }

    i {
        display: block;
        margin: 0 auto;
        font-size: 50px;

    }
}

.question {
    width: 70%;
    margin: 0 auto;

    li {

        margin-bottom: 10px;
        width: 100%;
        display: inline-flex;
        flex-direction: column;
        align-content: space-between;
        position: relative;

        input {
            width: 70%;
            height: 30%;
            margin: 0 auto;
            display: block;
            position: absolute;
            bottom: 10%;
            left: 15%;
        }
    }
}
</style>