const express = require('express');
const bcrypt = require('bcryptjs');
const JWT = require('jsonwebtoken');

const Users = require('../model/users.model');

const router = new express.Router();

router.get('/', async (req, res) => {
    try {
        const user = await Users.find();
        res.json(user);
    } catch (err) {
        res.status(500).json({message: err.message})
    }
})

router.post('/add', async (req, res) => {
    const users = new Users({
        user: req.body.name,
    });

    console.log(users);

    try {
        const newUsers = await users.save();
        res.status(201).json(newUsers);
    } catch (err) {
        res.status(400).json({ message: err.message })
    }
})



async function sign(request,response){
    const user = new Users({
        name : request.body.name,
        firstname : request.body.firstname,
        age : request.body.age,
        pwd : request.body.password,
        email : request.body.email
    })
    const emailExist = await Users.findOne({email:request.body.email}) 
    if(emailExist){
        return response.status(200).send({
            status : 400 ,
            message : "Email déjà existant"
        });
    }
    const salt = await bcrypt.genSalt(10); 
    request.body.pwd = await bcrypt.hash(request.body.pwd,salt) ;
    try{
        let saved_user = await new Users(request.body).save();
        const token =  JWT.sign({_id : saved_user._id},process.env.TOKEN_SECRET)
        delete saved_user.pwd;
        response.send({
            status : 200 ,
            data : {
                user : saved_user,
                token : token
            }
        })
    }catch(err){
        response.send(200).send({
            status : 400,
            message : err
        })
    } 
}

async function login(request,response){
    const userExist = await Users.findOne({email:request.body.email}) 
    if(!userExist){
        return response.status(200).send({
            status : 400 ,
            message : "Email Introuvable"
        });
    }
    const validPassword = await bcrypt.compare(request.body.pwd,userExist.pwd)
    if(!validPassword) {
        return response.status(200).send({
            status : 400,
            message : 'Mot de passe incorrect'
        });
    }

    const token =  JWT.sign({_id : userExist._id},process.env.TOKEN_SECRET)
    delete userExist.pwd;
    response.send({
        status : 200 ,
        data : {
            user : userExist,
            token : token
        }
    })
}

async function test(request,response){
    return response.send({
        status : 200 ,
        message : "cool"
    })

}

router.post('/sign',sign)
router.post('/login',login)
router.get('/test',test)

module.exports = router;