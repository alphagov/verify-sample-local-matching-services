#!/usr/bin/env bash
set -e

function cleanup() {
    kill "$(<./nancy/nancy_lms.pid)" && rm -f ./nancy/nancy_lms.pid
}

cd "$(dirname "$0")"

(cd nancy
 nuget restore
 xbuild
 mono ./bin/Debug/lms.exe&
 echo $! > nancy_lms.pid)

trap cleanup EXIT

(cd local-matching-service-tests
mvn test -DMATCHING_URL=http://localhost:9991/nancy/matching-service -DUSER_ACCOUNT_CREATION_URL=http://localhost:9991/nancy/account-creation)



