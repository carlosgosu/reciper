var path = require('path');

//El entry va a ser como el main. sourcemaps es para que al debuggear el JS pueda enlazar con los fuentes. Se creara un bundle bungle.js con todos los ficheros
module.exports = {
    entry: {
    	main: './src/main/js/app.js'
    },
    devtool: 'sourcemaps',
    cache: true,
    mode: 'development',
    output: {
        path: __dirname,
        filename: './src/main/resources/static/built/bundle.js'
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ["@babel/preset-env", "@babel/preset-react"]
                    }
                }]
            }
        ]
    }
};