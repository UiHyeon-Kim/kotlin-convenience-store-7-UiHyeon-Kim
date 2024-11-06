package store

import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class FileReader {
    fun readFile(fileName: String): String {
        val resource = object {}.javaClass.getResource("/$fileName") ?: FileNotFoundException("파일을 찾을 수 없습니다")

        return Files.readString(Paths.get(resource.toString()))
    }
}