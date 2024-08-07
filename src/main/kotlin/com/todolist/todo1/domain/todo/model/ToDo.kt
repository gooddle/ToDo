package com.todolist.todo1.domain.todo.model


import com.todolist.todo1.domain.comment.model.Comment
import com.todolist.todo1.domain.comment.model.toResponse
import com.todolist.todo1.domain.todo.dto.ToDoResponse
import com.todolist.todo1.domain.todo.dto.UpdateToDoRequest
import com.todolist.todo1.domain.user.model.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "todocard")
class ToDo(
    @Column(name ="title", nullable = false)
    var title: String,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "description", nullable = false)
    var description: String,

    @Column(name = "date", nullable = false)
    var date: LocalDateTime = LocalDateTime.now(),

    @Column(name = "status", nullable = false)
    var status : Boolean =false,

    @OneToMany(mappedBy = "todo", cascade = [(CascadeType.ALL)],orphanRemoval = true,fetch = FetchType.LAZY)
    var comments: MutableSet<Comment> = mutableSetOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User



    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun addComment(comment: Comment) {
        comments.add(comment)
    }
    fun deleteComment(comment: Comment) {
        comments.remove(comment)
    }
    fun done(request : UpdateToDoRequest) {
        title = request.title
        name = request.name
        description = request.description
    }
    fun isDone(){
        status = true
    }



}


fun ToDo.toResponse(): ToDoResponse {
    return ToDoResponse(
        id= id!!,
        title= title,
        name= name,
        description= description,
        date = date,
        status = status,
        comments = comments.map { it.toResponse() }
    )
}
