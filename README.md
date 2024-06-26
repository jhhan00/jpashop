# Spring Boot & JPA 공부하기

### Spring Boot에 대해서 알고 JPA에 대해서 알아가기

1. 시작
    * 진행할 프로젝트 구조에 대해 알아가기
    * domain 설계 및 제작
2. 진행
    * 개발 순서
      1. 회원 도메인 개발
      2. 상품 도메인 개발
      3. 주문 도메인 개발 (중요)
3. 마무리
    * 다음은?


### 알아둬야할 점
1. 엔티티 개발 시
   1. setter를 가능한 쓰지 말 것
      * 실무에 가서는 setter를 가능한 쓰지 않는 것이 유지보수에 좋음
   2. 연관관계는 지연로딩으로 할 것
      * @XXXToOne 어노테이션은 즉시로딩이 Default이기 떄문에 지연로딩으로 설정해야 함
   3. 컬렉션은 필드에서 초기화 하기
      * NULL 문제 해결 & Hibernate 맞춰서 진행 가능
   4. 테이블 & 컬럼명 생성
      * Default : SpringPhysicalNamingStrategy 사용

2. 준영속 엔티티
   1. 영속성 컨텍스트가 더는 관리하지 않는 엔티티
   2. 준영속 엔티티를 수정하는 2가지 방법
      1. 변경 감지 기능 사용
      2. merge(병합) 사용
   3. merge 사용 시 주의사항
      * 모든 데이터를 변경하기 때문에, 값을 설정해주지 않으면 null로 변경될 수 있음
   4. 결과
      1. 가능하면 변경 감지를 사용해서 업데이트 하기
      2. merge는 최후의 수단으로 사용할 것