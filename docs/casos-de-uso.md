# Casos de uso — Salva Livros

**Sistema:** catálogo pessoal de livros  
**Ator:** Usuário (dono da biblioteca)  
**Fora de escopo:** login, múltiplos usuários, empréstimos, capa, integração com livrarias, frontend (tratado depois)

Relacionado: [diagrama de casos de uso](diagrama_caso_de_uso_salva_livros.png)

## Regras de domínio (valem para todos os casos)


| Campo                 | Obrigatório | Observação                               |
| --------------------- | ----------- | ---------------------------------------- |
| título                | sim         | texto não vazio                          |
| autor                 | sim         | texto não vazio                          |
| categoria             | sim         | texto livre (ex.: Romance, Técnico)      |
| editora               | não         |                                          |
| isbn                  | não         | se informado, deve ser único no catálogo |
| id                    | gerado      | o usuário não informa na criação         |
| createdAt / updatedAt | gerado      | o usuário não informa                    |


- Não há status de leitura nesta versão (“possuo / pretendo ler” fica só como contexto do produto).
- Dois livros podem ter o mesmo título/autor se o ISBN for diferente ou ausente.
- “Buscar livro” neste documento = filtrar a lista. Consultar o detalhe de um livro entra em **Listar** (UC05).

## Feedback padrão

| Situação | Mensagem para o usuário |
| --- | --- |
| título, autor ou categoria vazios | Por favor, informe título, autor e categoria. (Se só um campo faltar: Por favor, informe o título.) |
| ISBN já usado por outro livro | Já existe um livro com esse ISBN. |
| livro selecionado não está mais no catálogo | Livro não encontrado. |
| busca ou listagem sem itens | Nenhum livro encontrado. (informativo, não é erro) |

---

## UC01 — Catalogar livro

**Objetivo:** registrar um livro novo no catálogo.  
**Pré-condição:** nenhuma.  
**Pós-condição:** o livro passa a existir no catálogo.

**Fluxo principal**

1. O usuário informa título, autor, categoria e, se quiser, editora e ISBN.
2. O sistema valida os campos obrigatórios.
3. Se houver ISBN, o sistema verifica se já existe outro livro com o mesmo ISBN.
4. O sistema grava o livro e mostra os dados salvos.

**Alternativas / erros**

- 2a. Campo obrigatório vazio  
  O sistema não grava o livro e informa:  
  *"Por favor, informe título, autor e categoria."*  
  (Caso falte um campo, o sistema deve ser específico: *"Por favor, informe o título."*)
- 3a. ISBN já cadastrado  
  O sistema não grava o livro e informa:  
  *"Já existe um livro com esse ISBN."*

**Aceite**

- [ ] Livro válido é gravado e aparece no catálogo.
- [ ] Sem título/autor/categoria a operação falha, exibindo a mensagem de feedback para o usuário.
- [ ] ISBN duplicado falha; ISBN omitido é aceito.

---

## UC02 — Buscar livro

**Objetivo:** encontrar livros por título, autor ou categoria.  
**Pré-condição:** nenhuma.  
**Pós-condição:** o catálogo não muda.

**Fluxo principal**

1. O usuário informa um ou mais filtros: título, autor, categoria (texto parcial, sem diferenciar maiúsculas e acentos).
2. O sistema devolve a lista de livros que batem com **todos** os filtros informados.
3. Sem nenhum filtro, este caso de uso não se aplica, e o sistema deve usar UC05 Listar.

**Alternativas / erros**

- 2a. Nenhum livro corresponde  
  O sistema retorna uma lista vazia e informa *"Nenhum livro encontrado."*

**Aceite**

- [ ] Filtrar por autor retorna só os livros daquele autor.
- [ ] “har” encontra título “Harry Potter”.
- [ ] Busca sem resultado devolve lista vazia e a mensagem informativa, não falha.

---

## UC03 — Editar livro

**Objetivo:** alterar dados de um livro já catalogado.  
**Pré-condição:** o usuário seleciona um livro existente (via UC02 ou UC05).  
**Pós-condição:** os campos alterados estão atualizados.

**Fluxo principal**

1. O sistema apresenta os dados do livro selecionado.
2. O usuário altera um ou mais campos (título, autor, categoria, editora, ISBN).
3. O sistema valida as informações alteradas como no UC01.
4. O sistema grava as alterações e mostra o livro atualizado.

**Alternativas / erros**

- 1a. O livro selecionado não está mais no catálogo  
  O sistema não grava e informa *"Livro não encontrado."*
- 3a. Campo obrigatório vazio  
  Igual UC01 (2a).
- 3b. O ISBN informado já pertence a outro livro  
  O sistema não grava e informa *"Já existe um livro com esse ISBN."*

**Aceite**

- [ ] Alterar um ou mais campos de um livro existente grava a mudança.
- [ ] Livro selecionado que não está mais no catálogo não cria livro novo e exibe *"Livro não encontrado."*
- [ ] Trocar ISBN para um já usado por outro livro falha e exibe a mensagem de ISBN duplicado.

---

## UC04 — Excluir livro

**Objetivo:** remover um livro do catálogo.  
**Pré-condição:** o usuário seleciona um livro existente (via UC02 ou UC05).  
**Pós-condição:** o livro não aparece mais em listagem/busca.

**Fluxo principal**

1. O sistema apresenta os dados do livro selecionado.
2. O usuário confirma a exclusão do livro.
3. O sistema remove o livro.

**Alternativas / erros**

- 1a. O livro selecionado não está mais no catálogo  
  O sistema não remove e informa *"Livro não encontrado."*
- 2a. O usuário cancela a exclusão  
  O catálogo não muda.

**Aceite**

- [ ] Livro excluído some da listagem e da busca.
- [ ] Tentar excluir um livro que não está mais no catálogo falha e exibe *"Livro não encontrado."*
- [ ] Cancelar a exclusão mantém o livro no catálogo.

---

## UC05 — Listar livros

**Objetivo:** ver todos os livros catalogados.  
**Pré-condição:** nenhuma.  
**Pós-condição:** o catálogo não muda.

**Fluxo principal**

1. O usuário solicita a lista.
2. O sistema devolve todos os livros, mais recentes primeiro.
3. O usuário seleciona um livro da lista e o sistema mostra o detalhe.

**Alternativas / erros**

- 2a. Catálogo vazio  
  O sistema retorna uma lista vazia e informa *"Nenhum livro encontrado."*
- 3a. O livro selecionado não está mais no catálogo  
  O sistema informa *"Livro não encontrado."*

**Aceite**

- [ ] Três livros cadastrados aparecem na lista.
- [ ] Catálogo vazio devolve lista vazia e a mensagem informativa.
- [ ] O detalhe do livro selecionado devolve aquele livro.

---

## Mapa rápido para a API (depois que estes UCs estiverem ok)


| Caso de uso    | Operação típica                         |
| -------------- | --------------------------------------- |
| UC01 Catalogar | `POST /livros`                          |
| UC02 Buscar    | `GET /livros?titulo=&autor=&categoria=` |
| UC03 Editar    | `PUT /livros/{id}`                      |
| UC04 Excluir   | `DELETE /livros/{id}`                   |
| UC05 Listar    | `GET /livros` e `GET /livros/{id}`      |


Essa tabela **não substitui** os UCs; só traduz o domínio para HTTP quando for a hora da API.
