const express = require('express');
const cors = require('cors');
const bodyParser = require("body-parser"); 

const category = require('./data/repository/category.repository');
const course = require('./data/repository/course.repository');
const users = require('./data/repository/users.repository');

const app = express();

app.use(cors());  
app.use(express.json());
app.use(bodyParser.json()); 

app.use('/category',category); 
app.use('/course',course);
app.use('/users',users);

app.get('/',(request,response)=>{
	response.send("Hello server !!!") ;
})

const mongoose = require('./data/db');

mongoose(app) ;
