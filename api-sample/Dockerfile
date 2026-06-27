# Estágio 1: Compilar a aplicação usando a imagem oficial do Java 25
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app
COPY . .
# Dando permissão de execução para o wrapper do Maven do seu projeto e compilando
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Estágio 2: Rodar a aplicação de forma leve
FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]