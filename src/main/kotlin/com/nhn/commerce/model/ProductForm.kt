package com.nhn.commerce.model

import java.time.LocalDateTime

data class ProductForm (
    var productName: String? = null,
    var salePrice: Int? = 0
)