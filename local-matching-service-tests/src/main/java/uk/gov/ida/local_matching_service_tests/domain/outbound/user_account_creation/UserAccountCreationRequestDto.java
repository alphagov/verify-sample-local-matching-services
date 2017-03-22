package uk.gov.ida.local_matching_service_tests.domain.outbound.user_account_creation;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import uk.gov.ida.local_matching_service_tests.domain.outbound.shared.LevelOfAssuranceDto;

@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class UserAccountCreationRequestDto {
    private String hashedPid;
    private LevelOfAssuranceDto levelOfAssurance;

    @SuppressWarnings("unused") //Required by JAXB
    private UserAccountCreationRequestDto() {}

    public UserAccountCreationRequestDto(String hashedPid, LevelOfAssuranceDto levelOfAssurance) {
        this.hashedPid = hashedPid;
        this.levelOfAssurance = levelOfAssurance;
    }

    public String getHashedPid() {
        return hashedPid;
    }

    public LevelOfAssuranceDto getLevelOfAssurance() {
        return levelOfAssurance;
    }

}
