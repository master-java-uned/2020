FROM redeskako/tomcat-mysql

ENV DEBIAN_FRONTEND noninteractive
ENV TOMCAT_MAJOR_VERSION=11
#ENV TOMCAT_VERSION=8.5.14
ENV TOMCAT_HOME=/usr/local/tomcat
ENV MYSQL_DATABASE=covid
ENV MYSQL_ROOT_PASSWORD=123456
ENV MYSQL_USER=coviduser
ENV MYSQL_PASSWORD=123456

RUN apt-get update && \
  apt-get upgrade -y

WORKDIR /tmp

WORKDIR /

ADD bind_0.cnf /etc/mysql/conf.d/bind_0.cnf
ADD mysql-run.sh /mysql-run.sh
ADD supervisord-mysql.conf /etc/supervisor/conf.d/supervisord-mysql.conf
ADD settings.xml $TOMCAT_HOME/conf/settings.xml
ADD tomcat-users.xml $TOMCAT_HOME/conf/tomcat-users.xml
ADD context.xml $TOMCAT_HOME/webapps/manager/META-INF/context.xml
RUN chmod 755 /*.sh

COPY ./target/coviddivstats.war /usr/local/tomcat/webapps/

VOLUME ["/var/lib/mysql"]

EXPOSE 3306 8080

ENTRYPOINT ["/run.sh"]
