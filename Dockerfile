FROM gradle:6.2.2-jdk11

WORKDIR /EdCartel

ADD . .

RUN gradle clean bootJar --no-daemon

EXPOSE 9696