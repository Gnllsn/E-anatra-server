var ObjectId = require('mongodb').ObjectID; 

const routerCours = require('express').Router();

async function getAllCours(mongo_db,response){
    var result = {};
    try {
        result.status = 200;
        result.data =await  mongo_db.collection(process.env.MG_COURS).find({}).toArray();
    }catch(err){
        result.status = 400;
        result.message = error.message
    }
    response.send(result)
}

module.exports = (mongo_db ) => {
    routerCours.get('/',(req, res)=>{
        getAllCours(mongo_db,res);
    });

    return routerCours;
}