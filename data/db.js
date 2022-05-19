require('dotenv').config();

module.exports = (app) =>{
	const mongoose = require('mongoose');
	const uri = process.env.DATABASE_URI;
	mongoose.connect(uri, { useNewUrlParser: true });

	const db = mongoose.connection;
	db.on('error', (error)=> console.log('error'));
	db.once('open', ()=> {
		app.listen(3000,()=> console.log('server started eh')); 
		console.log('Connected to database : '+db.name)
	});
};