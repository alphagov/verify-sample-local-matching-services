{-# LANGUAGE DeriveGeneric #-}

module Models.MatchingRequest where

import GHC.Generics
import Data.Aeson (FromJSON)

newtype NinoAttribute = NinoAttribute { nino :: String } deriving (Show, Generic)
instance FromJSON NinoAttribute

newtype Cycle3Dataset = Cycle3Dataset { attributes :: NinoAttribute } deriving (Show, Generic)
instance FromJSON Cycle3Dataset

data LevelOfAssurance = LEVEL_1 | LEVEL_2 | LEVEL_3 | LEVEL_4 deriving (Show, Generic)
instance FromJSON LevelOfAssurance

newtype MatchingDatasetString = MatchingDatasetString { value :: String } deriving (Show, Generic)
instance FromJSON MatchingDatasetString

newtype MatchingDataset = MatchingDataset { surnames :: [MatchingDatasetString] } deriving (Show, Generic)
instance FromJSON MatchingDataset

data MatchingRequest = MatchingRequest { 
  levelOfAssurance :: LevelOfAssurance,
  cycle3Dataset :: Maybe Cycle3Dataset,
  matchingDataset :: MatchingDataset
} deriving (Show, Generic)
instance FromJSON MatchingRequest
