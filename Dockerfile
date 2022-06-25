FROM openjdk:17-alpine

RUN apk add --no-cache maven

WORKDIR /opt/bank-account

COPY . .

RUN mvn package -Dmaven.test.skip=true

ENTRYPOINT ["java", "-jar","target/bank-account-0.0.1-SNAPSHOT.jar"]