module.exports = {
    entry: './src/main/resources/static/js/app.js',
    output: {
        path: __dirname,
        filename: './src/main/resources/static/public/bundle.js'
    },
    module: {
        loaders: [
            {
                loader: 'babel-loader',
                query: {
                    compact: false,
                    presets: ['es2015', 'react', 'stage-0']
                },
                test: /\.jsx?$/,
                exclude: /node_modules/
            }
        ]
    }
};
