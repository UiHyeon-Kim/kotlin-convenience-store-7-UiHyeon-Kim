import store.model.Product
import store.util.constant.Error

class Inventory(private val products: List<Product>) {

    // TODO: 재고 관리 -> 재고 파악, 결제 가능 여부 확인, 구매 후 재고 차감
    // products = 전체 재고
    // purchaseItems = 사용자가 구매한 상품과 개수

//    입력한 각 상품의 개수가 재고보다 큰지.(프로모션 재고와, 일반 재고 합쳐서)
//    *    크다면 [ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.
//    *    아니라면 구매 가능

//        구매 아이템의 갯수가 재고보다 많은지 적은지
//        근데 프로모션 적용 상품이면 프로모션 개수 포함 됐는지
//        포함 됐다면 그것의 총개수가 재고의


    // 재고 파악
    fun check(purchaseItems: Map<String, Int>) {
        val totalQuantity = getTotalQuantity(products)

        purchaseItems.forEach { name, quantity ->
            totalQuantity[name] ?: throw IllegalArgumentException(Error.ITEM_NOT_FOUND.message)
            require(totalQuantity[name]!! >= quantity) { Error.INSUFFICIENT_STOCK.message }
        }
    }

    private fun getTotalQuantity(products: List<Product>): Map<String, Int> {
        return products
            .groupBy { it.name }
            .mapValues { (_, productDetails) ->
                productDetails.sumOf { it.quantity }
        }
    }
}
