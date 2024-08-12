# CH.1 과제 - MSA, Redis

> **🚩 Goal:  MSA 구성, Redis 캐싱, Docker 기반 CI/CD 구성하기**
<br>

## 📑 프로젝트 요구사항

<details>
<summary>의존성 및 라이브러리 설치에 주의</summary>

```
각 서비스의 역할에 따라 필요한 의존성이 다릅니다. (Eureka Server, Gateway, Product 등)
이 부분을 유념해서 의존성 추가에 주의해주세요 🙂
```
</details>
<details>
<summary>패키지명 규칙과 포트 규칙 준수</summary>

```
- 패키지명은 `com.sparta.msa_exam` 으로 설정하고 유레카 서버는 `19090` 포트로 실행되도록 설정해주세요.
- 게이트웨이 서비스는 `com.sparta.msa_exam.gateway` 패키지로 추가하고 `19091` 포트로 실행되도록 설정해주세요.
- 상품 서비스를 `com.sparta.msa_exam.product` 패키지로 추가하고 `19093,19094` 포트로 실행되도록 설정해주세요.
- 주문 서비스를 `com.sparta.msa_exam.order` 패키지로 추가하고 `19092` 포트로 실행하도록 설정해주세요.
- 인증 서비스를 `com.sparta.msa_exam.auth` 패키지로 추가하고 `19095` 포트로 실행하도록 설정해주세요.
```
</details>
<details>
<summary>모든 API의 Response Header에 Server-Port Key로 현재 실행중인 서버의 포트를 추가해주세요.</summary>
</details>
<br>

## 📆 프로젝트 주요 기능
<details>
<summary>기본 API 구성하기</summary>

```
1. POST /products  상품 추가 API
2. GET /products 상품 목록 조회 API
3. POST /order 주문 추가 API
4. PUT /order/{orderId}  주문에 상품을 추가하는 API
5. GET /order/{orderId}  주문 단건 조회 API
6. GET /auth/signIn?user_id={string}  로그인 API 
```
</details>
<details>
<summary>상품 서비스는 라운드로빈 형식으로 로드밸런싱 구성하기</summary>

```
1. 라운드로빈 형식으로 로드밸런싱을 구현해서 상품 서비스가 호출될 때마다 두 서비스를 반복하며 호출되게 구성해 보세요.
2. 상품 목록을 조회할 때마다 API의 응답 헤더의 `Server-Port` 값이 `19093` , `19094` 로 변경되어야 합니다.
```
</details>
<details>
<summary>주문에 상품을 추가하는 API 만들 때 아래와 같이 구성해보세요.</summary>

```
1. `FeignClient`를 이용해서 주문 서비스에 상품 서비스 클라이언트 연결
2. [상품 목록 조회 API](https://www.notion.so/Chapter-1-8049fc370de54e2f84e07c4b5fb68705?pvs=21)를 호출해서 파라미터로 받은 `product_id` 가 상품 목록에 존재하는지 검증
3. 존재할경우 주문에 상품을 추가하고, 존재하지 않는다면 주문에 저장하지 않음.
```
</details>
<details>
<summary>분산추적 구현해보기</summary>

```
- 주문 서비스 와 상품 서비스 에 Zipkin 을 연동하고, 호출시 Zipkin 대시보드에 Duration 이 측정 되는지 확인해보세요.
```
</details>
<details>
<summary>캐싱 기능 구현하기</summary>

```
- 주문 조회 API 의 결과를 캐싱 처리하여 60초 동안은 DB 에서 불러오는 데이터가 아닌 메모리에 캐싱된 데이터가 보여지도록 설정해주세요.
```
</details>
<details>
<summary>외부 요청 보호</summary>

```
- Oauth2,JWT 기반으로 인증/인가를 구성하여 인가 없이 상품 서비스, 주문 서비스를 호출할 때 401 HTTP Status Code를 응답하도록 설정해주세요.
```
</details>
<details>
<summary>캐시를 더 활용 해볼까요?</summary>

```
1. 상품 추가 API를 호출 할 경우 상품 목록 조회 API의 응답 데이터 캐시가 갱신되도록 구현해주세요.
(~~MSA~~ **인메모리 저장소 및 캐싱 강의** 중 **Spring Boot 프로젝트에 캐싱 적용하기** 를 참고해서 구현해주세요.)
2. 상품 추가 후 상품 목록 조회 API 호출 시 데이터가 변경 되는지 확인합니다.
```
</details>
<details>
<summary>로컬과 서버의 환경을 분리해 보세요!</summary>

```
- 로컬에서는 localhost:3306 으로 DB에 접근하고, 서버에 배포시엔 RDS 주소로 접근하게 구성하도록 환경을 분리해봅시다! - 환경은 dev, prod 프로필로 나누어 주세요.) 
```
</details>
<br>

## ☁️ 프로젝트 구성도
![MAS_Exam](https://github.com/user-attachments/assets/40d22f64-0a46-4cd1-bcc8-a286dc55f9fe)
