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
⚙️ Configurações (application.properties)
spring.application.name=jwt-auth-demo

# Configuração do Banco de Dados H2
spring.datasource.url=jdbc:h2:mem:demo_db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.open-in-view=false

# Configurações do JWT
jwt.secret=404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970
jwt.expiration=86400000

⚠️ Respostas de Erro Personalizadas
1. Não Autorizado (HTTP 401)
Disparado ao tentar acessar um endpoint protegido sem enviar o header Authorization ou com um token inválido.
{
    "timestamp": "2026-09-21T18:00:00Z",
    "status": 401,
    "error": "Não Autorizado",
    "message": "Autenticação necessária para acessar este recurso.",
    "path": "/api/user/me"
}

2. Acesso Negado (HTTP 403)
Disparado quando o usuário está autenticado, mas não possui a Role necessária para o recurso.
{
    "timestamp": "2026-09-21T18:00:00Z",
    "status": 403,
    "error": "Acesso Negado",
    "message": "Você não tem permissão para acessar este recurso.",
    "path": "/api/admin/dashboard"
}

🧪 Como Testar
1. Registrar um Usuário
PowerShell:
Invoke-RestMethod -Uri "http://localhost:8080/api/auth/register" -Method Post -ContentType "application/json" -Body '{"username":"joao","password":"123","role":"USER"}'
CMD / cURL:
curl -X POST http://localhost:8080/api/auth/register -H "Content-Type: application/json" -d "{\"username\":\"joao\",\"password\":\"123\",\"role\":\"USER\"}"

2. Acessar Rota Protegida com o Token
PowerShell:
$token = "SEU_TOKEN_AQUI"
Invoke-RestMethod -Uri "http://localhost:8080/api/user/me" -Headers @{ Authorization = "Bearer $token" }
CMD / cURL:
curl -X GET http://localhost:8080/api/user/me -H "Authorization: Bearer SEU_TOKEN_AQUI"

3. Testar Acesso Negado (403)
Tente acessar a rota de Admin utilizando um token com perfil USER:
curl -X GET http://localhost:8080/api/admin/dashboard -H "Authorization: Bearer TOKEN_DO_USUARIO_USER"
