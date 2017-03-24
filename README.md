# verify-sample-local-matching-services

[![Build Status](https://travis-ci.org/alphagov/verify-sample-local-matching-services.svg?branch=master)](https://travis-ci.org/alphagov/verify-sample-local-matching-services)

A variety of example Local Matching Services for use with GOV.UK Verify

**NOTE:** These are not intended for production use and have been written for learning and development of the Verify tech team.

## What is a Local Matching Service?

There is [documentation about Local Matching Services](https://alphagov.github.io/rp-onboarding-tech-docs/pages/ms/ms.html) available

## Tests

There are some simple tests within this repository that can be used to test a Local Matching Service.

Run the tests as follows, replacing the URLs as appropriate for your test local matching service:

```
cd local-matching-service-tests
mvn test -DMATCHING_URL=http://localhost:50139/ruby/matching-service -DUSER_ACCOUNT_CREATION_URL=http://localhost:50139/ruby/account-creation
```

Note: the tests are not representative of a real matching strategy

## Example implementations

* [Ruby/Sinatra](./ruby/)
* [C#/Nancy](./csharp/)

## Running the tests

`./pre-commit.sh`

