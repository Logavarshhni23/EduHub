package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.Course;
import com.eduhub.eduhub_backend.exception.ResourseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {

//    another way to achieve this is by declaring
//    static List<Course> courseList=new ArrayList<>(); list as static and add elements in static block
//    static{
//        courseList.add(new Course(101,"JAVA",4));
//        courseList.add(new Course(102,"PYTHON",3));
//        courseList.add(new Course(103,"C",4));
//        courseList.add(new Course(104,"CPP",2));
//        courseList.add(new Course(105,"DSA",3));
//    }

    List<Course> courseList=new ArrayList<>();
    public CourseController(){
        courseList.add(new Course(101,"JAVA",4));
        courseList.add(new Course(102,"PYTHON",3));
        courseList.add(new Course(103,"C",4));
        courseList.add(new Course(104,"CPP",2));
        courseList.add(new Course(105,"DSA",3));
    }

    @GetMapping("get")
    public ResponseEntity<List<Course>> getAllCourses(){
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @GetMapping("courses/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable("id") int courseCode){
        for(Course c:courseList){
            if(c.getCourseCode()==courseCode){
                return new ResponseEntity<>(c,HttpStatus.OK);
            }
        }
        throw new ResourseNotFoundException("Course","id",String.valueOf(courseCode));
    }

    @GetMapping("course")
    public ResponseEntity<Course> getCourseByCode(@RequestParam int courseCode){
        for(Course c:courseList){
            if(c.getCourseCode()==courseCode){
                return new ResponseEntity<>(c,HttpStatus.OK);
            }
        }
        //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        throw new ResourseNotFoundException("Course","id",String.valueOf(courseCode));
    }

    @PostMapping("/add")
    public ResponseEntity<List<Course>> addCourse(@RequestBody Course course){
        courseList.add(new Course(course.getCourseCode(),course.getSubjectName(),course.getCredits()));
        return new ResponseEntity<>(courseList,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") int courseCode, @RequestBody Course updatedCourse){
        for(Course c:courseList){
            if(c.getCourseCode()==courseCode){
                c.setSubjectName(updatedCourse.getSubjectName());
                c.setCredits(updatedCourse.getCredits());
                return new ResponseEntity<>(c,HttpStatus.OK);
            }
        }
        //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        throw new ResourseNotFoundException("Course","id",String.valueOf(courseCode));
    }

    @DeleteMapping("delete/{id}")
    public String deleteCourse(@PathVariable("id") int courseCode){
        for(int i=0;i<courseList.size();i++){
            if(courseList.get(i).getCourseCode()==courseCode){
                courseList.remove(i);
                return "Course removed";
            }
        }
        return "Course Not Found";
    }
}