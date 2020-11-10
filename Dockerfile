FROM hax0rcorp/java11gradle:latest

COPY . /app
WORKDIR /app

ENV GRADLE_OPTS=""

RUN mkdir builds && gradle build && \
    mv application/build/libs/*.jar builds/build.jar

EXPOSE 8080 4545

CMD ["java", "-jar", "builds/build.jar"]
