# JWT Auth Demo - API REST com Spring Security e Spring Data JPA

API REST desenvolvida com **Spring Boot 3** para demonstrar a implementação completa de autenticação *stateless* via **Tokens JWT (JSON Web Tokens)** em substituição ao *Basic Auth*.

O projeto conta com persistência em banco de dados utilizando **Spring Data JPA**, controle de acessos por papéis (*Roles*), e **tratamento customizado para exceções de Acesso Negado (403) e Não Autorizado (401)**.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.4**
- **Spring Security** (Autenticação Stateless)
- **Spring Data JPA** (Persistência de Dados)
- **H2 Database** (Banco de dados em memória)
- **JJWT 0.12.5** (Criação e validação de tokens JWT)
- **Lombok** (Produtividade e redução de código boilerplate)
- **Maven** (Gerenciamento de dependências)

---

## 📂 Estrutura do Projeto

```text
jwt-auth-demo/
├── pom.xml
├── README.md
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── example/
        │           └── jwtauthdemo/
        │               ├── config/
        │               │   ├── ApplicationConfig.java      # Beans de segurança e autenticação
        │               │   └── SecurityConfig.java         # Filtros HTTP e rotas liberadas/protegidas
        │               ├── controller/
        │               │   ├── AuthController.java         # Endpoints públicos de Login e Registro
        │               │   └── DemoController.java         # Endpoints protegidos para validação
        │               ├── dto/
        │               │   └── Dtos.java                   # Objetos de transferência de dados (DTOs)
        │               ├── entity/
        │               │   ├── Role.java                   # Enum de papéis (USER, ADMIN)
        │               │   └── User.java                   # Entidade JPA implementando UserDetails
        │               ├── exception/
        │               │   ├── CustomAccessDeniedHandler.java        # Tratamento customizado (403)
        │               │   └── CustomAuthenticationEntryPoint.java  # Tratamento customizado (401)
        │               ├── repository/
        │               │   └── UserRepository.java         # Repositório Spring Data JPA
        │               ├── security/
        │               │   └── JwtAuthenticationFilter.java # Filtro interceptador do Bearer Token
        │               ├── service/
        │               │   ├── AuthenticationService.java  # Regras de negócio de cadastro/login
        │               │   └── JwtService.java             # Geração e parsing de tokens JWT
        │               └── JwtAuthDemoApplication.java
        └── resources/
            └── application.properties                      # Configurações de BD e JWT
