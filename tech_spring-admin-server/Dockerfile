From adoptopenjdk/openjdk11:alpine-jre
copy ./target/*.jar app.jar
copy wait-for.sh wait-for.sh
RUN chmod +x wait-for.sh
ENV EUREKA_SERVER_IP_PORT=eureka-hostname:8765
EXPOSE 9999
CMD ["java","-jar","app.jar"]