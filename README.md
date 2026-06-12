# E-Commerce API

## Descrição

A E-Commerce API é um microserviço desenvolvido com Spring Boot para gerenciamento de produtos de uma loja virtual.

A aplicação permite cadastrar, consultar, atualizar e remover produtos, além de realizar buscas utilizando filtros específicos.

O projeto foi desenvolvido como atividade acadêmica utilizando conceitos de Programação Orientada a Objetos (POO), APIs RESTful, persistência de dados com Spring Data JPA, documentação com Swagger/OpenAPI e testes unitários.

---

## Tecnologias Utilizadas

- Java 21 (ou versão utilizada pelo grupo)
- Spring Boot
- Spring Data JPA
- PostgreSQL
- H2 Database
- Maven
- JUnit 5
- Mockito
- Swagger / OpenAPI
- Git e GitHub
- Render (Deploy)

---

## Estrutura do Projeto

```text
src
├── controller
├── service
├── repository
├── model
├── dto
├── exception
└── config
```

---

## Funcionalidades

### Produtos

- Criar produto
- Listar produtos
- Buscar produto por ID
- Atualizar produto
- Excluir produto
- Buscar produtos por categoria

---

## Endpoints

### Criar Produto

```http
POST /produtos
```

Exemplo de requisição:

```json
{
  "nome": "Notebook Dell",
  "descricao": "Notebook para trabalho",
  "preco": 4500.00,
  "estoque": 10
}
```

---

### Listar Produtos

```http
GET /produtos
```

---

### Buscar Produto por ID

```http
GET /produtos/{id}
```

Exemplo:

```http
GET /produtos/1
```

---

### Atualizar Produto

```http
PUT /produtos/{id}
```

---

### Excluir Produto

```http
DELETE /produtos/{id}
```

---

### Buscar Produtos por Categoria

```http
GET /produtos/categoria/{categoria}
```

---

## Como Executar Localmente

### Pré-requisitos

- Java JDK
- Maven
- Git

### Clonar o projeto

```bash
git clone (https://github.com/Juliastudent18/E-commerce.git)
```

Entrar na pasta:

```bash
cd ecommerce-api
```

### Executar a aplicação

```bash
mvn spring-boot:run
```

ou

```bash
./mvnw spring-boot:run
```

---

## Banco de Dados

### Desenvolvimento

Banco H2 em memória.

Console H2:

```text
http://localhost:8080/h2-console
```

---

### Produção

Banco PostgreSQL.

As credenciais são carregadas através de variáveis de ambiente:

```properties
DB_URL
DB_USER
DB_PASSWORD
```

---

## Documentação da API

Swagger UI:
https://e-commerce-api-ms3m.onrender.com/swagger-ui/index.html

Após deploy:

https://dashboard.render.com/web/srv-d8lich4vikkc73bv2hmg/deploys/dep-d8m0h937uimc73ehvspg?r=2026-06-12%4013%3A23%3A52%7E2026-06-12%4013%3A27%3A55

---

## Testes

Executar os testes:

```bash
mvn test
```

Gerar relatório de cobertura:

```bash
mvn clean verify
```

Cobertura mínima exigida:

```text
90%
```

---

# Deploy em Produção

### Plataforma

Render

### Banco de Dados

PostgreSQL

### Processo de Deploy

1. Projeto hospedado no GitHub.
2. Repositório conectado ao Render.
3. Banco PostgreSQL configurado.
4. Variáveis de ambiente cadastradas.
5. Build automático realizado pelo Render.



### Swagger Produção

https://e-commerce-api-ms3m.onrender.com/swagger-ui/index.html

## Integrantes do Grupo

- Ana Luisa 
- Beatriz Gonçalves 
- Camila Santos 
- Julia Souza 
- Thiago Lima

---

## Repositório GitHub

Link do projeto:

https://github.com/Juliastudent18/E-commerce.git
