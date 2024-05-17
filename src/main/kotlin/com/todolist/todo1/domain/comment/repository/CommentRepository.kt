package com.todolist.todo1.domain.comment.repository

import com.todolist.todo1.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByTodoIdAndId(todoId: Long, commentId: Long): Comment?
}