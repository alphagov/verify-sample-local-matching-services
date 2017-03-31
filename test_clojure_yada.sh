#!/usr/bin/env bash
set -e

function cleanup() {
    curl http://localhost:50139/die -X delete
}

cd "$(dirname "$0")"

(cd clojure/yada
 lein run > lein-run.out &
 echo "waiting for server"
 until $(curl --output /dev/null --silent --head --fail http://localhost:50139/healthcheck); do
    printf '.'
    sleep 0.5
 done
 echo $! > yada_lms.pid)

trap cleanup EXIT

(cd local-matching-service-tests
mvn test -DMATCHING_URL=http://localhost:50139/matching-service -DUSER_ACCOUNT_CREATION_URL=http://localhost:50139/account-creation)
