FROM gradle:6.1-jdk11

WORKDIR /EdCartel

ADD . .

RUN gradle  --no-daemon clean build bootJar

EXPOSE 8080