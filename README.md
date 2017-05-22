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

- How many files can we handle per request?
  For this version, only two files are allow, it means we can calculate: diff file1 file2. This two files are mandatory

- What are we going to do if diff operation is called several times for the same ID?
  We have two options:
  1. We can save every diff calculation done for the same ID and return the latest one
  2. We can discard old diff results and just saved the latest one (Let's do this)

## Overview

![alt text](https://github.com/jjbustamante/scalable-web/blob/master/overview.png)

Previous image shows an overview with the main components:

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

## How to check the database

The database implementation is using InMemory H2 db, also the migration schema was done using liquibase. 
To access the database just open a browser with the following url: http://localhost:8080/h2-console once the application is running

Use the following parameters to connect:

*Driver Class:	 org.h2.Driver*
*JDBC URL:	 jdbc:h2:mem:testdb*
*User Name: sa*

## REST API Documentation

I tried to configured the REST documentation using swagger, but unfortunately it didn't work, I think some problem with the configuration. The swagger-iu is available at: http://localhost:8080/swagger-ui.html when the application is running.

## How can we do some test?

For testing I used curl. 

### Create diff ID

For creating the diff ID run: *curl -v -X POST http://localhost:8080/v1/diff* it should return a json object similar to: 
```json
{ "id":"1",
  "file1":null,
  "file2":null,
  "differences":null,
  "method":null
}
```
### Execute diff operation 
For executing a diff operation run: 

*curl -v  -H "Content-Type:multipart/mixed" -F files[0]=@{file1} -F files[1]=@{file2}  http://localhost:8080/v1/diff/{id}/{method}* 

where: 
 - id : Diff id obtained from previous command
 - file1 = path to your file in your local environment
 - file2 = path to your file in your local environment
 - method = left or right 
For example:
*curl -v  -H "Content-Type:multipart/mixed" -F files[0]=@test0.txt -F files[1]=@test0.txt  http://localhost:8080/v1/diff/1/left*

The Endpoints are configured to also received Json data into the same request, but, this option was only implemented to show the option, this json data is optional. if you want to test it, we can add to the previous command the following parameter:

*-F "meta={\"application\":\"curl\",\"author\":\"Juan Bustamante\"};type=application/json"*

This will send the JSON data
{
  applicatin: "curl",
  author: "Juan Bustamante"
}
As part of the request.

### Execute get operation to retrieve a diff result

For retrieving the result for a given diff calculation execute the following command:

*curl -v -X GET http://localhost:8080/v1/diff/{id}*

where: 
 - id : Diff id obtained from POST command
 
Response examples:

*Files are equal*
```json
{ "id":"1",
  "file1":"test0.txt",
  "file2":"test2.txt",
  "differences":["Files are Equal"],
  "method":"LEFT"
}
```

*Files are not equal size*
```json
{ "id":"1",
  "file1":"test0.txt",
  "file2":"test2.txt",
  "differences":["Files are not equal size"],
  "method":"LEFT"
}
```

*Files are same size*
```json
{ "id":"1",
  "file1":"test0.txt",
  "file2":"test2.txt",
  "differences":["00000001 102 98","00000008 114 111","00000006 98 102","00000003 111 114","00000007 97 111","00000002 111 97"],
  "method":"LEFT"
}
```
In this case, for example *00000001 102 98* it means:
 - offset: 1 
 - length for data in file 1: 102
 - lenght for data in file 2: 98
