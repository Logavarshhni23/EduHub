package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student=new Student(1,"Loga","varshhni");
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("students")
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> studentList=new ArrayList<>();
        studentList.add(new Student(1,"Ram","Kumar"));
        studentList.add(new Student(2,"Ravi","Kumar"));
        studentList.add(new Student(3,"Krishna","Kumar"));
        studentList.add(new Student(4,"Manoj","Kumar"));
        return new ResponseEntity<>(studentList,HttpStatus.OK);
    }

    @GetMapping("/{id}/{firstName}/{lastName}")
    public ResponseEntity<Student> StudentPathVariable(@PathVariable("id") int id, @PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
        Student student=new Student(id, firstName, lastName);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,@RequestParam String firstName,@RequestParam String lastName){
        Student student=new Student(id,firstName,lastName);
        return ResponseEntity.ok(student);
    }

    @PostMapping("create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    @PutMapping("{id}/update")
    public ResponseEntity updateStudent(@PathVariable("id") int studentId, @RequestBody Student student){
        return ResponseEntity.accepted().body(student);
    }

    @DeleteMapping("{id}/delete")

    public ResponseEntity deleteStudent(@PathVariable("id") int studentId){
        return ResponseEntity.accepted().body("Data removed successfully");
    }
}

