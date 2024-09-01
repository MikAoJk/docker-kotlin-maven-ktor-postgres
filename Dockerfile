FROM gcr.io/distroless/java21-debian12
WORKDIR /app
COPY target/*-jar-with-dependencies.jar app.jar
ENV JAVA_OPTS="-Dlogback.configurationFile=logback.xml"
ENV TZ="Europe/Oslo"
EXPOSE 8080
USER nonroot
CMD [ "app.jar" ]
