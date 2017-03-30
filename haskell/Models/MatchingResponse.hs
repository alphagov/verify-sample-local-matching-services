{-# LANGUAGE OverloadedStrings #-}
{-# LANGUAGE DeriveGeneric #-}

module Models.MatchingResponse where

import GHC.Generics
import Data.Aeson (ToJSON)

newtype MatchingResponse = MatchingResponse { result :: String } deriving (Show, Generic)
instance ToJSON MatchingResponse

match :: MatchingResponse
match = MatchingResponse "match"

noMatch :: MatchingResponse
noMatch = MatchingResponse "no-match"

