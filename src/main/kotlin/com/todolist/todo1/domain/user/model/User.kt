package com.todolist.todo1.domain.user.model



import com.todolist.todo1.domain.user.dto.UserResponse
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    val role : UserRole,

    @Column(name = "oauthProvider", nullable = false)
    var oauthProvider :String? = null




    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun toResponse(): UserResponse {
        return UserResponse(
            id = this.id!!,
            email = this.email,
            role = role.name
        )
    }




}

