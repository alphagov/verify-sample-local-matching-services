#!/usr/bin/env ruby
require 'sinatra'
require 'webrick'
require 'json'

set :server, 'webrick'
set :port, 50139

def get_body(request)
  encoding = request.env['HTTP_CONTENT_ENCODING']
  request.body.rewind
  request_body = request.body.read
  # deflate gzip body
  request_body = Zlib::Inflate.new(window_bits=16+Zlib::MAX_WBITS).inflate(request_body).to_s if !encoding.nil? && encoding == 'gzip'
  JSON.parse request_body
end

post '/ruby/matching-service', :provides => 'application/json' do
  body = get_body(request)

  return JSON.generate({ result: "no-match" }) if body['levelOfAssurance'] != 'LEVEL_2'

  if !body['cycle3Dataset'].nil?
    return JSON.generate({ result: "no-match" }) if body['cycle3Dataset']['attributes']['nino'] == 'badvalue'
  end

  return JSON.generate({ result: "no-match" }) if body['matchingDataset']['surnames'][0]['value'] == 'Griffin'

  JSON.generate({ result: "match" })
end

post '/ruby/account-creation', :provides => 'application/json' do
  body = get_body(request)

  return JSON.generate({ result: "failure" }) if body['levelOfAssurance'] != 'LEVEL_2'

  JSON.generate({ result: "success" })
end
