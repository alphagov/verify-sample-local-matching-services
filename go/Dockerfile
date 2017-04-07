FROM golang:1.8-alpine

RUN apk add --update --no-cache --virtual .build-deps git

ENV APPPATH $GOPATH/src/github.com/alphagov/verify-sample-local-matching-services/go

RUN mkdir -p $APPPATH
COPY . $APPPATH
WORKDIR $APPPATH

RUN go get ./...
RUN go build

RUN apk del .build-deps

EXPOSE 8080

CMD [ "/go/bin/verify_lms" ]