package store

import java.nio.file.Files
import java.nio.file.Paths

fun readProductsCsv(filePath: String): List<Products> {
    val lines = Files.readAllLines(Paths.get(filePath))
    return lines.drop(1).map { line ->
        val values = line.split(",")
        Products(
            name = values[0],
            price = values[1].toInt(),
            quantity = values[2].toInt(),
            promotion = if (values[3] == "null") "" else values[3]
        )
    }
}

//fun readPromotionCsv(filePath: String): List<Promotion> {
//    val lines = Files.readAllLines(Paths.get(filePath))
//    return lines.drop(1).map { line ->
//        val values = line.split(",")
//        Promotion(
//            name = values[0],
//            buy = values[1].toInt(),
//            get = values[2].toInt(),
//            start_date = values[3],
//            end_date = values[4]
//        )
//    }
//}

