package store

class ConvenienceStore(
    val products: List<Products>,
    val inputView: InputView,
    val outputView: OutputView,
) {
    fun start() {
        outputView.printWelcomeMessage()
        val rawProductAndQuantity = inputView.readProductAndQuantity()

    }
}
