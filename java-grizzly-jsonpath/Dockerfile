FROM openjdk:8-jdk-alpine

RUN wget http://ftp.fau.de/apache/maven/maven-3/3.5.0/binaries/apache-maven-3.5.0-bin.tar.gz
RUN tar -zxvf apache-maven-3.5.0-bin.tar.gz
RUN rm apache-maven-3.5.0-bin.tar.gz
RUN mv apache-maven-3.5.0 /usr/lib/mvn

ENV M2_HOME=/usr/lib/mvn
ENV M2=$M2_HOME/bin
ENV PATH $PATH:$JAVA_HOME:$JAVA:$M2_HOME:$M2

WORKDIR /usr/src/java-grizzly-jsonpath/

COPY ./ /usr/src/java-grizzly-jsonpath/

RUN mvn clean package

EXPOSE 8080

ENV JGS_HOST=0.0.0.0

CMD java -jar target/java-grizzly-jsonpath-1.0-SNAPSHOT-capsule.jar
