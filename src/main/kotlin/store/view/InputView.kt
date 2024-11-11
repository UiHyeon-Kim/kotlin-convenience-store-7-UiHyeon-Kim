package store.view

import camp.nextstep.edu.missionutils.Console
import store.util.constant.Input

class InputView {
    fun getPurchaseDetails(): String {
        println(Input.PRODUCT_AND_QUANTITY.getMessage())
        return Console.readLine()
    }

    fun selectAddPromotion(product: String): String {
        println(String.format(Input.PROMOTION_ITEM_ADDITION.getMessage(),product))
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
