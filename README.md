# SpringWithAWS
- 출처 : 스프링 부트와 AWS로 혼자 구현하는 웹 서비스(이동욱 지음)
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

10. @MappedSuperclass
    : JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들
    (createdDate, modifiedDate)도 컬럼으로 인식하도록 합니다.

11. @EntityListeners(AuditingEntityListener.class)
    :BaseTimeEntity 클래스에 Auditing 기능을 포함시킵니다.

12. @CreatedDate
    : Entity가 생성되어 저장될 때 시간이 자동 저장됩니다.

13. @LastModifiedDate
    : 조회한 Entity의 값을 변경할 때 시간이 자동 저장됩니다.

14. @EnableJpaAuditing
    : JPA Auditing 활성화, 최상위 애플리케이션 클래스에 추가

15. @Enumerated(EnumType.STRING)
   : JPA로 데이터베이스로 저장할 때 Enum값을 어떤 형태로 저장할지를
   결정합니다. 기본적으로는 int로 된 숫자가 저장됩니다. 숫자로 저장되면
   데이터베이스로 확인할 때 그 값이 무슨 코드를 의미하는지 알 수가
   없습니다. 그래서 문자열 (EnumType.STRING)으로 저장될 수 있도록
   선언합니다.

16. @EnableWebSecurity
   : Spring Security 설정들을 활성화시켜 줍니다.
---
# Spring Web Layer
![2022-10-19(spring web layer)](https://user-images.githubusercontent.com/96904103/196657295-4d42733d-c022-43d1-8e72-0b3a6bcd5c89.png)


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

---
# Template Engine

1. 템플릿 엔진
   : 지정된 템플릿 양식과 데이터가 합쳐져 HTML 문서를 출력하는
   소프트웨어를 이야기합니다.
   JSP, Freemarker는 서버 템플릿 엔진,
   리액트, 뷰는 클라이언트 템플릿 엔진이라고 불린다.
   자바스크립트는 브라우저 위에서 작동합니다.
   서버 템플릿 엔진은 서버에서 구동됩니다.

2. 필자(이동욱)가 생각하는 템플릿 엔진들의 단점은 다음과 같습니다.
    - Thymeleaf : 스프링 진영에서 적극적으로 밀고 있지만 문법이
      어렵습니다. HTML 태그에 속성으로 템플릿 기능을 사용하는 방식이
      기존 개발자분들께 높은 허들로 느껴지는 경우가 많습니다. 실제로
      사용해 본 분들은 자바스크립트 프레임워크를 배우는 기분이라고
      후기를 이야기하기도 합니다.

3. mustache(템플릿 엔진) 사용의 장점
   : 문법이 다른 템플릿 엔진보다 심플합니다.
   로직코드를 사용할 수 없어 View의 역할과 서버의 역할이 명확히
   분리됩니다.
   Mustache.js와 Mustache.java 2가지가 다 있어, 하나의 문법으로
   클라이언트/서버 템플릿을 모두 사용합니다.

4. mustache의 폴더 기본 위치
   : src/main/resources/templates <- 이 위치에 머스테치 파일을 두면
   스프링 부트에서 자동으로 로딩합니다.

5. 페이지 로딩속도
   : 속도를 높이기 위하여 css는 header에, js는 footer 두는게 좋다.

6. 브라우저의 스코프
   : 브라우저의 스코프는 공용 공간으로 쓰이기 때문에 나중에 로딩된
   js의 init, save가 먼저 로딩된 js의 function을 덮어쓰게 됩니다.
   여러 사람이 참여하는 프로젝트에서는 중복된 함수이름은 자주 발생
   할 수 있습니다. 모든 function 이름을 확인하면서 만들 수는 없습니다.
   이 문제를 피하려고 index.js만의 유효범위를 만들어서 사용합니다.
   이렇겧면 index 객체 안에서만 function이 유효하기 때문에 다른
   js와 겹칠 위험이 사라집니다.

7. 바닐라 자바스크립트
   : 플러그인이나, 라이브러리를 사용하지 않은 순수 자바스크립트를
   이야기합니다. 외국에서는 바닐라(Vanilla) = 일반(Plain)을 의미함

8. static폴더
   : 스프링부트는 기본적으로 src/main/resources/static에 위치한
   자바스크립트, CSS, 이미지 등 정적 파일들은 URL에서 /로 설정됩니다.

9. Basic Information
   : 자바스크립트는 브라우저에 내장되어있다!
   Browser는 HTML을 불러오고 HTML은 CSS와 JS를 불러온다.
   HTML이 접착제 느낌.

---
# Social Login

1. 소셜 로그인 기능
   : 많은 서비스에서 로그인 기능을 id/password 방식보다는 구글,
   페이스북, 네이버 로그인과 같은 소셜 로그인 기능을 사용한다.
   이는 로그인을 직접 구현할 경우 배보다 배꼽이 커지는 경우가
   많기 때문이다(필자 생각)

2. application-oauth.properties
   :이 파일에서 scope는 기본 값으로 openid, profile, email이다.
   강제로 profile, email을 등록한 이유는 openid라는 scope가 있으면
   Open Id Provider로 인식하기 때문입니다. 이렇게 되면 OpenId Provider
   인 서비스(구글)와 그렇지 않은 서비스(네비서/카카오 등)로 나눠서
   각각 OAuth2Service를 만들어야합니다. 하나의 OAuth2Service로
   사용하기 위해 일부러 openid scope를 빼고 등록합니다.

3. OAuth2
   :OAuth(Open Authentication, Open Authorization)는 사용자 리소스를
   관리하는 서비스(구글, 페이스북 등)에서 제 3의 애플리케이션에게
   사용자의 패스워드를 제공 없이 인증, 인가할 수 있는 인증 관련
   표준 프로토콜이다. OAuth 이전에 사용자의 권한을 위임받는 방식은
   사용자가 이용하는 서비스의 계정/패스워드를 제공받는 방식이었다.
   이는 패스워드 유출 뿐 아니라 권한을 위임받는 애플리케이션이 필요
   이상으로 계정에 대한 모든 권한을 획득하게 되는 등 다양한 문제점이
   존재한다. OAuth인증은 API를 제공하는 서버에서 사용자 인증 및 권한
   부여를 진행하고 이에 대한 'Access Token'을 발급하는 방식을 제공하며
   이러한 문제들을 해결할 수 있다.
   OAuth2는 OAuth1 프로토콜의 복잡한 인증방식을 단순화했다.
   관련 URL : https://mydevstorage.tistory.com/2

4. OAuth2 dependency
   : spring-boot-starter-oauth2-client는 소셜 로그인 등 클라이언트
   입장에서 소셜 기능 구현 시 필요한 의존성입니다.
   spring-boot-starter-oauth2-client와 spring-security-oauth2-jose를
   기본으로 관리해줍니다.


5. @EnableWebSecurity
   : Spring Security 설정들을 활성화시켜 줍니다.

6. csrf().disable().headers().frameOptions().disable()
   :h2-console 화면을 사용하기 위해 해당 옵션들을 disable합니다.

7. authorizeRequests
   :URL별 권한 관리를 설정하는 옵션의 시작점입니다. authorizeRequests가
   선언되어야만 antMatchers옵션을 사용할 수 있습니다.

8. antMatchers
   :권한 관리 대상을 지정하는 옵션입니다. URL, HTTP 메소드별로
   관리가 가능합니다. "/"등 지정된 URL들은 permitAll()옵션을 통해
   전체 열람 권한을 주었습니다. "/api/v1/**"주소를 가진 API는
   USER권한을 가진 사람만 가능하도록 했습니다.

9. anyRequest
   :설정된 값들 이외 나머지 URL들을 나타냅니다. 여기서는 authenticated()을
   추가하여 나머지 URL들은 모두 인증된 사용자들에게만 허용하게 합니다.
   인증된 사용자 즉, 로그인한 사용자들을 이야기합니다.

10. logout().logoutSuccessUrl("/")
    :로그아웃 기능에 대한 여러 설정의 진입점입니다. 로그아웃 성공 시 /
    주소로 이동합니다.

11. oauth2Login
    :OAuth2 로그인 기능에 대한 여러 설정의 진입점입니다.

12. userInfoEndpoint
    :OAuth2 로그인 성공 이후 사용자 정보를 가져올 때 설정들을 담당

13. userService
    :소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의
    구현체를 등록합니다. 리소스 서버(즉, 소셜 서비스들)에서 사용자
    정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있습니다.

- CustomOAuth2UserService 클래스 설명  
// delegate : 대리자, 대표, 대리하다, 파견하다.

1. registrationId
   :현재 로그인 진행 중인 서비스를 구분하는 코드입니다. 지금은 구글만
   사용하는 불필요한 값이지만, 이후 네이버 로그인 연동 시에 네이버
   로그인인지, 구글 로그인인지 구분하기 위해 사용합니다.

2. userNameAttributeName
   :OAuth2 로그인 진행 시 키가 되는 필드값을 이야기합니다. PK와 같은
   의미입니다. 구글의 경우 기본적으로 코드를 지원하지만, 네이버 카카오
   등은 기본 지원하지 않습니다. 구글의 기본 코드는 "sub"입니다.
   이후 네이버 로그인과 구글 로그인을 동시에 지원할 때 사용합니다.

3. OAuthAttributes
   :OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을
   클래스입니다. 이후 네이버 등 다른 소셜 로그인도 이 클래스를 사용합니다.

4. SessionUser
   :세션에서 사용자 정보를 저장하기 위한 Dto 클래스입니다.

- OAuthAttributes 클래스 설명

1. of()
   :OAuth2User에서 반환하는 사용자 정보는 Map이기 때문에 값 하나하나를
   변환해야만 합니다.

2. toEntity()
   :User 엔티티를 생성합니다. OAuthAttributes에서 엔티티를 생성하는
   시점은 처음 가입할 때 입니다. 가입할 때의 기본 권한을 GUEST로 주기
   위해서 role 빌더값에는 Role.GUEST를 사용합니다. OAuthAttributes 클래스
   생성이 끝났으면 같은 패키지에 SessionUser 클래스를 생성합니다. 
