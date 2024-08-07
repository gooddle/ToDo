package com.todolist.todo1.domain.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient


@Configuration
class RestClientConfig {

    @Bean
    fun restClient()= RestClient.builder().build()

}