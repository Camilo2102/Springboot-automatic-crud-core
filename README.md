# Java Library for CRUD Development with Spring Boot

This library provides an agile and straightforward solution for creating CRUD (Create, Read, Update, Delete) operations and their respective endpoints in Spring Boot applications using annotations, available in Sonatype Maven Central:

https://central.sonatype.com/artifact/cloud.webgen.web.starter/web-starter

## Main Features

- **Automatic CRUD Endpoint Generation**: With a simple annotation, the endpoints for handling CRUD operations of an entity are automatically created.
- **Generic CRUD Implementation**: A generic CRUD service transparently handles CRUD operations for any type of entity.
- **SQL and NoSQL Support**: Thanks to the `AuditRepository` interface, it can be used with SQL or NoSQL repositories.
- **Base Audit Model**: The `BaseAuditModel` class provides a base structure for audit handling and common fields.

## Example implementation

- **SQL + JPA implementation** , this implementation uses the jpa library and provides the components required for allow his use with SQL databases:

https://github.com/Camilo2102/web-starter-sql

- **MongoDB implementation** , this implementation uses the mongo library and provides the components required for allow his use with MongoDB:

https://github.com/Camilo2102/web-starter-nosql

## Core Components

The library consists of the following core components:

1. `@AutoControlable` annotation
2. `ApiController` class
3. `CrudService` class
4. `AuditRepository` interface
5. `BaseAuditModel` class

## Usage

1. Create an entity class that extends `BaseAuditModel` and annotate it with `@AutoControlable`.
2. Define a repository for the entity that implements `AuditRepository`.
3. Inject the repository into your Spring Boot application.
4. The library will automatically generate the CRUD endpoints for your entity.


