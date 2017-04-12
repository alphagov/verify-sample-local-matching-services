#!/usr/bin/env bash
set -e

lang="yada"
base=""
port=50139

function cleanup {
    curl http://localhost:50139/die -X delete
    sleep 0.1
    docker stop verify_sample_lms_${lang}
    docker rm -f verify_sample_lms_${lang}
}

docker build -t verify_sample_lms/${lang} .
docker run -d --name verify_sample_lms_${lang} -p ${port}:${port} verify_sample_lms/${lang}

trap cleanup EXIT

echo "waiting for server"
until $(curl --output /dev/null --silent --head --fail http://localhost:50139/healthcheck); do
   printf '.'
   sleep 0.5
done

cd "$(dirname "$0")"

(cd ../../local-matching-service-tests
mvn test -DMATCHING_URL=http://localhost:${port}${base}/matching-service -DUSER_ACCOUNT_CREATION_URL=http://localhost:${port}${base}/account-creation)
