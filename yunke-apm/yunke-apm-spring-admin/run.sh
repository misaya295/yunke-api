#!/usr/bin/env bash
mvn package

docker build -t yunke-apm-spring-admin .