package uk.gov.ida.local_matching_service_tests.domain.inbound;

public class MatchingResponseDto {

    public static final String MATCH = "match";
    public static final String NO_MATCH = "no-match";

    private String result;

    @SuppressWarnings("unused")//Needed by JAXB
    private MatchingResponseDto() {}

    public MatchingResponseDto(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

}
