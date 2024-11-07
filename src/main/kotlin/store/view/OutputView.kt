package store.view

import store.model.Product

class OutputView {

    fun welcomeMessage() = println("안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다.\n")

    fun printProductFormat(products: List<Product>) {
        products.forEach { product ->
            println("- ${product.name} ${product.price}원 ${product.quantity}개 ${product.promotion ?: ""}")
        }
    }

    fun printReceiptHeader() {
        println("===========W 편의점=============")
        println("상품명\t\t수량\t금액")
    }

    fun printReceiptBodyPurchasedItems() {
        println("%s\t\t%d\t%d")
    }

    fun printReceiptBody() {
        println("===========증\t정=============")
    }

    fun printReceiptBodyPromotionItems() {
        println("%s\t\t%d")
    }

    fun printReceiptFooter() {
        println("==============================")
        println("총구매액\t\t%d\t%d")
        println("행사할인\t\t\t%d")
        println("멤버십할인\t\t\t%d")
        println("내실돈\t\t\t%d")
    }

}
