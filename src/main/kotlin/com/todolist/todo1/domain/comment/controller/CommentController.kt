package com.todolist.todo1.domain.comment.controller

import com.todolist.todo1.domain.comment.dto.CommentResponse
import com.todolist.todo1.domain.comment.dto.CreateCommentRequest
import com.todolist.todo1.domain.comment.dto.DeleteCommentRequest
import com.todolist.todo1.domain.comment.dto.UpdateCommentRequest
import com.todolist.todo1.domain.comment.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todo/{todoId}/comment")
@RestController
class CommentController(
    private val commentService: CommentService
) {


    @PostMapping
    fun createComment(@PathVariable todoId: Long, @RequestBody createCommentRequest: CreateCommentRequest) : ResponseEntity<CommentResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(todoId,createCommentRequest))

    }
    @PutMapping("/{commentId}")
    fun updateComment(@PathVariable todoId: Long,@PathVariable commentId: Long, @RequestBody updateCommentRequest: UpdateCommentRequest) : ResponseEntity<CommentResponse> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(commentService.updateComment(todoId, commentId, updateCommentRequest))

    }
    @DeleteMapping("/{commentId}")
    fun deleteComment(@PathVariable todoId: Long,@PathVariable commentId: Long,@RequestBody deleteCommentRequest: DeleteCommentRequest) : ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(commentService.deleteComment(todoId, commentId,deleteCommentRequest))

    }


}