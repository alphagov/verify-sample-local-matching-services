#!/usr/bin/env ruby
require 'sinatra'
require 'webrick'
require 'json'

set :server, 'webrick'
set :port, 50139

post '/ruby/matching-service', :provides => 'application/json' do
  request.body.rewind
  body = JSON.parse request.body.read

  return JSON.generate({ result: "no-match" }) if body['levelOfAssurance'] != 'LEVEL_2'

  if !body['cycle3Dataset'].nil?
    return JSON.generate({ result: "no-match" }) if body['cycle3Dataset']['attributes']['nino'] == 'badvalue'
  end

  return JSON.generate({ result: "no-match" }) if body['matchingDataset']['surnames'][0]['value'] == 'Griffin'

  JSON.generate({ result: "match" })
end

post '/ruby/account-creation', :provides => 'application/json' do
  request.body.rewind
  body = JSON.parse request.body.read

  return JSON.generate({ result: "failure" }) if body['hashedPid'] == 'failurePid'

  JSON.generate({ result: "success" })
end
