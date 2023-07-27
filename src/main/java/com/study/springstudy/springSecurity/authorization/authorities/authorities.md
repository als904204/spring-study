# Authorities
권한

---

Authentication 섹션에서는 어떻게 모든 Authentication 구현체가
GrantedAuthority 객체 리스트를 저장하는지 설명한다.
GrantedAuthority 객체는 principal 에게 부여한 권한을 나타낸다.
AuthenticationManager 가 GrantedAuthority 객체를 Authentication 객체에 삽입하여, 이후 권한을 결정할 때 AccessDecisionManager  가 
GrantedAuthority 를 읽어간다.

GrantedAuthority 는 메소드가 하나 뿐인 인터페이스다.

```java
String getAurhority();
```

AccessDecisionManager 에선 메소드로 GrantedAuthority 를 명확한 String 으로 조회 할 수 있다.
GrantedAuthority 가 값을 String 으로 리턴하기 때문에 AccessDecisionManager 대부분이 이를 **쉽게 읽어** 갈 수 있다.
GrantedAuthority 를 명확하게 String 으로 표현할 수 없다면 **GrantedAuthority 는** 복잡한 케이스**로 간주하고, getAuthority() 에선 null 을 리턴**한다.

>복잡한 케이스란?
> GrantedAuthority 의 예시로는 고객 계정 번호에 따라 적용할 작업과 권한 임계치 리스트를 저장하는 일이 있다.
> 이 복잡한 GrantedAuthority 를 String 으로 표현하기 어렵기 때문에 getAuthority() 는 null 을 리턴할 것이다. null 을 
> 리턴했다는 것은 AccessDecisionManager 에 GrantedAuthor를 이해하기 위한 구체적인코드가 있어야 한다는 뜻이다.


스프링 시큐리티는 한 가지 GrantedAuthority 구현체, SimpleGrantedAuthority 를 제공한다.
이 클래스는 사용자가 지정한 String 을 GrantedAuthority 로 변환해 준다. 시큐리티 아키텍처에 속한 모든
AuthenticationProvider 는 Authentication 객체에 값을 채울 때
SimpleGrantedAuthority 를 사용한다

스프링 시큐리티에서는 기본적으로 역할(Role) 기반의 권한 부여 규칙을 사용한다
이 규칙은 권한 정보를 문자열 형태로 표현할 때 "ROLE_" 접두사를 포함시킨다
예를 들어, "USER"라는 역할을 가져야만 특정 자원에 접근할 수 있다고 가정하면,
실제로는 "ROLE_USER"라는 권한을 가지고 있어야만 해당 자원에 접근할 수 있다.

