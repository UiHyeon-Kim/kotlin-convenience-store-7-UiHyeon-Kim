package store

import camp.nextstep.edu.missionutils.Console
import jdk.internal.org.jline.utils.Colors.s
import javax.swing.text.html.HTML.Attribute.N

class InputView {

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

}
