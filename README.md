# Testes de API — DummyJSON

Suíte de testes automatizados em Java (JUnit), organizada no padrão **Use Case**, para validar os endpoints da API pública [DummyJSON](https://dummyjson.com/).

## 📋 Sobre

Os testes cobrem dois grupos de funcionalidades:

1. **Endpoint de teste (`/test`)** — validação básica de disponibilidade e resposta da API.
2. **Autenticação de usuário (`/auth/login`)** — validação do fluxo de login, incluindo casos de sucesso e cenários de erro (campos ausentes/vazios).

## 🏗️ Arquitetura

Os testes seguem o padrão **Use Case**, onde cada ação de teste é encapsulada em uma classe própria, promovendo reuso e legibilidade:

| Use Case | Responsabilidade |
|---|---|
| `getDummyJsonUseCase` | Executa uma requisição GET ao endpoint de teste |
| `postUserAuthenticationUseCase` | Executa o POST de autenticação com credenciais válidas |
| `postErrorsUserAuthenticationUseCase` | Executa o POST de autenticação esperando erro (retorna o corpo de erro) |
| `getLastStatusCodeUseCase` | Recupera o status code da última requisição executada |
| `statusCodeValidatorUseCase` | Valida se o status code retornado é o esperado |

## ✅ Casos de teste

### Endpoint de teste — `GET /test`

| Teste | Descrição |
|---|---|
| `shouldReturn200WhenCallingTestEndpoint` | Verifica se o endpoint retorna status `200`, `status = "ok"` e `method = "GET"` |
| `shouldThrowExceptionWhenStatusCodeIsWrong` | Garante que o status retornado **não** seja `"error"` |
| `shouldThrowExceptionWhenMethodIsWrong` | Garante que o método retornado **não** seja `"POST"` |

### Autenticação — `POST /auth/login`

| Teste | Descrição | Resultado esperado |
|---|---|---|
| `shouldAuthenticateUserWithValidCredentials` | Login com usuário e senha válidos | `200 OK`, retorna `accessToken`, `refreshToken` e dados do usuário (`id`, `username`, `email`, `firstName`, `lastName`, `gender`, `image`) |
| `shouldAuthenticateUserWithUsernameEmptyCredentials` | Login sem o campo `username` no corpo da requisição | `400 Bad Request` com mensagem de erro |
| `shouldAuthenticateUserWithUsernameBlankCredentials` | Login com `username` vazio | `400 Bad Request` com mensagem de erro |
| `shouldAuthenticateUserWithPasswordEmptyCredentials` | Login sem o campo `password` no corpo da requisição | `400 Bad Request` com mensagem de erro |
| `shouldAuthenticateUserWithPasswordBlankCredentials` | Login com `password` vazio | `400 Bad Request` com mensagem de erro |
| `shouldAuthenticateUserWithContentTypeEmptyCredentials` | Login sem o header `Content-Type` | `400 Bad Request` com mensagem de erro |

## 🔑 Massa de dados utilizada

- `USERNAME`: usuário válido para autenticação (ex.: `emilys`)
- `PASSWORD`: senha válida correspondente ao usuário
- `EMPTY_USERNAME` / `EMPTY_PASSWORD`: valores vazios para simular campos em branco
- `X_CONTENT_TYPE`: `application/json` (ou equivalente)
- `STATUS_OK`: `200`
- `STATUS_BAD_REQUEST`: `400`
- `ERROR_MESSAGE`: mensagem de erro esperada pela API em cenários inválidos

> ⚠️ Recomenda-se manter essas constantes centralizadas em uma classe de constantes/config, evitando *hardcode* espalhado pelos testes.

## ▶️ Como executar

```bash
mvn test
```

Ou, para rodar uma classe específica:

```bash
mvn test -Dtest=NomeDaClasseDeTeste
```

## 🛠️ Tecnologias

- **Java**
- **JUnit** — framework de testes
- **REST Assured** (ou equivalente) — client HTTP para chamadas à API, abstraído via Use Cases

## 📌 Observações

- Os testes de erro (`postErrorsUserAuthenticationUseCase`) usam trechos de código comentados (`// body.put(...)`) para simular omissão de campos — considerar refatorar para métodos parametrizados (`@ParameterizedTest`) e eliminar a necessidade de comentar/descomentar linhas manualmente.
- A validação de status code é feita sempre via `statusCodeValidatorUseCase`, garantindo consistência entre os testes.
