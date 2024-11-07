package store

class ConvenienceController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
){
    private val fileManager: FileManager = FileManager()

    fun run() {
        outputView.welcomeMessage()
        val products = fileManager.readProductFile("src/main/resources/products.md")
        val promotion = fileManager.readPromotionFile("src/main/resources/promotions.md")

        println("- ${products.joinToString(", ") }}")
    }
}
