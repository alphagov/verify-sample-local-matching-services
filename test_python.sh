#!/usr/bin/env bash
set -e

function cleanup() {
    kill "$(<./python/python_lms.pid)" && rm -f ./python/python_lms.pid
}

cd "$(dirname "$0")"

(cd python
 ./lms -p 50139 &
 echo $! > python_lms.pid)

trap cleanup EXIT

(cd local-matching-service-tests
mvn test -DMATCHING_URL=http://localhost:50139/matching-service -DUSER_ACCOUNT_CREATION_URL=http://localhost:50139/account-creation)



