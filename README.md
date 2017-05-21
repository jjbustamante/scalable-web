# scalable-web
This is a microservice develop as part of an assignment.

## Requirements

Provide 2 http endpoints that accepts JSON base64 encoded binary data on both endpoints
- <host>/v1/diff/<ID>/left and <host>/v1/diff/<ID>/right
  - The provided data needs to be diff-ed and the results shall be available on a third end point
- <host>/v1/diff/<ID>
  - The results shall provide the following info in JSON format
    - If equal return that
    - If not of equal size just return that
    - If of same size provide insight in where the diffs are, actual diffs are not needed. (So mainly offsets + length in the data)

## Build Requirements

Before being able to build he code you need:
- Maven 3.3+ 
- Java  1.8

## How to run Unit Test
After you have installed Building requirements tool:

- Open a console and go to the folder where you cloned the repo
- run: *mvn test*

After maven download all the dependencies need it, you should see a success message where everything was one

## How to run Integration Test

- Open a console and go to the folder where you cloned the repo
- run: *mvn verify*

## How to run code locally

- Open a console and go to the folder where you cloned the repo
- run: *mvn spring-boot:run*
- After everything is fine, the microservice should be running in your http://localhost:8080/v1/diff 

## REST API Documentation
