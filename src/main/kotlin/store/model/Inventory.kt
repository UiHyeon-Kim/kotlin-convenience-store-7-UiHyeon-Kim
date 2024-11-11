import store.model.Product
import store.util.constant.Error
import store.util.constant.General.ZERO
import store.util.constant.Input

class Inventory(private val products: List<Product>) {

    fun check(purchaseItems: Map<String, Int>) {
        val totalQuantity = getQuantities().mapValues { (_, pair) -> pair.first + pair.second }

        purchaseItems.forEach { name, quantity ->
            totalQuantity[name] ?: throw IllegalArgumentException(Error.ITEM_NOT_FOUND.message)
            require(totalQuantity[name]!! >= quantity) { Error.INSUFFICIENT_STOCK.message }
        }
    }

    fun ComparePromotionQuantity(purchaseItems: Map<String, Int>) {
        val promotionQuantity = getQuantities().mapValues { it.value.first } // 구매한 상품의 프로모션 재고

        purchaseItems.forEach { name, quantity ->
            val availablePromotionQuantity = promotionQuantity[name] ?: ZERO

            if(availablePromotionQuantity < quantity) {
                val message = Input.NON_PROMOTION.getMessage(name, quantity - availablePromotionQuantity)
                println(message)
            }
        }
    }

    private fun getQuantities(): Map<String, Pair<Int, Int>> {
        return products
            .groupBy { it.name }
            .mapValues { (_, productDetails) ->
                val promotionQuantity =
                    productDetails.filter { it.promotion != null && it.promotion.isNotEmpty() }.sumOf { it.quantity }
                val regularQuantity =
                    productDetails.filter { it.promotion == null || it.promotion.isEmpty() }.sumOf { it.quantity }

                Pair(promotionQuantity, regularQuantity)
            }
    }

}
