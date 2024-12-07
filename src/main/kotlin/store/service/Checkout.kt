package store.service

import store.domain.Product
import store.domain.Promotion
import store.domain.ShoppingCart
import java.time.LocalDate

class Checkout(private val promotions: List<Promotion>) {
    // TODO: 프로모션 함수 추가
    /*fun promotionDiscount(product: Product, cartItems: List<ShoppingCart>): Int {
        // 일단 재고. 일반, 프로모션 재고를 가져와야함
        val promotion = promotions.find {
            it.name == product.promotion && LocalDate.now() in it.startDate..it.endDate
        } ?: return 0

        // 이후 계산
        val promotionItem = (promotion.buy) * promotion.get
    }*/

    fun membershipDiscount(cartItems: List<ShoppingCart>): Int {
        val totalPrice = cartItems.sumOf { it.price * it.quantity }
        val discountAmount = (totalPrice * 0.3).toInt()
        return if (discountAmount > 8000) 8000 else discountAmount
    }

    ///


}
