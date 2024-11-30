package store

import store.model.Products
import store.model.Promotion
import store.model.ShoppingCart
import store.service.Checkout
import store.service.Inventory
import store.utils.retryInput
import store.view.InputView
import store.view.OutputView

class ConvenienceStore(
    private val products: MutableList<Products>,
    private val promotion: List<Promotion>,
    private val inputView: InputView,
    private val outputView: OutputView,
    private val inventory: Inventory,
    private val checkout: Checkout,
) {
    fun start() {
        outputView.printWelcomeMessage()
        outputView.printProducts(products)
        val cartItems = parseAndValidateCartItems()
        //TODO: 프로모션 적용 함수 추가하기
        choiceMembershipDiscount()
        printReceipt()
        choiceMorePurchase()
    }

    private fun parseAndValidateCartItems(): List<ShoppingCart> = retryInput {
        val rawCartItems = inputView.readProductAndQuantity()
        val splitCartItems = rawCartItems.split(",")
        return@retryInput splitCartItems.map { item ->
            val (product, quantityString) = item.trim('[', ']').split("-")
            val quantity = requireNotNull(quantityString.toIntOrNull()) {"[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."}
            require(product in products.map { it.name }) { "[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요." }
            require(quantity > 0) {"[ERROR] 상품은 1개 이상 구매해야 합니다. 다시 입력해 주세요."}
            ShoppingCart(product, quantity)
        }
    }

    private fun choiceMembershipDiscount() = retryInput {
        val choice = inputView.readMembershipDiscount()
        when (choice.uppercase()) {
            "Y" -> println() //TODO: 멤버십 할인 함수 넣기
            "N" -> println()
            else -> throw IllegalArgumentException("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.")
        }
    }

    private fun printReceipt(
//        purchaseInfo: List<ShoppingCart>,
//        promotionDiscount: Int,
//        membershipDiscount: Int,
    ) {
//        outputView.printPurchaseInfo(purchaseInfo)
//        outputView.printPromotionInfo()
//        outputView.printTotalInfo()
    }

    private fun choiceMorePurchase() = retryInput {
        val choice = inputView.readMorePurchase()
        when (choice.uppercase()) {
            "Y" -> start()
            "N" -> return@retryInput
            else -> throw IllegalArgumentException("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.")
        }
    }
}
