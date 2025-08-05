# 📚 Salva Livros

Sistema pessoal para catalogar e gerenciar minha biblioteca de livros.

## 📋 Sobre o Projeto

O **Salva Livros** é uma aplicação para organizar minha coleção pessoal de livros, permitindo catalogar, buscar, editar e gerenciar informações sobre os livros que possuo ou pretendo ler.

## 🎯 Funcionalidades

- ✅ Catalogar novos livros
- 🔍 Buscar livros por título, autor ou categoria
- ✏️ Editar informações dos livros
- 🗑️ Excluir livros da biblioteca
- 📋 Listar todos os livros cadastrados

## 🏗️ Arquitetura

- **Backend**: Java 17 + Spring Boot 3.x
- **Banco de Dados**: PostgreSQL 
- **Frontend**: TypeScript (planejado)

## 🛠️ Tecnologias Utilizadas

### Backend
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Spring Web
- PostgreSQL Driver
- Lombok
- Spring Boot DevTools
- Gradle

## 🚀 Como Executar

### Pré-requisitos
- Java 17 ou superior
- Gradle (incluído via wrapper)

### Executando o projeto
```bash
# Clonar o repositório
git clone https://github.com/daniel-gondim/salva-livros.git
cd salvalivros-backend

# Executar a aplicação
./gradlew bootRun
```

A aplicação estará disponível em `http://localhost:8080`

## 📁 Estrutura do Projeto

```
salvalivros-backend/
├── docs/                   # Documentação
│   ├── casos-de-uso.md
│   └── diagrama-casos-uso.png
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
├── README.md
├── build.gradle
└── .gitignore
```

## 📖 Documentação

- [Casos de Uso](docs/casos-de-uso.md)
- [Diagrama de Casos de Uso](docs/diagrama-casos-uso.png)

## 🚧 Status do Projeto

🔨 **Em Desenvolvimento**

### Concluído
- [x] Configuração inicial do projeto
- [x] Estrutura base Spring Boot

### Planejado
- [] Configuração do banco de dados
- [ ] Modelagem das entidades
- [ ] Implementação dos repositories
- [ ] Criação das APIs REST
- [ ] Validações de dados
- [ ] Testes unitários e de integração
- [ ] Frontend em TypeScript
- [ ] Deploy da aplicação

## 🤝 Contribuição

Este é um projeto pessoal, mas sugestões e feedback são sempre bem-vindos!

## 📝 Licença

Este projeto é de uso pessoal.

---

Desenvolvido por Daniel Gondim de Castro Rocha