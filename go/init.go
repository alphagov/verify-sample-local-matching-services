package verify_lms

import "github.com/alphagov/verify-sample-local-matching-services/go/model"

var store model.Store

func init() {
	store = model.NewStore()
	// This record being added is to model a pre-existing record a the CMS
	// It does not demonstrate that the LMS saves anything except hash pid
	store = append(store, &model.Record{"", "Griffin","goodvalue",""})
}
