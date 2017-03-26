{-# LANGUAGE OverloadedStrings #-}
{-# LANGUAGE DeriveGeneric #-}

module Models.AccountCreationResponse where

import GHC.Generics
import Data.Aeson (ToJSON)

newtype AccountCreationResponse = AccountCreationResponse { result :: String } deriving (Show, Generic)
instance ToJSON AccountCreationResponse

success :: AccountCreationResponse
success = AccountCreationResponse "success"

failure :: AccountCreationResponse
failure = AccountCreationResponse "failure"


