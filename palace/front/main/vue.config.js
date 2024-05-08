const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    publicPath:'/foo/',
    transpileDependencies: true,
    devServer: {
        port: 8080,
        proxy: {
            '/api': {
                target: 'http://localhost:9090',
                chargeOrigin: true, // 开启代理服务器
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    },
    chainWebpack: config => {
        config.plugin('html')
            .tap(args => {
                args[0].title = "管理系统";
                return args;
            })
    }
})
