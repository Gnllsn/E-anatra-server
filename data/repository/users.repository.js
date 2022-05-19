const express = require('express');

const Users = require('../model/users.model');

const router = new express.Router();

router.get('/users', async (req, res) => {
    try {
        const user = await Users.find();
        res.json(user);
    } catch (err) {
        res.status(500).json({message: err.message})
    }
})

router.post('/users/add', async (req, res) => {
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

module.exports = router;