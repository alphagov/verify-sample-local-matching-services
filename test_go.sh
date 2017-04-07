#!/usr/bin/env bash

set -e

function cleanup {
    docker stop verify_sample_lms_go
}

docker build -t verify_sample_lms/go go
docker run -d --rm --name verify_sample_lms_go -p 8080:8080 verify_sample_lms/go

trap cleanup EXIT

(cd local-matching-service-tests
mvn test -DMATCHING_URL=http://localhost:8080/matching-service -DUSER_ACCOUNT_CREATION_URL=http://localhost:8080/account-creation)

