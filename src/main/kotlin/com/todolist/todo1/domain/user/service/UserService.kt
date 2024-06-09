package com.todolist.todo1.domain.user.service

import com.todolist.todo1.domain.user.dto.*

interface  UserService {
    fun signupUser(signupRequest: SignUpRequest) : UserResponse
    fun loginUser(loginRequest: LoginRequest) : LoginResponse


}