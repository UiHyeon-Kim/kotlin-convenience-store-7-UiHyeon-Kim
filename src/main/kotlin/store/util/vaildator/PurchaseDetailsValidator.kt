package store.util.vaildator

import store.util.constant.Error

object PurchaseDetailsValidator {

    fun getParseAndValidatePurchaseDetails(rawPurchaseDetails: String): Map<String, Int> {
        val detachedValues = detacheValue(rawPurchaseDetails)
        validateDetachedValue(detachedValues)

        val trimmedValues = trimValues(detachedValues)
        validateTrimmedValue(trimmedValues)

        val productQuantities = mutableMapOf<String, Int>()
        initProductQuantities(trimmedValues, productQuantities)

        return productQuantities
    }

    private fun detacheValue(rawPurchaseDetails: String) = rawPurchaseDetails.split(",")

    private fun trimValues(detachedValues: List<String>): List<String> = detachedValues.map { it.trim('[', ']') }

    private fun validateDetachedValue(detachedValue: List<String>) {
        detachedValue.forEach { value ->
            require(value.startsWith("[") && value.endsWith("]")) {Error.INVAILD_FORMAT.message}
        }
    }

    private fun validateTrimmedValue(trimmedValues: List<String>) {
        val regex = "[가-힣]+-\\d+".toRegex()
        trimmedValues.forEach { value ->
            require(regex.matches(value)) { Error.INVAILD_FORMAT.message }
        }
    }

    private fun initProductQuantities(
        purchaseDetails: List<String>,
        productQuantities: MutableMap<String, Int>
    ) {
        purchaseDetails.forEach { item ->
            val product = item.split("-")[0]
            val quantities = item.split("-")[1].toInt()

            productQuantities.put(product, quantities)
        }
    }
}
