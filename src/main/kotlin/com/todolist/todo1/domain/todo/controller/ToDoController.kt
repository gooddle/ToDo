package com.todolist.todo1.domain.todo.controller

import com.todolist.todo1.domain.todo.dto.*
import com.todolist.todo1.domain.todo.service.ToDoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todo")
@RestController
class ToDoController(
    private val toDoService: ToDoService
) {
    @GetMapping
   fun getToDoList(
       @RequestParam(value = "name", required = false) name: String?,
       @RequestParam(value = "orderBy", required = false) orderBy: String?,
   ) : ResponseEntity<List<ToDoResponse>> {
       return ResponseEntity.status(HttpStatus.OK).body(toDoService.getAllToDoList(orderBy,name))
   }
    @GetMapping("/{todoId}")
    fun getToDo(@PathVariable todoId : Long) : ResponseEntity<ToDoResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(toDoService.getToDoById(todoId))
    }
    @PostMapping
    fun createToDo( @RequestBody @Valid createToDoRequest: CreateToDoRequest) : ResponseEntity<ToDoResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(toDoService.createNewToDo(createToDoRequest))
    }
    @PutMapping("/{todoId}")
    fun  updateToDo(@PathVariable todoId: Long, @RequestBody  @Valid updateToDoRequest: UpdateToDoRequest) : ResponseEntity<ToDoResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(toDoService.updateToDo(todoId, updateToDoRequest))
    }
    @DeleteMapping("/{todoId}")
    fun deleteToDo(@PathVariable todoId : Long) : ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(toDoService.deleteToDo(todoId))
    }

}