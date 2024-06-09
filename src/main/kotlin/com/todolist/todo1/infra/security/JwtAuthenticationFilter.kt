package com.todolist.todo1.infra.security

import com.todolist.todo1.infra.security.jwt.JwtAuthenticationToken
import com.todolist.todo1.infra.security.jwt.JwtPlugin
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtPlugin: JwtPlugin,
) :OncePerRequestFilter(){
    companion object {
        private val BEARER_PATTERN = Regex("^Bearer(.+?)$")

    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = request.getBearerToken()

        if(jwt != null){
            jwtPlugin.validateToken(jwt)
                .onSuccess {
                    val id = it.payload.subject.toLong()
                    val email = it.payload.get("email",String::class.java)
                    val role = it.payload.get("role", String::class.java)


                    val principal = UserPrincipal(
                        id =id,
                        email = email,
                        roles = setOf(role)
                    )
                    val authentication = JwtAuthenticationToken(
                        principal = principal,
                        detail = WebAuthenticationDetailsSource().buildDetails(request),

                    )

                    SecurityContextHolder.getContext().authentication = authentication
                }
                .onFailure { exception ->
                    logger.error("Failed to validate JWT token: ${exception.message}")
                }
        }


        filterChain.doFilter(request, response)

    }
    private fun HttpServletRequest.getBearerToken(): String? {
        val headerValue = this.getHeader(HttpHeaders.AUTHORIZATION) ?: return null
        return BEARER_PATTERN.find(headerValue)?.groupValues?.get(1)?.trim()
    }
}