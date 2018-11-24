package com.fesal.todo.controller;


import com.fesal.todo.models.ToDo;
import com.fesal.todo.models.User;
import com.fesal.todo.service.ToDoSerivce;
import com.fesal.todo.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserSerivce userSerivce;


    @PostMapping("/register")
    public ResponseEntity<User> Register(@RequestBody User user) {
        if(userSerivce.existsByEmail(user.getEmail())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        User saveUser =  userSerivce.saveUser(user);
        if(saveUser == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(saveUser, HttpStatus.OK);
    }


    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<User> login(@RequestBody User user) {
        User userDb = userSerivce.getUserByEmailAndPassword(user.getEmail(),user.getPassword());
        if (userDb == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDb, HttpStatus.OK);
    }



}
