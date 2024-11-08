package store.controller

import camp.nextstep.edu.missionutils.DateTimes
import store.model.FileManager
import store.model.Inventory
import store.util.Vaildator.PurchaseDetailsValidator.getValidatePurchaseDetails
import store.view.InputView
import store.view.OutputView

class ConvenienceController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
){
    private var fileManager: FileManager = FileManager()
    private lateinit var inventory: Inventory

    fun start() {
        val products = fileManager.readProductFile("src/main/resources/products.md")
        val promotion = fileManager.readPromotionFile("src/main/resources/promotions.md")
        // println(DateTimes.now())

        //
        outputView.welcomeMessage()
        outputView.printProductFormat(products)

        val rawPurchaseDetails = inputView.getPurchaseDetails()
        val validatedPurchaseList = getValidatePurchaseDetails(rawPurchaseDetails)
        Inventory(products)

        println(validatedPurchaseList)


        inputView.selectPromotion()
        inputView.selectMembership()

        inputView.selectAdditionalPurchases()

    }
}
