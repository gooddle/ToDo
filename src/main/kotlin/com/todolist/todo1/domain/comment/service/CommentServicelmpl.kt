package com.todolist.todo1.domain.comment.service


import com.todolist.todo1.domain.comment.dto.CommentResponse
import com.todolist.todo1.domain.comment.dto.CreateCommentRequest
import com.todolist.todo1.domain.comment.dto.DeleteCommentRequest
import com.todolist.todo1.domain.comment.dto.UpdateCommentRequest
import com.todolist.todo1.domain.comment.model.Comment
import com.todolist.todo1.domain.comment.model.toResponse
import com.todolist.todo1.domain.comment.repository.CommentRepository
import com.todolist.todo1.domain.exception.ModelNotFoundException
import com.todolist.todo1.domain.todo.repository.ToDoRepository
import com.todolist.todo1.domain.user.repository.UserRepository
import com.todolist.todo1.infra.security.UserPrincipal
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service



@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val toDoRepository: ToDoRepository,
    private val userRepository: UserRepository,
): CommentService {
    @Transactional
    override fun createComment(todoId: Long, request: CreateCommentRequest): CommentResponse {
        val userEmail = SecurityContextHolder.getContext().authentication.principal as UserPrincipal
        val user = userRepository.findByEmail(userEmail.email) ?: throw ModelNotFoundException("User", 1)
        val todo = toDoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("ToDo", todoId)
        val comment = Comment(
            name = request.name,
            description = request.description,
            todo = todo,
            user = user
        )
        todo.addComment(comment)
        commentRepository.save(comment)
        return comment.toResponse()
    }


    @Transactional
    override fun updateComment(todoId: Long, commentId: Long, request: UpdateCommentRequest): CommentResponse {

        val comment = commentRepository.findByTodoIdAndId(todoId, commentId) ?: throw ModelNotFoundException("Comment", commentId)

        val userEmail = SecurityContextHolder.getContext().authentication.principal as UserPrincipal

        val user = userRepository.findByEmail(userEmail.email) ?: throw ModelNotFoundException("User", 1)

        if (comment.user != user) {
            throw IllegalStateException("권한 없습니다.")
        }
        comment.name = request.name
        comment.description = request.description

        commentRepository.save(comment)
        return comment.toResponse()
    }

    @Transactional
    override fun deleteComment(todoId: Long, commentId: Long, request: DeleteCommentRequest) {
        val todo = toDoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("ToDo", todoId)

        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)

        val userEmail = SecurityContextHolder.getContext().authentication.principal as UserPrincipal

        val user = userRepository.findByEmail(userEmail.email) ?: throw ModelNotFoundException("User", 1)

        if (comment.user != user) {
            throw IllegalStateException("권한 없습니다.")
        }
        todo.deleteComment(comment)
        toDoRepository.save(todo)
    }
}
