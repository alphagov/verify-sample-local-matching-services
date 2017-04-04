package uk.gov.ida.rules;

import java.util.Optional;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

public abstract class RulesEngine {
	public abstract String apply(String json);

	protected Optional<String> readJsonPathOrNull(Object parsedJsonPathDocument, String path) {
		try {
			return Optional.of(JsonPath.read(parsedJsonPathDocument, path));
		} catch (PathNotFoundException e) {
			return Optional.empty();
		}
	}
}
