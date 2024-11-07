package store

import camp.nextstep.edu.missionutils.Console
import javax.swing.text.html.HTML.Attribute.N

class InputView {

    fun purchaseMessage() {
        println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])")
        val purchaseList = Console.readLine()
    }

    fun membershipMessage() {
        println("멤버십 할인을 받으시겠습니까? (Y/N)")
        val membershipChoice = Console.readLine()
    }

}
