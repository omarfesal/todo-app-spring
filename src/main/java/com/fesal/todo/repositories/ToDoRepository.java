package com.fesal.todo.repositories;

import com.fesal.todo.models.ToDo;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo,Integer> {

    @Query("select t from ToDo t where t.user.id= :user_id")
    List<ToDo> findAllByUserId(@Param("user_id") int user_id);

    @Query("select t from ToDo t where t.user.id= :user_id and t.id =:id")
    ToDo findByUserId(@Param("user_id") int user_id , @Param("id") int id );
}
