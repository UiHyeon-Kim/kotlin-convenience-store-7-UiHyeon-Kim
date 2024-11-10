package store.controller

import Inventory
import store.model.Checkout
import store.model.FileManager
import store.model.Product
import store.model.Promotion
import store.util.constant.Output
import store.util.validator.PurchaseDetailsValidator.getParseAndValidatePurchaseDetails
import store.view.InputView
import store.view.OutputView

class ConvenienceController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
){
    private var fileManager: FileManager = FileManager()
    private lateinit var inventory: Inventory
    private lateinit var checkout: Checkout

    fun start() {
        val products = fileManager.readProductFile("src/main/resources/products.md")
        val promotions = fileManager.readPromotionFile("src/main/resources/promotions.md")

        repeatedVisits(products, promotions)
    }

    private fun repeatedVisits(products: List<Product>, promotions: List<Promotion>) {
         while (true){
             initialFormatMessage(products)
             val inventory = Inventory(products)

             val purchaseProductQuantities = validateOfPurchase(inventory)
             inventory.ComparePromotionQuantity(purchaseProductQuantities)

             val checkout = Checkout()
             checkout.applyPromotion(products, promotions, purchaseProductQuantities)

             if (selectMembership()) checkout.membershipDiscount()

             printReceipt()
             if (!selectMorePurchase()) return
         }
    }

    private fun initialFormatMessage(products: List<Product>) {
        outputView.welcomeMessage()
        outputView.printProductFormat(products)
    }

    private fun validateOfPurchase(inventory: Inventory): Map<String, Int> {
        while (true) {
            try {
                val rawPurchaseDetails = inputView.getPurchaseDetails()
                val purchaseProductQuantities = getParseAndValidatePurchaseDetails(rawPurchaseDetails)
                inventory.check(purchaseProductQuantities)
                return purchaseProductQuantities
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    private fun selectMembership(): Boolean {
        while (true) {
            val choice = inputView.selectMembership()
            when (choice.trim().uppercase()) {
                "N" -> return false
                "Y" -> return true
                else -> println(Output.RE_INPUT.getMessage())
            }
        }
    }

    // TODO: 출력할 매개변수 필요..?
    private fun printReceipt() {
        outputView.printReceiptHeader()
//        outputView.printReceiptBodyPurchasedItems()
        outputView.printReceiptBody()
//        outputView.printReceiptBodyPromotionItems()
//        outputView.printReceiptFooter()
    }

    private fun selectMorePurchase(): Boolean {
        while (true) {
            val choice = inputView.selectMorePurchases()
            when (choice.trim().uppercase()) {
                "N" -> { println(Output.GOOD_BYE.getMessage()); return false }
                "Y" -> { println("\n"); return true }
                else -> println(Output.RE_INPUT.getMessage())
            }
        }
    }

}
