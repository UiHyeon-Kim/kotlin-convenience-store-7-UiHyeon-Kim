package store.controller

import camp.nextstep.edu.missionutils.DateTimes
import store.model.FileManager
import store.view.InputView
import store.view.OutputView

class ConvenienceController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
){
    private lateinit var fileManager: FileManager

    fun start() {
        val products = fileManager.readProductFile("src/main/resources/products.md")
        val promotion = fileManager.readPromotionFile("src/main/resources/promotions.md")
        println(DateTimes.now())

        outputView.welcomeMessage()
        outputView.printProductFormat(products)
        inputView.getPurchaseDetails()
        inputView.selectPromotion()
        inputView.selectMembership()

        inputView.selectAdditionalPurchases()

    }
}
