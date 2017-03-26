#!/usr/bin/env bash
set -e

./test_ruby.sh
./test_csharp.sh
./test_haskell.sh


echo "SUCCESS!"
