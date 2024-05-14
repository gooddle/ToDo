package com.todolist.todo1.domain.todo.dto

import java.time.LocalDateTime


data class ToDoResponse (
    val id : Long,
    val title : String?,
    val name : String,
    val description : String,
    val date: LocalDateTime
)