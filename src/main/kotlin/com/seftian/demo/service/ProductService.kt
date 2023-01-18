package com.seftian.demo.service

import com.seftian.demo.model.product.CreateProductRequest
import com.seftian.demo.model.product.ProductResponse
import com.seftian.demo.model.product.UpdateProductRequest


interface ProductService {
    fun create(createProductRequest: CreateProductRequest): ProductResponse
    fun get(idProduct: String): ProductResponse
    fun update(idProduct: String, updateProductRequest: UpdateProductRequest): ProductResponse
    fun getAll(): List<ProductResponse>
    fun delete(idProduct: String): String
}