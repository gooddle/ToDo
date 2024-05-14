package com.todolist.todo1.domain.todo.dto

data class CreateToDoRequest(
    val title: String,
    val description: String,
    var name :String,
)