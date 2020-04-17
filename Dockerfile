FROM openjdk:8
VOLUME /tmp
ADD ./target/microservice_product-0.0.1-SNAPSHOT.jar microservice_product.jar
ENTRYPOINT ["java","-jar","/microservice_product.jar"]