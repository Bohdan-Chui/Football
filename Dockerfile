#FROM maven:3.8.4-jdk-11
#WORKDIR /airportRestful
#COPY . .
#RUN mvn clean install
##EXPOSE 8080
#CMD mvn spring-boot:run


FROM openjdk:11-jdk-slim
WORKDIR /app
COPY target/Football-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
