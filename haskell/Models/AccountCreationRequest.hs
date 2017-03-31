{-# LANGUAGE DeriveGeneric #-}

module Models.AccountCreationRequest where

import GHC.Generics
import Data.Aeson (FromJSON)

newtype AccountCreationRequest = AccountCreationRequest { levelOfAssurance :: String } deriving (Show, Generic)
instance FromJSON AccountCreationRequest
