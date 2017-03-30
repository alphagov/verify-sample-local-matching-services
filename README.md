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

**Note:**

* the tests are not representative of a real matching strategy
* older versions of the MSA send POSTs with gzip compressed JSON by default

## Tests

| Matching Cycle   | Data to check                  | Expected Response |
| ---------------- | ------------------------------ | ----------------- |
| Cycle 0+1        | n/a                            | `match`           |
| Cycle 0+1        | Surname[0] is "Griffin"        | `no-match`        |
| Cycle 3          | "nino" is "goodvalue"          | `match`           |
| Cycle 3          | "nino" is "badvalue"           | `no-match`        |
| Account creation | PersistentId is "successPid" (see note) | `success`         |
| Account creation | PersistentId is "failurePid" (see note) | `failure`         |

**Note:** account creation is not expected to fail based on a PersistentId, but this allow deterministic testing

## Example implementations

* [Ruby/Sinatra](./ruby/)
* [C#/.NET Core](./csharp/)
* [C#/Mono/Nancy](./nancy/)
* [Haskell/Scotty](./haskell/)

## Running the tests

`./pre-commit.sh`

