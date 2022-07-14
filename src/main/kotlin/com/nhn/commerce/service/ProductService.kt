package com.nhn.commerce.service

import com.nhn.commerce.model.Product
import com.nhn.commerce.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
) {
    fun findProductList(): List<Product> = productRepository.findProductList()

    fun findProductById(id: Int): Product? = productRepository.findById(id)

    fun createProduct(product: Product): Int = productRepository.insert(product)

    fun deleteProduct(id: Int): Int = productRepository.deleteById(id)

    fun updateProduct(product: Product): Int = productRepository.update(product)
}
