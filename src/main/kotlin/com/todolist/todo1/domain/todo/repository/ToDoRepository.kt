package com.todolist.todo1.domain.todo.repository


import com.todolist.todo1.domain.todo.model.ToDo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface ToDoRepository: JpaRepository<ToDo, Long> {

 @EntityGraph(attributePaths = ["comments"])
 @Query("select t from ToDo t  where :name is null  or t.name = :name")
 fun findAllOrByName(name: String?,pageable: Pageable): Page<ToDo>

}
