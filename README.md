## Objetivo do Projeto

O objetivo deste projeto é implementar o Quartz Scheduler em um cenário de Horizontal Pod Autoscaling (HPA), onde múltiplos pods estão executando a mesma aplicação, garantindo que apenas um dos pods execute um job por vez.

## Rodando o Projeto

### Via IDE

#### Subir a Base PostgreSQL

A aplicação requer uma base de dados PostgreSQL em execução. Você pode optar por utilizar uma instância já existente ou iniciar uma nova via Docker Compose. 

Para subir via Docker Compose, comente a seção referente ao app no arquivo `docker-compose.yml`. O arquivo deverá ficar assim:

```yaml
services:
#  app:
#    build: .
#    image: spring-boot-quartz-docker
#    deploy:
#      replicas: 3
#    environment:
#      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/quartz-scheduler
#      SPRING_DATASOURCE_USERNAME: user
#      SPRING_DATASOURCE_PASSWORD: password
#    depends_on:
#      - db

  db:
    image: postgres:latest
    environment:
      POSTGRES_DB: quartz-scheduler
      POSTGRES_USER: user
      POSTGRES_PASSWORD: password
    ports:
      - "5432:5432"
```

Para iniciar o banco de dados, execute o seguinte comando:

```bash
docker compose up --build
```

#### Rodar a Aplicação

Após iniciar o banco de dados, execute a aplicação a partir da IDE, rodando a classe `QuartzSchedulerApplication.java`, localizada em `src/main/java/com/exemplo/quartzdocker/QuartzSchedulerApplication.java`. A aplicação estará configurada para se conectar à base de dados PostgreSQL.

### Via Docker Compose

Caso deseje rodar a aplicação juntamente com o banco de dados, mantenha o arquivo `docker-compose.yml` em sua forma original e execute o seguinte comando:

```bash
docker-compose down --rmi all && ./mvnw clean package && docker compose up --build
```

Pronto! O ambiente estará configurado e a aplicação em funcionamento.

## Testes

Para executar os testes, utilize o seguinte comando:

```bash
./mvnw clean test -Dspring.profiles.active=test
```

## Considerações Finais

Este projeto foi desenvolvido para demonstrar a integração do Quartz Scheduler em um ambiente com múltiplas instâncias, garantindo a execução única de jobs. Sinta-se à vontade para contribuir ou modificar conforme suas necessidades.

