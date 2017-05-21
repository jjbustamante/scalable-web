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

## Assumptions

What are we doing? We are creating a microservice to calculate diff between files, this microservice can be used by another microservice or system. We can asume it is important to save information regarding who is making the call to the API (client)

- What are we going to do with the ID? 
  We have two options:	
  1. The client (the service that is going to call this microservice) decides which is the id and send it back, our solution can just received the id and deal with it.
	2. We expose an API to create the id and the client must call this API first to create the diff instance (Let's do this)

- What are we going to do with the files?
  We have two options:
	1. We can discard the files because real diff is not need it
  2. We can save the files for later used (Let's do this)

- What are we going to do if diff operation is called several times for the same ID?
  We have two options:
  1. We can save every diff calculation done for the same ID and return the latest one
  2. We can discard old diff results and just saved the latest one (Let's do this)

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
