FROM openjdk:8-alpine
COPY ./* /app/
WORKDIR /app/
RUN javac -d ./output ./my/path/Word_anzer.java
WORKDIR /app/output