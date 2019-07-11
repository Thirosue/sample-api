FROM openjdk:8-alpine

MAINTAINER takeshi.hirosue@bigtreetc.com

VOLUME /tmp

COPY sample-web-admin.jar /

EXPOSE 18081
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./sample-web-admin.jar"]