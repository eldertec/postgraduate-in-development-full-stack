//bootstrap da aplicação

//Este arquivo irar carregar as dependências que

//serão utilizadas para prover a API de serviços


// Import express
let express = require('express');

// Import Body parser
let bodyParser = require('body-parser');

// Import Mongoose
let mongoose = require('mongoose');

// Initialize the app
let app = express();


// Add headers
app.use(function (req, res, next) {

    // Website you wish to allow to connect
    res.setHeader('Access-Control-Allow-Origin', '*');

    // Request methods you wish to allow
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');

    // Request headers you wish to allow
    res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,content-type');

    // Set to true if you need the website to include cookies in the requests sent
    // to the API (e.g. in case you use sessions)
    res.setHeader('Access-Control-Allow-Credentials', true);

    // Pass to next layer of middleware
    next();
});

// Import routes
let apiRoutes = require("./api-routes")

// Configure bodyparser to handle post requests
app.use(bodyParser.urlencoded({

    extended: true

}));

app.use(bodyParser.json());

// Connect to Mongoose and set connection variable
async function connect() {

    try {
        //ATENÇÂO - o IP e a PORTA do banco podem mudar
        await mongoose.connect("mongodb://localhost:32769/node-app", {
            useNewUrlParser: true
        });
        console.log(">>> Database connected");
    } catch {
        console.log("Error");
    }
}

var db = connect()

// Setup server port
var port = process.env.PORT || 8080;

// Send message for default URL
app.get('/', (req, res) => res.send('Hello World with Express'));

// Use Api routes in the App
app.use('/api', apiRoutes)

// Launch app to listen to specified port
app.listen(port, function () {
    console.log("Running RestHub on port " + port);
});

