package com.todolist.todo1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@EnableAspectJAutoProxy
@SpringBootApplication
class ToDo1Application

fun main(args: Array<String>) {
	runApplication<ToDo1Application>(*args)
}
