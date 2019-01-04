FROM maven:3.6.0-jdk-8 

COPY WebContent /Academic-Teaching-Affair-Management-System/WebContent
COPY src /Academic-Teaching-Affair-Management-System/src
COPY pom.xml /Academic-Teaching-Affair-Management-System/

RUN cd /Academic-Teaching-Affair-Management-System && \
    mvn clean install package

FROM tomcat:9-jre8

COPY --from=0 /Academic-Teaching-Affair-Management-System/target/Academic-Teaching-Affair-Management-System.war $CATALINA_HOME/webapps/
