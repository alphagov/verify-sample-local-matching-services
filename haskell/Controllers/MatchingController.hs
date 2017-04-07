{-# LANGUAGE OverloadedStrings #-}

module Controllers.MatchingController (routes) where

import Web.Scotty (json, jsonData, post, ActionM, ScottyM, setHeader)
import Models.MatchingRequest
import Models.MatchingResponse

type MatchingRequestChecker = (MatchingRequest -> ActionM ()) -> MatchingRequest -> ActionM ()

checkLevelOfAssurance :: MatchingRequestChecker
checkLevelOfAssurance nextCheck request = case request of
  MatchingRequest { levelOfAssurance = LEVEL_2 } -> nextCheck request
  _                                              -> json noMatch

checkCycle3 :: MatchingRequestChecker
checkCycle3 _         MatchingRequest { cycle3Dataset = Just (Cycle3Dataset (NinoAttribute "goodvalue")) } = json match
checkCycle3 nextCheck request                                                                             = nextCheck request

checkMatchingDataset :: MatchingRequestChecker
checkMatchingDataset _         MatchingRequest { matchingDataset = MatchingDataset [MatchingDatasetString "Griffin"] } = json match
checkMatchingDataset nextCheck request                                                                                 = nextCheck request

routes :: ScottyM ()
routes = post "/haskell/matching-service" $ do
  req <- jsonData :: ActionM MatchingRequest
  setHeader "Content-Type" "application/json"
  (checkLevelOfAssurance $ checkCycle3 $ checkMatchingDataset $ const $ json noMatch) req
