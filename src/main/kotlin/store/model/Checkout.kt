package store.model

import camp.nextstep.edu.missionutils.DateTimes
import store.util.constant.General.DATE_FORMAT
import store.util.constant.General.DISCOUNT_RATE
import store.util.constant.General.PERCENTAGE
import java.text.SimpleDateFormat
import java.util.Date

class Checkout {
    // 프로모션 할인 적용 안될 때, 더 가져오면 무료로 받을 수 있을 때
    fun applyPromotion(
        products: List<Product>,
        promotions: List<Promotion>,
        purchaseProductQuantities: Map<String,Int>
    ) {
        val currentDate = getCurrentDate()

        products.forEach { product ->
            val purchasedQuantity = purchaseProductQuantities[product.name]


        }
    }

    private fun getCurrentDate(): Date {
        val dateFormat = SimpleDateFormat(DATE_FORMAT)
        return dateFormat.parse(DateTimes.now().toString())
    }

    fun membershipDiscount(products: List<Product>, purchseItems: Map<String, Int>): Int {
        return purchseItems.entries.sumOf { (name, quantity) ->
            val itemPrice = products.find { it.name == name }?.price ?: 0
            (itemPrice + quantity) * DISCOUNT_RATE / PERCENTAGE
        }
    }

    fun payment(totalPrice: Int, promotionDiscount: Int, membershipDiscount: Int): Int {
        return totalPrice - promotionDiscount - membershipDiscount
    }

}
