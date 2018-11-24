package com.fesal.todo.repositories;

import com.fesal.todo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByEmailAndPassword(String email , String pass);
    public boolean existsByEmail(String email);

}
