<h1 align="center">
  MiniShop API
</h1>

<p align="center">
  <a href="#-tecnologias">Technologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-projeto">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-solução">Solução</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-configuração">Configuração</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-build-and-run">Build and Run</a>&nbsp;&nbsp;&nbsp;
</p>

## ✨ Tecnologias

- [Java](https://www.oracle.com/java/technologies/downloads/)
- [Maven](https://maven.apache.org/download.cgi)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring Security jwt](https://docs.spring.io/spring-security/reference/index.html)
- [Sql Server](https://www.microsoft.com/pt-br/sql-server/sql-server-downloads)
- [Liquibase](https://www.liquibase.org/)
- [Aws bucket](https://docs.aws.amazon.com/pt_br/AmazonS3/latest/userguide/UsingBucket.html)
- [Docker](https://docs.docker.com/)

## 💻 Projeto

API desenvolvida pensando em uma loja que oferece controle de acesso com roles para cada usuário. Através desta API, é possível cadastrar novos usuários e realizar a listagem de produtos, clientes, pedidos e fornecedores. Além disso, também é possível fazer upload de imagens.

## 💡 Solução

Este projeto foi desenvolvido com

[x] Controllers para as diferentes rotas

[x] Liquibase para realizar as migrations

[x] Spring Data para salvar os dados

[x] Swagger como documentação para a api

[x] Exception padrões para os erros

[x] SqlServer rodando em um container docker

## 🛠️ Configuration

Você precisa criar uma base de dados de sua preferencia e alterar as configurações no application.properties e criar um aws bucket para salvar as imagens 

## 🚀 Build and Run

Para realizar o build e executar, execute os comandos:

```
- mvn clean install

- mvn spring-boot:run
```
Ou execute por alguma Ide.

The APIs will be available in Swagger-UI: http://localhost:8080/swagger-ui.html.
