# Brainshare Api

Small and simple online educational platform. This is an API part. Only REST server on Spring boot.

## Prerequisites

- Java 8 installed
- MySQL >5.7.8 running anywhere

## Usage

- run `database_full_setup.sql` on any your MySQL instance to create schema, triggers, functions and procedures, plus insert needful data;
    - i've tried to do this by `sql-maven-plugin` or with Spring DDL creation on startup, but had a delimiters' problem any time...
- set your datasource properties in `application.properties`;
- *[Optional]* You can regenerate table classes and their POJO classes prom just created scema by `mvn initialize jooq-codegen:generate`;
- run project by your IDE or just `mvn spring-boot:start`.
