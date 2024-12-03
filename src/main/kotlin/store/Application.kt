package store

import store.service.Checkout
import store.service.Inventory
import store.utils.FileManager
import store.view.InputView
import store.view.OutputView

fun main() {
    val products = FileManager.loadProductsCsv("src/main/resources/products.md").toMutableList()
    val promotion = FileManager.loadPromotionsCsv("src/main/resources/promotions.md")
    val inputView = InputView()
    val outputView = OutputView()
    val inventory = Inventory(products)
    val checkout = Checkout(promotion)
    val convenienceStore = ConvenienceStore(products, promotion, inputView, outputView, inventory, checkout)
    convenienceStore.start()
}
