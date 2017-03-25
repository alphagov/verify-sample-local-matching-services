#!/usr/bin/env bash

set -e

function cleanup {
  pid="$(<./csharp/csharp.pid)"

  # dotnet run starts a child process, but neglects to kill it when it receives a SIGTERM
  # explicitly kill its child processes first:
  pkill -P $pid
  kill $pid

  rm -f ./cshrp/csharp.pid
  rm -f ./csharp/dotnet-run.out
}

cd "$(dirname "$0")"

(
cd csharp
dotnet restore
dotnet run > dotnet-run.out &
echo $! > csharp.pid
until grep -m 1 'Application started' dotnet-run.out; do sleep 0.1; done
)

trap cleanup EXIT

(
cd local-matching-service-tests
mvn test -DMATCHING_URL=http://localhost:5000/csharp/matching-service -DUSER_ACCOUNT_CREATION_URL=http://localhost:5000/csharp/account-creation
)

