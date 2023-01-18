package com.seftian.demo.model.product

data class WebResponse<T>(
        val code: Int,
        val status: String,
        val data: T
)
