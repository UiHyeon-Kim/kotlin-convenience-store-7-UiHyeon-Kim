package store

import camp.nextstep.edu.missionutils.Console

class InputView {
    fun readProductAndQuantity(): String {
        println("\n구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])")
        return Console.readLine()
    }

}
