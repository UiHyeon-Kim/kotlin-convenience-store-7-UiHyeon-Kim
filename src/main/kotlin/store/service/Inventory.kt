package store.service

import store.domain.Product
import store.domain.ShoppingCart

class Inventory(private val products: MutableList<Product>) {
    fun getProduct(name:String): Product {
        return products.find { it.name == name }!!
    }

    fun getQuantity(): Pair<Int, Int> {
        val promotionQuantity = products.count { it.promotion == null }
        val generalQuantity = products.count { it.promotion != null }
        return promotionQuantity to generalQuantity
    }

    fun check() {
//        println(products.filter { it.pro })
    }

    // TODO: 재고 차감 함수 구현하기
    fun reduceStock(cartItems: List<ShoppingCart>) {
        cartItems.forEach { item ->
            val product = getProduct(item.name)
            val afterProduct = product.copy(quantity = product.quantity - item.quantity)
            products[products.indexOf(product)] = afterProduct

        }
    }

}
