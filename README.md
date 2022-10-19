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
       
       
---
# Spring Web Layer
![2022-10-19(spring web layer)](https://user-images.githubusercontent.com/96904103/196657295-4d42733d-c022-43d1-8e72-0b3a6bcd5c89.png)

1. Web Layer
   - 흔히 사용하는 컨트롤러(@Controller)와 JSP/Freemarker 등의 뷰 템플릿 영역입니다.
   - 이외에도 필터(@Filter), 인터셉터, 컨트롤러 어드바이스(@ControllerAdvice) 등 외부    
     요청과 응답에 대한 전반적인 영역을 이야기합니다.

2. Service Layer 
   - @Service에 사용되는 서비스 영역입니다. 일반적으로 Cotroller와 Dao의 중간 영역에서   
   사용됩니다. @Transcational이 사용되어야 하는 영역이기도 합니다.

3. Repository Layer
   - Database와 같이 데이터 저장소에 접근하는 영역입니다. Dao(Data Access Object)영역   
   으로 이해하시면 쉬울 것입니다.
