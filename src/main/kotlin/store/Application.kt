package store

fun main() {
    val products = FileManager.loadProductsCsv("src/main/resources/products.md").toMutableList()
    val promotion = FileManager.loadPromotionCsv("src/main/resources/promotions.md")
    val inputView = InputView()
    val outputView = OutputView()
    val inventory = Inventory()
    val checkout = Checkout()
    val convenienceStore = ConvenienceStore(products, promotion, inputView, outputView, inventory, checkout)
    convenienceStore.start()
}
