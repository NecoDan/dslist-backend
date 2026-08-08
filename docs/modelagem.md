# Documentação da Modelagem

## Visão Geral da Aplicação

Este é um projeto **Spring Boot multifuncional** que implementa soluções para desafios de desenvolvimento backend. O repositório contém três módulos principais: um sistema de catálogo de jogos (DSList), uma solução para o desafio Itaú (transações), e uma solução para o desafio PicPay (pagamentos).

### Stack Tecnológico
- **Linguagem:** Java 21
- **Framework:** Spring Boot 3.5.16 + Spring MVC
- **Persistência:** Spring Data JPA, Spring Data Redis
- **Banco de Dados:** PostgreSQL, H2 (em memória)
- **Bibliotecas Notáveis:** MapStruct, Lombok, Spring Cloud OpenFeign, Spring Retry, Spring AOP
- **Testes:** JUnit, Mockito, JavaFaker
- **Observabilidade:** Spring Boot Actuator, SpringDoc OpenAPI (Swagger)

---

## Modelo de Dados Completo

### **Módulo 1: DSList (Catálogo de Jogos)**

#### Entidades Principais

**Tabela: `dslistapp.tb_game`** (Jogos)
```
┌─ id (bigint, PK, auto-increment)
├─ title (varchar 255)
├─ score (float)
├─ game_year (int)
├─ genre (varchar 255)
├─ platforms (varchar 255)
├─ img_url (varchar 255)
├─ short_description (text)
├─ long_description (text)
└─ created_at (timestamp)
```

**Tabela: `dslistapp.tb_game_list`** (Listas de Jogos)
```
┌─ id (bigint, PK, auto-increment)
├─ name (varchar 255)
└─ created_at (timestamp)
```

**Tabela: `dslistapp.tb_belonging`** (Associação Game ↔ GameList)
```
┌─ game_id (bigint, FK → tb_game, PK composta)
├─ list_id (bigint, FK → tb_game_list, PK composta)
└─ position (int) [ordem do jogo na lista]
```

**Constraints:**
- `FK: tb_belonging.list_id → tb_game_list.id (FK2slybclee7wdfxhfltbvqkgpg)`
- `FK: tb_belonging.game_id → tb_game.id (FKrchwdikeu66uky1hf75ym1kh)`

**Dados Pré-carregados:**
- 2 listas: "Aventura e RPG", "Jogos de plataforma"
- 10 jogos incluindo: Mass Effect Trilogy, Red Dead Redemption 2, The Witcher 3, Super Mario World, Hollow Knight, Cuphead, etc.

---

### **Módulo 2: Desafio Itaú v1 (Transações)**

#### Entidades Principais

**Tabela: `dslistapp.tb_users`** (Usuários DSList)
```
┌─ id (bigint, PK, auto-increment)
├─ nome (varchar 255)
├─ email (varchar 255)
├─ password (varchar 255)
├─ salario (numeric 38,2)
└─ created_at (timestamp)
```

**Dados Pré-carregados:**
- 20+ usuários com diferentes salários (de R$ 1.348,74 a R$ 10.688,93)

**API de Transações:**
- **POST** `/transaction` - Criar transação
  ```json
  {
    "value": 100.0,
    "payer": 1,
    "payee": 2
  }
  ```
- **GET** `/transaction` - Listar todas as transações
  ```json
  {
    "id": 20,
    "value": 100.0,
    "payer": 1,
    "payee": 2,
    "createdAt": "2024-03-05T16:07:50.749774"
  }
  ```

---

### **Módulo 3: Desafio PicPay (Sistema de Pagamentos)**

#### Entidades Principais

**Tabela: `picpay.user`** (Usuários PicPay)
```
┌─ id (bigint, PK, auto-increment)
├─ first_name (varchar 255)
├─ last_name (varchar 255)
├─ document (varchar 255, UNIQUE)
├─ email (varchar 255, UNIQUE)
├─ password (varchar 255)
├─ user_type (varchar 255) [COMMON | MERCHANT]
│  └─ Constraint: CHECK (user_type IN ('COMMON','MERCHANT'))
├─ balance (numeric 38,2)
└─ created_at (timestamp)
```

**Tabela: `picpay.transaction`** (Transações PicPay)
```
┌─ id (bigint, PK, auto-increment)
├─ sender_id (bigint, FK → picpay.user)
├─ receiver_id (bigint, FK → picpay.user)
├─ amount (numeric 38,2)
└─ created_at (timestamp)
```

**Constraints:**
- `FK: transaction.receiver_id → user.id (FKey21a233t8tlwfsbs228q3b2u)`
- `FK: transaction.sender_id → user.id (FKjpter5yuohdb58gyg6k5nympt)`
- **Restrição de tipo:** Apenas usuários COMMON podem receber pagamentos; MERCHANT pode enviar
- **Validação externa:** Integração com serviço de autorização via OpenFeign

---

## Arquitetura da Aplicação

### Estrutura de Diretórios

```
src/main/java/com/devsuperior/dslist/
├── Application.java                 # Ponto de entrada Spring Boot
├── adapter/                         # Adaptadores (ex: OpenFeign clients)
├── config/                          # Configurações (Redis, banco de dados)
├── exceptions/                      # Exceções customizadas
├── game_collections/                # Módulo DSList
│   ├── dto/                        # Data Transfer Objects
│   ├── entity/                     # Entidades JPA (Game, GameList, Belonging)
│   ├── repository/                 # Interfaces repositório (Spring Data JPA)
│   ├── service/                    # Lógica de negócio
│   └── controller/                 # Endpoints REST
├── itau_v1_challenge/              # Módulo Desafio Itaú
│   ├── entity/                     # Entidades (User, Transaction)
│   ├── repository/                 # Repositórios
│   ├── service/                    # Serviços
│   └── controller/                 # Controllers
├── picpay_challenge/               # Módulo Desafio PicPay
│   ├── dto/                        # DTOs (request/response)
│   ├── entity/                     # Entidades (User, Transaction)
│   ├── repository/                 # Repositórios
│   ├── service/                    # Serviços com lógica transacional
│   ├── controller/                 # Controllers REST
│   └── validation/                 # Validadores customizados
├── users_jpa/                      # Módulo de Usuários (JPA)
│   ├── entity/                     # Entidades de usuário
│   ├── repository/                 # Repositórios JPA
│   └── service/                    # Serviços
├── taxes/                          # Módulo de Cálculo de Impostos
│   ├── service/                    # Lógica de cálculo
│   └── entity/                     # Modelos de imposto
└── utils/                          # Utilitários e helpers
```

---

## Fluxo de Dados Principal

### Módulo DSList (Catálogo de Jogos)
```
Request HTTP (GET /games)
    ↓
GameController
    ↓
GameService (lógica de negócio)
    ↓
GameRepository (Spring Data JPA)
    ↓
PostgreSQL/H2
    ↓
GameDTO (mapeado com MapStruct)
    ↓
Response JSON
```

### Módulo PicPay (Pagamentos)
```
Request HTTP (POST /transactions)
    ↓
TransactionController
    ↓
TransactionService
    ├── Valida saldo do pagador
    ├── Valida tipo de usuário (COMMON/MERCHANT)
    ├── Integra com serviço de autorização (OpenFeign)
    └── Executa transferência em transação ACID
    ↓
TransactionRepository
    ↓
PostgreSQL
    ↓
Redis Cache (para consultas frequentes)
    ↓
Response JSON
```

---

## Padrões e Tecnologias Utilizadas

| Padrão | Implementação | Propósito |
|--------|--------------|----------|
| **DTO Pattern** | GameDTO, UserDTO, TransactionDTO | Desacoplamento entre camadas |
| **Repository Pattern** | Spring Data JPA | Abstração de acesso a dados |
| **Service Layer** | *Service classes | Lógica de negócio centralizada |
| **Dependency Injection** | @Autowired, constructor injection | Injeção de dependências |
| **OpenFeign** | FeignClient adapters | Chamadas HTTP a serviços externos |
| **Retry Policy** | @Retry, spring-retry | Resilência em chamadas de rede |
| **AOP** | @Transactional, aspectos | Tratamento transacional e cross-cutting |
| **Validation** | @Valid, jakarta.validation | Validação de entrada |
| **Caching** | Redis, Spring Cache | Otimização de performance |
| **OpenAPI/Swagger** | springdoc-openapi | Documentação automática de API |

---

## Configurações Importantes

**application.properties (Spring Boot):**
- Suporte a perfis: `dev`, `test`, `prod`
- Datasource: PostgreSQL (producção) + H2 (testes)
- Redis: Configurado para cache de listagens
- JPA: Hibernate com auto-update de schema
- Logging: SLF4J com nível configurável

**Docker Compose:**
- PostgreSQL 15+
- Redis 7+
- Rede compartilhada entre serviços

**Java Version:** Java 21 (LTS)

---

## Endpoints Principais

### DSList (Catálogo)
- `GET /games` - Listar todos os jogos
- `GET /games/{id}` - Obter jogo por ID
- `GET /lists` - Listar todas as listas
- `GET /lists/{id}/games` - Listar jogos de uma lista
- `POST /games` - Criar novo jogo
- `PUT /games/{id}` - Atualizar jogo

### Itaú v1 Challenge
- `POST /transaction` - Criar transação
- `GET /transaction` - Listar transações

### PicPay Challenge
- `POST /users` - Criar usuário
- `POST /transactions` - Transferência de valores
- `GET /users/{id}` - Obter dados do usuário
- `GET /transactions` - Histórico de transações

---

## Documentação Adicional

**Swagger/OpenAPI:**
- Acesso em: `http://localhost:8080/swagger-ui.html`
- Spec JSON: `http://localhost:8080/v3/api-docs`

**Postman Collection:**
- Arquivo: `DSList.postman_collection.json~` (legacy backup)

**Arquivos de Inicialização:**
- `create.sql` - Schema e dados pré-carregados para todos os módulos
- `system.properties` - Configurações de JVM para Heroku

---

Esta documentação cobre completamente o modelo de dados, arquitetura, padrões de design e fluxos de integração do projeto dslist-backend.
