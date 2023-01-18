package com.seftian.demo.controller

import com.seftian.demo.model.product.CreateProductRequest
import com.seftian.demo.model.product.ProductResponse
import com.seftian.demo.model.product.UpdateProductRequest
import com.seftian.demo.model.product.WebResponse
import com.seftian.demo.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(val productService: ProductService) {
    
    @PostMapping(
            value = ["/api/products"],
            produces = ["application/json"],
            consumes = ["application/json"]
    )
    fun createProduct(@RequestBody body: CreateProductRequest): WebResponse<ProductResponse> {
        val productResponse = productService.create(body)
        return WebResponse(
                code = 200,
                status = "OK",
                data = productResponse
        )
    }

    @GetMapping(
            value = ["api/products/{id_product}"],
            produces = ["application/json"],
            consumes = ["application/json"])
    fun getProduct(@PathVariable("id_product") id: String): WebResponse<ProductResponse> {
        val productResponse = productService.get(id)
        return WebResponse(
                code = 200,
                status = "OK",
                data = productResponse
        )
    }

    @PutMapping("api/products/{id_product}")
    fun updateProduct(
            @PathVariable("id_product") id: String,
            @RequestBody body: UpdateProductRequest
    ): WebResponse<ProductResponse> {
        val productResponse = productService.update(id,body)
        return WebResponse(
                code = 200,
                status = "OK",
                data = productResponse
        )
    }

    @GetMapping("api/products/allproducts")
    fun getAllProduct(): WebResponse<List<ProductResponse>> {
        val productResponse = productService.getAll()
        return WebResponse(
                code = 200,
                status = "OK",
                data = productResponse
        )
    }

    @DeleteMapping("api/products/{id_product}")
    fun deleteProduct(
            @PathVariable("id_product") id: String,
    ): WebResponse<String> {
        val productResponse = productService.delete(id)
        return WebResponse(
                code = 200,
                status = "OK",
                data = productResponse
        )
    }
}