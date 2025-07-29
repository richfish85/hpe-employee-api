# ---------- BUILD STAGE ----------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml first to leverage Docker cache
COPY pom.xml .
RUN mvn -B -q -e -DskipTests dependency:go-offline

# Copy source and build
COPY src ./src
RUN mvn -B -DskipTests package

# ---------- RUNTIME STAGE ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

# Create non-root user for container security
RUN useradd -r -u 10001 appuser
USER appuser

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Optional: expose configurable port
ENV SERVER_PORT=8080
EXPOSE 8080

# Healthcheck (optional but good)
HEALTHCHECK --interval=30s --timeout=3s --retries=5 \
  CMD wget -qO- http://localhost:${SERVER_PORT}/actuator/health || wget -qO- http://localhost:${SERVER_PORT}/v3/api-docs || exit 1

# Run the app
ENTRYPOINT ["java","-jar","/app/app.jar"]
