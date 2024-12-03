package store.utils

import store.model.Products
import store.model.Promotion
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDate

object FileManager {
    fun loadProductsCsv(filePath: String): List<Products> {
        return File(filePath).readLines().drop(1).map { line ->
            val parts = line.split(",")
            Products(
                name = parts[0],
                price = parts[1].toInt(),
                quantity = parts[2].toInt(),
                promotion = if (parts[3] == "null") "" else parts[3]
            )
        }
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



