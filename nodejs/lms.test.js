
const lms = require('./lms')

describe('unit tests', () => {
  test('get no-match response for unknown input', () => {
    const request = {empty: 'yes'}
    const result = lms.localMatchingService(request)
    expect(result).toEqual({result: 'no-match'})
  })
  test('get match response for matching input', () => {
    const request = { levelOfAssurance: 'LEVEL_2',
      matchingDataset: {
        surnames: [
          { value: 'Griffin' }
        ]
      }
    }
    const result = lms.localMatchingService(request)
    expect(result).toEqual({result: 'match'})
  })
  test('get match response for cycle3 input', () => {
    const request = { levelOfAssurance: 'LEVEL_2',
      matchingDataset: {
        surnames: [
          { value: 'Griffin' }
        ]
      },
      cycle3Dataset: {
        attributes: {
          nino: 'goodvalue'
        }
      }
    }
    const result = lms.localMatchingService(request)
    expect(result).toEqual({result: 'match'})
  })
  test('account creation success for LEVEL_2', () => {
    const request = { levelOfAssurance: 'LEVEL_2' }
    const result = lms.accountCreationService(request)
    expect(result).toEqual({result: 'success'})
  })
  test('account creation failure for LEVEL_1', () => {
    const request = { levelOfAssurance: 'LEVEL_1' }
    const result = lms.accountCreationService(request)
    expect(result).toEqual({result: 'failure'})
  })
})
