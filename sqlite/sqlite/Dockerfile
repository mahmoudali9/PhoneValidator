FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/sqlite.jar /usr/local/lib/sqlite.jar
ADD sample.db sample.db
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/sqlite.jar"]

#FROM maven:3.5-jdk-8 AS build
#COPY src /usr/src/app/src
#COPY pom.xml /usr/src/app
#RUN mvn -f /usr/src/app/pom.xml clean package
#
#FROM gcr.io/distroless/java
#COPY --from=build /usr/src/app/target/sqlite.jar /usr/app/sqlite.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/usr/app/sqlite.jar"]
