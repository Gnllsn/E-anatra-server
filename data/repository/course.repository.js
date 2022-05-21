const express = require('express');

const Course = require('../model/course.model');
const ObjectId =  require('mongoose').Types.ObjectId;

const routerCourse = new express.Router();

routerCourse.get('/', async (req, res) => {
    try {
        const course = await Course.find();
        res.json(course);
    } catch (err) {
        res.status(500).json({message: err.message})
    }
})

routerCourse.post('/add', async (req, res) => {
    const course = new Course({
        title : req.body.title,
        urlVideo : req.body.urlVideo,
        shortDescription : req.body.shortDescription,
        content : req.body.content
    });

    try {
        const newCourse = await course.save();
        res.status(201).json(newCourse);
    } catch (err) {
        res.status(400).json({ message: err.message })
    }
})

async function getCoureseById(request,response){
    var result = {} ;
    try{
        result.data = await new Course().find({_id:ObjectId(request.params.id)})
        result.status = 200 ; 
    }catch(err){
        result.status = 400 ; 
        result.message = err.message ; 
    }
    return response.send(200).send(result)
}

routerCourse.get('/:id',getCoureseById)

module.exports = routerCourse;