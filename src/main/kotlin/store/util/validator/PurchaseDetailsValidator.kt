package store.util.validator

import store.model.Product
import store.util.constant.Error
import store.util.constant.General.END_SYMBOL
import store.util.constant.General.PRODUCT_DELIMITER
import store.util.constant.General.PRODUCT_FORMAT
import store.util.constant.General.START_SYMBOL
import store.util.constant.General.STRING_DELIMITER
import store.util.constant.General.ZERO

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

    private fun detacheValue(rawPurchaseDetails: String) = rawPurchaseDetails.split(STRING_DELIMITER)

    private fun trimValues(detachedValues: List<String>): List<String> = detachedValues.map { it.trim(START_SYMBOL, END_SYMBOL) }

    private fun validateDetachedValue(detachedValue: List<String>) {
        detachedValue.forEach { value ->
            require(value.startsWith(START_SYMBOL) && value.endsWith(END_SYMBOL)) {Error.INVAILD_FORMAT.message}
        }
    }

    private fun validateTrimmedValue(trimmedValues: List<String>) {
        val productFormat = PRODUCT_FORMAT.toRegex()
        trimmedValues.forEach { value ->
            require(productFormat.matches(value)) { Error.INVAILD_FORMAT.message }
        }
    }

    // FIXME: List<Product>로 변경해야 함
    fun validateProductQuantities(productQuantities: Map<String, Int>, product: Product) {
        productQuantities.forEach { item, quantities ->
            require(item in product.name) { Error.INVAILD_FORMAT.message }
            require(quantities > ZERO) { Error.INVALID_INPUT.message }
        }
    }

    private fun initProductQuantities(purchaseDetails: List<String>, productQuantities: MutableMap<String, Int>) {
        purchaseDetails.forEach { item ->
            val product = item.split(PRODUCT_DELIMITER)[0]
            val quantities = item.split(PRODUCT_DELIMITER)[1].toInt()

            productQuantities.put(product, quantities)
        }
    }
}
