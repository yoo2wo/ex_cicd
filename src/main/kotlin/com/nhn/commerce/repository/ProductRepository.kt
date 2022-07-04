package com.nhn.commerce.repository

import com.nhn.commerce.model.Product
import org.apache.ibatis.annotations.*

@Mapper
interface ProductRepository {
    @Select("SELECT * FROM product")
    fun findProductList(): List<Product>
}
