package store

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path

class FileManager {

    fun readFile(path: String): String {
        val filePath = Path.of(path)
        return Files.readString(filePath, StandardCharsets.UTF_8)

//        val classLoader = Thread.currentThread().contextClassLoader
//        val resource = classLoader.getResource(fileName)
//        return resource?.readText()
    }

    fun writeFile() {}
}