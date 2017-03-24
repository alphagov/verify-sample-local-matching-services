using System;
using System.Collections.Generic;
using System.Json;
using System.Runtime.Remoting.Messaging;
using Nancy;
using Nancy.Hosting.Self;
using Nancy.Routing;

namespace lms
{
    public class LMSResponse
    {
        public string result { get; }

        public LMSResponse(string result)
        {
            this.result = result;
        }
    }

    public class MatchingModule : NancyModule
    {
        public MatchingModule()
        {
            Post("/csharp/matching-service", args =>
            {
                var request = JsonValue.Load(Request.Body);

                var noMatchResponse = new LMSResponse("no-match");

                var matchingResponse = new LMSResponse("match");

                if (request["levelOfAssurance"] != "LEVEL_2")
                {
                    matchingResponse = noMatchResponse;
                }

                if (request["cycle3Dataset"] != null)
                {
                    if (request["cycle3Dataset"]["attributes"]["nino"] == "badvalue")
                    {
                        matchingResponse = noMatchResponse;
                    }
                }

                if (request["matchingDataset"]["surnames"][0]["value"] == "Griffin")
                {
                    matchingResponse = noMatchResponse;
                }

                var response = Response.AsJson(matchingResponse);
                //default content type used is application/json;charset=utf-8
                response.ContentType = "application/json";
                return response;
            });

            Post("/csharp/account-creation", args =>
            {
                var request = JsonValue.Load(Request.Body);

                var response = Response.AsJson(new LMSResponse("success"));
                if ("failurePid".Equals(request["hashedPid"]))
                {
                    response = Response.AsJson(new LMSResponse("failure"));
                }
                //default content type used is application/json;charset=utf-8
                response.ContentType = "application/json";
                return response;
            });
        }
    }

    internal class LocalMatchingService
    {
        public static void Main(string[] args)
        {
            var httpHost = "http://localhost:9991/";
            var nancyHost = new NancyHost(new Uri(httpHost));
            Console.Write("Starting local matching service at "+httpHost+"... ");
            nancyHost.Start();
            Console.WriteLine("done");
            Console.WriteLine("Ctrl-c to quit");
            while (true)
            {
                // do nothing
            }
        }
    }
}