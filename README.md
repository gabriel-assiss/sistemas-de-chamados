Sistema de Chamados

API REST para gerenciamento de chamados internos, desenvolvida com Java e Spring Boot.

O sistema possui autenticação baseada em JWT, controle de acesso por perfis e funcionalidades para gerenciamento de chamados, técnicos, funcionários e usuários.

🚀 Tecnologias
Java 21
Spring Boot
Spring Web
Spring Data JPA
Spring Security
JWT
BCrypt
MySQL
Maven Wrapper
📌 Funcionalidades
Autenticação de usuários com JWT
Geração de token JWT no login
Expiração do token em 2 horas
Controle de acesso baseado em perfis
Cadastro de usuários por administrador
Gerenciamento de chamados
Gerenciamento de técnicos
Gerenciamento de funcionários
Busca dos chamados do usuário autenticado
👥 Perfis de Usuário

O sistema possui três perfis:

Perfil	Permissões
ADMIN	Cadastro de usuários
TECNICO	Acesso aos recursos de técnicos e chamados
FUNCIONARIO	Acesso aos recursos de funcionários e chamados
⚙️ Configuração

Crie o arquivo src/main/resources/application.properties e configure suas credenciais locais:

spring.application.name=chamados

spring.datasource.url=jdbc:mysql://localhost:3306/bancosistemaschamados
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080

Não versione credenciais reais de banco de dados no repositório.

JWT

Configure a variável de ambiente utilizada para assinar os tokens:

JWT_SECRET=sua_chave_secreta_com_tamanho_seguro

A chave deve possuir um tamanho adequado para o algoritmo utilizado pelo projeto.

▶️ Como executar
Windows
.\mvnw.cmd spring-boot:run
Linux / macOS
./mvnw spring-boot:run

Após iniciar a aplicação, a API estará disponível em:

http://localhost:8080
🔑 Autenticação
Login
POST /auth/login

Exemplo de body:

{
  "email": "admin@email.com",
  "senha": "123456"
}

Após a autenticação, a API retorna um token JWT.

Para acessar endpoints protegidos, envie o token no header:

Authorization: Bearer seu_token_jwt
📋 Endpoints
Administração
Método	Endpoint	Descrição
POST	/admin/usuarios	Cadastra usuário

Exemplo:

{
  "nome": "Joao Silva",
  "email": "joao@email.com",
  "senha": "123456",
  "role": "FUNCIONARIO",
  "cargo": "Analista"
}
📌 Chamados
Método	Endpoint	Descrição
GET	/chamados	Lista os chamados
GET	/chamados/{id}	Busca um chamado por ID
GET	/chamados/meus	Busca os chamados do usuário autenticado
GET	/chamados/buscar?problema=internet	Busca chamados por problema
POST	/chamados	Cria um chamado
PUT	/chamados/{id}	Atualiza um chamado
PATCH	/chamados/{id}/resolver	Marca um chamado como resolvido
DELETE	/chamados/{id}	Remove um chamado
Chamados do usuário autenticado

O endpoint:

GET /chamados/meus

utiliza o usuário autenticado pelo Spring Security.

O frontend não precisa enviar o id ou a role do usuário. Essas informações são obtidas a partir da autenticação realizada através do token JWT.

Fluxo simplificado:

JWT
 ↓
JwtAuthenticationFilter
 ↓
SecurityContext
 ↓
Usuário autenticado
 ↓
Busca dos chamados
👨‍💼 Funcionários
Método	Endpoint	Descrição
GET	/funcionarios	Lista funcionários
GET	/funcionarios/{id}	Busca funcionário por ID
GET	/funcionarios/buscar?nome=Joao	Busca funcionário por nome
POST	/funcionarios	Cadastra funcionário
PUT	/funcionarios/{id}	Atualiza funcionário
DELETE	/funcionarios/{id}	Remove funcionário
🧑‍💻 Técnicos
Método	Endpoint	Descrição
GET	/tecnicos	Lista técnicos
GET	/tecnicos/{id}	Busca técnico por ID
GET	/tecnicos/buscar?nome=Maria	Busca técnico por nome
POST	/tecnicos	Cadastra técnico
PUT	/tecnicos/{id}	Atualiza técnico
DELETE	/tecnicos/{id}	Remove técnico
🛡️ Segurança

As rotas protegidas exigem autenticação através de JWT.

Regras de acesso
Rota	Acesso
/auth/login	Público
/admin/**	ADMIN
/tecnicos/**	TECNICO
/funcionarios/**	FUNCIONARIO
/chamados/**	FUNCIONARIO ou TECNICO

O projeto utiliza:

Spring Security
JWT
BCrypt
Roles / Authorities
SecurityContext
Filtro de autenticação JWT
🔄 Fluxo de autenticação

O processo de autenticação funciona de forma simplificada da seguinte maneira:

Usuário
   ↓
Email + senha
   ↓
AuthenticationManager
   ↓
UserDetailsService
   ↓
Busca usuário no banco
   ↓
PasswordEncoder
   ↓
Senha válida?
   ↓
Usuário autenticado
   ↓
Geração do JWT
   ↓
Cliente envia JWT nas próximas requisições
   ↓
JwtAuthenticationFilter
   ↓
Validação do JWT
   ↓
SecurityContext
   ↓
Autorização por perfil
📁 Estrutura

O projeto segue uma organização baseada na separação de responsabilidades do Spring:

src/
└── main/
    ├── java/
    │   └── br.com.projeto.chamados/
    │       ├── controller/
    │       ├── service/
    │       ├── repository/
    │       ├── entity/
    │       ├── dto/
    │       └── security/
    │
    └── resources/
        └── application.properties
🔮 Melhorias Futuras
 Adicionar validações com Bean Validation
 Criar tratamento global de exceções com @RestControllerAdvice
 Adicionar testes automatizados
 Adicionar documentação com Swagger/OpenAPI
 Adicionar Docker Compose com MySQL
 Adicionar migrations com Flyway ou Liquibase
📌 Status

🚧 Em desenvolvimento

Projeto desenvolvido para aprofundar conhecimentos em:

Desenvolvimento backend com Java
Spring Boot
APIs REST
Spring Data JPA
Hibernate
MySQL
Spring Security
Autenticação e autorização com JWT
Arquitetura de aplicações backend
