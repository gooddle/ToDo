package com.todolist.todo1.domain.todo.service

import com.todolist.todo1.domain.todo.dto.CreateToDoRequest
import com.todolist.todo1.domain.todo.dto.ToDoResponse
import com.todolist.todo1.domain.todo.dto.UpdateToDoRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


interface ToDoService {
    fun getAllToDoList(name:String?,pageable: Pageable): Page<ToDoResponse>
    fun getToDoById(todoId:Long):ToDoResponse
    fun createNewToDo(request: CreateToDoRequest):ToDoResponse
    fun updateToDo(todoId: Long,request: UpdateToDoRequest):ToDoResponse
    fun deleteToDo(todoId: Long)
}