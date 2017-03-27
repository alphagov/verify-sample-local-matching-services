using csharp.Hacks;
using Microsoft.AspNetCore.Mvc;

namespace csharp.Controllers
{
    [Route("csharp/matching-service")]
    public class MatchingServiceController : Controller
    {
        [HttpPost]
        public dynamic Post([FromBody] dynamic body)
        {
            if (body?.levelOfAssurance != "LEVEL_2") {
                return MatchResponse(false);
            }

            var cycle3Dataset = body?.cycle3Dataset;
            if (cycle3Dataset != null && cycle3Dataset.attributes?.nino == "badvalue") {
                return MatchResponse(false);
            }

            if (body?.matchingDataset?.surnames?[0]?.value == "Griffin") {
                return MatchResponse(false);
            }

            return MatchResponse(true);
        }

        private dynamic MatchResponse(bool match) {
            var result = match ? "match" : "no-match";
            return this.OverrideContentType(new { result });
        }
    }
}
