From adoptopenjdk/openjdk11:alpine-jre
copy ./target/*.jar app.jar
ENV EUREKA_SERVER_IP_PORT=discovery-service:8765
EXPOSE 8888
CMD ["java","-jar","app.jar"]