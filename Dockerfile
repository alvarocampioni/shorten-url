FROM openjdk:23
COPY ./target/urlshorten-0.0.1-SNAPSHOT.jar /app/urlshorten-0.0.1-SNAPSHOT.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "urlshorten-0.0.1-SNAPSHOT.jar"]
EXPOSE 3000
