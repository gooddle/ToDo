package com.todolist.todo1.domain.todo.service

import com.todolist.todo1.domain.exception.ModelNotFoundException
import com.todolist.todo1.domain.todo.dto.*
import com.todolist.todo1.domain.todo.model.toResponse
import com.todolist.todo1.domain.todo.repository.ToDoRepository
import org.springframework.data.repository.findByIdOrNull
import com.todolist.todo1.domain.todo.model.ToDo

import org.springframework.stereotype.Service

@Service
class ToDoServicelmpl(
 private val toDoRepository: ToDoRepository
):ToDoService{
    override fun getAllToDoList(): List<ToDoResponse> {
        return toDoRepository.findAll().map { it.toResponse() }
    }

    override fun getToDoById(todoId: Long): ToDoResponse {
        val todo = toDoRepository.findByIdOrNull(todoId)?: throw ModelNotFoundException("Todo", todoId)
        return todo.toResponse()
    }

    override fun createNewToDo(request: CreateToDoRequest): ToDoResponse {
      return toDoRepository.save(
          ToDo(
              title = request.title,
              description =  request.description,
              name = request.name,
          )
      ).toResponse()
    }

    override fun updateToDo(todoId: Long, request: UpdateToDoRequest): ToDoResponse {
        val todo = toDoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val (title, description) = request

        todo.title = title
        todo.description = description
        return toDoRepository.save(todo).toResponse()
    }

    override fun deleteToDo(todoId: Long) {
      val todo = toDoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
          toDoRepository.delete(todo)
    }

}
