#!/usr/bin/env bash
set -e

./test_go.sh
./test_ruby.sh
./test_csharp.sh
./test_nancy.sh
./test_haskell.sh
./test_python.sh
./test_visualbasic.sh
./test_clojure_yada.sh

echo "SUCCESS!"
