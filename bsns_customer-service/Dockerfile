From adoptopenjdk/openjdk11:alpine-jre
copy ./target/*.jar app.jar
copy wait-for.sh wait-for.sh
RUN chmod +x wait-for.sh
ENV EUREKA_SERVER_IP_PORT=eureka-hostname:8765
ENV ZIPKIN_SERVER_IP_PORT=zipkin-hostname:9411
EXPOSE 8092
CMD ["java","-jar","app.jar"]