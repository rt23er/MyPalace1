/*
* Model Resources
* */
import request, {getImg} from "../src/api/img.js";
let imgUrls = null; // 注意这里是 "let" 而不是 "const"
export let BOARD_TEXTURES = [];
export let BOARDS_INFO: Record<string, {title: string, author: string, describe: string}> = {};
async function fetchAndHandleImage() {
	try {
		const res = await getImg(22);
		console.log(res.data);
		imgUrls = res.data; // 现在我们修改了全局变量
		console.log("可用的图片数据:", imgUrls);
		return imgUrls; // 仍然返回 imgUrls 以供外部使用
	} catch (error) {
		console.error("获取图片数据出错:", error);
	}
}
// 设置请求回来的图片数组
fetchAndHandleImage()
	.then(data => {
		BOARD_TEXTURES = [
			new URL(imgUrls[0].imageUrl, import.meta.url).href,
			new URL(imgUrls[1].imageUrl, import.meta.url).href,
			new URL(imgUrls[2].imageUrl, import.meta.url).href,
			new URL(imgUrls[3].imageUrl, import.meta.url).href,
			new URL(imgUrls[4].imageUrl, import.meta.url).href,
			new URL(imgUrls[5].imageUrl, import.meta.url).href,
			new URL(imgUrls[6].imageUrl, import.meta.url).href,
			new URL(imgUrls[7].imageUrl, import.meta.url).href,
			new URL(imgUrls[8].imageUrl, import.meta.url).href,
			new URL(imgUrls[9].imageUrl, import.meta.url).href
		];
		BOARDS_INFO = {
			1: {
				title: "1",
				author: "",
				describe: imgUrls[0].description||"人性的弱点",
			},
			2: {
				title: "3",
				author: "北栅",
				describe: imgUrls[1].description||"人性的弱点"

			},
			3: {
				title: "2",
				author: "北栅",
				describe: imgUrls[2].description||"人性的弱点"
			},
			4: {
				title: "6",
				author: "北栅",
				describe: imgUrls[3].description||"人性的弱点"
			},
			5: {
				title: "7",
				author: "北栅",
				describe: imgUrls[4].description||"人性的弱点"
			},
			6: {
				title: "8",
				author: "北栅",
				describe: imgUrls[5].description||"人性的弱点"
			},
			7: {
				title: "9",
				author: "北栅",
				describe: imgUrls[6].description||"人性的弱点"
			},
			8: {
				title: "4",
				author: "北栅",
				describe: imgUrls[7].description||"人性的弱点"
			},
			9: {
				title: "5",
				author: "北栅",
				describe: imgUrls[8].description||"人性的弱点"
			},
			10: {
				title: "10",
				author: "北栅",
				describe: imgUrls[9].description||"人性的弱点"
			}
		};
		// 在这里，你可以确保 imgUrls 已经被设置
		console.log("在外部获取的图片数据:", data);
	})
	.catch(error => {
		console.error("处理图片数据时出错:", error);
	});
console.log("在控制台打印 imgUrls:", imgUrls);

export const COLLISION_SCENE_URL = new URL("./assets/models/scene_collision.glb", import.meta.url).href;
export const STATIC_SCENE_URL = new URL("./assets/models/scene_desk_obj.glb", import.meta.url).href;

/*
* Texture Resources
* */
// 请求border资源

// export const BOARD_TEXTURES = [
// 	new URL(imgUrls[0].imageUrl, import.meta.url).href,
// 	new URL("./assets/boards/刘亦菲.jpg", import.meta.url).href,
// 	new URL("./assets/boards/加肥猫.jpg", import.meta.url).href,
// 	new URL("./assets/boards/喜羊羊与灰太狼.jpg", import.meta.url).href,
// 	new URL("./assets/boards/比萨斜塔.jpg", import.meta.url).href,
// 	new URL("./assets/boards/熊大熊二.jpg", import.meta.url).href,
// 	new URL("./assets/boards/胡歌.jpg", import.meta.url).href,
// 	new URL("./assets/boards/8.jpg", import.meta.url).href,
// 	new URL("./assets/boards/9.jpg", import.meta.url).href,
// 	new URL("./assets/boards/10.png", import.meta.url).href
// ];

/*
* Audio Resources
* */
export const AUDIO_URL = new URL("./assets/audio/我记得.m4a", import.meta.url).href;

/*
* Intro
* */

/*
* Computer Iframe SRC
* */
export const IFRAME_SRC = new URL("/universe/index.html", import.meta.url).href;

/*
* Events
* */
export const ON_LOAD_PROGRESS = "on-load-progress";
export const ON_LOAD_MODEL_FINISH = "on-load-model-finish";
export const ON_CLICK_RAY_CAST = "on-click-ray-cast";
export const ON_SHOW_TOOLTIP = "on-show-tooltip";
export const ON_HIDE_TOOLTIP = "on-hide-tooltip";
export const ON_KEY_DOWN = "on-key-down";
export const ON_KEY_UP = "on-key-up";
export const ON_ENTER_APP = "on-enter-app";
