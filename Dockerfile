# Step 1: Use an official OpenJDK base image from Docker Hub
FROM amazoncorretto:17-alpine3.22

# Step 2: Set build arguments for user, UID, and GID
ARG USER=problem-solving-journal
ARG UID=10001
ARG GID=10001

# Step 3: Create group with GID and user with UID, assign home directory
RUN addgroup -g ${GID} ${USER} && \
    adduser -D -u ${UID} -G ${USER} -h /home/${USER} ${USER}

# Step 4: Set HOME environment variable
ENV HOME=/home/${USER}

# Step 5: Set the working directory inside the container
WORKDIR ${HOME}

# Step 6: Copy the Spring Boot JAR file into the container 
COPY target/*.jar /app/app.jar

# Step 7: Give ownership of /app and $HOME to the non-root user
RUN chown -R ${USER}:${USER} /app && chown -R ${USER}:${USER} ${HOME}

# Step 8: Expose the port your application runs on
EXPOSE 8080

# Step 9: Switch to non-root user
USER ${USER}
WORKDIR ${HOME}

# Step 10: Define the command to run your Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

