package uk.gov.ida.rules;

import com.jayway.jsonpath.JsonPath;

public class AccountCreationRulesEngine extends RulesEngine {
	private static final String SUCCESS = "{\"result\":\"success\"}";
	private static final String FAILURE = "{\"result\":\"failure\"}";
	
	@Override
	public String apply(String json) {
		return JsonPath.read(json, "$.levelOfAssurance").equals("LEVEL_2") ? SUCCESS : FAILURE;
	}
}
