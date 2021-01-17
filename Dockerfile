FROM openjdk:11-jre
COPY ./build/libs/layouts.jar /app/layouts.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/layouts.jar"]
