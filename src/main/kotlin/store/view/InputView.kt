package store.view

import camp.nextstep.edu.missionutils.Console
import store.util.constant.Input

class InputView {
    fun getPurchaseDetails(): String {
        println(Input.PRODUCT_AND_QUANTITY.getMessage())
        return Console.readLine()
    }

    fun selectGeneralPrice(): String {
        println("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)")
        return Console.readLine()
    }

    fun selectAddPromotion(): String {
        println("현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)")
        return Console.readLine()
    }

    fun selectMembership(): String {
        println(Input.MEMBERSHIP_DISCOUNT.getMessage())
        return Console.readLine()
    }

    fun selectMorePurchases(): String {
        println(Input.ADDITIONAL_PURCHASES.getMessage())
        return Console.readLine()
    }

}
