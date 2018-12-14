FROM java:8
LABEL maintainer="Nomikos Mouzourakis"
LABEL mail1="mouzmike [at] gmail.com"
LABEL mail2="nmouzourakis [at] ubitech.eu"
RUN apt-get update -qq -y
RUN apt-get install -qq -y nmap
COPY target/nmap-service-0.0.1-SNAPSHOT.jar .
EXPOSE 7777
ENTRYPOINT ["java", "-jar", "nmap-service-0.0.1-SNAPSHOT.jar"]
