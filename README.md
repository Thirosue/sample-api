# Spring Boot Api Sample

clone to

https://github.com/miyabayt/spring-boot-doma2-sample

and api implements

## required

* Docker

## Db Setup

```
$ cd /path/to/root/docker
$ docker-compose up -d
Starting mysql ... done
$ docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                              NAMES
caa5314942cd        docker_mysql        "docker-entrypoint.s…"   About an hour ago   Up 1 second         0.0.0.0:3306->3306/tcp             mysql
```

## Run App

```
$ cd /path/to/root
$ ./gradlew :sample-web-admin:bootRun
```

## Swagger

http://localhost:18081/admin/swagger-ui.html