package com.nhn.commerce.model

import java.time.LocalDateTime

data class Product(
    val productNo: Int? = null,
    var productName: String? = null,
    val registerYmdt: LocalDateTime?,
    val salePrice: Int,
    var updateYmdt: LocalDateTime?,
)
