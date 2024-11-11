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
             val checkout = Checkout()
             val purchaseProductQuantities = validateOfPurchase(inventory)
             inventory.ComparePromotionQuantity(purchaseProductQuantities)
             // TODO: 프로모션 개수 비교
             // checkout.applyPromotion(products, promotions, purchaseProductQuantities)
             val applyDiscount = if (selectMembership()) {
                 checkout.membershipDiscount(products, purchaseProductQuantities)
             } else {
                 0
             }
             // TODO: 영수증
             outputView.printReceiptHeader()

             purchaseProductQuantities.forEach { (name, quantity) ->
                val price = products.find { it.name == name }?.price ?: 0
                outputView.printReceiptBodyPurchasedItems(name, quantity, price)
             }

             outputView.printReceiptBody()
//             outputView.printReceiptBodyPromotionItems(name, quantity)

             var totalQuantity = 0
             var totalPrice = 0
             purchaseProductQuantities.forEach { (name, quantity) ->
                 val price = products.find { it.name == name }?.price ?: 0
                 totalQuantity += quantity
                 totalPrice += price
             }
             val totalPay = totalPrice - applyDiscount
             outputView.printReceiptFooter(totalQuantity, totalPrice, 0, applyDiscount, totalPay)
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
