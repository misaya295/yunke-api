FROM openjdk:8u212-jre
MAINTAINER chachae chachae@foxmail.com

COPY ./target/yunke-core-1.0.jar /yunke/yunke-core-1.0.jar
ADD agent/ /agent

ENTRYPOINT ["java", "-javaagent:/agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=yunke-core", "-Dskywalking.collector.backend_service=skywalkingIp:11800", "-jar", "/yunke/yunke-core-1.0.jar"]