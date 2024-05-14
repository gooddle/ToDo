package com.todolist.todo1.domain.todo.model
import com.todolist.todo1.domain.todo.dto.ToDoResponse
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "todolist")
class ToDo(
    @Column(name ="title",nullable = false)
    var title: String,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "description", nullable = false)
    var description: String,

    @Column(name = "date", nullable = false)
    val date: LocalDateTime = LocalDateTime.now()

    ){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null


    }


fun ToDo.toResponse(): ToDoResponse {
    return ToDoResponse(
        id= id!!,
        title= title,
        name= name,
        description= description,
        date = date,
    )
}
