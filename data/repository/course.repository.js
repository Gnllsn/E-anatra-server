const express = require('express');

const Course = require('../model/course.model');

const router = new express.Router();

router.get('/course', async (req, res) => {
    try {
        const course = await Course.find();
        res.json(course);
    } catch (err) {
        res.status(500).json({message: err.message})
    }
})

router.post('/course/add', async (req, res) => {
    const course = new Course({
        title : req.body.title,
        urlVideo : req.body.urlVideo,
        shortDescription : req.body.shortDescription,
        content : req.body.content
    });

    console.log(course);

    try {
        const newCourse = await course.save();
        res.status(201).json(newCourse);
    } catch (err) {
        res.status(400).json({ message: err.message })
    }
})

module.exports = router;