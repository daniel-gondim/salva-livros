# Salva Livros

Sistema pessoal para catalogar e gerenciar uma biblioteca de livros.

## Sobre o Projeto

O **Salva Livros** é uma aplicação em desenvolvimento para organizar uma coleção de livros. Atualmente, o projeto oferece uma API REST para cadastrar, consultar, atualizar e excluir livros.

## O que você pode aprender

Este projeto apresenta conceitos como APIs REST, validação de dados, persistência com banco de dados e testes automatizados. Você também pode acompanhar como uma requisição passa pelas diferentes camadas da aplicação.

## Funcionalidades

- Cadastrar livros
- Listar os livros cadastrados
- Consultar um livro pelo ID
- Atualizar os dados de um livro
- Excluir um livro

A busca por título, autor ou categoria ainda não está disponível.

## Arquitetura e Tecnologias

- **Backend:** Java 17, Spring Boot 3.5.4, Spring Web, Spring Data JPA e Bean Validation
- **Banco de dados:** PostgreSQL
- **Build:** Gradle (via Gradle Wrapper)
- **Frontend:** Angular planejado; ainda não implementado

## API

| Método | Rota | Descrição | Respostas principais |
|---|---|---|---|
| `POST` | `/livros` | Cadastra um livro | `201 Created`, `400 Bad Request` |
| `GET` | `/livros` | Lista os livros | `200 OK` |
| `GET` | `/livros/{id}` | Consulta um livro pelo ID | `200 OK`, `404 Not Found` |
| `PUT` | `/livros/{id}` | Atualiza os dados de um livro | `200 OK`, `400 Bad Request`, `404 Not Found` |
| `DELETE` | `/livros/{id}` | Exclui um livro | `200 OK`, `404 Not Found` |

Nos pedidos de criação e atualização, `titulo`, `autor` e `categoria` são obrigatórios. O `PUT` representa uma atualização completa e exige esses campos mesmo quando apenas outro dado foi alterado. `editora` e `isbn` são opcionais.

## Comece aqui

### Pré-requisitos

- Git
- JDK 17
- PostgreSQL em execução

Confira se o Git e o Java estão disponíveis no terminal com `git --version` e `java -version`.

### Clonar o projeto

No terminal, execute:

```bash
git clone https://github.com/daniel-gondim/salva-livros.git
cd salva-livros
```

### Preparar o banco de dados

Crie um banco PostgreSQL chamado `salvalivros`. Por exemplo, no `psql`:

```sql
CREATE DATABASE salvalivros;
```

A URL e o usuário têm valores locais padrão em `src/main/resources/application.properties`. A senha é lida da variável de ambiente `DB_PASSWORD`; não a escreva nesse arquivo nem a compartilhe no repositório.

Em um terminal Bash, você pode informar a senha sem digitá-la em um comando salvo no histórico:

```bash
read -s -p "Senha do PostgreSQL: " DB_PASSWORD
printf '\n'
export DB_PASSWORD
```

Essa variável vale apenas para o terminal atual. Se preferir iniciar a aplicação pelo IntelliJ IDEA, configure `DB_PASSWORD` nas variáveis de ambiente da configuração de execução. Se o seu banco usar outra URL ou usuário, configure também `DB_URL` ou `DB_USERNAME`.

### Executar a aplicação

Na raiz do projeto, execute:

```bash
./gradlew bootRun
```

A API ficará disponível em `http://localhost:8080`.

## Experimentar a API

Com a aplicação em execução, cadastre um livro:

```bash
curl -i -X POST http://localhost:8080/livros \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Dom Casmurro","autor":"Machado de Assis","categoria":"Romance","editora":"Editora Exemplo","isbn":"9780000000000"}'
```

Uma criação bem-sucedida retorna `201 Created` e os dados do livro, incluindo o ID atribuído pela aplicação. Para listar os livros cadastrados:

```bash
curl -i http://localhost:8080/livros
```

Para consultar um livro específico, substitua `1` pelo ID retornado no cadastro:

```bash
curl -i http://localhost:8080/livros/1
```

Nos pedidos de criação e atualização, `titulo`, `autor` e `categoria` são obrigatórios. `editora` e `isbn` são opcionais. O `PUT` atualiza o recurso por completo, então envie todos os campos obrigatórios mesmo quando alterar apenas um deles.

## Por onde começar a ler o código

Uma requisição percorre estas partes:

1. `LivroController` recebe a requisição HTTP e encaminha a operação.
2. `LivroRequest` representa os dados enviados e valida os campos obrigatórios.
3. `LivroService` executa a lógica da operação e prepara a resposta.
4. `LivroRepository` acessa os dados no PostgreSQL.
5. `Livro` representa o livro armazenado no banco; `LivroResponse` representa os dados devolvidos pela API.

Essas classes estão em `src/main/java/com/salvalivros/backend/`, nas pastas `controller`, `dto`, `service`, `repository` e `model`.

## Testes

Os testes de integração usam a conexão PostgreSQL configurada, e os testes de repository apagam os livros da tabela antes de cada teste. **Use um banco de teste separado** para não afetar seus dados. Crie, por exemplo, `salvalivros_test`:

```sql
CREATE DATABASE salvalivros_test;
```

Os testes de controller verificam respostas HTTP e validações; os de service verificam a lógica da aplicação; e os de repository verificam o acesso ao banco. No terminal Bash em que definiu `DB_PASSWORD`, aponte `DB_URL` para o banco de teste e execute a suíte:

```bash
export DB_URL=jdbc:postgresql://localhost:5432/salvalivros_test
./gradlew test
```

Não execute os testes apontando para um banco com dados que deseja preservar.

## Problemas comuns

- **Não foi possível conectar ao banco:** confirme que o PostgreSQL está em execução, que o banco `salvalivros` existe e que `DB_URL` e `DB_USERNAME` correspondem ao seu ambiente.
- **Falha de autenticação no PostgreSQL:** confira a senha fornecida em `DB_PASSWORD`.
- **A aplicação reclama que `DB_PASSWORD` não está definida:** configure essa variável no terminal atual ou na configuração de execução da IDE.
- **A porta 8080 já está em uso:** encerre a outra aplicação que usa essa porta ou configure uma porta diferente.
- **A API retorna `400 Bad Request`:** confira se `titulo`, `autor` e `categoria` foram enviados e não estão em branco.
- **A API retorna `404 Not Found`:** confira se o ID informado corresponde a um livro cadastrado.

## Estrutura do Projeto

```
├── docs/                   # Documentação do projeto
├── src/main/java/          # Código da aplicação
├── src/main/resources/     # Configurações
├── src/test/java/          # Testes
├── README.md
└── build.gradle
```

## Documentação

- [Casos de uso](docs/casos-de-uso.md)
- [Diagrama de casos de uso](docs/diagrama_caso_de_uso_salva_livros.png)
- [Modelo entidade-relacionamento](docs/MVPmodelo-er-salvalivros.jpg)
- [Diagrama de classes](docs/class-diagram-salvalivros.mermaid)

## Status do Projeto

🔨 **Em desenvolvimento**

### Concluído

- [x] Estrutura e configuração do backend Spring Boot
- [x] Entidade, repositório e camada de serviço para livros
- [x] API REST para criação, consulta, atualização e exclusão
- [x] Validação dos campos obrigatórios
- [x] Testes de controller, service e repository

### Planejado

- [ ] Frontend em Angular
- [ ] Deploy da aplicação

## Contribuição

Este é um projeto pessoal, mas sugestões e feedback são bem-vindos.

## Licença

Este projeto é de uso pessoal.

---

Desenvolvido por Daniel Gondim de Castro Rocha