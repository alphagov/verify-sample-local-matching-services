package verify_lms

import (
	"log"
	"errors"
)

func (s *VerifyLMS)Matched() (bool,error){
	if s.Cycle3Dataset == nil {
		return s.cycle10()
	}
	return s.cycle3()
}

func (s *VerifyLMS)cycle10() (bool, error){
	if s.MatchingDataset == nil {
		if store.PidExists(s.HashedPid) {
			log.Printf("Seen pid, but not Matching Dataset: %s\n", s.HashedPid)
			return true, nil
		}
		log.Printf("No Matching Dataset: %s\n", s.HashedPid)
		return false, errors.New("Not seen pid, and no matching dataset")
	}

	return store.RecordMatch01(s.HashedPid, s.MatchingDataset.Surnames[0].Value), nil
}

func (s *VerifyLMS)cycle3() (bool, error){
	return store.RecordMatch3(s.Cycle3Dataset.Attributes["nino"]), nil
}