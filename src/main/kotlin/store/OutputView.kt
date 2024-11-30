package store

class OutputView {
    fun printWelcomeMessage() {
        println("안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다.\n")
    }

    fun printProducts(products: List<Products>) {
        products.forEach { product ->
            if (product.quantity < 0) {
                println("- ${product.name} ${product.price}원 재고 없음 ${product.promotion}")
            } else {
                println("- ${product.name} ${product.price}원 ${product.quantity}개 ${product.promotion}")
            }
        }
    }

    fun printReceiptHeader() {
        println("\n===========W 편의점=============\n상품명\t\t수량\t금액")
    }
    fun printPurchaseInfo(purchasesInfo: List<ShoppingCart>) {
        purchasesInfo.forEach { purchaseInfo ->
            println("${purchaseInfo.name}\t\t${purchaseInfo.quantity}\t${purchaseInfo.quantity * purchaseInfo.price}")
        }
    }
    fun printPromotionHeader() {
        println("===========증\t정=============")
    }
    fun printPromotionInfo() {

    }
    fun printTotalHeader() {
        println("==============================")
    }
    fun printTotalInfo(
        totalCount: Int,
        totalPrice: Int,
        promotionDiscount: Int,
        membershipDiscount: Int,
        totalPayment:Int
        ) {
        println("총구매액\t\t${totalCount}\t${totalPrice}")
        println("행사할인\t\t\t${promotionDiscount}")
        println("멤버십할인\t\t\t${membershipDiscount}")
        println("내실돈\t\t\t ${totalPayment}")
    }
}
