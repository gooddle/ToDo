package com.todolist.todo1.infra.security.oauth

interface OauthClient {

    fun getLoginUrl(): String
    fun getAccessToken(code:String): String
    fun getUserInfo(accessToken: String) :OauthClientResponse

}