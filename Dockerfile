FROM java:8
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
COPY  ./deploy/target/ad.deploy-2.0.jar /usr/share

EXPOSE 8084
ENTRYPOINT java -jar /usr/share/web.deploy-1.0-SNAPSHOT.jar
