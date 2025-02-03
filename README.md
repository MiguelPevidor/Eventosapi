# Projeto de Gerenciamento de Eventos

Este projeto é uma aplicação completa para gerenciar eventos, permitindo o cadastro de eventos, endereços e cupons promocionais. Ele tem como objetivo principal facilitar a organização e consulta de eventos, oferecendo funcionalidades como registro de detalhes do evento, geração de cupons de desconto, e associação de endereços aos eventos. Além disso, o projeto é acompanhado por uma documentação interativa feita com Swagger, que permite explorar e testar as APIs diretamente.

A solução é implementada em Java, utilizando um banco de dados PostgreSQL para armazenamento seguro, e faz uso do Amazon S3 para gerenciar o upload e armazenamento de imagens. Com infraestrutura hospedada na AWS, utilizando serviços como o EC2 para execução de instâncias de servidores e o RDS para gerenciamento de bancos de dados relacionais.
## Tecnologias Utilizadas

- **Java**: Linguagem principal para o backend.
- **Spring Boot**: Framework para desenvolvimento rápido e simplificado de aplicações Java.
- **PostgreSQL**: Banco de dados relacional utilizado para armazenar as informações.
- **Amazon Web Services**: (EC2,RDS,S3)
- **Amazon S3**: Serviço de armazenamento em nuvem para upload e gerenciamento de imagens relacionadas aos eventos.
- **Swagger**: Ferramenta para documentação e testes das APIs do projeto.
- **Docker**: Utilizado para contêineres, facilitando a configuração do ambiente de desenvolvimento.

## Funcionalidades

- Cadastro de Eventos e Cupons.
- Gerenciamento de cupons e endereços vinculados aos eventos.
- Upload de imagens para o Amazon S3.
- Documentação interativa via Swagger.

## Documentação da API

A documentação da API foi desenvolvida com Swagger. É possível acessá-la e testar os endpoints diretamente através do link:(http://34.230.11.43:8080/swagger-ui/index.html). A documentação é gerada automaticamente e inclui todas as rotas disponíveis na aplicação.


## Modelo de Banco de Dados

Abaixo está um exemplo do modelo de banco de dados utilizado no projeto. O modelo inclui as tabelas principais: `event`, `coupon` e `address`. A imagem do modelo está anexada neste repositório para consulta.

![WhatsApp Image 2025-02-03 at 16 15 57](https://github.com/user-attachments/assets/58216ec5-3583-442d-b12b-99d5d031f489)


## Arquitetura da Solução

A arquitetura do projeto segue uma abordagem em nuvem utilizando os serviços da AWS:

- **Servidor EC2**: Hospeda a aplicação Java.
- **Amazon S3**: Armazena as imagens enviadas.
- **Amazon Aurora**: Gerencia os dados do banco de maneira eficiente.

![WhatsApp Image 2025-02-03 at 16 16 13](https://github.com/user-attachments/assets/c6488ebf-7a48-4d58-900a-6c88a2f6ab96)

## Executando Localmente

Para executar o projeto localmente, siga as etapas abaixo:

1. **Pré-requisitos**:
   - Java 11 ou superior instalado.
   - Docker (opcional, mas recomendado para simplificar a configuração).
   - PostgreSQL instalado e configurado.
   - Criar um banco de dados com o nome `eventdb` (ou outro de sua preferência, ajustando as configurações no `application.properties`).

2. **Clone o repositório**:
   ```bash
   git clone https://github.com/miguelpevidor/Eventosapi.git
   cd Eventosapi
   ```

3. **Configure o Banco de Dados**:
   - Certifique-se de que o PostgreSQL está rodando.
   - Crie o banco de dados necessário para a aplicação.

4. **Execute a Aplicação**:
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Acesse a Aplicação**:
   - A aplicação estará disponível em: `http://localhost:8080`.

6. **Swagger**:
   - Acesse a documentação da API em: `http://localhost:8080/swagger-ui.html`.

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.

---

Caso tenha dúvidas ou precise de mais informações, não hesite em entrar em contato.
