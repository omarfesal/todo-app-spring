package com.fesal.todo.controller;


import com.fesal.todo.models.Tag;
import com.fesal.todo.models.ToDo;
import com.fesal.todo.service.ToDoSerivce;
import com.fesal.todo.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ToDoController {

    @Autowired
    ToDoSerivce toDoSerivce;

    @Autowired
    UserSerivce userSerivce;


    @PostMapping("/{user_id}/todo")
    public ResponseEntity<ToDo> saveToDo(@PathVariable int user_id,@RequestBody ToDo toDo) {
        toDo.setUser(userSerivce.getUserById(user_id));
        ToDo savedToDo = toDoSerivce.saveToDo(toDo);
        for(Tag tag : toDo.getTags()){
            tag.setToDo(savedToDo);
            toDoSerivce.saveTag(tag);
        }
        if (savedToDo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(savedToDo, HttpStatus.OK);
    }


    @GetMapping("/{user_id}/todo")
    public ResponseEntity<List<ToDo>> getAllToDoList(@PathVariable int user_id) {
        List<ToDo> toDoList = toDoSerivce.getAllToDoListById(user_id);
        if (toDoList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(toDoList, HttpStatus.OK);
    }


    @DeleteMapping("{user_id}/todo/{id}")
    public ResponseEntity<String> removeToDo(@PathVariable int user_id,@PathVariable int id) {
        try {
            toDoSerivce.removeToDoByUserId(user_id , id);
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (EmptyResultDataAccessException exp) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @PutMapping("{user_id}/todo/{id}")
    public ResponseEntity<ToDo> markAsDone(@PathVariable int user_id , @PathVariable int id, @RequestBody ToDo todo) {
        try {
            ToDo toDoDB = toDoSerivce.findByUserId(user_id , id);
            toDoDB.setComplete(todo.isComplete());
            ToDo savedToDo = toDoSerivce.saveToDo(toDoDB);
            return new ResponseEntity<ToDo>(savedToDo, HttpStatus.OK);
        } catch (EmptyResultDataAccessException exp) {
            return new ResponseEntity<ToDo>(HttpStatus.NOT_FOUND);
        }

    }


}
