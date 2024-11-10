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

private const val s = "W편의점을 이용해주셔서 감사합니다."

/**
 * 재고는 프로모션 재고와 일반 재고로 나누어짐.
 *
 *  프로모션 상품 재고가 남아있다면.
 *    프로모션 재고보다 적게 산다면
 *      프로모션 재고 판매. 영수증에 증정 추가
 *        프로모션 상품보다 적게 가져온 경우. 추가로 받을지 안내.
 *          추가로 받으면 영수증에 증정 추가
 *          아니라면 일반 재고와 똑같이 판매(아니면 일반 재고 판매?)
 *        근데 딱 맞으면 굳이 안내할 필요 없음.
 *    프로모션 재고보다 많이 산다면
 *      프로모션 재고 먼저 판매.
 *        남은 개수는 정가로 구매할지 여부 안내
 *          구매한다면 일반 재고 판매
 *          구매하지 않는다면 돌려 놓기
 *  프로모션 상품 재고가 없다면
 *      일반 재고 판매
 *
 *  멤버십 할인 여부
 *    멤버십 회원 - 할인 받는다면 프로모션 미적용 금액(즉, 전체 금액)의 30% 할인
 *    일반 회원 - 할인 받는다면 프로모션 적용후 금액에 대해 할인(아마 30%)
 *    최대 한도 8000원
 *    어떻게 구분하지?
 *
 *  영수증 출력
 */

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

//        inputView.selectMembership()
//        inputView.selectAdditionalPurchases()

    }

    private fun repeatedVisits(products: List<Product>, promotions: List<Promotion>) {
         while (true){
             initialFormatMessage(products)
             val inventory = Inventory(products)

             val purchaseProductQuantities = validateOfPurchase(inventory)
             inventory.ComparePromotionQuantity(purchaseProductQuantities)

             // TODO: 프로모션 재고가 남아있는데, 구매 개수가 안 맞을 경우
             //inputView.selectAddPromotion()
             val checkout = Checkout()
             checkout.applyPromotion(products, promotions, purchaseProductQuantities)

             // TODO: 멤버십 구현
             // checkout.membershipDiscount() // 프로모션 추가한 최종 구매를 인수로 넣기

             printReceipt()
             if (selectMorePurchase()) return
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
                "N" -> { println(Output.GOOD_BYE.getMessage()); return true }
                "Y" -> { println("\n"); return false }
                else -> println(Output.RE_INPUT.getMessage())
            }
        }
    }


}
