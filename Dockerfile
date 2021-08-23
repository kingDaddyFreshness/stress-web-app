FROM tomcat:jdk8

LABEL maintainer=”john.hilgedick@gmail.com”

ADD target/test-service-webapp.war /usr/local/tomcat/webapps/

EXPOSE 8080
