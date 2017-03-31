package verify_lms

func (s *VerifyLMS)CreateAccount() error {
	err := store.AddRecord(s.HashedPid, s.LevelOfAssurance)
	if err != nil {
		return err
	}

	return nil
}