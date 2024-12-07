package store.utils

import store.domain.Product
import store.domain.Promotion
import java.io.File
import java.time.LocalDate

object FileManager {
    fun loadProductsCsv(filePath: String): List<Product> {
        val products = File(filePath).readLines().drop(1).map { line ->
            val parts = line.split(",")
            Product(
                name = parts[0],
                price = parts[1].toInt(),
                quantity = parts[2].toInt(),
                promotion = if (parts[3] == "null") "" else parts[3]
            )
        }.toMutableList()
        return getAllProducts(products)
    }

    private fun getAllProducts(products: MutableList<Product>): List<Product> {
        val nonPromotionProduct = products
            .filter { product -> product.promotion != null && products.count { it.name == product.name } == 1 }
        nonPromotionProduct.forEach { products.add(it.copy(quantity = 0, promotion = null)) }
        return products.groupBy { it.name }.flatMap { (_, products) -> products.sortedByDescending { it.promotion } }
    }

    fun loadPromotionsCsv(filePath: String): List<Promotion> {
        return File(filePath).readLines().drop(1).map { line ->
            val parts = line.split(",")
            Promotion(
                name = parts[0],
                buy = parts[1].toInt(),
                get = parts[2].toInt(),
                startDate = LocalDate.parse(parts[3]),
                endDate = LocalDate.parse(parts[4])
            )
        }
    }
}



