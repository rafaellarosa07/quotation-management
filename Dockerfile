FROM openjdk:17-alpine
EXPOSE 8081
ARG JAR_FILE=target/quotation-management-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} quotation-management.jar
ENTRYPOINT ["java","-jar","/quotation-management.jar"]

