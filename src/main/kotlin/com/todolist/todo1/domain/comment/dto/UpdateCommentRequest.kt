package com.todolist.todo1.domain.comment.dto

data class UpdateCommentRequest(
    val name : String,
    val description :String,
    val password : String
)