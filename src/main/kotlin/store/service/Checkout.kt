package store.service

import store.model.Products
import store.model.Promotion
import store.model.ShoppingCart
import java.time.LocalDate

class Checkout(private val promotions: List<Promotion>) {
    // TODO: 프로모션 함수 추가
    fun promotionDiscount(product: Products) {
        val promotion = promotions.find {
            it.name == product.promotion && LocalDate.now() in it.startDate..it.endDate
        }
    }

    fun membershipDiscount(cartItems: List<ShoppingCart>): Int {
        // (총 금액 - 프로모션 할인) * 30 / 100
        val totalPrice = cartItems.sumOf { it.price * it.quantity }
        val discount = (totalPrice * 0.3).toInt()
        return if (discount > 8000) 8000 else discount
    }
}
