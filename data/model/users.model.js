const mongoose = require('mongoose');
const Schema = mongoose.Schema;

let user = new Schema({
    name : String,
    firstname : String,
    age : Number,
    pwd : String,
    email : String
});

module.exports = mongoose.model('User', user);

