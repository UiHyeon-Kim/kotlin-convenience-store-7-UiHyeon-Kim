package store

class ConvenienceStore(
    val products: List<Products>,
    val inputView: InputView,
    val outputView: OutputView,
) {
    fun start() {
        outputView.printWelcomeMessage()
        outputView.printProducts(products)
        val rawProductAndQuantity = inputView.readProductAndQuantity()
        val productsAndQuantities = validateProductAndQuantity(rawProductAndQuantity)
        productsAndQuantities.forEach { pair ->
            println("${pair.first} ${pair.second}")
        }

    }

    // TODO: 예외처리 넣기
    fun validateProductAndQuantity(rawProductAndQuantity: String): List<Pair<String, Int>> {
        val splitProductsAndQuantities = rawProductAndQuantity.split(",")
        val productsAndQuantities = splitProductsAndQuantities.map { item ->
            val product = item.trim('[', ']').split("-")[0]
            val quantities = item.trim('[', ']').split("-")[1]
            product to quantities.toInt()
        }
        return productsAndQuantities
    }

}
