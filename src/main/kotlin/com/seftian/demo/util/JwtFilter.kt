package com.seftian.demo.util

import com.seftian.demo.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter


@Component
class JwtFilter(
        private val jwtService: JwtService,
        private val userDetailsService: UserDetailsService
): OncePerRequestFilter() {

    override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            filterChain: FilterChain) {

        val header = request.getHeader("Authorization")
        if(header == null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request, response)
            return
        }

        val jwt = header.substring(7)
        val username = jwtService.extractUsername(jwt)

        if (username != null && SecurityContextHolder.getContext().authentication == null){
            val userDetails = userDetailsService.loadUserByUsername(username)

            if (userDetails?.let { jwtService.isTokenValid(jwt, it) } == true){
                val authToken = UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        null
                )

                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
            }
        }

        filterChain.doFilter(request, response)
    }

}
