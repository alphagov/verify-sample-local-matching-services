package model

import "errors"

type Store []*Record

type Record struct {
	Pid string
	Surname string
	Nino string
	LevelOfAssurance string
}

func NewStore() Store {
	return make(Store, 0)
}

func (s Store)PidExists(pid string) bool{
	for _, v := range s {
		if v.Pid == pid {
			return true
		}
	}
	return false
}

func (s Store)RecordMatch01(pid, surname string) bool {
	for _, v := range s {
		if (v.Pid == pid || v.Pid == "" ) &&
			v.Surname == surname {
			return true
		}
	}
	return false
}


func (s Store)RecordMatch3(nino string) bool {
	for _, v := range s {
		if v.Nino == nino && nino != "" {
			return true
		}
	}
	return false
}

func (s Store)AddRecord(pid, loa string) error {
	if s.PidExists(pid) {
		return errors.New("Account already exists")
	}

	s = append(s, &Record{pid, "", "", loa})
	return nil
}