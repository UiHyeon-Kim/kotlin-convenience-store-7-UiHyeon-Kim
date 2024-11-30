package store

fun main() {
    val products = readProductsCsv("src/main/resources/products.md")
    val inputView = InputView()
    val outputView = OutputView()
    val convenienceStore = ConvenienceStore(products, inputView, outputView)
    convenienceStore.start()
}
