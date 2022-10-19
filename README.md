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
![2022-10-19(spring web layer)](https://user-images.githubusercontent.com/96904103/196657295-4d42733d-c022-43d1-8e72-0b3a6bcd5c89.png)
