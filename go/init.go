package verify_lms

import "github.com/alphagov/verify-sample-local-matching-services/go/model"

var store model.Store

func init() {
	store = model.NewStore()
	store = append(store, &model.Record{"", "Griffin","goodvalue",""})
}
