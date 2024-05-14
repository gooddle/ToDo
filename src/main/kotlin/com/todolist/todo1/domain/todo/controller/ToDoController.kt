package com.todolist.todo1.domain.todo.controller

import com.todolist.todo1.domain.todo.dto.CreateToDoRequest
import com.todolist.todo1.domain.todo.dto.ToDoResponse
import com.todolist.todo1.domain.todo.dto.UpdateToDoRequest
import com.todolist.todo1.domain.todo.service.ToDoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todo")
@RestController
class ToDoController(
    private val toDoService: ToDoService
) {
    @GetMapping
   fun getToDoList() : ResponseEntity<List<ToDoResponse>> {
       return ResponseEntity.status(HttpStatus.OK).body(toDoService.getAllToDoList())
   }
    @GetMapping("/{todoId}")
    fun getToDo(@PathVariable todoId : Long) : ResponseEntity<ToDoResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(toDoService.getToDoById(todoId))
    }
    @PostMapping
    fun createToDo(@RequestBody createToDoRequest: CreateToDoRequest) : ResponseEntity<ToDoResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(toDoService.createNewToDo(createToDoRequest))
    }
    @PutMapping("/{todoId}")
    fun  updateToDo(@PathVariable todoId: Long,@RequestBody updateToDoRequest: UpdateToDoRequest) : ResponseEntity<ToDoResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(toDoService.updateToDo(todoId, updateToDoRequest))
    }
    @DeleteMapping("/{todoId}")
    fun deleteToDo(@PathVariable todoId : Long) : ResponseEntity<Unit> {
        toDoService.deleteToDo(todoId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}