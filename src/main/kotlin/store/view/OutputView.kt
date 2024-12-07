package store.view

import store.domain.Product
import store.domain.ShoppingCart
import store.utils.toWonFormat

class OutputView {
    fun printWelcomeMessage() {
        println("안녕하세요. W편의점입니다.")
        println("현재 보유하고 있는 상품입니다.\n")
    }

    fun printProducts(products: List<Product>) {
        products.forEach { product ->
            val quantity = if (product.quantity > 0) "${product.quantity}개" else "재고 없음"
            println("- ${product.name} ${product.price.toWonFormat()}원 $quantity ${product.promotion ?: ""}")
        }
    }

    fun printPurchaseInfo(purchasesInfo: List<ShoppingCart>) {
        println("\n===========W 편의점=============")
        println("상품명".padEnd(10) + "\t" + "수량".padEnd(4) + "\t" + "금액".padStart(4))
        purchasesInfo.forEach { purchaseInfo ->
            println(
                purchaseInfo.name.padEnd(12) + "\t" +
                        "${purchaseInfo.quantity}".padEnd(4) + "\t" +
                        (purchaseInfo.quantity * purchaseInfo.price).toWonFormat().padStart(6)
            )
        }
    }

    fun printPromotionInfo(promotionItem: String, promotionItemCount: Int) {
        println("===========증   정=============")
        // TODO: 증정 상품 추가
        println(promotionItem.padEnd(12) + "\t" + "$promotionItemCount".padEnd(4))
    }

    fun printTotalInfo(
        totalCount: Int,
        totalPrice: Int,
        promotionDiscount: Int,
        membershipDiscount: Int,
        totalPayment: Int
    ) {
        println("==============================")
        println("총구매액".padEnd(12) + "\t" + "$totalCount".padEnd(4) + totalPrice.toWonFormat().padStart(10))
        println("행사할인".padEnd(16) + "\t" + promotionDiscount.toWonFormat().padStart(10))
        println("멤버십할인".padEnd(16) + "\t" + membershipDiscount.toWonFormat().padStart(10))
        println("내실돈".padEnd(16) + "\t" + totalPayment.toWonFormat().padStart(10))
    }
}
