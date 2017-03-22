package uk.gov.ida.local_matching_service_tests.domain.outbound.matching;

public enum GenderDto {
    FEMALE("Female"),
    MALE("Male"),
    NOT_SPECIFIED("Not Specified");

    private final String value;

    GenderDto(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
