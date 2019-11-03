# CRM
This is a demo project of a customer relation management system. 
  - Store customer information and update customer status
  - Add, change & delete customer notes

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites

JDK 13
Maven 3.6.2


### Installing

To build project with maven

```
cd [project root directory]
mvn clean package
```

To start project 

```
java -jar target/crm-0.0.1-SNAPSHOT.jar
```

End point can be accessed via Swagger ui. You can access the ui via the folling link

```
http://127.0.0.1:8080/swagger-ui.html


Sample tables and data are crea to H2 database. You can see find the database console in the following link

```
http://127.0.0.1:8080/h2/

## Running the tests

Not much test is build due to time contraint. More unit test and integration test can be built.

## Built With

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/maven-plugin/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Rest Repositories](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/htmlsingle/#howto-use-exposing-spring-data-repositories-rest-endpoint)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/htmlsingle/#using-boot-devtools)
* [JDBC API](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/htmlsingle/#boot-features-sql)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Data JDBC](https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/htmlsingle/#production-ready)
* [Selma] (http://www.selma-java.org)
* [Swagger] (https://swagger.io)
* [H2] (https://www.h2database.com/html/main.html)
* [Mockito (https://site.mockito.org/)]



## Authors

* **Emma LAW** - *Initial work* - [EmmaLaw](https://github.com/emmalaw/)


