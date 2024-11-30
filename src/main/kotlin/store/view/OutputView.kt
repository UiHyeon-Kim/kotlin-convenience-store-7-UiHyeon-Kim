package store.view

import store.model.Products
import store.model.ShoppingCart

class OutputView {
    fun printWelcomeMessage() {
        println("안녕하세요. W편의점입니다.")
        println("현재 보유하고 있는 상품입니다.\n")
    }

    fun printProducts(products: List<Products>) {
        products.forEach { product ->
            val quantity = if (product.quantity > 0) "${product.quantity}개" else "재고 없음"
            println("- ${product.name} ${product.price}원 $quantity ${product.promotion}")
        }
    }

    fun printPurchaseInfo(purchasesInfo: List<ShoppingCart>) {
        println("\n===========W 편의점=============")
        println("상품명\t\t\t수량\t\t금액")
        purchasesInfo.forEach { purchaseInfo ->
            // TODO: 구매 개수 추가
            println("${purchaseInfo.name}\t\t${purchaseInfo.quantity}\t${purchaseInfo.quantity}")
        }
    }

    fun printPromotionInfo() {
        println("===========증\t정=============")

    }

    fun printTotalInfo(
        totalCount: Int,
        totalPrice: Int,
        promotionDiscount: Int,
        membershipDiscount: Int,
        totalPayment:Int
        ) {
        println("==============================")
        println("총구매액\t\t${totalCount}\t${totalPrice}")
        println("행사할인\t\t\t${promotionDiscount}")
        println("멤버십할인\t\t\t${membershipDiscount}")
        println("내실돈\t\t\t${totalPayment}")
    }
}
