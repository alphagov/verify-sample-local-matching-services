package uk.gov.ida.rules;

import java.util.Optional;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.PathNotFoundException;

public class MatchingServiceRulesEngine extends RulesEngine {
	private static final String MATCH 		= "{\"result\":\"match\"}";
	private static final String NO_MATCH 	= "{\"result\":\"no-match\"}";
	
	@Override
	public String apply(String json) {
		Object matchingServiceRequest = Configuration.defaultConfiguration().jsonProvider().parse(json);

		try {
			Optional<String> levelOfAssurance = readJsonPathOrNull(matchingServiceRequest, "$.levelOfAssurance");
			Optional<String> cycle3DatasetAttributeNino = readJsonPathOrNull(matchingServiceRequest, "$.cycle3Dataset.attributes.nino");
			Optional<String> firstSurnameOfMatchingDataset = readJsonPathOrNull(matchingServiceRequest, "$.matchingDataset.surnames[0].value");
			
			if (levelOfAssurance.isPresent() && !levelOfAssurance.get().equals("LEVEL_2")) {
				return NO_MATCH;
			} 
			
			if ((cycle3DatasetAttributeNino.isPresent() && cycle3DatasetAttributeNino.get().equals("goodvalue")) ||
				(firstSurnameOfMatchingDataset.isPresent() && firstSurnameOfMatchingDataset.get().equals("Griffin"))) {
				return MATCH;
			}
		} catch (PathNotFoundException e) {
			return NO_MATCH;
		}
		return NO_MATCH;
	}
}
