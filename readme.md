# Voll.med API

Esta é a documentação da API Rest da aplicação Voll.med. A API contém funcionalidades de CRUD de médicos e pacientes, além de agendamento e cancelamento de consultas.
Utilizei o Swagger para geração de Documentação com base na OpenAPI e utilizei os módulos de teste do próprio Spring para fazer testes unitários.

# IMPORTANTE!
Defina variáveis de ambiente com os dados de acesso ao seu banco de dados, no caso, utilizei MySQL.
- **DB_HOST_PORT** = host do banco;
- **DB_USERNAME** = usuario do banco;
- **DB_PASSWORD** = senha do banco;
- **JWT_SECRET** = segredo para desencriptar o Token JWT;

## Contato

**Dev Filipe**  
- Email: fbianchi.andrade@gmail.com

## Licença

- [Apache 2.0](http://voll.med/api/licenca)

## Servidores

- URL: [http://localhost:8080](http://localhost:8080)
- Descrição: Generated server url

## Endpoints

### Pacientes

#### Detalhar Paciente

- Método: GET
- Path: `/pacientes/{id}`
- Parâmetros:
  - id (path) - **integer(int64)** - ID do paciente
- Resposta:
  - 200: OK
    - Schema: [DadosDetalhamentoPacienteDTO](#dadosdetalhamentopacientedto)

#### Alterar Paciente

- Método: PUT
- Path: `/pacientes/{id}`
- Parâmetros:
  - id (path) - **integer(int64)** - ID do paciente
- Corpo da Requisição:
  - application/json:
    - Schema: [DadosAtualizacaoPacienteDTO](#dadosatualizacaopacientedto)
- Resposta:
  - 200: OK
    - Schema: [DadosDetalhamentoPacienteDTO](#dadosdetalhamentopacientedto)

#### Deletar Paciente

- Método: DELETE
- Path: `/pacientes/{id}`
- Parâmetros:
  - id (path) - **integer(int64)** - ID do paciente
- Resposta:
  - 200: OK

#### Listar Todos os Pacientes

- Método: GET
- Path: `/pacientes`
- Parâmetros:
  - pageable (query) - **Pageable**
- Resposta:
  - 200: OK
    - Schema: [PageDadosListagemPacienteDTO](#pagedadoslistagempacientedto)

#### Cadastrar Paciente

- Método: POST
- Path: `/pacientes`
- Corpo da Requisição:
  - application/json:
    - Schema: [DadosCadastroPacienteDTO](#dadoscadastropacientedto)
- Resposta:
  - 200: OK
    - Schema: [DadosDetalhamentoPacienteDTO](#dadosdetalhamentopacientedto)

### Médicos

#### Buscar Médico

- Método: GET
- Path: `/medicos/{id}`
- Parâmetros:
  - id (path) - **integer(int64)** - ID do médico
- Resposta:
  - 200: OK
    - Schema: [DadosDetalhamentoMedicoDTO](#dadosdetalhamentomedicodto)

#### Alterar Médico

- Método: PUT
- Path: `/medicos/{id}`
- Parâmetros:
  - id (path) - **integer(int64)** - ID do médico
- Corpo da Requisição:
  - application/json:
    - Schema: [DadosAtualizacaoMedicoDTO](#dadosatualizacaomedicodto)
- Resposta:
  - 200: OK
    - Schema: [DadosDetalhamentoMedicoDTO](#dadosdetalhamentomedicodto)

#### Deletar Médico

- Método: DELETE
- Path: `/medicos/{id}`
- Parâmetros:
  - id (path) - **integer(int64)** - ID do médico
- Resposta:
  - 200: OK

#### Listar Todos os Médicos

- Método: GET
- Path: `/medicos`
- Parâmetros:
  - pageable (query) - **Pageable**
- Resposta:
  - 200: OK
    - Schema: [PageDadosListagemMedicoDTO](#pagedadoslistagemmedicodto)

#### Cadastrar Médico

- Método: POST
- Path: `/medicos`
- Corpo da Requisição:
  - application/json:
    - Schema: [DadosCadastroMedicoDTO](#dadoscadastromedicodto)
- Resposta:
  - 200: OK
    - Schema: [DadosDetalhamentoMedicoDTO](#dadosdetalhamentomedicodto)

### Autenticação

#### Efetuar Login

- Método: POST
- Path: `/login`
- Corpo da Requisição:
  - application/json:
    - Schema: [DadosAutenticacao](#dadosautenticacao)
- Resposta:
  - 200: OK

### Consultas

#### Listar Todas as Consultas Não Canceladas

- Método: GET
- Path: `/consultas`
- Parâmetros:
  - pageable (query) - **Pageable**
- Resposta:
  - 200: OK
    - Schema: [PageDadosConsultaListagem](#pagedadosconsultalistagem)

#### Agendar Consulta

- Método: POST
- Path: `/consultas`
- Corpo da Requisição:
  - application/json:
    - Schema: [DadosAgendamentoConsulta](#dadosagendamentoconsulta)
- Resposta:
  - 200: OK
    - Schema: [DadosDetalhamentoConsulta](#dadosdetalhamentoconsulta)

#### Cancelar Consulta

- Método: DELETE
- Path: `/consultas`
- Corpo da Requisição:
  - application/json:
    - Schema: [DadosCancelamentoConsulta](#dadoscancelamentoconsulta)
- Resposta:
  - 200: OK

#### Cadastrar Usuário

- Método: POST
- Path: `/addUser`
- Corpo da Requisição:
  - application/json:
    - Schema: [DadosCadastroUsuarioDTO](#dadoscadastrousuariodto)
- Resposta:
  - 200: OK
    - Schema: [UsuarioListagemDTO](#usuariolistagemdto)

### Hello

#### Olá Mundo

- Método: GET
- Path: `/hello`
- Resposta:
  - 200: OK
    - Schema: string
