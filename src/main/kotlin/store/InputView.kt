package store

import camp.nextstep.edu.missionutils.Console

class InputView {
    fun readProductAndQuantity(): String {
        println("\n구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])")
        return Console.readLine()
    }

    fun readAddPromotion(): String {
        return Console.readLine()
    }

    fun readNonDiscountPromotion(): String {
        return Console.readLine()
    }

    fun readMembershipDiscount(): String {
        println("멤버십 할인을 받으시겠습니까? (Y/N)")
        return Console.readLine()
    }

    fun readMorePurchase(): String {
        println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)")
        return Console.readLine()
    }

}
