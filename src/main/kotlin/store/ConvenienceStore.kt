package store

class ConvenienceStore(
    private val products: List<Products>,
    private val promotion: List<Promotion>,
    private val inputView: InputView,
    private val outputView: OutputView,
    private val inventory: Inventory,
    private val checkout: Checkout,
) {
    fun start() {
        outputView.printWelcomeMessage()
        outputView.printProducts(products)
        val productsAndQuantities = validateProductAndQuantity()

        readMembershipDiscount()
        printReceipt()
        readMorePurchase()
    }

    private fun validateProductAndQuantity(): List<Pair<String, Int>> = retryInput {
        val rawProductAndQuantity = inputView.readProductAndQuantity()
        val splitProductsAndQuantities = rawProductAndQuantity.split(",")
        val productsAndQuantities = validateFormat(splitProductsAndQuantities)
        productsAndQuantities
    }

    private fun validateFormat(splitProductsAndQuantities: List<String>): List<Pair<String, Int>> {
        val productsAndQuantities = splitProductsAndQuantities.map { item ->
            val product = item.trim('[', ']').split("-")[0]
            val quantities = item.trim('[', ']').split("-")[1]
            require(product in products.map { it.name }) { "[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요." }
            product to requireNotNull(quantities.toIntOrNull()) { "[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요." }
        }
        return productsAndQuantities
    }

    private fun readMembershipDiscount() = retryInput {
        val choice = inputView.readMembershipDiscount()
        when (choice.uppercase()) {
            "Y" -> println() //TODO: 멤버십 할인 함수 넣기
            "N" -> println()
            else -> throw IllegalArgumentException("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.")
        }
    }

    private fun printReceipt(
//        purchaseInfo: List<PurchaseInfo>,
    ) {
        outputView.printReceiptHeader()
//        outputView.printPurchaseInfo(purchaseInfo)
        outputView.printPromotionHeader()
        outputView.printPromotionInfo()
        outputView.printTotalHeader()
//        outputView.printTotalInfo()
    }

    private fun readMorePurchase() = retryInput {
        val choice = inputView.readMorePurchase()
        when (choice.uppercase()) {
            "Y" -> start()
            "N" -> return@retryInput
            else -> throw IllegalArgumentException("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.")
        }
    }
}
