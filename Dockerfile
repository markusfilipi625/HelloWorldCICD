From tomcat:8.0.51-jre8-alpine
RUN rm -rf /usr/local/tomcat/webapps/*
VOLUME /tmp
EXPOSE 8080:8080
COPY /target/HelloWorldCICD-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/HelloWorldCICD.war
CMD ["catalina.sh","run"]