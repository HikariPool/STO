FROM openjdk:17

COPY /target/flat_assessment-*.jar /java/flat_assessment.jar
WORKDIR /java/

EXPOSE 6060

CMD ["java", "-jar", "/java/flat_assessment.jar"]