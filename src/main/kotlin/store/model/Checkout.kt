package store.model

import camp.nextstep.edu.missionutils.DateTimes
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
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        return dateFormat.parse(DateTimes.now().toString())
    }

    // 전체값 * 퍼센트 / 100
    // (최종 구매 물건 개수 * 금액) * 30 / 100
    fun membershipDiscount() {

    }

}
