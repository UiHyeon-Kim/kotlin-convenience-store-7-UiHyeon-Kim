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

    }
}
