#!/usr/bin/env bash
set -e

function cleanup() {
    kill "$(<./csharp/csharp_lms.pid)" && rm -f ./csharp/csharp_lms.pid
}

cd "$(dirname "$0")"

(cd csharp
 type nuget > /dev/null
 if [ $? -eq 0 ]; then
     nuget restore
 else
     mono nuget.exe restore
 fi 
 xbuild
 mono ./bin/Debug/lms.exe&
 echo $! > csharp_lms.pid)

trap cleanup EXIT

(cd local-matching-service-tests
mvn test -DMATCHING_URL=http://localhost:9991/csharp/matching-service -DUSER_ACCOUNT_CREATION_URL=http://localhost:9991/csharp/account-creation)



