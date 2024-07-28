package com.todolist.todo1.domain.user.controller

import com.todolist.todo1.domain.user.dto.*
import com.todolist.todo1.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class UserController(
    private val userService: UserService
) {
    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest) : ResponseEntity<LoginResponse> {
         return ResponseEntity.status(HttpStatus.OK).body(userService.loginUser(loginRequest))
    }

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest) : ResponseEntity<UserResponse> {
       return ResponseEntity.status(HttpStatus.OK).body(userService.signupUser(signUpRequest))
    }


}