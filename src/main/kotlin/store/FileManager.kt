package store

import java.nio.file.Files
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.util.*

class FileManager {
    fun readProductFile(filePath: String): List<Product> {
        val lines = Files.readAllLines(Paths.get(filePath))

        return lines.drop(1).map { line ->
            val values = line.split(",")
            Product(
                name = values[0],
                price = values[1].toInt(),
                quantity = values[2].toInt(),
                promotion = if (values[3].isNotEmpty()) values[3] else null
            )
        }
    }

    fun readPromotionFile(filePath: String): List<Promotion> {
        val lines = Files.readAllLines(Paths.get(filePath))
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")

        return lines.drop(1).map { line ->
            val values = line.split(",")
            Promotion(
                name = values[0],
                buy = values[1].toInt(),
                get = values[2].toInt(),
                start_date = dateFormat.parse(values[3]),
                end_date = dateFormat.parse(values[4])
            )
        }
    }
}

