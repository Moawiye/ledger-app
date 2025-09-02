# Use OpenJDK 21 for Spring Boot
FROM openjdk:21-jdk-slim

# Install curl for health checks
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY backend/mvnw .
COPY backend/.mvn .mvn
COPY backend/pom.xml .

# Make mvnw executable
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY backend/src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Verify the JAR was created
RUN ls -la target/

# Set working directory to where the JAR is
WORKDIR /app

# Expose port
EXPOSE 8080

# Add health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8080/api/ledger || exit 1

# Run the application with proper JVM options
CMD ["java", "-Xmx512m", "-Xms256m", "-jar", "target/ledger-backend-0.0.1-SNAPSHOT.jar"]
