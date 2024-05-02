/*
* Model Resources
* */
export const COLLISION_SCENE_URL = new URL("./assets/models/scene_collision.glb", import.meta.url).href;
export const STATIC_SCENE_URL = new URL("./assets/models/scene_desk_obj.glb", import.meta.url).href;

/*
* Texture Resources
* */
// 请求border资源


export const BOARD_TEXTURES = [
	new URL("./assets/boards/蒙娜丽莎.jpg", import.meta.url).href,
	new URL("./assets/boards/刘亦菲.jpg", import.meta.url).href,
	new URL("./assets/boards/加肥猫.jpg", import.meta.url).href,
	new URL("./assets/boards/喜羊羊与灰太狼.jpg", import.meta.url).href,
	new URL("./assets/boards/比萨斜塔.jpg", import.meta.url).href,
	new URL("./assets/boards/熊大熊二.jpg", import.meta.url).href,
	new URL("./assets/boards/胡歌.jpg", import.meta.url).href,
	new URL("./assets/boards/8.jpg", import.meta.url).href,
	new URL("./assets/boards/9.jpg", import.meta.url).href,
	new URL("./assets/boards/10.png", import.meta.url).href
];

/*
* Audio Resources
* */
export const AUDIO_URL = new URL("./assets/audio/我记得.m4a", import.meta.url).href;

/*
* Intro
* */
export const BOARDS_INFO: Record<string, {title: string, author: string, describe: string}> = {
	1: {
		title: "《蒙娜丽莎》",
		author: "北栅",
		describe: `
		描绘了一位身穿宽松黑色衣服、头戴头巾的女士坐在一把椅子上，背景是一座山丘和一条河流。<br/>
		她微微侧头，微笑着，目光直视观众。画中的女士通常被认为是丽萨·格赞迪（Lisa Gherardini），<br/>
		是佛罗伦萨商人弗朗切斯科·德尔·吉奥康多的妻子。<br/>
		`
	},
	2: {
		title: "《刘亦菲》",
		author: "北栅",
		describe: `
		   刘亦菲（Crystal Liu，1987年8月25日—[1]），华裔美籍女演员、歌手。[4]原籍湖北武汉，<br/>
		   后随母亲移居美国纽约，毕业于北京电影学院表演系2002级本科班，2002年，主演电视剧《金粉世家》进入演艺圈。<br/>
		   之后几年相继饰演《天龙八部》王语嫣、《神雕侠侣》小龙女等角色。2005年签约唱片公司进军歌坛，<br/>
		   2008年凭借好莱坞电影《功夫之王》亮相国际银幕[5]，2012年主演电影《铜雀台》，<br/>
		   斩获第5届澳门国际电影节金莲花奖最佳女主角[6]，2017年从迪士尼《花木兰》真人版电影试镜中脱颖而出，成为花木兰饰演者[3]。<br/>
		`
	},
	3: {
		title: "《加菲猫》",
		author: "北栅",
		describe: `
		 一只很胖且好吃的猫
		`
	},
	4: {
		title: "《喜羊羊与灰太狼》",
		author: "北栅",
		describe: `
		经典的儿时动画
		`
	},
	5: {
		title: "《比萨斜塔》",
		author: "北栅",
		describe: `
		  比萨斜塔（意大利语：Torre pendente di Pisa或Torre di Pisa） <br\>
		  是一座位于意大利托斯卡纳大区比萨省比萨市城区北面国家建筑群的高塔，<br\>
		  与它相邻的大教堂、洗礼堂、墓园均对11世纪至14世纪意大利建筑艺术有非常大的影响，故被联合国教育科学文化组织评选为世界遗产[1]。<br\>
		`
	},
	6: {
		title: "《熊大熊二》",
		author: "北栅",
		describe: `
		  《熊出没》是深圳华强方特动漫公司制作的系列动画，<br/>
		  主要讲述东北地区山岭“狗熊岭”中的两个名为“熊大”和“熊二”的狗熊与伐木工“光头强”之间围绕保护森林为线索的斗争。<br/>
		  该动画片自2012年1月22日在中国大陆各大电视台首播后，其幽默风格受到观众欢迎，<br/>
		  多次刷新电视台动画收视记录。《熊出没》系列大电影自2012年推出以来，累积票房已超过70亿人民币。<br/>
		  《熊出没》系列作品网络点播超2000亿次[1]。<br/>
		`
	},
	7: {
		title: "《胡歌》",
		author: "北栅",
		describe: `
		胡歌，1982年9月20日出生于上海市徐汇区，中国内地影视男演员、流行乐歌手，<br\>
		民盟盟员，二级演员（副高级）。毕业于上海戏剧学院表演系。2004年主演个人首部电视剧《蒲公英》。<br\>
		2005年在仙侠剧《仙剑奇侠传》中饰演李逍遥，并演唱该剧插曲《六月的雨》、<br\>
		《逍遥叹》。2006年主演电视剧《天外飞仙》；同年遭遇严重车祸。2008年发行个人首张音乐专辑《出发》<br\>
		。2009年主演仙侠剧《仙剑奇侠传三》<br\>
		`
	},
	8: {
		title: "《向日葵》",
		author: "北栅",
		describe: `
		阳光照耀，金黄的花盘。<br>
		宛如一盏明灯，指引前行。<br>
		向日葵，你是信仰，你是力量，你是光辉，你是坚毅，你是忠诚，你是爱慕，你是美丽。
		`
	},
	9: {
		title: "《花·虎·蝶》",
		author: "北栅",
		describe: `
		一段奇妙的相遇，是自由和勇气的结合，是一份神秘而又动人的韵味。<br>
		在这片色彩斑斓的花海之中，一只带着蝴蝶翅膀的老虎，骑着踏板车，<br>
		它像是一道闪电，划破了这片美好的天地。<br>
		翅膀轻轻地振动，仿佛随时可以飞离这片美好的天地，飞向更广阔的天空。
		`
	},
	10: {
		title: "《豚》",
		author: "北栅",
		describe: `
		所有的转折隐藏在密集的鸟群中，天空与海洋都无法察觉，怀着美梦却可以看见。<br>
		摸索颠倒的一瞬间，所有的怀念隐藏在相似的日子里，心里的蜘蛛模仿人类张灯结彩
		`
	}
};

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
