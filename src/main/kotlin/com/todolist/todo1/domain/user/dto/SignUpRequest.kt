package com.todolist.todo1.domain.user.dto

data class SignUpRequest(
    val email: String,
    val password: String,
    val role : String
)