package store

import java.nio.file.Files

class OutputView {

    fun welcomeMessage() = println("안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다.\n")

    fun printProductFormat(products: List<Product>) {
        products.forEach { product ->
            println("- ${product.name} ${product.price}원 ${product.quantity}개 ${product.promotion ?: ""}")
        }
    }


}
