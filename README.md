# video-service — Atelier GraphQL

Spring Boot 3.5 · Spring for GraphQL · Spring Data JPA · H2

## Lancer
mvn spring-boot:run

## Endpoints
- GraphiQL : http://localhost:8090/graphiql
- Console H2 : http://localhost:8090/h2-console (jdbc:h2:mem:video-db)

## Exemples
{ videoList { id name creator { name } } }

subscription { notifyVideoChange { id name creator { name } } }