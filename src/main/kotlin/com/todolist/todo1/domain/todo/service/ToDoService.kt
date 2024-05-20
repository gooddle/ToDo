package com.todolist.todo1.domain.todo.service

import com.todolist.todo1.domain.todo.dto.CreateToDoRequest
import com.todolist.todo1.domain.todo.dto.ToDoResponse
import com.todolist.todo1.domain.todo.dto.UpdateToDoRequest

interface ToDoService {
    fun getAllToDoList(orderBy : String?,name : String?):List<ToDoResponse>
    fun getToDoById(todoId:Long):ToDoResponse
    fun createNewToDo(request: CreateToDoRequest):ToDoResponse
    fun updateToDo(todoId: Long,request: UpdateToDoRequest):ToDoResponse
    fun deleteToDo(todoId: Long)
}