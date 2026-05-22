FROM eclipse-temurin:21

COPY target/otp-login-app-1.0.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
