package store.controller

import Inventory
import store.model.FileManager
import store.util.validator.PurchaseDetailsValidator.getParseAndValidatePurchaseDetails
import store.view.InputView
import store.view.OutputView

/**
 * 재고는 프로모션 재고와 일반 재고로 나누어짐.
 *
 *  구매할 상품과 갯수 입력.
 *   - 형식이 올바르지 않다면 [ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.
 *   - 상품이 존재하지 않는다면 [ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.
 *   - 기타 잘못 입력된 경우 [ERROR] 잘못된 입력입니다. 다시 입력해 주세요.
 *
 *  입력한 각 상품의 개수가 재고보다 큰지.(프로모션 재고와, 일반 재고 합쳐서)
 *    크다면 [ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.
 *    아니라면 구매 가능
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

    fun start() {
        val products = fileManager.readProductFile("src/main/resources/products.md")
        val promotion = fileManager.readPromotionFile("src/main/resources/promotions.md")
        // println(DateTimes.now())

        outputView.welcomeMessage()
        outputView.printProductFormat(products)

        val rawPurchaseDetails = inputView.getPurchaseDetails()
        val purchaseProductQuantities = getParseAndValidatePurchaseDetails(rawPurchaseDetails)

        val inventory = Inventory(products)
        inventory.check(purchaseProductQuantities)

        inventory.ComparePromotionQuantity(purchaseProductQuantities)


        // TODO: 프로모션 재고보다 많이 구매하면
        //inputView.selectGeneralPrice()

        // TODO: 프로모션 재고가 남아있는데, 구매 개수가 안 맞을 경우
        //inputView.selectAddPromotion()


        inputView.selectMembership()
        inputView.selectAdditionalPurchases()

    }

//    fun settingConvenience
}
