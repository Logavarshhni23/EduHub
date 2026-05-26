package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.CourseDetailsService;
import com.eduhub.eduhub_backend.component.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
    CourseService courseService;
    public CourseController(CourseService courseService){
        this.courseService=courseService;
    }
//    @Autowired
//    CourseService courseService;

    @Autowired
    CourseDetailsService courseDetailsService;

    @GetMapping("get-course")
    public String getCourse(){
        return courseService.getCourse()+":"+"\n"+courseDetailsService.getCourseDetails();
    }
}
