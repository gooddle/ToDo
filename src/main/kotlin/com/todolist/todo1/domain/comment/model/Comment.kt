package com.todolist.todo1.domain.comment.model

import com.todolist.todo1.domain.comment.dto.CommentResponse
import com.todolist.todo1.domain.todo.model.ToDo
import jakarta.persistence.*

@Entity
@Table(name = "comment")
class Comment(

    @Column(name = "name", nullable = false)
    var name: String,
    @Column(name = "description", nullable = false)
    var description: String,
    @Column(name = "password", nullable = false)
    var password: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    var todo : ToDo


) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id =id!!,
        name = name,
        description = description,
    )
}