{-# LANGUAGE OverloadedStrings #-}

module Controllers.AccountCreationController (routes) where

import Web.Scotty (json, jsonData, post, ActionM, ScottyM, setHeader)
import Models.AccountCreationRequest
import Models.AccountCreationResponse

routes :: ScottyM ()
routes = post "/haskell/account-creation" $ do
  req <- jsonData :: ActionM AccountCreationRequest
  setHeader "Content-Type" "application/json"
  case req of
      AccountCreationRequest { levelOfAssurance = "LEVEL_2" } -> json success
      _                                                       -> json failure
