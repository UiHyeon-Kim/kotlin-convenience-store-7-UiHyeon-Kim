package store

import Inventory
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import store.model.Product

class InventoryTest {

    private lateinit var inventory: Inventory

    @BeforeEach
    fun setUp() {
        val products = listOf(
            Product("콜라", 1000, 10, "탄산2+1"),
            Product("콜라", 1000, 10, null),
            Product("사이다", 1000, 8, "탄산2+1"),
            Product("사이다", 1000, 7, null),
            Product("오렌지주스", 1800, 9, "MD추천상품"),
            Product("탄산수", 1200, 5, "탄산2+1"),
            Product("물", 500, 10, null)
        )
        inventory = Inventory(products)
    }

    @Test
    fun `구매하려는 상품 재고가 충분한 경우 테스트`() {
        val purchaseItems = mapOf("콜라" to 5, "사이다" to 7)

        assertThatCode { inventory.check(purchaseItems) }
            .doesNotThrowAnyException()
    }

    @Test
    fun `구매하려는 상품이 재고보다 많은 경우 예외 테스트`() {
        val purchaseItems = mapOf("콜라" to 20, "사이다" to 20)

        assertThrows<IllegalArgumentException> {
            inventory.check(purchaseItems)
        }
    }

    @Test
    fun `존재하지 않는 상품을 구매하려고 할 때 예외 테스트`() {
        val purchaseItems = mapOf("없는상품" to 3)

        assertThrows<IllegalArgumentException> {
            inventory.check(purchaseItems)
        }
    }

}
