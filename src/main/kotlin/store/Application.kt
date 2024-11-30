package store

import store.service.Checkout
import store.service.Inventory
import store.utils.FileManager
import store.view.InputView
import store.view.OutputView

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
