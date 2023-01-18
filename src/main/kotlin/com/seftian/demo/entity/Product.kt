package com.seftian.demo.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*


@Entity
@Table(name = "products")
data class Product(

        @Id
        var id : String = "",

        @Column(name="name")
        var name: String = "",

        @Column(name="price")
        var price: Long = 0L,

        @Column(name="quantity")
        var quantity: Int = 0,

        @Column(name="created_at")
        var createdAt: Date = Date(),

        @Column(name="updated_at")
        var updatedAt: Date? = null
)
