# kotlin-convenience-store-precourse
## 편의점
구매자의 할인 혜택과 재고 상황을 고려하여 최종 결제 금액을 계산하고 안내하는 결제 시스템
<details>
<summary>실행 결과 보기</summary>
<div markdown="1">

```
안녕하세요. W편의점입니다.
현재 보유하고 있는 상품입니다.

- 콜라 1,000원 10개 탄산2+1
- 콜라 1,000원 10개
- 사이다 1,000원 8개 탄산2+1
- 사이다 1,000원 7개
- 오렌지주스 1,800원 9개 MD추천상품
- 오렌지주스 1,800원 재고 없음
- 탄산수 1,200원 5개 탄산2+1
- 탄산수 1,200원 재고 없음
- 물 500원 10개
- 비타민워터 1,500원 6개
- 감자칩 1,500원 5개 반짝할인
- 감자칩 1,500원 5개
- 초코바 1,200원 5개 MD추천상품
- 초코바 1,200원 5개
- 에너지바 2,000원 5개
- 정식도시락 6,400원 8개
- 컵라면 1,700원 1개 MD추천상품
- 컵라면 1,700원 10개

구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
[콜라-3],[에너지바-5]

멤버십 할인을 받으시겠습니까? (Y/N)
Y

===========W 편의점=============
상품명		수량	금액
콜라		3 	3,000
에너지바 		5 	10,000
===========증	정=============
콜라		1
==============================
총구매액		8	13,000
행사할인			-1,000
멤버십할인			-3,000
내실돈			 9,000

감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
Y

안녕하세요. W편의점입니다.
현재 보유하고 있는 상품입니다.

- 콜라 1,000원 7개 탄산2+1
- 콜라 1,000원 10개
- 사이다 1,000원 8개 탄산2+1
- 사이다 1,000원 7개
- 오렌지주스 1,800원 9개 MD추천상품
- 오렌지주스 1,800원 재고 없음
- 탄산수 1,200원 5개 탄산2+1
- 탄산수 1,200원 재고 없음
- 물 500원 10개
- 비타민워터 1,500원 6개
- 감자칩 1,500원 5개 반짝할인
- 감자칩 1,500원 5개
- 초코바 1,200원 5개 MD추천상품
- 초코바 1,200원 5개
- 에너지바 2,000원 재고 없음
- 정식도시락 6,400원 8개
- 컵라면 1,700원 1개 MD추천상품
- 컵라면 1,700원 10개

구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
[콜라-10]

현재 콜라 4개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)
Y

멤버십 할인을 받으시겠습니까? (Y/N)
N

===========W 편의점=============
상품명		수량	금액
콜라		10 	10,000
===========증	정=============
콜라		2
==============================
총구매액		10	10,000
행사할인			-2,000
멤버십할인			-0
내실돈			 8,000

감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
Y

안녕하세요. W편의점입니다.
현재 보유하고 있는 상품입니다.

- 콜라 1,000원 재고 없음 탄산2+1
- 콜라 1,000원 7개
- 사이다 1,000원 8개 탄산2+1
- 사이다 1,000원 7개
- 오렌지주스 1,800원 9개 MD추천상품
- 오렌지주스 1,800원 재고 없음
- 탄산수 1,200원 5개 탄산2+1
- 탄산수 1,200원 재고 없음
- 물 500원 10개
- 비타민워터 1,500원 6개
- 감자칩 1,500원 5개 반짝할인
- 감자칩 1,500원 5개
- 초코바 1,200원 5개 MD추천상품
- 초코바 1,200원 5개
- 에너지바 2,000원 재고 없음
- 정식도시락 6,400원 8개
- 컵라면 1,700원 1개 MD추천상품
- 컵라면 1,700원 10개

구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
[오렌지주스-1]

현재 오렌지주스은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)
Y

멤버십 할인을 받으시겠습니까? (Y/N)
Y

===========W 편의점=============
상품명		수량	금액
오렌지주스		2 	3,600
===========증	정=============
오렌지주스		1
==============================
총구매액		2	3,600
행사할인			-1,800
멤버십할인			-0
내실돈			 1,800

감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
N
```
</div>
</details>

## 기능 목록
### 입력 및 출력
- [ ] 환영 인사
- [ ] 재고 출력
  - [ ] src/main/resources/products.md
  - [ ] src/main/resources/promotions.md
- [ ] 구매 할 상품과 수량 입력
  - [ ] `[상품명-수량], ...`
- [ ] 프로모션
  - [ ] 프로모션 수량만큼 안가져온 경우, 수량만큼 가져오면 혜택 안내
  - [ ] 프로모션 제품이 부족한 경우, 일부 수량 정가로 결제 됨을 안내
- [ ] 멤버십 할인 적용 유무
- [ ] 영수증 출력
  - [ ] 구매 내역 출력
    - [ ] 구매 상품명, 수량, 가격
  - [ ] 증정 상품 내역
    - [ ] 프로모션에 따라
  - [ ] 산출 금액 정보 출력
    - [ ] 총구매액: 구매한 상품의 총 수량과 총 금액
    - [ ] 행사할인: 프로모션에 의해 할인된 금액
    - [ ] 멤버십 할인: 멤버십에 의해 추가로 할인된 금액
    - [ ] 내실 돈: 최종 결제 금액
- [ ] 추가 구매 여부 입력
### 프로세스
- [ ] 금액 계산
  - [ ] 총 구매액 = 상품별 가격 * 수량
  - [ ] 최종 결제 금액 = (총 구매액 ? 프로모션 가격) 멤버십 할인 30%
- [ ] 재고 관리
  - [ ] 구매 전 재고 파악
  - [ ] 결제 가능 여부 확인
  - [ ] 구매 후 재고 차감
- [ ] 프로모션
  - [ ] 오늘 날짜가 프로모션 기간인지
- [ ] 멤버십 할인
  - [ ] 멤버십 회원은 프로모션 적용 전 금액의 30% 할인
  - [ ] 프로모션 적용 후 남은 금액 할인(아마 똑같이 30%)
  - [ ] 최대 한도 8,000원
### 예외
- [ ] 입력값이 잘못된 경우 `IllegalArgumentException`
  - [ ] 구매할 상품과 수량 형식이 올바르지 않은 경우:`[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.`
  - [ ] 존재하지 않는 상품을 입력한 경우:`[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.`
  - [ ] 구매 수량이 재고 수량을 초과한 경우:`[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.`
  - [ ] 기타 잘못된 입력의 경우:`[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.`
## 파일 구조




