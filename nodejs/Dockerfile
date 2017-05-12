FROM node:7-alpine

WORKDIR /lms

ADD package.json .
RUN npm install

ADD lms.js .
ADD lms.test.js .

RUN npm test

EXPOSE 4063

CMD [ "node", "lms.js", "server"]