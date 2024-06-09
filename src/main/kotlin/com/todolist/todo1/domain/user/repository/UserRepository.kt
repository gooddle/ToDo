package com.todolist.todo1.domain.user.repository


import com.todolist.todo1.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean


}