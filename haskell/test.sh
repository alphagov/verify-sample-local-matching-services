#!/usr/bin/env bash

set -e

lang="haskell"
base="/${lang}"
port=3000

function cleanup {
    docker stop verify_sample_lms_${lang}
    docker rm -f verify_sample_lms_${lang}
}

cd "$(dirname "$0")"

docker build -t verify_sample_lms/${lang} .
docker run -d --name verify_sample_lms_${lang} -p ${port}:${port} verify_sample_lms/${lang}

trap cleanup EXIT

(cd ../local-matching-service-tests
mvn test -DMATCHING_URL=http://localhost:${port}${base}/matching-service -DUSER_ACCOUNT_CREATION_URL=http://localhost:${port}${base}/account-creation)
