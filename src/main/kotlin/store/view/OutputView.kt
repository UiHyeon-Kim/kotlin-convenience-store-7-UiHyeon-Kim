package store.view

import store.model.Product
import store.util.constant.Output

class OutputView {

    fun welcomeMessage() = println("안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다.\n")

    fun printProductFormat(products: List<Product>) {
        products.forEach { product ->
            println("- ${product.name} ${product.price}원 ${product.quantity}개 ${product.promotion}")
        }
    }

    fun printReceiptHeader() {
        println(Output.RECEIPT_HEADER.getMessage())
        println(Output.RECEIPT_ITEM_LABEL.getMessage())
    }

    fun printReceiptBodyPurchasedItems(name: String, quantity: Int, price: Int) {
        println(String.format(Output.RECEIPT_PURCHASED_ITEM.getMessage(), name, quantity, price))
    }

    fun printReceiptBody() {
        println(Output.RECEIPT_PROMOTION_HEADER.getMessage())
    }

    fun printReceiptBodyPromotionItems(name: String, quantity: Int) {
        println(String.format(Output.RECEIPT_PROMOTION_ITEM.getMessage(), name, quantity))
    }

    fun printReceiptFooter(totalQuantity: Int,
                           totalPrice: Int,
                           promoDiscount: Int,
                           membershipDiscount: Int,
                           totalPay: Int
    ) {
        println(Output.RECEIPT_PROMOTION_FOOTER.getMessage())
        println(String.format(Output.RECEIPT_TOTAL_AMOUNT.getMessage(), totalQuantity, totalPrice))
        println(String.format(Output.RECEIPT_EVENT_DISCOUNT.getMessage(), promoDiscount))
        println(String.format(Output.RECEIPT_MEMBERSHIP_DISCOUNT.getMessage(), membershipDiscount))
        println(String.format(Output.RECEIPT_FINAL_AMOUNT.getMessage(), totalPay))
    }

}
