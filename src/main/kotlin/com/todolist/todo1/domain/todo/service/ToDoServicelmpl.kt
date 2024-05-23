package com.todolist.todo1.domain.todo.service

import com.todolist.todo1.domain.exception.ModelNotFoundException
import com.todolist.todo1.domain.todo.dto.*
import com.todolist.todo1.domain.todo.model.toResponse
import com.todolist.todo1.domain.todo.repository.ToDoRepository
import org.springframework.data.repository.findByIdOrNull
import com.todolist.todo1.domain.todo.model.ToDo
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page

import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service


@Service
class ToDoServicelmpl(
 private val toDoRepository: ToDoRepository
):ToDoService{
    override fun getAllToDoList(name:String?,pageable: Pageable): Page<ToDoResponse> {
        return toDoRepository.findByNameOrFindAll(name, pageable).map { it.toResponse() }
        }

    override fun getToDoById(todoId: Long): ToDoResponse {
        val todo = toDoRepository.findByIdOrNull(todoId)?: throw ModelNotFoundException("Todo", todoId)
        return todo.toResponse()
    }
    @Transactional
    override fun createNewToDo(request: CreateToDoRequest): ToDoResponse {
        return toDoRepository.save(
          ToDo(
              title = request.title,
              description =  request.description,
              name = request.name,
              status = false
          )
      ).toResponse()

    }

    @Transactional
    override fun updateToDo(todoId: Long, request: UpdateToDoRequest): ToDoResponse {
        val todo = toDoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val (title, description) = request
        todo.title = title
        todo.description = description
        todo.done(request.status)
        return toDoRepository.save(todo).toResponse()
    }
    @Transactional
    override fun deleteToDo(todoId: Long) {
      val todo = toDoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
          toDoRepository.delete(todo)
    }

}
