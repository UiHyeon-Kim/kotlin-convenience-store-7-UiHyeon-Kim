package store.view

import camp.nextstep.edu.missionutils.Console

class InputView {
    val regex = Regex("[\\[\\]]")

    fun purchaseMessage() {
        println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])")
        val purchaseList = Console.readLine()
    }

    fun promotionMessage() {
        println("현재 %s는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)")
        val promotionChoice = Console.readLine()
    }

    fun membershipMessage() {
        println("멤버십 할인을 받으시겠습니까? (Y/N)")
        val membershipChoice = Console.readLine()
    }

    fun additionalPurchasesMessage() {
        println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)")
        val additionalPurchaseChoice = Console.readLine()
    }

}
