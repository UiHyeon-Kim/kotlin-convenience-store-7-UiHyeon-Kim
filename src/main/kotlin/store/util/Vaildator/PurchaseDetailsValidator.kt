package store.util.Vaildator

object PurchaseDetailsValidator {

    fun getValidatePurchaseDetails(rawPurchaseDetails: String): Map<String, Int> {
        while (true){
            try {
                val purchaseDetails = rawPurchaseDetails.split(",").map { it.trim('[', ']') }

                val productQuantities = mutableMapOf<String, Int>()

                purchaseDetails.forEach { item ->
                    val product = item.split("-")[0]
                    val quantities = item.split("-")[1].toInt()

                    productQuantities.put(product, quantities)
                }

                return productQuantities
            } catch (e: IllegalArgumentException) {
                println(e.message)
            } catch (e: NumberFormatException) {
                println(e.message)
            }
        }
    }
}
