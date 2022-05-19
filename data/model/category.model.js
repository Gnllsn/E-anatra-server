const mongoose = require('mongoose');
const Schema = mongoose.Schema;

let category = new Schema({
    category : String,
});

module.exports = mongoose.model('Category', category);