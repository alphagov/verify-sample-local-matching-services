using Microsoft.AspNetCore.Mvc;

namespace csharp.Controllers
{
    [Route("csharp/account-creation")]
    public class AccountCreationController : Controller
    {
        [HttpPost]
        public dynamic Post([FromBody] dynamic body)
        {
            var result = body?.hashedPid == "failurePid" ?  "failure" : "success";
            return new { result };
        }
    }
}

