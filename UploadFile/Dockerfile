# Sử dụng OpenJDK 17 chính thức làm base image
FROM openjdk:17-jdk-alpine

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Copy file JAR của ứng dụng vào container
COPY target/UploadFile.jar /app/UploadFile.jar

# Thiết lập biến môi trường để chạy ứng dụng
ENV SPRING_DATASOURCE_URL=jdbc:mysql://127.0.0.1:3306/tcom
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=123456

# Lệnh để chạy ứng dụng
ENTRYPOINT ["java", "-jar", "/app/UploadFile.jar"]

# Expose cổng mà ứng dụng sử dụng
EXPOSE 8080
