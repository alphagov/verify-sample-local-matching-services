package verify_lms

type Addresses struct {
  FromDate string `json:"fromDate,omitempty"`
  InternationalPostCode string `json:"internationalPostCode,omitempty"`
  Lines []string `json:"lines,omitempty"`
  PostCode string `json:"postCode,omitempty"`
  ToDate string `json:"toDate,omitempty"`
  Uprn string `json:"uprn,omitempty"`
  Verified bool `json:"verified,omitempty"`
}

type Cycle3Dataset struct {
  Attributes map[string]string `json:"attributes,omitempty"`
}

type DateOfBirth struct {
  From string `json:"from,omitempty"`
  To string `json:"to,omitempty"`
  Value string `json:"value,omitempty"`
  Verified bool `json:"verified,omitempty"`
}

type FirstName struct {
  From string `json:"from,omitempty"`
  To string `json:"to,omitempty"`
  Value string `json:"value,omitempty"`
  Verified bool `json:"verified,omitempty"`
}

type Gender struct {
  From string `json:"from,omitempty"`
  To string `json:"to,omitempty"`
  Value string `json:"value,omitempty"`
  Verified bool `json:"verified,omitempty"`
}

type MatchingDataset struct {
  Addresses []Addresses `json:"addresses,omitempty"`
  DateOfBirth *DateOfBirth `json:"dateOfBirth,omitempty"`
  FirstName *FirstName `json:"firstName,omitempty"`
  Gender *Gender `json:"gender,omitempty"`
  MiddleNames *MiddleNames `json:"middleNames,omitempty"`
  Surnames []Surnames `json:"surnames,omitempty"`
}

type MiddleNames struct {
  From string `json:"from,omitempty"`
  To string `json:"to,omitempty"`
  Value string `json:"value,omitempty"`
  Verified bool `json:"verified,omitempty"`
}

type VerifyLMS struct {
  Cycle3Dataset *Cycle3Dataset `json:"cycle3Dataset,omitempty"`
  HashedPid string `json:"hashedPid"`
  LevelOfAssurance string `json:"levelOfAssurance"`
  MatchId string `json:"matchId"`
  MatchingDataset *MatchingDataset `json:"matchingDataset"`
}

type Surnames struct {
  From string `json:"from,omitempty"`
  To string `json:"to,omitempty"`
  Value string `json:"value,omitempty"`
  Verified bool `json:"verified,omitempty"`
}
