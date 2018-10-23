# springboot-rabbitmq-pub
Example Java Spring Boot application which can run locally and on Cloud Foundry. Main focus is to show how the publisher subscriber communication model works using RabbitMQ. 

## Run locally
* `docker pull rabbitmq:3.7.7-management`
* `docker run -d -p 15672:15672 -p 5672:5672 rabbitmq:3-management`
* `PORT=3000 mvn clean spring-boot:run`

## Run on Cloud Foundry
* `mvn clean install -DskipTests`
* create a rabbitmq service in Cloud Foundry named `rabbitmq`
* `cf push`

## Run integration tests
* `docker pull rabbitmq:3-management`
* `docker run -d -p 15672:15672 -p 5672:5672 rabbitmq:3-management`
* `mvn test` to run explicitly. Running `mvn install` will also run them.
