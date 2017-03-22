#!/usr/bin/env bash
set -e

function cleanup() {
    kill "$(<./ruby/ruby_lms.pid)" && rm -f ./ruby/ruby_lms.pid
}

cd "$(dirname "$0")"

(cd ruby
 bundle check || bundle install
 bundle exec ./lms.rb&
 echo $! > ruby_lms.pid)

trap cleanup EXIT

(cd local-matching-service-tests
mvn test -DMATCHING_URL=http://localhost:50139/ruby/matching-service -DUSER_ACCOUNT_CREATION_URL=http://localhost:50139/ruby/account-creation)



