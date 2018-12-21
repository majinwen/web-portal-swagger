FROM java:8
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
COPY  ./deploy/target/web.deploy-1.0-SNAPSHOT.jar /usr/share

EXPOSE 8085
ENTRYPOINT java -jar /usr/share/web.deploy-1.0-SNAPSHOT.jar
