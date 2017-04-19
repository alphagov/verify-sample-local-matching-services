package uk.gov.ida;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import uk.gov.ida.rules.AccountCreationRulesEngine;
import uk.gov.ida.rules.MatchingServiceRulesEngine;

@Path("/java/grizzly/")
public class MatchingService {
	@POST
	@Path("matching-service")
	@Produces(MediaType.APPLICATION_JSON)
	public Response matchingService(String json) {
		return Response.status(Response.Status.OK)
				.entity(new MatchingServiceRulesEngine().apply(json))
				.build();
	}

	@POST
	@Path("account-creation")
	@Produces(MediaType.APPLICATION_JSON)
	public Response accountCreation(String json) {
		return Response.status(Response.Status.OK)
				.entity(new AccountCreationRulesEngine().apply(json))
				.build();
	}
}
