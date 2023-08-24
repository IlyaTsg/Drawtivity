FROM tomcat:9.0.79
WORKDIR /usr/local/tomcat/webapps/
ADD api.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]