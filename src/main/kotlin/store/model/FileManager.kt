package store.model

import store.util.constant.General.DATE_FORMAT
import store.util.constant.General.EMPTY
import store.util.constant.General.NULL_TEXT
import store.util.constant.General.STRING_DELIMITER
import java.nio.file.Files
import java.nio.file.Paths
import java.text.SimpleDateFormat

class FileManager {
    fun readProductFile(filePath: String): List<Product> {
        val lines = Files.readAllLines(Paths.get(filePath))

        return lines.drop(1).map { line ->
            val values = line.split(STRING_DELIMITER)
            Product(
                name = values[0],
                price = values[1].toInt(),
                quantity = values[2].toInt(),
                promotion = if (values[3] != NULL_TEXT) values[3] else EMPTY
            )
        }
    }

    fun readPromotionFile(filePath: String): List<Promotion> {
        val lines = Files.readAllLines(Paths.get(filePath))
        val dateFormat = SimpleDateFormat(DATE_FORMAT)

        return lines.drop(1).map { line ->
            val values = line.split(STRING_DELIMITER)
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

