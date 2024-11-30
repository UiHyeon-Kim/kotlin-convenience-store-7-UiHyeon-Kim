package store.service

import store.model.Products

class Inventory(private val products: MutableList<Products>) {
    fun getProduct(name:String): Products? {
        return products.find { it.name == name }
    }

    // TODO: 재고 차감 함수 구현하기
}
