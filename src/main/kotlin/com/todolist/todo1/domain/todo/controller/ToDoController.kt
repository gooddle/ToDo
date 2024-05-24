package com.todolist.todo1.domain.todo.controller


import com.todolist.todo1.domain.todo.dto.*
import com.todolist.todo1.domain.todo.service.ToDoService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*



@RequestMapping("/todo")
@RestController
class ToDoController(
    private val toDoService: ToDoService
) {
    @GetMapping
    fun getToDoList(
        @RequestParam(value = "name", required = false) name: String?,
        @PageableDefault pageable: Pageable
    ) : ResponseEntity<Page<ToDoResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(toDoService.getAllToDoList(name,pageable))
    }

    @GetMapping("/{todoId}")
    fun getToDo(@PathVariable todoId : Long) : ResponseEntity<ToDoResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(toDoService.getToDoById(todoId))
    }
    @PostMapping
    fun createToDo( @RequestBody @Valid createToDoRequest: CreateToDoRequest,bindingResult:BindingResult) : ResponseEntity<ToDoResponse>
    {
        if(bindingResult.hasErrors()){
            throw IllegalStateException("제목 :최소 1자에서 최대 200자 까지, 내용 :최소 1자에서 최대 1000까지")
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(toDoService.createNewToDo(createToDoRequest))

    }
    @PutMapping("/{todoId}")
    fun  updateToDo(@PathVariable todoId: Long, @RequestBody  @Valid updateToDoRequest: UpdateToDoRequest,bindingResult: BindingResult) : ResponseEntity<ToDoResponse> {
        if(bindingResult.hasErrors()){
            throw IllegalStateException("제목 :최소 1자에서 최대 200자 까지, 내용 :최소 1자에서 최대 1000까지")
        }

        return ResponseEntity.status(HttpStatus.OK).body(toDoService.updateToDo(todoId, updateToDoRequest))
    }
    @DeleteMapping("/{todoId}")
    fun deleteToDo(@PathVariable todoId : Long) : ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(toDoService.deleteToDo(todoId))
    }

}