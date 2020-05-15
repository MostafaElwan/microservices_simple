From adoptopenjdk/openjdk11:alpine-jre
copy ./target/*.jar app.jar
copy wait-for.sh wait-for.sh
RUN chmod +x wait-for.sh
EXPOSE 8765
CMD ["java","-jar","app.jar"]