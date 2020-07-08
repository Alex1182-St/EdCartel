FROM gradle:6.5.0-jdk11

WORKDIR /EdCartel

ADD . .

RUN gradle clean bootJar --no-daemon

EXPOSE 9696