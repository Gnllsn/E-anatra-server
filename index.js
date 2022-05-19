const express = require('express');
const cors = require('cors');

const category = require('./data/repository/category.repository');
const course = require('./data/repository/course.repository');
const users = require('./data/repository/users.repository');

const app = express();

const {mongoose} = require('./data/db');

//app.use(express.json);
//app.use(cors());

app.use(category); 
app.use(course);
app.use(users);

app.listen( 
    3000,
    ()=> console.log('server started eh')
); 