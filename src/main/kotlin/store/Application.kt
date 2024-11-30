package store

fun main() {
    val products = FileManager.loadProductsCsv("src/main/resources/products.md")
    val promotion = FileManager.loadPromotionCsv("src/main/resources/promotions.md")
    val inputView = InputView()
    val outputView = OutputView()
    val convenienceStore = ConvenienceStore(products, promotion, inputView, outputView)
    convenienceStore.start()
}
