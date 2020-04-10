From adoptopenjdk/openjdk11:alpine-jre
copy ./target/*.jar app.jar
ENV EUREKA_SERVER_IP_PORT=discovery-service:8765
EXPOSE 8091
CMD ["java","-jar", "-DEUREKA_SERVER_IP_PORT=${EUREKA_SERVER_IP_PORT}","app.jar"]