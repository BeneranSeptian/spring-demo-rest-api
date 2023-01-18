package com.seftian.demo.service

import com.seftian.demo.model.user.CreateUserRequest
import com.seftian.demo.model.user.LoginRequest
import com.seftian.demo.model.user.LoginResponse

interface UserService {
    fun createUser(createUserRequest: CreateUserRequest): String
    fun login(loginRequest: LoginRequest): LoginResponse
}