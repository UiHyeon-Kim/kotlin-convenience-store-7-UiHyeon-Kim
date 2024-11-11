package store.controller

import Inventory
import store.model.Checkout
import store.model.FileManager
import store.model.Product
import store.model.Promotion
import store.util.constant.General.NEW_LINE
import store.util.constant.General.NO
import store.util.constant.General.PRODUCT_PATH
import store.util.constant.General.PROMOTION_PATH
import store.util.constant.General.YES
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
        val products = fileManager.readProductFile(PRODUCT_PATH)
        val promotions = fileManager.readPromotionFile(PROMOTION_PATH)

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

             //if (selectMembership()) checkout.membershipDiscount()

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
                YES -> return true
                NO -> return false
                else -> println(Output.RE_INPUT.getMessage())
            }
        }
    }

    // TODO: 출력할 매개변수 필요..?
    private fun printReceipt() {
        outputView.printReceiptHeader()
//        outputView.printReceiptBodyPurchasedItems(name, quantity, price)
        outputView.printReceiptBody()
//        outputView.printReceiptBodyPromotionItems(name, quantity)
//        outputView.printReceiptFooter(totalQuantity, totalPrice, promoDiscount, membershipDiscount, totalPay)
    }

    private fun selectMorePurchase(): Boolean {
        while (true) {
            val choice = inputView.selectMorePurchases()
            when (choice.trim().uppercase()) {
                YES -> { println(NEW_LINE); return true }
                NO -> { println(Output.GOOD_BYE.getMessage()); return false }
                else -> println(Output.RE_INPUT.getMessage())
            }
        }
    }

}
