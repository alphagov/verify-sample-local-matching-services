package main

import (
	"github.com/gorilla/mux"
	"net/http"
	"time"
	"log"
	"github.com/alphagov/verify-sample-local-matching-services/go/handlers"
)

func main() {
	r := mux.NewRouter()
	r.HandleFunc("/matching-service", handlers.MatchingServiceHandler)
	r.HandleFunc("/account-creation", handlers.AccountCreationHandler)

	srv := &http.Server{
		Handler:      r,
		Addr:         "127.0.0.1:8080",
		WriteTimeout: 15 * time.Second,
		ReadTimeout:  15 * time.Second,
	}

	log.Fatal(srv.ListenAndServe())
}
