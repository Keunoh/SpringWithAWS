# SpringWithAWS

---

1. TDD와 단위테스트는 다르다.
   TDD는 테스트 먼저 작성 -> 테스트 통과한 프로덕션 코드 작성
   -> 프로덕션 코드 리팩토링
   단위테스트는 TDD의 첫 번째 단계인 기능 단위의 테스트코드를 작성

2. @SpringBootApplication
   : 프로젝트의 메인 클래스로써 스프링 부트의 자동설정, 스프링 Bean
   읽기와 생성을 모두 자동으로 설정, @SpringBootApplication이 있는
   위치부터 설정을 읽어 가기 때문이 이 클래스는 항상 프로젝트의
   최상단에 위치하여야 한다.

3. @RunWith(SpringRunner.class)
   : 테스트를 진행할 때 JUnit에 내장된 실행자 이외 다른 실행자를
   실행시킨다. 여기서는 SpringRunner라는 스프링 실행자를 사용한다.
   즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할

4. @WebMvcTest
   :여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는
   어노테이션이다. 선언할 경우 @Controller, @ControllerAdvice등 사용이
   가능하다. 단, @Service, @Component, @Repository 등은 사용할 수 없다.

5. @RequiredArgsConstructor
       : 선언된 모든 final 필드가 포함된 생성자를 생성해 준다.
       final이 없는 필드는 생성자에 포함되지 않는다.

6. @Entity
   : 테이블과 링크될 클래스임을 나타냅니다. 기본값으로 클래스의 카멜
   케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭합니다.
   ex) SalesManager.java -> sales_manager table

7. @GeneratedValue
   : PK의 생성 규칙을 나타냅니다. 스프링 부트 2.0에서는 GenerationType.
   IDENTITY 옵션을 추가해야만 auto_increment가 됩니다

8. @Column
   : 테이블의 컬럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의
   필드는 모두 컬럼이 됩니다. 사용하는 이유는, 기본값 외에 추가로
   변경이 필요한 옵션이 있으면 사용합니다.
   ex) 문자열의 경우 VARCHAR(255)가 기본 값인데, 사이즈를 500으로
   늘리고 싶다던가, 타이븡ㄹ TEXT로 변경하고 싶다던가 등등.

9. @Transcational
   :해당 애노테이션이 붙은 메서드는 메서드가 포함하고 있는 작업 중에
   하나라도 실패할 경우 전체 작업을 취소한다.
   관련 URL : https://tecoble.techcourse.co.kr/post/2021-05-25-transactional/       
---
# Spring Web Layer
![2022-10-19(spring web layer)](https://user-images.githubusercontent.com/96904103/196657295-4d42733d-c022-43d1-8e72-0b3a6bcd5c89.png)
### 출처 : 스프링 부트와 AWS로 혼자 구현하는 웹 서비스(이동욱 지음)

###Service Layer 오해###
	: 많은 분들이 오해하고 계신 것이, Service에서 비지니스 로직을
	처리해야 한다는 것입니다. 하지만 전혀 그렇지 않습니다. Service는
	트랜잭션, 도메인 간 순서 보장의 역할만 합니다.

1. Web Layer
   - 흔히 사용하는 컨트롤러(@Controller)와 JSP/Freemarker 등의 뷰 템플릿 영역입니다.
   - 이외에도 필터(@Filter), 인터셉터, 컨트롤러 어드바이스(@ControllerAdvice) 등 외부
     요청과 응답에 대한 전반적인 영역을 이야기합니다.
2. Service Layer 
   - @Service에 사용되는 서비스 영역입니다. 
   - 일반적으로 Cotroller와 Dao의 중간 영역에서 사용됩니다. 
   - @Transcational이 사용되어야 하는 영역이기도 합니다.
3. Repository Layer
   - Database와 같이 데이터 저장소에 접근하는 영역입니다. 
   - Dao(Data Access Object)영역으로 이해하시면 쉬울 것입니다.
4. Dtos
   - Dto(Data Transfer Object)는 계층 간에 데이터 교환을 위한 객체를 이야기하며 Dtos는 이들의 영역을 이야기합니다. 
   - 예를 들어 뷰 템플릿 엔진에서 사용될 객체나 Repository Layer에서 결과로 넘겨준 객체 등이 이들을 이야기합니다.
5. Domain Model
   - 도메인이라 불리는 개발 대상을 모든 사람이 동일한 관점에서 이해할 수 있고 공유할 수 있도록 단순화시킨 것을 도메인 모델이라고 합니다. 
   - 이를테면 택시 앱이라고 하면 배차, 탑승, 요금 등이 모두 도메인이 될 수 있습니다. @Entity가 사용된 영역 역시 도메인 모델이라고 이해하면 됩니다.
   - 다만, 무조건 데이터베이스의 테이블과 관계가 있어야만 하는 것은아닙니다. 
   - VO처럼 값 객체들도 이 영역에 해당하기 때문입니다.

---
# Persistence Context

1. DB 쿼리문이 없어도 테이블이 업데이팅 됨(@Service구간에서)
   @Service 애노테이션이 붙어있기 때문에 발생하는 것은 아님
     : JPA의 영속성 컨텍스트 때문입니다. 영속성 컨텍스트란, 엔티티를
     영구 저장하는 환경입니다. 일종의 논리적 개념이라고 보시면 되며,
     JPA의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐
     로 갈립니다.
     JPA의 엔티티 매니저(Entity Manager)가 활성화된 상태로
     (Spring Data Jpa를 쓴다면 기본 옵션) 트랜잭션 안에서 데이터베이스에서
     데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태입니다.
     이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에
     해당 테이블에 변경분을 반영합니다. 즉, Entity 객체의 값만 변경하면
     별도로 Update 쿼리를 날릴 필요가 없다는 것이죠. 이 개념을
     더티 체킹(Dirty Checking)이라고 합니다.

2. 더티 체킹(Dirty Checking)
    관련 URL : https://jojoldu.tistory.com/415
