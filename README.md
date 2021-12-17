# Eksempel på Spring Cloud Config Server

Dette er et eksempel på brug af Spring Cloud Config til centralisering af konfigurationer i en arkitektur med flere applikationer baseret på Spring Boot.

Eksemplet viser
* En konfigurationsserver (`server`)
* Et meget simpelt eksempel på en klient (`client`)
* Event Bus (baseret på `rabbitmq`)

Dokumentationen for Spring Cloud Config kan læses [her](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/).

## Konfigurationsfiler
Konfigurationsfilerne der benyttes i dette eksempel ligger på https://github.com/RuneMolin/cloud-config-repo

Dette kan ændres med parameteren `spring.cloud.config.server.git.uri` (se `docker-compose.yml`).

## Start eksemplet lokalt
Eksemplet startes ved at køre `docker-compose up`.

Klientens nuværende konfigurationsværdi kan ses ved at kalde `curl localhost:8080/message`.

## Server
En Spring Cloud Config Server er meget simpel. Den består blot af en minimal Spring Boot applikationen, annoteret med `@EnableConfigServer`.

Resten af serverens virkemåde styres af opstartsparametre, som det ses i `docker-compose.yml`.

Her er det vigtigste at lægge mærke til `spring.cloud.config.server.git.uri`, der styrer i hvilket git projekt konfiguration befinder sig, samt `spring.cloud.config.server.git.default-label`, som styrer hvilket git label (branch, tag eller commit id) der skal hentes.

Det sidste kan bruges til f.eks. at benytte en strategi hvor man har seperate branches for development, staging og produktion.

Læs yderligere om en sådan strategi [her](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/#_placeholders_in_git_uri).

## Klient
Klienten i dette eksempel er også simpel. Den indeholder blot en enkelt værdi (`example.message`), som hentes fra konfiguration serveren.

I klienten er det klassen `ExampleConfiguration` som indeholder værdien fra serveren. Denne klasse er annoteret med `@Configuration`, og bliver derfor automatisk opdateret fra serveren. I nogle tilfælde kan det være nødvendigt at annotere en klasse eller en `@Bean` metode med `@RefreshScope` (se eventuelt https://www.baeldung.com/spring-reloading-properties).

Hvilke konfigurationsværdier klienten modtager fra serveren styres dels af klientens navn (`spring.application.name`) og hvilken profil den afvikles som (`spring.profiles.active`).

I eksemplet startes klienten med navnet "config-client" og profilen "development". Dette resulterer i at konfiguration hentes fra filen "config-client-development.yml" i selve konfigurationsprojektet.


## Event Bus
Eksempel bruger en _event bus_, baseret på RabbitMQ. Dette betyder at når serveren bliver opmærksom på at der er sket ændringer i konfigurationen, bliver applikationer automatisk "opfrisket" med nye konfigurationsindstillinger.


## Webhooks
Konfigurationsserveren kan notificeres om opdateringer i konfigurationen ved at POST'e til endpointet `/monitor`. Serveren forstår webhook kald fra Gitlab, Github, BitBucket m.fl.

Se flere detaljer i [dokumentationen](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/#_push_notifications_and_spring_cloud_bus).

Et webhook kald kan simuleres lokalt med:

    curl -X POST -F "path=config-client" http://localhost:8888/monitor