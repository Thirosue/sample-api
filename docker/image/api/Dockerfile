FROM openjdk:8-alpine

MAINTAINER takeshi.hirosue@bigtreetc.com

VOLUME /tmp

COPY sample-web-admin.jar /

EXPOSE 18081
ENTRYPOINT ["java","-Dspring.datasource.url=jdbc:mysql://sample-db:3306/sample?useSSL=false&characterEncoding=UTF-8","-Djava.security.egd=file:/dev/./urandom","-jar","./sample-web-admin.jar"]