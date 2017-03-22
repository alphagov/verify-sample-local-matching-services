package uk.gov.ida.local_matching_service_tests.domain.outbound.matching;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import uk.gov.ida.local_matching_service_tests.domain.outbound.shared.LevelOfAssuranceDto;

import java.util.Optional;

@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class MatchingServiceRequestDto {

    private MatchingDatasetDto matchingDataset;
    private Optional<Cycle3DatasetDto> cycle3Dataset = Optional.empty();
    private String hashedPid;
    private String matchId;
    private LevelOfAssuranceDto levelOfAssurance;

    @SuppressWarnings("unused")//Needed by JAXB
    private MatchingServiceRequestDto() {}

    public MatchingServiceRequestDto(
            MatchingDatasetDto matchingDataset,
            Optional<Cycle3DatasetDto> cycle3Dataset,
            String hashedPid,
            String matchId,
            LevelOfAssuranceDto levelOfAssurance) {

        this.matchingDataset = matchingDataset;
        this.cycle3Dataset = cycle3Dataset;
        this.hashedPid = hashedPid;
        this.matchId = matchId;
        this.levelOfAssurance = levelOfAssurance;
    }

    public MatchingDatasetDto getMatchingDataset() {
        return matchingDataset;
    }

    public String getHashedPid() {
        return hashedPid;
    }

    public String getMatchId() {
        return matchId;
    }

    public Optional<Cycle3DatasetDto> getCycle3Dataset() {
        return cycle3Dataset;
    }

    public LevelOfAssuranceDto getLevelOfAssurance() {
        return levelOfAssurance;
    }

}
