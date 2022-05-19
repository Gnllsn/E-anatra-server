const mongoose = require('mongoose');
const Schema = mongoose.Schema;

let course = new Schema({
    title : String, 
    urlVideo : String,
    shortDescription : String,
    content : String,
    category : []
});

module.exports = mongoose.model('Course', course);