package com.todolist.todo1.infra.security.oauth

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestClient
import org.springframework.web.client.body


@Component
class KaKaoOauthClient(
    @Value("\${kakao.client_id}") val clientId: String,
    @Value("\${kakao.redirect_url}") val redirectUrl: String,
    @Value("\${kakao.auth_server_base_url}") val authServerBaseUrl: String,
    @Value("\${kakao.resource_server_base_url}") val resourceServerBaseUrl: String,
    private val restClient: RestClient
):OauthClient {
    override fun getLoginUrl(): String {
      return StringBuilder(authServerBaseUrl)
          .append("/oauth/authorize")
          .append("?client_id=").append(clientId)
          .append("&redirect_uri=").append(redirectUrl)
          .append("&response_type=").append("code")
          .toString()
    }

    override fun getAccessToken(code: String): String {
        val requestData = mutableMapOf(
            "grant_type" to "authorization_code",
            "client_id" to clientId,
            "redirect_uri" to redirectUrl,
            "code" to code
        )

        return restClient.post()
            .uri("$authServerBaseUrl/oauth/token")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(LinkedMultiValueMap<String,String>().apply { this.setAll(requestData) })
            .retrieve()
            .onStatus(HttpStatusCode::isError) { _, response ->
                throw RuntimeException("${response.statusCode} kakao AccessToken 조회 실패2")
            }
            .body<KakaoTokenResponse>()
            ?.accessToken
            ?:throw RuntimeException("kakao AccessToken 조회 실패13")
    }

    override fun getUserInfo(accessToken: String): OauthClientResponse {
        return restClient.get()
            .uri("$resourceServerBaseUrl/v2/user/me")
            .header("Authorization","Bearer $accessToken")
            .retrieve()
            .onStatus(HttpStatusCode::isError) { _, response ->
                throw RuntimeException("${response.statusCode} kakao AccessToken 조회 실패1")
            }
            .body<KakaoOauthUserInfo>()
            ?:throw RuntimeException("인증 실패")
    }
}