# Use OpenJDK 21 for Spring Boot
FROM openjdk:21-jdk-slim

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

# Run the application (using the correct path)
CMD ["java", "-jar", "target/ledger-backend-0.0.1-SNAPSHOT.jar"]
