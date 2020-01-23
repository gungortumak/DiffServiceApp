# Gungor Tumak Assesment Diff Service

####  Tech Stack:

- java 1.8
- Spring Boot 2.2.3.RELEASE.
- Maven.
- Spring Cache
- H2 db.


####  Run Application:

java -jar target/diff-0.0.1-SNAPSHOT.jar

### Configuration

Log path can be configured in application.properties

logging.file.path=logs
logging.file.name=${logging.file.path}/myapp.log

H2 db console can be activated in application.properties

spring.h2.console.enabled=true

log level can be configured in logback.xml


####  URLS:

http://HOST:PORT/v1/diff/{diffId]/right
http://HOST:PORT/v1/diff/{diffId]/left
http://HOST:PORT/v1/diff/{diffId]

SWAGGER UI : http://localhost:8080/swagger-ui.html
H2 Console : http://localhost:8080/h2-console

Default HOST:PORT = localhost:8080

### SAMPLE POSTTMAN COLLECTION

/test/data/diffSample.postman_collection.json

JSON POST REQUEST:

- http://HOST:PORT/v1/diff/{diffId]/right  **POST** 

{
   "data" : "MTQ5ODMxMjkzOTQ5OTY3ODQ1"
}

JSON POST REQUEST:

- http://HOST:PORT/v1/diff/{diffId]/left  **POST** 

{
   "data" : "MTQ5ODMxMjkzOTQ5OTY3ODQ1"
}

GET REQUEST:

-http://HOST:PORT/v1/diff/1

JSON RESPONSE

{
    "result": "EQUALS",
    "diffInformation": []
}

### Assumptions

- Binary data is encoded in base64 format in a field in JSON.
- No need to keep base64 data since it is larger than binary data.
- For multible instances of same micro services, common h2 db will be used.
- Sides can be updated with apis.
- no manual change is allowed in db, because result in cached in spring cache.

### IMPROVEMENTS 

- dockerizing and scaling with multiple instances
- Keeping binary data in cloud storage like S3 and diff model in document based data store.
- Using Serverless architecture to find diffs(another approach other than containers)
- improving unit and integration tests
- Caching improvement for diff result
- Exception handling enhancements, basic since there is only one controller.
- Code documentation enhancements
- Logging enhancements

