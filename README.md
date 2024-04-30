[![Build Status](https://travis-ci.com/givanthak/spring-boot-rest-api-tutorial.svg?branch=master)](https://travis-ci.com/givanthak/spring-boot-rest-api-tutorial)
[![Known Vulnerabilities](https://snyk.io/test/github/givanthak/spring-boot-rest-api-tutorial/badge.svg)](https://snyk.io/test/github/givanthak/spring-boot-rest-api-tutorial)



# ClusteredData Werehouse REST CRUD API with Spring Boot, Mysql, JPA, Swagger and Hibernate

## Steps to Setup

**1. Clone the application**

```bash
https://github.com/YazanAta/ClusteredData-Warehouse.git
```

**2. Create Mysql database**
```bash
create database deal
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Build and run the app using maven**

```bash
mvn package
java -jar target/javatask-0.0.1-SNAPSHOT.jar

```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

    GET /api/deals
    
    GET /api/deals/{dealUniqueId}

    POST /api/deals/import

You can explore more about these APIs and interact with them using Swagger. Swagger provides a user-friendly interface for testing endpoints, making it easy to understand and interact with the API functionalities. You can access the Swagger UI by navigating to /swagger-ui/index.html on your localhost, typically at http://localhost:8080/swagger-ui/index.html.
![Alt text](https://live.staticflickr.com/65535/53688508053_9d70e44125_c.jpg)
