# 🚀 Sistema de Login - Backend

Este projeto é uma API REST desenvolvida com **Java Spring Boot**, que permite:

✅ Cadastro de usuários (nome, e-mail, senha, CPF, perfil).  
✅ Login com autenticação JWT.  
✅ Troca de senha para usuários autenticados.  
✅ Gerenciamento de usuários para administradores.  
✅ Banco de dados H2 in-memory.  
✅ Documentação com Swagger.

## 🛠️ Tecnologias

- Java 17
- Spring Boot 3.5.3
- Spring Security
- JWT (Json Web Token)
- Banco H2 (in-memory)
- Swagger (OpenAPI)

## 🚀 Como rodar o projeto

1. Clone o repositório:
   ```bash
   git clone https://LuisHenriqueSevero/Backend-Teste-Duett.git
   cd Backend-Teste-Duett

2. Execute o projeto:

Com Maven:
  ./mvnw spring-boot:run
Ou na IDE (IntelliJ/Eclipse):
Rodar a classe principal BackendApplication.

3.Acesse a API:

Swagger: http://localhost:8080/swagger-ui/index.html
Banco H2: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Usuário: sa
Senha: (deixe em branco)

4. Usuarios padrões:

Ao iniciar o projeto, o banco é populado com:

E-mail:admin@admin.com	Senha:admin123	Perfil:Administrador
E-mail:usuario@teste.com	Senha:usuario123	Perfil:Usuario
