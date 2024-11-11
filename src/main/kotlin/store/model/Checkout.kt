package store.model

import camp.nextstep.edu.missionutils.DateTimes
import store.util.constant.General.DATE_FORMAT
import store.util.constant.General.DISCOUNT_RATE
import store.util.constant.General.PERCENTAGE
import java.text.SimpleDateFormat
import java.util.Date

class Checkout {
    // TODO: 금액 계산, 프로모션, 멤버십
    // 프로모션 할인 적용 안될 때, 더 가져오면 무료로 받을 수 있을 때
    fun applyPromotion(
        products: List<Product>,
        promotions: List<Promotion>,
        purchaseProductQuantities: Map<String,Int>
    ) {
        products.forEach { product ->
            val matchItem = purchaseProductQuantities
                .filter { it.key in product.name }
                .map {  }
            println(matchItem)
        }
    }

    private fun getCurrentDate(): Date {
        val dateFormat = SimpleDateFormat(DATE_FORMAT)
        return dateFormat.parse(DateTimes.now().toString())
    }

//    fun promotionDiscount(products: List<Product>, purchaseProductQuantities: Map<String, Int>): Int {
//
//    }

    // 전체값 * 퍼센트 / 100
    // (최종 구매 물건 개수 * 금액) * 30 / 100
    fun membershipDiscount(intetmediatePrice: Int): Int {
        return intetmediatePrice * DISCOUNT_RATE / PERCENTAGE
    }

    fun payment(totalPrice: Int, promotionDiscount: Int, membershipDiscount: Int): Int {
        return totalPrice - promotionDiscount - membershipDiscount
    }

}
