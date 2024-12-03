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
        if (isSelect("멤버십 할인을 받으시겠습니까? (Y/N)")) checkout.membershipDiscount(cartItems)
        printReceipt(cartItems, 300, 300)
        choiceMorePurchase()
    }

    private fun parseAndValidateCartItems(): List<ShoppingCart> = retryInput {
        val rawCartItems = inputView.readProductAndQuantity()
        return@retryInput rawCartItems.split(",").map { item ->
            val (productName, quantityString) = item.trim('[', ']').split("-")
            val product = requireNotNull(inventory.getProduct(productName)) { "[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요." }
            val quantity = requireNotNull(quantityString.toIntOrNull()) {"[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."}
            require(quantity > 0) {"[ERROR] 상품은 1개 이상 구매해야 합니다. 다시 입력해 주세요."}
            //TODO: 재고 부족할 시 에러 처리
            products.filter { it.name == productName }.sumOf { it.quantity } >= quantity
            //TODO: 일반재고만 없는 아이템 채우기
            ShoppingCart(
                name = product.name,
                quantity = quantity,
                price = product.price,
                promotionStock = product.quantity,
            )
        }
    }

    /*fun choiceMembershipDiscount(cartItems: List<ShoppingCart>) = retryInput {
        val choice = inputView.readMembershipDiscount()
        when (choice.uppercase()) {
            "Y" -> checkout.membershipDiscount(cartItems)
            "N" -> println()
            else -> throw IllegalArgumentException("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.")
        }
    }*/

    private fun isSelect(message: String) = retryInput {
        val choice = inputView.readSelect(message)
        when (choice.uppercase()) {
            "Y" -> return@retryInput true
            "N" -> return@retryInput false
            else -> throw IllegalArgumentException("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.")
        }
    }

    private fun printReceipt(
        cartItems: List<ShoppingCart>,
        promotionDiscount: Int,
        membershipDiscount: Int,
    ) {
        outputView.printPurchaseInfo(cartItems)
        outputView.printPromotionInfo("오렌지 주스", 20)
        outputView.printTotalInfo(10,100,1000,10000,100000)
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
