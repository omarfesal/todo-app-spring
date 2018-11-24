package com.fesal.todo.service;


import com.fesal.todo.models.Tag;
import com.fesal.todo.models.ToDo;
import com.fesal.todo.repositories.TagRepository;
import com.fesal.todo.repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoSerivce  {

    @Autowired
    ToDoRepository toDoRepository;

    @Autowired
    TagRepository tagRepository;


    public ToDo saveToDo(ToDo toDo){

        return toDoRepository.save(toDo);
    }

    public List<ToDo> getAllToDoListById(int id){
        return toDoRepository.findAllByUserId(id);
    }

    public void removeToDoByUserId(int user_id , int id){
        toDoRepository.delete(toDoRepository.findByUserId(user_id , id));
    }

    public ToDo findByUserId(int user_id , int id){
        return toDoRepository.findByUserId(user_id , id);
    }

    public Optional<ToDo> findToDoById(int id){
        return toDoRepository.findById(id);
    }


    public void saveTag(Tag tag){
        tagRepository.save(tag);
    }
}
