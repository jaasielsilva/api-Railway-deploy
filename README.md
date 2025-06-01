# Painel do CEO - Portal Administrativo

Este projeto é uma aplicação web desenvolvida com **Spring Boot** e **Thymeleaf**, voltada para administração centralizada de usuários, relatórios, produtos, configurações e recursos humanos dentro de um sistema empresarial. A interface foi desenhada para ser responsiva, moderna e funcional para o gerenciamento do sistema pelo CEO ou time administrativo.

## 🖥️ Funcionalidades

- 🔐 Cadastro de usuários com:
  - Nome, email, telefone e endereço
  - Username e senha
  - Função (User/Admin)
  - Status (Ativo/Inativo)
- 📊 Acesso a diferentes seções administrativas:
  - Dashboard de Relatórios
  - Gestão de Usuários
  - Configurações do Sistema
  - Relatórios do Sistema
  - Recursos Humanos
  - Projetos
  - Produtos
- 🚪 Logout rápido e seguro

## 📂 Estrutura do Projeto

- `src/main/java`: Código Java backend com Spring Boot.
- `src/main/resources/templates`: Páginas HTML com Thymeleaf.
- `static`: CSS e scripts estáticos.
- `application.properties`: Configurações da aplicação, como conexão com banco de dados.
- `Dockerfile`: Contêinerização com Docker.
- `pom.xml`: Gerenciamento de dependências Maven.

## 🚀 Como Executar

### Pré-requisitos

- Java 17+
- Maven
- Docker (opcional para contêinerização)
- MySQL ou outro banco configurado

### Passos

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/painel-ceo.git
   cd painel-ceo
