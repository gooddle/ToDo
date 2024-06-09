package com.todolist.todo1.domain.user.dto


data class UserResponse(
    val id : Long,
    val email: String,
    val role : String

)