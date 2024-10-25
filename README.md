# Pokedex

## Aplicação com lista, informações e quiz sobre os Pokémon da 1ª Geração

Este projeto foi desenvolvido como parte da disciplina de **Laboratório de Programação II** do curso de **Engenharia da Computação** do IME. O objetivo é criar uma aplicação **web** e **Android**, integrando diversas tecnologias modernas para a construção da API, banco de dados, backend e frontend.

### Tecnologias Utilizadas

- **API**: Spring Boot (Java)
- **Banco de Dados**: MySQL
- **Backend**: JavaScript
- **Frontend**: HTML e CSS

### Descrição do Projeto

O projeto oferece uma experiência interativa onde o usuário pode explorar uma lista com todos os 151 Pokémon da 1ª geração, visualizar informações detalhadas de cada um e testar seus conhecimentos em um quiz. Além disso, os usuários podem criar suas próprias contas, salvar seus Pokémon favoritos e acessar funcionalidades personalizadas.

### Funcionalidades Principais

- **Cadastro e Login**: Criação de conta e autenticação.
- **Lista de Pokémon**: Visualize todos os 151 Pokémon da 1ª geração.
- **Detalhes Individuais**: Informações completas de cada Pokémon.
- **Quiz "Quem é Esse Pokémon?"**: Teste seus conhecimentos com um quiz divertido.
- **Favoritos**: Cada usuário pode salvar seus Pokémon favoritos para fácil acesso.

## Passo a Passo para Rodar o Projeto

### Pré-requisitos

Certifique-se de que os seguintes programas estão instalados e configurados:

- **Java Development Kit (JDK)**: Versão 11 ou superior.
- **Gradle**: Ferramenta de automação de builds.
- **MySQL**: Banco de dados MySQL rodando localmente.

### Passo 0: Clonar esse repositório
```bash
git clone https://github.com/jpadrono/Pokedex
cd Pokedex
```

### Passo 1: Configuração do Banco de Dados
- Certifique-se de que o MySQL está rodando localmente na porta 3306.
- Crie o banco de dados crudaula:
```sql
CREATE DATABASE crudaula
```

- Crie o usuário crudaula com a senha 12345
```sql
CREATE USER 'crudaula'@'localhost' IDENTIFIED BY '12345';
GRANT ALL PRIVILEGES ON crudaula.* TO 'crudaula'@'localhost';
FLUSH PRIVILEGES;
```

### Passo 2: Compilação e execução
- Compile e execute o porgrama usando Gradle
```bash
cd .\Backend\pokedex-api\
.\gradlew build
.\gradlew bootRun
```

### Passo 3: Acessando a Aplicação
- A API estará disponível em http://localhost:8080.
- Para acessar a interface da aplicação, vá até a pasta Frontend com o seguinte comando:
```bash
cd ..\..\Frontend\HTML\
```
- Abra o arquivo login.html no navegador para visualizar a tela inicial
