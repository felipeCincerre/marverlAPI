# marverlAPI

This is a backend system in Java that retrieves IDs some information about Marvel's characters. 
It has integration with the Marvel API to get some information about characters.

## Pre requirements

1. Java 8
2. Maven 3.5
4. Marvel account

## Installation

### Marvel

You need to create 2 environment variables:

1. api_private_key (add your Marvel private key)
2. api_public_key (add your Marvel public key)

Important: Do not forget to reboot your machine after creating the environment variables.

### Maven Dependencies

1. spring-boot-starter-web
2. lombok
3. spring-cloud-starter-openfeign
4. spring-boot-starter-test
5. java-google-speech-api 
6. junit
7. junit-jupiter-api
8. junit-jupiter-params
9. junit-jupiter-engine
10. junit-platform-launcher
11. junit-platform-commons

### Maven Repositories

1. https://jitpack.io

### Build and Run

To build the system using maven, it is needed to run the maven command:

```
mvn install
```

To run the system using maven, it is needed to run the maven command: 

```
mvn spring-boot:run
```

The system runs in port 8080 (http://localhost:8080)

## Module information

### Character

The character module is composed by 2 GET APIs.

The basic actions runs on the endpoints:

1. GET `/characters` - get all charactes' ids
2. GET `/characters/{id}?language={languageCode}` - get a specific character. If languageCode is sent, then description will be tranlasted into the selected language.
