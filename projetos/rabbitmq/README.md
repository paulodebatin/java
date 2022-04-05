# rabbirmq-java

Projeto Java que demonstra enviar e receber mensagens do RabbitMQ.

### Para testar
Inicie um servidor RabbitMQ. Recomendo um docker.
cd rabbirmq-java
mvn install
Enviar: java -cp target/rabbitmq-java-0.0.1-SNAPSHOT-jar-with-dependencies.jar br.com.mensageria.rabbitmq.Send
Consumir: java -cp target/rabbitmq-java-0.0.1-SNAPSHOT-jar-with-dependencies.jar br.com.mensageria.rabbitmq.Consumer

### Tecnologias utilizadas:
* Java 8
* RabbitMQ


