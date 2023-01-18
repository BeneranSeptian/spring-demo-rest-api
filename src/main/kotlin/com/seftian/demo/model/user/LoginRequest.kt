package com.seftian.demo.model.user

import jakarta.validation.constraints.NotBlank

data class LoginRequest(

        @field: NotBlank
        var username: String,

        @field: NotBlank
        var password: String
)
