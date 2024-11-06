package store

import java.io.FileReader

class Convenience(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
){
    private val fileManager: FileManager = FileManager()

    fun run() {
        outputView.welcomeMessage()
        val products = fileManager.readFile("src/main/resources/products.md")
        println(products)
    }
}