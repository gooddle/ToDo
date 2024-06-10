package com.todolist.todo1.domain.comment.service

import com.todolist.todo1.domain.comment.dto.CommentResponse
import com.todolist.todo1.domain.comment.dto.CreateCommentRequest
import com.todolist.todo1.domain.comment.dto.DeleteCommentRequest
import com.todolist.todo1.domain.comment.dto.UpdateCommentRequest

interface CommentService {
    fun createComment(todoId: Long,request: CreateCommentRequest) : CommentResponse
    fun updateComment(todoId: Long, commentId : Long,request: UpdateCommentRequest  ) : CommentResponse
    fun deleteComment(todoId: Long, commentId : Long,request: DeleteCommentRequest)
    fun deleteCommentByAdmin(todoId: Long, commentId : Long)

}