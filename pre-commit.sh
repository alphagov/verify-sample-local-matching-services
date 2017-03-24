#!/usr/bin/env bash
set -e

./test_ruby.sh
./test_csharp.sh

echo "SUCCESS!"
