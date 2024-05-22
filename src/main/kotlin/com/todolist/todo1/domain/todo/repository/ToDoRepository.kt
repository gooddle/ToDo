package com.todolist.todo1.domain.todo.repository


import com.todolist.todo1.domain.todo.model.ToDo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


import org.springframework.data.jpa.repository.JpaRepository




interface ToDoRepository: JpaRepository<ToDo, Long> {
 fun findByNameContainingIgnoreCase(name:String?,pageable: Pageable): Page<ToDo>
}
