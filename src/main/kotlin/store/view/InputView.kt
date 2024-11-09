package store.view

import camp.nextstep.edu.missionutils.Console
import store.util.constant.Input

class InputView {
    val regex = Regex("[\\[\\]]")

    fun getPurchaseDetails(): String {
        println(Input.PRODUCT_AND_QUANTITY.message)
        return Console.readLine()
    }

    fun selectPromotion(): String {
        println("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)")
        return Console.readLine()
    }



    fun selectMembership(): String {
        println("멤버십 할인을 받으시겠습니까? (Y/N)")
        return Console.readLine()
    }

    fun selectAdditionalPurchases(): String {
        println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)")
        return Console.readLine()
    }

}
