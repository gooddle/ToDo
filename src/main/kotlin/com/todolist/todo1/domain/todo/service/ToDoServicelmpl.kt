package com.todolist.todo1.domain.todo.service

import com.todolist.todo1.domain.exception.ModelNotFoundException
import com.todolist.todo1.domain.todo.dto.*
import com.todolist.todo1.domain.todo.model.toResponse
import com.todolist.todo1.domain.todo.repository.ToDoRepository
import org.springframework.data.repository.findByIdOrNull
import com.todolist.todo1.domain.todo.model.ToDo
import com.todolist.todo1.domain.user.repository.UserRepository
import com.todolist.todo1.infra.security.UserPrincipal
import com.todolist.todo1.infra.security.jwt.JwtPlugin
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service


@Service
class ToDoServicelmpl(
    private val toDoRepository: ToDoRepository,
    private val userRepository: UserRepository,



    ):ToDoService {
    override fun getAllToDoList(name: String?, pageable: Pageable): Page<ToDoResponse> {
        val page = PageRequest.of(pageable.pageNumber, 5, pageable.sort)
        return toDoRepository.findAllOrByName(name, page).map { it.toResponse() }
    }

    override fun getToDoById(todoId: Long): ToDoResponse {
        val todo = toDoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        return todo.toResponse()
    }

    @Transactional
    override fun createNewToDo(request: CreateToDoRequest): ToDoResponse {
        val authentication = SecurityContextHolder.getContext().authentication

        val userEmail = authentication.principal as UserPrincipal


        val user = userRepository.findByEmail(userEmail.email) ?: throw ModelNotFoundException("User", null)

        return toDoRepository.save(
            ToDo(
                title = request.title,
                description =  request.description,
                name = request.name,
                user =user
            )
        ).toResponse()

    }


    @Transactional
    override fun updateToDo(todoId: Long, request: UpdateToDoRequest): ToDoResponse {


        val authentication = SecurityContextHolder.getContext().authentication

        val userEmail = authentication.principal as UserPrincipal


        val user = userRepository.findByEmail(userEmail.email) ?: throw ModelNotFoundException("User", null)


        val todo = toDoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)

        if (todo.user.id != user.id) {
            throw ModelNotFoundException("Invalid token",1)
        }

        todo.done(request)
        return todo.toResponse()
    }


    @Transactional
    override fun deleteToDo(todoId: Long){
        val authentication = SecurityContextHolder.getContext().authentication
        authentication.principal as UserPrincipal
        val todo = toDoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        toDoRepository.delete(todo)
    }

    @Transactional
    override fun finishedToDo(todoId: Long): ToDoResponse {
        val todo = toDoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        todo.isDone()
        return todo.toResponse()
    }





}






