FROM alpine:3.5

RUN apk add --update g++ ghc cabal --update-cache --repository http://dl-3.alpinelinux.org/alpine/edge/testing/

RUN apk add --update zlib-dev

ENV APPPATH /app

ENV PATH $APPPATH/bin:$PATH

RUN cabal update

RUN mkdir -p $APPPATH
COPY haskell.cabal $APPPATH
WORKDIR $APPPATH

RUN cabal install --only-dependencies -j4

COPY . $APPPATH

RUN cabal install \
    && cabal configure \
    && cabal build

EXPOSE 3000

CMD [ "cabal", "run" ]