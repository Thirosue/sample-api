# Spring Boot Api Sample

[![Build Status](https://travis-ci.org/Thirosue/sample-api.svg?branch=master)](https://travis-ci.org/Thirosue/sample-api)
![Build Status](https://codebuild.us-east-1.amazonaws.com/badges?uuid=eyJlbmNyeXB0ZWREYXRhIjoiLzBYNXJjbWhTUTl1UzMvSGlmVU9hSVRRWnRzN2NjR0JQNjU2WW1la3dJTExNQWNaTE5jTlhabUlTS2FqY0F3SGZIRG5CM2MxN2tKSUFwWW54SzFwOVE0PSIsIml2UGFyYW1ldGVyU3BlYyI6IkFibDUvbnhrSG44YXZZdUciLCJtYXRlcmlhbFNldFNlcmlhbCI6MX0%3D&branch=master)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

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