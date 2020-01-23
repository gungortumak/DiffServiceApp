# Gungor Tumak Assesment Diff Service

####  Tech Stack:

- java 1.8
- Spring Boot 2.2.3.RELEASE.
- Maven.
- Spring Cache
- H2 db.



####  Run Application:

java -jar target/diff-0.0.1-SNAPSHOT.jar

####  URLS:

http://localhost:8080/v1/diff/{diffId]/right
http://localhost:8080/v1/diff/{diffId]/left
http://localhost:8080/v1/diff/{diffId]

SWAGGER UI : http://localhost:8080/swagger-ui.html
H2 Console : http://localhost:8080/h2-console


JSON POST REQUEST:

- http://localhost:8080/v1/diff/{diffId]/right  **POST** 

{
   "data" : "MTQ5ODMxMjkzOTQ5OTY3ODQ1"
}

JSON POST REQUEST:

- http://localhost:8080/v1/diff/{diffId]/left  **POST** 

{
   "data" : "MTQ5ODMxMjkzOTQ5OTY3ODQ1"
}

GET REQUEST:

-http://localhost:8080/v1/diff/1

JSON RESPONSE

{
    "result": "EQUALS",
    "diffInformation": []
}

