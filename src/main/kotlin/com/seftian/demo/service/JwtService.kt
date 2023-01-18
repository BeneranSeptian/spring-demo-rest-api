package com.seftian.demo.service

import io.jsonwebtoken.Claims
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

interface JwtService {
    fun extractUsername(token: String): String?
    fun extractAllClaims(token: String):Claims
    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T
    fun generateToken(extraClaims: Map<String, Any>, userDetails: UserDetails): String
    fun generateToken(userDetails: UserDetails) : String
    fun isTokenValid(token: String, userDetails: UserDetails): Boolean
    fun isTokenExpired(token: String): Boolean
    fun extractExpiration(token: String): Date
}