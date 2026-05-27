package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.User;
import com.eduhub.eduhub_backend.exception.ResourseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {
    static List<User> userList=new ArrayList<>();
    static{
        userList.add(new User(101,"Loga","Loga123"));
        userList.add(new User(102,"Kavyasri","Kavyasri123"));
        userList.add(new User(103,"Adarsha","Adarsha123"));
        userList.add(new User(104,"Dhanushya","Dhanushya123"));
        userList.add(new User(105,"Varshhni","Varshhni123"));
    }

    @GetMapping("/get")
    public ResponseEntity<List<User>> getUser(){
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int userId){
        for(User u:userList){
            if(u.getUserId()==userId){
                return new ResponseEntity<>(u,HttpStatus.OK);
            }
        }
        throw new ResourseNotFoundException("User","userId",String.valueOf(userId));
    }

    @GetMapping("/get/query")
    public ResponseEntity<User> getUserByIdParam(@RequestParam String userId){
        if(userId.startsWith("*")){
            throw new IllegalArgumentException("It is having a special character");
        }
        int num=Integer.parseInt(userId);
        for(User u:userList){
            if(u.getUserId()==num){
                return new ResponseEntity<>(u,HttpStatus.OK);
            }
        }
        throw new ResourseNotFoundException("User","userId",userId);
    }

    @PostMapping("/add")
    public ResponseEntity<List<User>> addUser(@RequestBody User user){
        userList.add(new User(user.getUserId(), user.getUserName(), user.getPassword()));
        return new ResponseEntity<>(userList,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<List<User>> updateUser(@PathVariable("id") int userId, @RequestBody User updatedUser){
        for(User u:userList){
            if(u.getUserId()==userId){
                u.setPassword(updatedUser.getPassword());
                return new ResponseEntity<>(userList,HttpStatus.OK);
            }
        }
        throw new ResourseNotFoundException("User","userId",String.valueOf(userId));
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int userId){
        for(int i=0;i<userList.size();i++){
            if(userList.get(i).getUserId()==userId){
                userList.remove(i);
                return "Course removed";
            }
        }
        throw new RuntimeException();
    }

}
