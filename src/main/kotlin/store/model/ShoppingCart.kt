package store.model

data class ShoppingCart(
    val name: String,
    val quantity: Int,
    val price: Int,
    val promotionStock: Int? = null,
)
