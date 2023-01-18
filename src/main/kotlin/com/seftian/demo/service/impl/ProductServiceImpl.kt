package com.seftian.demo.service.impl

import com.seftian.demo.entity.Product
import com.seftian.demo.model.product.CreateProductRequest
import com.seftian.demo.model.product.ProductResponse
import com.seftian.demo.model.product.UpdateProductRequest
import com.seftian.demo.repository.ProductRepository
import com.seftian.demo.service.ProductService
import com.seftian.demo.util.getRandomString
import com.seftian.demo.util.validation.ValidationUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductServiceImpl(
        val productRepository: ProductRepository,
        val validationUtil: ValidationUtil,
        ): ProductService{

    override fun create(createProductRequest: CreateProductRequest): ProductResponse {
        validationUtil.validate(createProductRequest)

        var product = Product(
                id = getRandomString(6),
                name = createProductRequest.name!!,
                price = createProductRequest.price!!,
                quantity = createProductRequest.quantity!!,
                createdAt = Date(),
                updatedAt = Date()
        )

        var existingProduct = productRepository.existsById(product.id)
        while(existingProduct){
            product.id = getRandomString(6)
            existingProduct = productRepository.existsById(product.id)
        }

        productRepository.save(product)

        return ProductResponse(
                id = product.id,
                name = product.name,
                price = product.price,
                quantity = product.quantity,
                createdAt = product.createdAt,
                updatedAt = product.updatedAt
        )
    }

    override fun get(idProduct: String): ProductResponse {
        val product = productRepository.findByIdOrNull(idProduct)
        if(product != null){
            return ProductResponse(
                    id = product.id,
                    name = product.name,
                    price = product.price,
                    quantity = product.quantity,
                    createdAt = product.createdAt,
                    updatedAt = product.updatedAt
            )
        }

        throw Exception("Item dengan id: $idProduct tidak ditemukan")
    }

    override fun update(idProduct: String, updateProductRequest: UpdateProductRequest): ProductResponse {
        validationUtil.validate(updateProductRequest)

        val findProduct = productRepository.findByIdOrNull(idProduct)

        if(findProduct != null){
            val product = Product(
                    id = idProduct,
                    name = updateProductRequest.name!!,
                    price = updateProductRequest.price!!,
                    quantity = updateProductRequest.quantity!!,
                    updatedAt = Date(),
                    createdAt = findProduct.createdAt,
            )

            productRepository.save(product)

            return ProductResponse(
                    id = product.id,
                    name = product.name,
                    price = product.price,
                    quantity = product.quantity,
                    createdAt = product.createdAt,
                    updatedAt = product.updatedAt
            )
        }
        throw Exception("Produk dengan id $idProduct kaga ada cuk!")
    }

    override fun getAll(): List<ProductResponse> {
        val allProducts = productRepository.findAll()
        return allProducts.map{product ->
            ProductResponse(
                    id = product.id,
                    name = product.name,
                    price = product.price,
                    quantity = product.quantity,
                    createdAt = product.createdAt,
                    updatedAt = product.updatedAt
            )
        }
    }

    override fun delete(idProduct: String): String {
       val item =  productRepository.findById(idProduct)

        if(item.isPresent){
            productRepository.delete(item.get())
            return "Item dengan id $idProduct berhasil dihapus!"
        }

        throw Exception("Gagal hapus item $idProduct, itemnya ga ada goblok!")
    }
}