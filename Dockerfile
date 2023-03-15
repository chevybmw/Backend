FROM amazoncorretto:11
MAINTAINER marcosnatiello
COPY target/N_M-0.0.1-SNAPSHOT.jar N_M-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/N_M-0.0.1-SNAPSHOT.jar"] 
