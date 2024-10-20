# REST APIs for Inventory Management

## Overview
A REST API project using Spring Boot framework. 

#### Included APIs
- Stock Management: Create and update stocks/inventories.
- Order Management: Create and update orders.
- Auditing APIs: Stock movement auditing and logging.

#### Included Spring Boot Concepts
- Spring Web APIs
- Spring JPA
- Spring AOP
- Exception Handling
- Response Handling
- In-Memory Database
- Unit Testing

## Setting up development environment

- Clone git repository
- Open repo code in your IDE (IntelliJ, VS Code etc)
- Build and install dependencies

For Linux/Mac
````
./gradlew build --refresh-dependencies
````

For Windows
````
gradlew build --refresh-dependencies
````

- Run unit tests
For Linux/Mac
````
./gradlew test
````

For Windows
````
gradlew test
````

## Running and testing APIs locally

- Run gradle run command
For Linux/Mac
````
./gradlew bootRun
````

For Windows
````
gradlew bootRun
````

- Open browser and open api-docs url http://localhost:8080/api-docs


## Deploying APIs to GCP Cloud using gcloud cli

- Initialize gcloud project
```
gcloud init
```
- Enable Cloud Run and Build APIs
```
gcloud services enable run.googleapis.com cloudbuild.googleapis.com
```

- Grant Cloud Build Service Account role for your project
````
gcloud projects add-iam-policy-binding PROJECT_ID \
    --member=serviceAccount:PROJECT_NUMBER-compute@developer.gserviceaccount.com \
    --role=roles/cloudbuild.builds.builder
````

- Deploy application from source
```
gcloud run deploy --source .
```