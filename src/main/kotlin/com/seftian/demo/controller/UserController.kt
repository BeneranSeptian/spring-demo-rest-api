package com.seftian.demo.controller

import com.seftian.demo.model.product.WebResponse
import com.seftian.demo.model.user.CreateUserRequest
import com.seftian.demo.model.user.LoginRequest
import com.seftian.demo.model.user.LoginResponse
import com.seftian.demo.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(val userService: UserService) {

    @PostMapping(
            value = ["api/createUser"],
            consumes = ["application/json"],
            produces = ["application/json"]
            )
    fun createUser(@RequestBody body: CreateUserRequest): WebResponse<String>{
        val userId = userService.createUser(body)

        return WebResponse(
                code = 200,
                status = "OK",
                data = "Berhasil membuat user dengan id: $userId"
        )

    }

    @PostMapping(
            value = ["api/login"],
            consumes = ["application/json"],
            produces = ["application/json"]
    )
    fun login(@RequestBody body: LoginRequest): WebResponse<LoginResponse>{
        val token = userService.login(body)

        return WebResponse(
                code = 200,
                status = "OK",
                data = token
        )

    }
}