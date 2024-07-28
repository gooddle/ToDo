package com.todolist.todo1.infra.security.oauth

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class KakaoOauthUserInfo (
    val id :Long,
    val properties : UserInfoProperties
    ) :OauthClientResponse
