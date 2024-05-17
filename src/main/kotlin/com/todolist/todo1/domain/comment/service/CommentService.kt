package com.todolist.todo1.domain.comment.service

import com.todolist.todo1.domain.comment.dto.CommentResponse
import com.todolist.todo1.domain.comment.dto.CreateCommentRequest
import com.todolist.todo1.domain.comment.dto.DeleteCommentRequest
import com.todolist.todo1.domain.comment.dto.UpdateCommentRequest

interface CommentService {
    fun getComments(todoId: Long) : List<CommentResponse>
    fun getCommentById(todoId: Long,commentId : Long) : CommentResponse
    fun getCreateComment(todoId: Long,request: CreateCommentRequest) : CommentResponse
    fun getUpdateComment(todoId: Long, commentId : Long,request: UpdateCommentRequest  ) : CommentResponse
    fun getDeleteComment(todoId: Long, commentId : Long,request: DeleteCommentRequest)

}