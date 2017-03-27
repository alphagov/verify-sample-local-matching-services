using Microsoft.AspNetCore.Mvc;

namespace csharp.Hacks {
    public static class ContentTypeHack {
        public static JsonResult OverrideContentType(this Controller controller, dynamic input) {
            // FIXME: by default, ASP.NET returns `Content-Type: application/json; charset=utf-8`.
            // This is a perfectly valid content type, and the MSA should have no issue parsing it.
            // TODO: Confirm this, and update the tests to allow charset in the content type.
            var response = controller.Json(input);
            response.ContentType = "application/json";
            return response;
        }
    }
}