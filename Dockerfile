FROM java:8
COPY  ./deploy/target/web.deploy-1.0-SNAPSHOT.jar /usr/share

EXPOSE 8085
ENTRYPOINT java -jar /usr/share/web.deploy-1.0-SNAPSHOT.jar
