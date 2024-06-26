﻿/****/







import {element} from "three/nodes";

export function echarts_4(element) {

    let myChart = echarts.init(element);

    let data = [{
        "name": "邮件总量",
        "value": 20000
    }, {
        "name": "公文交换",
        "value": 18170
    }, {
        "name": "内部流转",
        "value": 19870
    }]


   let option = {

        color: ['#A0CE3A', '#31C5C0', '#1E9BD1', '#0F347B', '#585247', '#7F6AAD', '#009D85', "rgba(250,250,250,0.3)"],

        grid: {
            bottom: 0,
            left: 0,
            right: '0'
        },
        series: [
            // 主要展示层的
            {
                radius: ['30%', '50%'],
                center: ['50%', '50%'],
                type: 'pie',
                label: {
                    normal: {
                        show: true,
                        formatter: ['{b|{b}}', '{c|{c}次}', '{d|同比：{d}%}'].join('\n'),
                        rich: {
                            c: {
                                color: 'rgb(241,246,104)',
                                fontSize: 20,
                                fontWeight: 'bold',
                                lineHeight: 25
                            },
                            b: {
                                color: 'rgb(98,137,169)',
                                fontSize: 15,
                                height: 40
                            },
                        },

                        position: 'outside'
                    },
                    emphasis: {
                        show: true
                    }
                },
                labelLine: {
                    normal: {
                        show: true,
                        length: 15,
                        length2: 50
                    },
                    emphasis: {
                        show: true
                    }
                },
                name: "民警训练总量",
                data: data,

            },
            // 边框的设置
            {
                radius: ['30%', '35%'],
                center: ['50%', '50%'],
                type: 'pie',
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: false
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: false
                    }
                },
                animation: false,
                tooltip: {
                    show: false
                },
                data: [{
                    value: 1,
                    itemStyle: {
                        color: "rgba(250,250,250,0.3)",
                    },
                }],
            }, {
                name: '外边框',
                type: 'pie',
                clockWise: false, //顺时加载
                hoverAnimation: false, //鼠标移入变大
                center: ['50%', '50%'],
                radius: ['60%', '61%'],
                label: {
                    normal: {
                        show: false
                    }
                },
                data: [{
                    value: 9,
                    name: '',
                    itemStyle: {
                        normal: {
                            borderWidth: 2,
                            borderColor: '#0c2c69'
                        }
                    }
                }]
            },
        ]
    };

    myChart.setOption(option);
    window.addEventListener("resize", function () {
        myChart.resize();
    });


}

export  function echarts_1(element) {
    let myChart =null
    if (!(element instanceof HTMLElement)) {
        console.error('Invalid parameter: element must be an HTMLElement.');
        return;
    }

    try {
        // ECharts图表初始化
         myChart = echarts.init(element);

        // 假设这里还有一些其他的配置和逻辑（这部分代码根据实际需求添加）
        // ...

        // 这里假设对myChart做一些操作，然后返回或者继续其他操作
    } catch (error) {
        // 错误处理：捕获并处理初始化过程中可能抛出的异常
        console.error('Failed to initialize the chart:', error);
    }
  let  option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {type: 'shadow'},

        },
        legend: {
            x: 'center',
            y: '0',
            icon: 'circle',
            itemGap: 8,
            textStyle: {color: 'rgba(255,255,255,.5)'},
            itemWidth: 10,
            itemHeight: 10,
        },
        grid: {
            left: '0',
            top: '30',
            right: '15',
            bottom: '0',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
            axisLine: {show: false},

            axisLabel: {
                textStyle: {
                    color: 'rgba(255,255,255,.6)',
                    fontSize: 14
                }
            },
        },

        yAxis: {
            type: 'value',
            splitNumber: 4,
            axisLine: {show: false},
            axisTick: {show: false},
            splitLine: {
                show: true,
                lineStyle: {
                    color: 'rgba(255,255,255,0.05)'
                }
            },
            axisLabel: {
                textStyle: {
                    color: 'rgba(255,255,255,.6)',
                    fontSize: 14
                },
            },
        },
        series: [
            {
            name: 'AI会员',
            type: 'bar',
            barWidth: '15%',
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: '#8bd46e'
                    }, {
                        offset: 1,
                        color: '#03b48e'
                    }]),
                    barBorderRadius: 11,
                }

            },
            data: [123, 154, 234, 321, 120, 390, 634, 123, 154, 234, 321, 108]

        },
            {
                name: '3D训练会员',
                type: 'bar',
                barWidth: '15%',
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: '#3893e5'
                        }, {
                            offset: 1,
                            color: '#248ff7'
                        }]),
                        barBorderRadius: 11,
                    }
                },
                data: [63, 194, 234, 321, 278, 110, 534, 63, 194, 234, 321, 278]

            },
            {
                name: '专题视频会员',
                type: 'bar',
                barWidth: '15%',
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: '#43cadd'
                        }, {
                            offset: 1,
                            color: '#0097c9'
                        }]),
                        barBorderRadius: 11,
                    }
                },
                data: [23, 354, 334, 221, 178, 190, 234, 354, 334, 221, 178, 190]

            }

        ]
    };

    myChart.setOption(option);
    window.addEventListener("resize", function () {
        myChart.resize();
    });


}

export function echarts_2(element) {
    // 基于准备好的dom，初始化echarts实例
    let myChart = echarts.init(element);

   let option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                lineStyle: {
                    color: '#dddc6b'
                }
            }
        },
        grid: {
            left: '0',
            top: '30',
            right: '20',
            bottom: '-10',
            containLabel: true
        }, legend: {
            data: ['AI模块', '3D训练模块'],
            right: 'center',
            top: 0,
            textStyle: {
                color: "#fff"
            },
            itemWidth: 12,
            itemHeight: 10,
            // itemGap: 35
        },

        xAxis: [{
            type: 'category',
            boundaryGap: false,
            axisLabel: {
                textStyle: {
                    color: "rgba(255,255,255,.6)",
                    fontSize: 14,
                },
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,.1)'
                }

            },

            data: ['8:00', '9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00']

        }, {

            axisPointer: {show: false},
            axisLine: {show: false},
            position: 'bottom',
            offset: 20,


        }],

        yAxis: [{
            type: 'value',
            axisTick: {show: false},
            // splitNumber: 6,
            axisLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,.1)'
                }
            },
            axisLabel: {
                textStyle: {
                    color: "rgba(255,255,255,.6)",
                    fontSize: 14,
                },
            },

            splitLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,.1)'
                }
            }
        }],
        series: [
            {
                name: 'AI模块',
                type: 'line',
                smooth: true,
                symbol: 'circle',
                symbolSize: 5,
                showSymbol: false,
                lineStyle: {

                    normal: {
                        color: 'rgba(228, 228, 126, 1)',
                        width: 2
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(228, 228, 126, .8)'
                        }, {
                            offset: 0.8,
                            color: 'rgba(228, 228, 126, 0.1)'
                        }], false),
                        shadowColor: 'rgba(0, 0, 0, 0.1)',
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#dddc6b',
                        borderColor: 'rgba(221, 220, 107, .1)',
                        borderWidth: 12
                    }
                },
                data: [600, 200, 600, 200, 400, 200, 400, 300, 400, 300, 400, 300, 200, 400, 200]
            }, {
                name: '3D训练模块',
                type: 'line',
                smooth: true,
                symbol: 'circle',
                symbolSize: 5,
                showSymbol: false,
                lineStyle: {

                    normal: {
                        color: 'rgba(255, 128, 128, 1)',
                        width: 2
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(255, 128, 128,.8)'
                        }, {
                            offset: 0.8,
                            color: 'rgba(255, 128, 128, .1)'
                        }], false),
                        shadowColor: 'rgba(0, 0, 0, 0.1)',
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#dddc6b',
                        borderColor: 'rgba(221, 220, 107, .1)',
                        borderWidth: 12
                    }
                },
                data: [300, 100, 300, 400, 300, 400, 300, 600, 200, 400, 200, 400, 100, 300, 400]

            },

        ]

    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    window.addEventListener("resize", function () {
        myChart.resize();
    });
}

export function echarts_3(elment) {
    // 基于准备好的dom，初始化echarts实例
    let myChart = echarts.init(elment);

   let option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                lineStyle: {
                    color: '#dddc6b'
                }
            }
        },
        grid: {
            left: '0',
            top: '30',
            right: '20',
            bottom: '-10',
            containLabel: true
        },

        xAxis: [{
            type: 'category',
            boundaryGap: false,
            axisLabel: {
                textStyle: {
                    color: "rgba(255,255,255,.6)",
                    fontSize: 14,
                },
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,.1)'
                }

            },

            data: ['8:00', '9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00']

        }, {

            axisPointer: {show: false},
            axisLine: {show: false},
            position: 'bottom',
            offset: 20,


        }],

        yAxis: [{
            type: 'value',
            axisTick: {show: false},
            axisLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,.1)'
                }
            },
            axisLabel: {
                textStyle: {
                    color: "rgba(255,255,255,.6)",
                    fontSize: 14,
                },
            },

            splitLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,.1)',

                }
            }
        }],
        series: [
            {
                name: '在线用户',
                type: 'line',
                smooth: true,
                symbol: 'circle',
                symbolSize: 10,
                showSymbol: true,
                lineStyle: {

                    normal: {
                        color: 'rgba(228, 228, 126, 1)',
                        width: 2
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(228, 228, 126, .8)'
                        }, {
                            offset: 0.8,
                            color: 'rgba(228, 228, 126, 0.1)'
                        }], false),
                        shadowColor: 'rgba(0, 0, 0, 0.1)',
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#dddc6b',
                        borderColor: 'rgba(221, 220, 107, .1)',
                        borderWidth: 12
                    }
                },
                data: [600, 200, 600, 200, 400, 200, 400, 300, 400, 300, 400, 300, 200, 400, 200]

            }

        ]

    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    window.addEventListener("resize", function () {
        myChart.resize();
    });
}

export function pe01(element) {
    // 基于准备好的dom，初始化echarts实例
    let myChart = echarts.init(element);
    let txt = 50
    let option = {
        title: {
            text: txt + '%',
            x: 'center',
            y: 'center',
            textStyle: {
                fontWeight: 'normal',
                color: '#fff',
                fontSize: '18'
            }
        },
        color: '#49bcf7',

        series: [{
            name: 'Line 1',
            type: 'pie',
            clockWise: true,
            radius: ['65%', '80%'],
            itemStyle: {
                normal: {
                    label: {
                        show: false
                    },
                    labelLine: {
                        show: false
                    }
                }
            },
            hoverAnimation: false,
            data: [{
                value: txt,
                name: '已使用',
                itemStyle: {
                    normal: {
                        color: '#eaff00',
                        label: {
                            show: false
                        },
                        labelLine: {
                            show: false
                        }
                    }
                }
            }, {
                name: '未使用',
                value: 100 - txt
            }]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    window.addEventListener("resize", function () {
        myChart.resize();
    });
}

export function pe02(element) {
    // 基于准备好的dom，初始化echarts实例
    let myChart = echarts.init(element);
    let txt = 23
   let option = {
        title: {
            text: txt + '%',
            x: 'center',
            y: 'center',
            textStyle: {
                fontWeight: 'normal',
                color: '#fff',
                fontSize: '18'
            }
        },
        color: '#49bcf7',

        series: [{
            name: 'Line 1',
            type: 'pie',
            clockWise: true,
            radius: ['65%', '80%'],
            itemStyle: {
                normal: {
                    label: {
                        show: false
                    },
                    labelLine: {
                        show: false
                    }
                }
            },
            hoverAnimation: false,
            data: [{
                value: txt,
                name: '已使用',
                itemStyle: {
                    normal: {
                        color: '#ea4d4d',
                        label: {
                            show: false
                        },
                        labelLine: {
                            show: false
                        }
                    }
                }
            }, {
                name: '未使用',
                value: 100 - txt
            }]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    window.addEventListener("resize", function () {
        myChart.resize();
    });
}

export function pe03(elment) {
    // 基于准备好的dom，初始化echarts实例
    let myChart = echarts.init(elment);
    let txt = 27
    let option = {
        title: {
            text: txt + '%',
            x: 'center',
            y: 'center',
            textStyle: {
                fontWeight: 'normal',
                color: '#fff',
                fontSize: '18'
            }
        },
        color: '#49bcf7',

        series: [{
            name: 'Line 1',
            type: 'pie',
            clockWise: true,
            radius: ['65%', '80%'],
            itemStyle: {
                normal: {
                    label: {
                        show: false
                    },
                    labelLine: {
                        show: false
                    }
                }
            },
            hoverAnimation: false,
            data: [{
                value: txt,
                name: '已使用',
                itemStyle: {
                    normal: {
                        color: '#395ee6',
                        label: {
                            show: false
                        },
                        labelLine: {
                            show: false
                        }
                    }
                }
            }, {
                name: '未使用',
                value: 100 - txt
            }
            ]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    window.addEventListener("resize", function () {
        myChart.resize();
    });
}

export function pe04(elment) {
    // 基于准备好的dom，初始化echarts实例
    let myChart = echarts.init(elment);
   let option = {

        tooltip: {
            trigger: 'axis'
        },
        radar: [{
            indicator: [{
                text: '盈利能力',
                max: 100
            }, {
                text: '发展水平',
                max: 100
            }, {
                text: '融资能力',
                max: 100
            }, {
                text: '技术能力',
                max: 100
            }, {
                text: '系统规模',
                max: 100
            }],
            textStyle: {
                color: 'red'
            },
            center: ['50%', '50%'],
            radius: '70%',
            startAngle: 90,
            splitNumber: 4,
            shape: 'circle',

            name: {
                padding: -5,
                formatter: '{value}',
                textStyle: {
                    fontSize: 14,
                    color: 'rgba(255,255,255,.6)'
                }
            },
            splitArea: {
                areaStyle: {
                    color: 'rgba(255,255,255,.05)'
                }
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,.05)'
                }
            },
            splitLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,.05)'
                }
            }
        },],
        series: [{
            name: '雷达图',
            type: 'radar',
            tooltip: {
                trigger: 'item'
            },
            data: [{
                name: '系统平均值',
                value: [90, 80, 20, 10, 30],
                lineStyle: {
                    normal: {
                        color: '#03b48e',
                        width: 2,
                    }
                },
                areaStyle: {
                    normal: {
                        color: '#03b48e',
                        opacity: .4
                    }
                },
                symbolSize: 0,

            }, {
                name: '当前月',
                value: [30, 20, 75, 80, 70],
                symbolSize: 0,
                lineStyle: {
                    normal: {
                        color: '#3893e5',
                        width: 2,
                    }
                },
                areaStyle: {
                    normal: {
                        color: 'rgba(19, 173, 255, 0.5)'
                    }
                }
            }]
        },]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    window.addEventListener("resize", function () {
        myChart.resize();
    });
}




		
		
		


		



















