var ObjectId = require('mongodb').ObjectID; 

const routerComments = require('express').Router();

async function getAllComments(mongo_db,response){
    var result = {};
    try {
        result.status = 200;
        result.data =await  mongo_db.collection(process.env.MG_COMMENTS).find({}).toArray();
    }catch(err){
        result.status = 400;
        result.message = error.message
    }
    response.send(result)
}

module.exports = (mongo_db ) => {
    routerComments.get('/',(req, res)=>{
        getAllComments(mongo_db,res);
    });

    return routerComments;
}