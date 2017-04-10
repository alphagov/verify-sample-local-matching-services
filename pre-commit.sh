#!/usr/bin/env bash
set -e

./go/test.sh
./ruby/test.sh
./csharp/test.sh
./nancy/test.sh
./haskell/test.sh
./python/test.sh
./visualbasic/test.sh

echo "SUCCESS!"
