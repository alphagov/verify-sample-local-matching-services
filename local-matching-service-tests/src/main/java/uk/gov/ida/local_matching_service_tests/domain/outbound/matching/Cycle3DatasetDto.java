package uk.gov.ida.local_matching_service_tests.domain.outbound.matching;

import java.util.Map;

public class Cycle3DatasetDto {
    private Map<String, String> attributes;

    @SuppressWarnings("unused") // needed by JAXB
    private Cycle3DatasetDto() {}

    public Cycle3DatasetDto(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

}
