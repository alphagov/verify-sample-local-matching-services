{-# LANGUAGE OverloadedStrings #-}
{-# LANGUAGE DeriveGeneric #-}

import GHC.Generics
import Web.Scotty
import Data.Aeson (ToJSON)
import qualified Controllers.MatchingController as MC
import qualified Controllers.AccountCreationController as ACC

newtype ErrorResponse = ErrorResponse { error :: String } deriving (Show, Generic)
instance ToJSON ErrorResponse

simpleErrors :: a -> ActionM ()
simpleErrors _ = json ErrorResponse { Main.error = "Something went wrong" }

main :: IO ()
main = scotty 3000 $ do
  defaultHandler simpleErrors
  MC.routes
  ACC.routes
