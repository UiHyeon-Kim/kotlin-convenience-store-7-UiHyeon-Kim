package store.model

class Inventory(private val products: List<Product>) {

    // TODO: 재고 관리 -> 재고 파악, 결제 가능 여부 확인, 구매 후 재고 차감

    // 재고 파악
    fun check(purchaseItems: Map<String, Int>) {
        /*
        구매 아이템의 갯수가 재고보다 많은지 적은지
        근데 프로모션 적용 상품이면 프로모션 개수 포함 됐는지
        포함 됐다면 그것의 총개수가 재고의
        */
    }

    //



}
