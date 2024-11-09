package store

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.Validator
import store.util.validator.PurchaseDetailsValidator

class PurchaseDetailsValidatorTest {

    private lateinit var validator: PurchaseDetailsValidator

    @BeforeEach
    fun setup() {
        validator = PurchaseDetailsValidator
    }

    @Test
    fun `구매 상품과 개수가 정상적일 경우`() {
        val input = "[콜라-3],[사이다-2],[감자칩-5]"
        val result = validator.getParseAndValidatePurchaseDetails(input)

        assertThat(result)
            .containsEntry("콜라", 3)
            .containsEntry("사이다", 2)
            .containsEntry("감자칩", 5)
    }

    @ParameterizedTest
    @CsvSource("[콜라_3]", "{콜라-3}", "콜라-3", "[coke-3]", "[콜라-three]", "[콜라-3].[사이다-3]")
    fun `다양한 입력 예외에 대한 테스트`(input: String) {
        assertThrows<IllegalArgumentException> {
            validator.getParseAndValidatePurchaseDetails(input)
        }
    }
}
