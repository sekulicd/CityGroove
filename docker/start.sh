#!/bin/bash

cd ..
./mvnw clean install
# shellcheck disable=SC2164
cd docker
cp ../target/citygroove-0.0.1-SNAPSHOT.jar citygroove-0.0.1-SNAPSHOT.jar
docker image build -t city-groove .
