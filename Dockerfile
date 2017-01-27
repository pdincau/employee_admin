FROM pdincau/workshop_java_8

RUN mkdir /app

COPY target/employee_admin-1.0-SNAPSHOT.jar /app
WORKDIR /app

EXPOSE 8080

CMD ["java" , "-jar", "employee_admin-1.0-SNAPSHOT.jar"]
