package com.todolist.todo1.infra.security.oauth.service

import com.todolist.todo1.domain.user.model.User
import com.todolist.todo1.domain.user.model.UserRole
import com.todolist.todo1.domain.user.repository.UserRepository
import com.todolist.todo1.infra.security.jwt.JwtPlugin
import com.todolist.todo1.infra.security.oauth.KaKaoOauthClient
import com.todolist.todo1.infra.security.oauth.KakaoOauthUserInfo
import org.springframework.stereotype.Service

@Service
class OauthService(
    private val kakaoOauthClient :KaKaoOauthClient,
    private val userRepository : UserRepository,
    private val jwtPlugin: JwtPlugin
) {
    fun getLoginPage():String{
    return kakaoOauthClient.getLoginUrl()
    }

    fun login(code: String): String {
        val token = kakaoOauthClient.getAccessToken(code)
        val userInfo = kakaoOauthClient.getUserInfo(token) as KakaoOauthUserInfo
        val existingUser = userRepository.findByEmail(userInfo.properties.nickname)

        val user = if (existingUser != null) {
            existingUser
        } else {
            val newUser = User(
                email = userInfo.properties.nickname,
                password = "",
                role = UserRole.MEMBER,
                oauthProvider = "KAKAO"
            )
            userRepository.save(newUser)
        }

        return jwtPlugin.generateAccessToken(user.id.toString(), user.email, user.role.name)
    }

}