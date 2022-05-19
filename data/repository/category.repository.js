const express = require('express');

const Category = require('../model/category.model');

const router = new express.Router();

router.get('/category', async (req, res) => {
    try {
        const category = await Category.find();
        res.json(category);
    } catch (err) {
        res.status(500).json({message: err.message})
    }
})

router.post('/category/add', async (req, res) => {
    const category = new Category({
        category: req.body.name,
    });

    console.log(category);

    try {
        const newCategory = await category  .save();
        res.status(201).json(newCategory);
    } catch (err) {
        res.status(400).json({ message: err.message })
    }
})

module.exports = router;