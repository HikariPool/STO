FROM openjdk:17

COPY /target/STO-*.jar /java/STO.jar
WORKDIR /java/

EXPOSE 6060

CMD ["java", "-jar", "/java/STO.jar"]