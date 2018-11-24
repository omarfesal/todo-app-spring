package com.fesal.todo.service;


import com.fesal.todo.models.ToDo;
import com.fesal.todo.models.User;
import com.fesal.todo.repositories.ToDoRepository;
import com.fesal.todo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSerivce {

    @Autowired
    UserRepository userRepository;

    public User saveUser(User user) {
       return userRepository.save(user);
    }

    public boolean existsByEmail(String email){
       return userRepository.existsByEmail(email);
    }

    public void deleteUser(int user_id) {
        userRepository.deleteById(user_id);
    }


    public User getUserByEmailAndPassword(String email, String pass) {
        return userRepository.findByEmailAndPassword(email, pass);
    }

    public User getUserById(int user_id){
        return userRepository.findById(user_id).get();
    }

}
