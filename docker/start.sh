#!/bin/bash

docker network create citygroove
cd ..
./mvnw clean install
# shellcheck disable=SC2164
cd docker
cp ../target/citygroove-0.0.1-SNAPSHOT.jar citygroove-0.0.1-SNAPSHOT.jar
docker container run --name mysqldb --network citygroove -p 3306:3306  -e MYSQL_USER=sa -e MYSQL_ROOT_PASSWORD=sa -e MYSQL_DATABASE=citygroove -d mysql:8
echo "sleep 15 sec, waiting mysql to start ..."
sleep 15
echo "wake up"
docker image build -t citygroove-spring-boot .
docker container run --network citygroove --name citygroove-spring-boot -p 8080:8080 -d citygroove-spring-boot
echo "spring boot server should start in around 20 sec"
sleep 20
echo "City Groove started ..."