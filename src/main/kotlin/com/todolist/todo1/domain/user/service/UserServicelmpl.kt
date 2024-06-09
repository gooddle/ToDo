package com.todolist.todo1.domain.user.service

import com.todolist.todo1.domain.exception.ModelNotFoundException
import com.todolist.todo1.domain.user.dto.*
import com.todolist.todo1.domain.user.model.User
import com.todolist.todo1.domain.user.model.UserRole
import com.todolist.todo1.domain.user.repository.UserRepository
import com.todolist.todo1.infra.security.jwt.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserServicelmpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin,
): UserService {
    override fun signupUser(signupRequest: SignUpRequest): UserResponse {
        if (userRepository.existsByEmail(signupRequest.email)) {
            throw IllegalStateException("이미 사용중인 이메일")
        }

        return userRepository.save(
            User(
                email = signupRequest.email,
                password = passwordEncoder.encode(signupRequest.password),
                role = when (signupRequest.role) {
                    "MEMBER" -> UserRole.MEMBER
                    "ADMIN" -> UserRole.ADMIN
                    else -> throw IllegalArgumentException("Invalid role")
                }


            )
        ).toResponse()
    }

    @Transactional
    override fun loginUser(loginRequest: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(loginRequest.email) ?: throw ModelNotFoundException("User", null)

        if (user.role.name != loginRequest.role || !passwordEncoder.matches(loginRequest.password, user.password) ) {
            throw ModelNotFoundException("Dd",1)
        }

        return LoginResponse(
            token = jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                email = user.email,
                role = user.role.name
            )
        )
    }





}











