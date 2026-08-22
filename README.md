Sistema de Chamados
API REST para gerenciamento de chamados internos, com autenticacao JWT, controle de acesso por perfis e operacoes para chamados, tecnicos, funcionarios e usuarios.

Tecnologias
Java 21
Spring Boot
Spring Web
Spring Data JPA
Spring Security
JWT
BCrypt
MySQL
Maven Wrapper
Funcionalidades
Login com geracao de token JWT
Token JWT com expiracao de 2 horas
Controle de acesso por perfil
Cadastro de usuarios por administrador
Gerenciamento de chamados
Gerenciamento de tecnicos
Gerenciamento de funcionarios
Busca dos chamados do usuario autenticado
Perfis
O sistema trabalha com tres perfis de usuario:

ADMIN: cadastra usuarios
TECNICO: acessa recursos de tecnicos e chamados
FUNCIONARIO: acessa recursos de funcionarios e chamados
Configuracao
Crie o arquivo src/main/resources/application.properties com as configuracoes locais:

spring.application.name=chamados

spring.datasource.url=jdbc:mysql://localhost:3306/bancosistemaschamados
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080
Configure tambem a variavel de ambiente usada para assinar os tokens JWT:

JWT_SECRET=sua_chave_secreta_com_tamanho_seguro
Como Executar
No Windows:

./mvnw.cmd spring-boot:run
No Linux/macOS:

./mvnw spring-boot:run
A API ficara disponivel em:

http://localhost:8080
Autenticacao
Login
POST /auth/login
Exemplo de body:

{
  "email": "admin@email.com",
  "senha": "123456"
}
A resposta retorna um token JWT. Para acessar endpoints protegidos, envie o token no header:

Authorization: Bearer seu_token_jwt
Endpoints
Administracao
POST /admin/usuarios
Exemplo de cadastro:

{
  "nome": "Joao Silva",
  "email": "joao@email.com",
  "senha": "123456",
  "role": "FUNCIONARIO",
  "cargo": "Analista"
}
Chamados
GET    /chamados
GET    /chamados/{id}
GET    /chamados/meus
GET    /chamados/buscar?problema=internet
POST   /chamados
PUT    /chamados/{id}
PATCH  /chamados/{id}/resolver
DELETE /chamados/{id}
GET /chamados/meus usa o usuario autenticado no Spring Security. O frontend nao precisa enviar id ou role; essas informacoes sao obtidas a partir do token JWT.

Funcionarios
GET    /funcionarios
GET    /funcionarios/{id}
GET    /funcionarios/buscar?nome=Joao
POST   /funcionarios
PUT    /funcionarios/{id}
DELETE /funcionarios/{id}
Tecnicos
GET    /tecnicos
GET    /tecnicos/{id}
GET    /tecnicos/buscar?nome=Maria
POST   /tecnicos
PUT    /tecnicos/{id}
DELETE /tecnicos/{id}
Seguranca
As rotas protegidas exigem autenticacao via JWT.

Regras principais:

/auth/login: publico
/admin/**: somente ADMIN
/tecnicos/**: somente TECNICO
/funcionarios/**: somente FUNCIONARIO
/chamados/**: FUNCIONARIO ou TECNICO
Melhorias Futuras
Adicionar validacao com Bean Validation
Criar tratamento global de excecoes com @RestControllerAdvice
Adicionar testes automatizados
Adicionar Swagger/OpenAPI
Adicionar Docker Compose com MySQL
Adicionar migrations com Flyway ou Liquibase
