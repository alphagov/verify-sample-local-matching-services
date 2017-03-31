using Microsoft.AspNetCore.Mvc;

namespace csharp.Controllers
{
    [Route("csharp/account-creation")]
    public class AccountCreationController : Controller
    {
        [HttpPost]
        public dynamic Post([FromBody] dynamic body)
        {
            var result = body?.levelOfAssurance == "LEVEL_2" ?  "success" : "failure";
            return new { result };
        }
    }
}

