package com.todolist.todo1.domain.todo.dto

import com.todolist.todo1.domain.comment.dto.CommentResponse
import java.time.LocalDateTime


data class ToDoResponse(
    val id: Long,
    val title: String?,
    val name: String,
    val description: String,
    var date: LocalDateTime,
    var status : Boolean,
    var comments:List<CommentResponse>
)