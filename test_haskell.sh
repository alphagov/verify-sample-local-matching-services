#!/usr/bin/env bash
set -e

function cleanup() {
  pid="$(<./haskell/haskell_lms.pid)"

  # cabal run starts a child process, but neglects to kill it when it receives a SIGTERM
  # explicitly kill its child processes first:
  pkill -P $pid
  kill $pid

  rm -f ./haskell/haskell_lms.pid
}

cd "$(dirname "$0")"

(cd haskell
 cabal configure
 cabal build
 cabal run &
 echo $! > haskell_lms.pid)

trap cleanup EXIT

(cd local-matching-service-tests
mvn test -DMATCHING_URL=http://localhost:3000/haskell/matching-service -DUSER_ACCOUNT_CREATION_URL=http://localhost:3000/haskell/account-creation)



