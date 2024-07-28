package com.todolist.todo1.infra.security.oauth.controller

import com.todolist.todo1.infra.security.oauth.service.OauthService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class OauthController(
    private val oauthService: OauthService
) {


    @GetMapping("/kakao")
    fun getLoginPage() :String{
      return  oauthService.getLoginPage()
    }

    @GetMapping("/kakao/callback")
    fun getLoginPageCallback(@RequestParam code: String) :String {
        return oauthService.login(code)
    }








}