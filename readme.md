# Voll.med API

Esta é a documentação da API Rest da aplicação Voll.med. A API contém funcionalidades de CRUD de médicos e pacientes, além de agendamento e cancelamento de consultas.

## Contato

Para mais informações, entre em contato com Dev Filipe via email: fbianchi.andrade@gmail.com.

## Endpoints

A API está hospedada em `http://localhost:8080`.

### Pacientes

#### Detalhar Paciente

- **Descrição**: Retorna detalhes de um paciente específico.
- **Método**: GET
- **URL**: `/pacientes/{id}`
- **Parâmetros**:
    - `id` (path): ID do paciente a ser detalhado (integer)
- **Resposta**: Retorna os detalhes do paciente.
- **Autenticação**: Token JWT

#### Alterar Paciente

- **Descrição**: Altera informações de um paciente existente.
- **Método**: PUT
- **URL**: `/pacientes/{id}`
- **Parâmetros**:
    - `id` (path): ID do paciente a ser alterado (integer)
- **Corpo da Requisição**: JSON contendo os dados atualizados do paciente.
- **Resposta**: Retorna os detalhes do paciente após a alteração.
- **Autenticação**: Token JWT

#### Deletar Paciente

- **Descrição**: Remove um paciente existente.
- **Método**: DELETE
- **URL**: `/pacientes/{id}`
- **Parâmetros**:
    - `id` (path): ID do paciente a ser deletado (integer)
- **Resposta**: Confirmação da remoção do paciente.
- **Autenticação**: Token JWT

### Médicos

#### Buscar Médico

- **Descrição**: Retorna detalhes de um médico específico.
- **Método**: GET
- **URL**: `/medicos/{id}`
- **Parâmetros**:
    - `id` (path): ID do médico a ser buscado (integer)
- **Resposta**: Retorna os detalhes do médico.
- **Autenticação**: Token JWT

#### Alterar Médico

- **Descrição**: Altera informações de um médico existente.
- **Método**: PUT
- **URL**: `/medicos/{id}`
- **Parâmetros**:
    - `id` (path): ID do médico a ser alterado (integer)
- **Corpo da Requisição**: JSON contendo os dados atualizados do médico.
- **Resposta**: Retorna os detalhes do médico após a alteração.
- **Autenticação**: Token JWT

#### Deletar Médico

- **Descrição**: Remove um médico existente.
- **Método**: DELETE
- **URL**: `/medicos/{id}`
- **Parâmetros**:
    - `id` (path): ID do médico a ser deletado (integer)
- **Resposta**: Confirmação da remoção do médico.
- **Autenticação**: Token JWT

### Consultas

#### Listar Consultas Não Canceladas

- **Descrição**: Retorna a lista de todas as consultas que não foram canceladas.
- **Método**: GET
- **URL**: `/consultas`
- **Parâmetros**: Paginação
- **Resposta**: Retorna a lista de consultas não canceladas.
- **Autenticação**: Token JWT

#### Agendar Consulta

- **Descrição**: Agenda uma nova consulta.
- **Método**: POST
- **URL**: `/consultas`
- **Corpo da Requisição**: JSON contendo os dados para agendamento da consulta.
- **Resposta**: Retorna os detalhes da consulta agendada.
- **Autenticação**: Token JWT

#### Cancelar Consulta

- **Descrição**: Cancela uma consulta existente.
- **Método**: DELETE
- **URL**: `/consultas`
- **Corpo da Requisição**: JSON contendo o ID da consulta e o motivo do cancelamento.
- **Resposta**: Confirmação do cancelamento da consulta.
- **Autenticação**: Token JWT

### Outros Endpoints

- **Login**: `/login` - Autenticação do usuário.
- **Cadastro de Usuário**: `/addUser` - Cadastro de novo usuário.
- **Olá Mundo**: `/hello` - Retorna uma mensagem de saudação.

# IMPORTANTE!
Defina variáveis de ambiente com os dados de acesso ao seu banco de dados, no caso, utilizei MySQL.
- **DB_HOST_PORT** = host do banco;
- **DB_USERNAME** = usuario do banco;
- **DB_PASSWORD** = senha do banco;
- **JWT_SECRET** = segredo para desencriptar o Token JWT;
