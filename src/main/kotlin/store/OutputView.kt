package store

class OutputView {
    fun printWelcomeMessage() {
        println("안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다.\n")
    }

    fun printProducts(products: List<Products>) {
        products.forEach { product ->
            if (product.quantity < 0) {
                println("- ${product.name} ${product.price}원 재고 없음 ${product.promotion}")
            } else {
                println("- ${product.name} ${product.price}원 ${product.quantity}개 ${product.promotion}")
            }
        }
    }
}
