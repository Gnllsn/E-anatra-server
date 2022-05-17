require('dotenv/config');
const app = require('express')(); 
const cors = require('cors');
const express = require("express"); 
const bodyParser = require("body-parser"); 
const mongoClient = require("mongodb");
const http = require('http').createServer(app);
app.use(cors());  
app.use(express.json());
app.use(bodyParser.json()); 
const MongoClient = mongoClient.MongoClient;
let mongo_db ;

const cours = require('./Routes/cours');
const users = require('./Routes/users');
const comments = require('./Routes/comments');

MongoClient.connect(process.env.MG_HOST,async (err, database)=>{
	if(err)throw err ;
	try{
		mongo_db = await database.db(process.env.MG_DBNAME);
		http.listen(3000)

		// app.use('/cours',cours(mongo_db))
		// app.use('/users',users(mongo_db))
		// app.use('/comments',comments(mongo_db))

		console.log("Listening on port 3000")
	}catch(error){
		console.log(err)
	}
})

app.get('/' , (req , res)=>{
    res.send('Hello :)')
})