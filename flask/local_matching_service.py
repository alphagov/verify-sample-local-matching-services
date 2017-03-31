from flask import Flask, request, jsonify
import json

app = Flask(__name__)

@app.route('/matching-service', methods=['POST'])
def matching_service():
    body = json.loads(request.data)
    if body['levelOfAssurance'] != 'LEVEL_2':
        return jsonify({ 'result': 'no-match' })

    if 'cycle3Dataset' in body and body['cycle3Dataset'] and body['cycle3Dataset']['attributes']['nino'] == 'goodvalue':
        return jsonify({ 'result': 'match' })

    if 'cycle3Dataset' in body and body['matchingDataset']['surnames'][0]['value'] == 'Griffin':
        return jsonify({ 'result': 'match' })

    return jsonify({ 'result': 'no-match' })


@app.route('/account-creation', methods=['POST'])
def account_creation():
    body = json.loads(request.data)
    if body['levelOfAssurance'] != 'LEVEL_2':
        return jsonify({ 'result': 'failure' })
    return jsonify({'result': 'success'})


