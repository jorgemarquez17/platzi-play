
# etapa 1: Build con Gradle 8.14.2 y JDK 21 (Compilacion)
FROM gradle:8.14.2-jdk-21 AS build
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle bootJar --no-deamon

# Etapa 2 : runtime con jdk 21 (Ejecucion)
FROM eclipse-temurin
WORKDIR  /app
COPY --from=build /app/build/libs/*.jar platzi_play.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.activate=prod","-jar","platzi_play.jar"]
