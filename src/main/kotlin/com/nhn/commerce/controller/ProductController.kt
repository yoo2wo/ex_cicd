package com.nhn.commerce.controller

import com.nhn.commerce.model.Product
import com.nhn.commerce.model.ProductForm
import com.nhn.commerce.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import kotlin.reflect.typeOf


@Controller
class ProductController(
    private val productService: ProductService,

) {
    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/product")
    fun getProductList(model: Model): String {
        model.addAttribute("productList", productService.findProductList())
        return "product"
    }

    // TODO (상품 상세 조회 기능 + Exception 처리)

    // TODO (상품 추가 기능)
    @GetMapping("product/new")
    fun createProductForm(model: Model): String {
        model.addAttribute("form", ProductForm())
        return "/createProduct"
    }

    private fun Int.isPositive():Boolean = this >= 0
    private fun Int.isNotPositive():Boolean = !isPositive()

    //default parameter 이용
    @PostMapping( "/product/new")
    fun createProduct( @RequestParam("productName") productName: String="기본", @RequestParam("salePrice") salePrice: Int?=0 ) : String {

        val product = Product(productNo = null, productName = productName, salePrice = salePrice?:0, registerYmdt = null, updateYmdt = null)
        product.let {
            if (it.salePrice.isNotPositive()){
                log.error("가격이 음수입니다.")
                throw Exception("가격이 음수입니다.")
                //return "redirect:/product"
            }
        }
        productService.createProduct(product)
        return "redirect:/product"
    }

//    @PostMapping("/product/new")
//    fun createProduct(productForm: ProductForm) : String {
//        val product = Product(null, productForm.productName, null, productForm.salePrice?:0, null)
//        product.let {
//            if (it.salePrice.isNotPositive()){
//                log.error("가격이 음수입니다.")
//                throw Exception("가격이 음수입니다.")
//               // return "redirect:/product"
//            }
//        }
//        productService.createProduct(product)
//        return "redirect:/product"
//    }

    // TODO (상품 수정 기능 + Exception 처리)
    @GetMapping("/product/{productNo}/edit")
    fun updateProductForm(@PathVariable(name="productNo") productNo : Int, model : Model) : String{
        val product = productService.findProductById(productNo);
        if (product == null)
            return "redirect:/product"
        val productForm = ProductForm(product?.productName, product?.salePrice)
        model.addAttribute("form", productForm);
        model.addAttribute("productNo", productNo)
        return "/updateProduct"
    }

//    @PostMapping( "/product/{productNo}/edit")
//    fun updateProduct(@PathVariable(name="productNo") productNo : Int, @ModelAttribute("form") productForm: ProductForm) : String {
//        val product = Product(productNo = productNo, productName = productForm.productName, salePrice = productForm.salePrice?:0, registerYmdt = null, updateYmdt = null)
//        productService.updateProduct(product)
//        return "redirect:/product"
//    }

    //default parameter 이용
    @PostMapping( "/product/{productNo}/edit")
    fun updateProduct(@PathVariable(name="productNo") productNo : Int, @RequestParam("productName") productName: String="기본", @RequestParam("salePrice") salePrice: Int?=0 ) : String {

        val product = Product(productNo = productNo, productName = productName, salePrice = salePrice?:0, registerYmdt = null, updateYmdt = null)
        productService.updateProduct(product)
        return "redirect:/product"
    }

    // TODO (상품 삭제 기능 + Exception 처리)
    @GetMapping("/product/{productNo}/delete")
    fun deleteProduct(@PathVariable(name="productNo") productNo : Int) : String {
        productService.deleteProduct(productNo)
        return "redirect:/product"
    }

}
