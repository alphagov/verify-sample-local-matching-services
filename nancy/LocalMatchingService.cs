using System;
using System.IO;
using System.IO.Compression;
using System.Json;
using System.Linq;
using Nancy;
using Nancy.Hosting.Self;

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
        private Stream getStream(Request request)
        {
            if (request.Headers["Content-Encoding"] != null && request.Headers["Content-Encoding"].Contains("gzip"))
            {
                return new GZipStream(request.Body, CompressionMode.Decompress);
            }
            return request.Body;
        }

        public MatchingModule()
        {
            Post("/nancy/matching-service", args =>
            {
                var request = JsonValue.Load(getStream(Request));

                var noMatchResponse = new LMSResponse("no-match");

                var matchingResponse = new LMSResponse("match");

                if (request["levelOfAssurance"] != "LEVEL_2")
                {
                    matchingResponse = noMatchResponse;
                }

                if (request.ContainsKey("cycle3Dataset") && request["cycle3Dataset"] != null)
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
                return response;
            });

            Post("/nancy/account-creation", args =>
            {
                var request = JsonValue.Load(getStream(Request));

                var response = Response.AsJson(new LMSResponse("success"));
                if (!"LEVEL_2".Equals(request["levelOfAssurance"]))
                {
                    response = Response.AsJson(new LMSResponse("failure"));
                }
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