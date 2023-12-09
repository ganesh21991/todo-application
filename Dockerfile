FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8080
ADD todo-build/target/todo-application-0.0.1-SNAPSHOT.jar todo-application-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/todo-application-0.0.1-SNAPSHOT.jar"]

