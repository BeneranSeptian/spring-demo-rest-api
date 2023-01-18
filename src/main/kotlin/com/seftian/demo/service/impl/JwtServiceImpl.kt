package com.seftian.demo.service.impl

import com.seftian.demo.service.JwtService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*

@Service
class JwtServiceImpl : JwtService {

    override fun extractUsername(token: String): String {
        return extractClaim(token, Claims::getSubject)
    }


    override fun extractAllClaims(token: String): Claims {
        return Jwts
                .parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token)
                .body
    }

    override fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    override fun generateToken(extraClaims: Map<String, Any>, userDetails: UserDetails): String {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.username)
                .setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(SignatureAlgorithm.HS256, getSigningKey())
                .compact()
    }

    override fun generateToken(userDetails: UserDetails): String {
        return generateToken(HashMap(), userDetails)
    }

    override fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return (username == userDetails.username) && !isTokenExpired(token)
    }

    override fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    override fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }


    private fun getSigningKey(): Key {
        val keyBytes = Decoders.BASE64.decode(SECRET_KEY)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    companion object {
        private const val SECRET_KEY = "28482B4D6251655468576D5A7134743677397A24432646294A404E635266556A"
    }
}