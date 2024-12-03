package store.view

import camp.nextstep.edu.missionutils.Console

class InputView {
    fun readProductAndQuantity(): String {
        println()
        println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])")
        return Console.readLine()
    }

    fun readSelect(message: String): String {
        println()
        println(message)
        return Console.readLine()
    }

    /*fun readAddPromotion(name: String): String {
        println()
        println("현재 ${name}은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)")
        return Console.readLine()
    }

    fun readNonDiscountPromotion(name: String, quantity: String): String {
        println()
        println("현재 $name ${quantity}개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)")
        return Console.readLine()
    }

    fun readMembershipDiscount(): String {
        println()
        println("멤버십 할인을 받으시겠습니까? (Y/N)")
        return Console.readLine()
    }*/

    fun readMorePurchase(): String {
        println()
        println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)")
        return Console.readLine()
    }

}
