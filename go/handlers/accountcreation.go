package handlers

import (
	"net/http"
	"encoding/json"
	"log"
	"github.com/alphagov/verify-sample-local-matching-services/go"
)

func AccoutCreationHandler(w http.ResponseWriter, r *http.Request) {
	log.Println("Starting AccoutCreationHandler")
	w.Header().Set("Content-Type", "application/json")
	decoder := json.NewDecoder(r.Body)
	var t verify_lms.VerifyLMS
	err := decoder.Decode(&t)
	if err != nil {
		panic(err)
	}
	defer r.Body.Close()

	resp := &Response{
		Result: "success",
	}

	err = t.CreateAccount()
	if err != nil {
		log.Printf("Error Creating Account: %s\n", err)
		resp.Result = "failure"
	}

	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(resp)

}