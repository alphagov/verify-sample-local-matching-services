package uk.gov.ida.local_matching_service_tests.domain.inbound;

public class UserAccountCreationResponseDto {

    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    private String result;

    @SuppressWarnings("unused")//Needed by JAXB
    private UserAccountCreationResponseDto() {}

    public UserAccountCreationResponseDto(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }


}
