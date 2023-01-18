package com.seftian.demo.repository


import com.seftian.demo.entity.PrimaryKeyUserClass
import com.seftian.demo.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, PrimaryKeyUserClass>{
    fun findByUsernameBeneran(username: String): User?
    fun findByUserId(userId: String):User?
    fun findByToken(token: String):User?
}