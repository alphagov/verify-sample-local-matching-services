package handlers

import (
	"net/http"
	"log"
	"encoding/json"
	"github.com/alphagov/verify-sample-local-matching-services/go"
)

func MatchingServiceHandler(w http.ResponseWriter, r *http.Request) {
	log.Println("Starting MatchingServiceHandler")
	w.Header().Set("Content-Type", "application/json")

	decoder := json.NewDecoder(r.Body)
	var t verify_lms.VerifyLMS
	err := decoder.Decode(&t)
	if err != nil {
		panic(err)
	}
	defer r.Body.Close()

	resp := &Response{
		Result: "no-match",
	}

	matched, err := t.Matched()
	if err != nil {
		w.WriteHeader(500)
		return
	}
	if matched {
		resp.Result = "match"
	}

	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(resp)
}
