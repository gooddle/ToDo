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
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentServicelmpl(
    private val commentRepository: CommentRepository,
    private val toDoRepository: ToDoRepository
):CommentService{

    @Transactional
    override fun getCreateComment(todoId: Long, request: CreateCommentRequest): CommentResponse {
        val todo = toDoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("ToDo",todoId)
        val comment = Comment(
            name =request.name,
            description =request.description,
            password = request.password,
            todo = todo,
        )
        todo.addComment(comment)
       commentRepository.save(comment)
        return comment.toResponse()
    }
    @Transactional
    override fun getUpdateComment(todoId: Long, commentId: Long, request: UpdateCommentRequest): CommentResponse {
        val comment = commentRepository.findByTodoIdAndId(todoId,commentId) ?: throw ModelNotFoundException("Comment",todoId)
        val(name,description,password)=request
        if(password != comment.password || name != comment.name){
            throw IllegalStateException("아이디 혹은 비밀번호가 일치하지 않습니다.")
        }
        comment.name = name
        comment.description = description
        return  comment.toResponse()


    }
    @Transactional
    override fun getDeleteComment(todoId: Long, commentId: Long,request: DeleteCommentRequest) {
      val todo = toDoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("ToDo",todoId)
      val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment",commentId)
      val (passwords,names) = request
        if(passwords != comment.password || names != comment.name){
          throw IllegalStateException("아이디 혹은 비밀번호가 일치하지 않습니다.")
      }
        todo.deleteComment(comment)
        toDoRepository.save(todo)
    }
}