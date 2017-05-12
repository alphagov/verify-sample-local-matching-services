
const http = require('http')

const hostname = '0.0.0.0'
const port = 4063

function localMatchingService (request) {
  if (request['levelOfAssurance'] !== 'LEVEL_2') {
    return { result: 'no-match' }
  }
  if (request['cycle3Dataset'] !== null &&
      request['cycle3Dataset'] !== undefined &&
      request['cycle3Dataset']['attributes'] !== null &&
      request['cycle3Dataset']['attributes']['nino'] === 'goodvalue') {
    return { result: 'match' }
  }
  if (request['matchingDataset'] !== null &&
      request['matchingDataset'] !== undefined &&
      request['matchingDataset']['surnames'] !== null &&
      request['matchingDataset']['surnames'][0]['value'] === 'Griffin') {
    return { result: 'match' }
  }
  return { result: 'no-match' }
}

function accountCreationService (request) {
  if (request['levelOfAssurance'] === 'LEVEL_2') {
    return { result: 'success' }
  }
  return { result: 'failure' }
}

module.exports.localMatchingService = localMatchingService
module.exports.accountCreationService = accountCreationService

if (process.argv[2] === 'server') {
  const server = http.createServer((request, response) => {
    console.log('Request for: ' + request.method + ' ' + request.url)

    var body = []
    request.on('data', function (chunk) {
      body.push(chunk)
    }).on('end', function () {
      body = Buffer.concat(body).toString()

      if (request.method === 'POST') {
        if (request.headers['content-type'] === 'application/json') {
          var requestJSON = JSON.parse(body)

          switch (request.url) {
            case '/nodejs/matching-service':
              response.statusCode = 200
              response.setHeader('Content-Type', 'application/json')
              response.write(JSON.stringify(localMatchingService(requestJSON)))
              response.end()
              break
            case '/nodejs/account-creation':
              response.statusCode = 200
              response.setHeader('Content-Type', 'application/json')
              response.write(JSON.stringify(accountCreationService(requestJSON)))
              response.end()
              break
            default:
              break
          }
        }
      }
      response.statusCode = 404
      response.end()
    })
  })

  server.listen(port, hostname, () => {
    console.log('Starting local matching service')
  })
}
