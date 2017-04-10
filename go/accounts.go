package verify_lms

import "errors"

func (s *VerifyLMS)CreateAccount() error {
	if s.LevelOfAssurance != "LEVEL_2" {
		return errors.New("Only LEVEL_2 assurance supported")
	}

	err := store.AddRecord(s.HashedPid, s.LevelOfAssurance)
	if err != nil {
		return err
	}

	return nil
}