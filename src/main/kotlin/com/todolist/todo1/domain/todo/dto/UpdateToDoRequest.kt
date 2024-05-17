package com.todolist.todo1.domain.todo.dto

data class UpdateToDoRequest(
    val title: String,
    val description: String,
    var name : String,
    var status : Boolean
)