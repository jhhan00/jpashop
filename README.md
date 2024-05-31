# Spring Boot & JPA 공부하기

### Spring Boot에 대해서 알고 JPA에 대해서 알아가기

1. 시작
    * 진행할 프로젝트 구조에 대해 알아가기
    * domain 설계 및 제작
2. 진행
    * 개발 순서
      1. 회원 도메인 개발
      2. 상품 도메인 개발
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

