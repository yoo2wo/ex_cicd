package com.nhn.commerce.repository

import com.nhn.commerce.model.Product
import org.apache.ibatis.annotations.*
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface ProductRepository {
    @Select("SELECT * FROM product")
    fun findProductList(): List<Product>

    @Select("SELECT * FROM product WHERE productNo = #{id}")
    fun findById(id : Int) : Product?

    @Insert(
            "INSERT INTO product(productName, salePrice) " +
                    " VALUES (#{productName}, #{salePrice})")
    fun insert(product: Product?): Int

    @Update(
            "Update product set productName=#{productName}, salePrice=#{salePrice} where productNo=#{productNo}"
    )
    fun update(product: Product?): Int

    @Delete("DELETE FROM product WHERE productNo=#{productNo}")
    fun deleteById(id: Int):Int
}
