package store.util.constant

enum class Output(private val message: String) {
    WELCOME("안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다.\n"),

    RECEIPT_HEADER("===========W 편의점============="),
    RECEIPT_ITEM_LABEL("상품명\t\t수량\t\t\t금액"),
    RECEIPT_PURCHASED_ITEM("%s\t\t%d\t%d"),
    RECEIPT_PROMOTION_HEADER("===========증\t정============="),
    RECEIPT_PROMOTION_ITEM("%s\t\t%d"),
    RECEIPT_PROMOTION_FOOTER("=============================="),
    RECEIPT_TOTAL_AMOUNT("총구매액\t\t%d\t%d"),
    RECEIPT_EVENT_DISCOUNT("행사할인\t\t\t%d"),
    RECEIPT_MEMBERSHIP_DISCOUNT("멤버십할인\t\t\t%d"),
    RECEIPT_FINAL_AMOUNT("내실돈\t\t\t%d"),

    RE_INPUT("잘못 입력하셨습니다. 다시 입력해주세요."),

    GOOD_BYE("W편의점을 이용해주셔서 감사합니다.");

    fun getMessage(vararg any: Any): String {
        return String.format(message, *any)
    }

}
