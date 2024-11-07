package store

class ConvenienceController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
){
    private val fileManager: FileManager = FileManager()

    fun run() {
        val products = fileManager.readProductFile("src/main/resources/products.md")
        val promotion = fileManager.readPromotionFile("src/main/resources/promotions.md")

        outputView.welcomeMessage()
        outputView.printProductFormat(products)
        inputView.purchaseMessage()
        inputView.promotionMessage()
        inputView.membershipMessage()

        inputView.additionalPurchasesMessage()

    }
}
