package com.todolist.todo1.domain.todo.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size


data class UpdateToDoRequest(
    @field:NotBlank
    @field:Size(min = 1, max = 200)
    val title: String,
    @field:NotBlank
    @field:Size(min = 1, max = 1000)
    var name : String,
    val description: String,
    var status : Boolean
)