package uk.gov.ida.local_matching_service_tests.support;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import uk.gov.ida.local_matching_service_tests.domain.outbound.matching.Cycle3DatasetDto;
import uk.gov.ida.local_matching_service_tests.domain.outbound.matching.MatchingServiceRequestDto;
import uk.gov.ida.local_matching_service_tests.domain.outbound.shared.LevelOfAssuranceDto;

import java.util.Optional;
import java.util.UUID;

import static uk.gov.ida.local_matching_service_tests.support.MatchingDatasetDtoBuilder.aMatchingDatasetDto;

public class MatchingServiceRequestDtoBuilder {

    private MatchingDatasetDtoBuilder matchingDataset = aMatchingDatasetDto();
    private Optional<Cycle3DatasetDto> cycle3Dataset = Optional.empty();
    private String hashedPid = UUID.randomUUID().toString();
    private String matchId = UUID.randomUUID().toString();
    private LevelOfAssuranceDto levelOfAssurance = LevelOfAssuranceDto.LEVEL_2;

    private MatchingServiceRequestDtoBuilder() {}

    public static MatchingServiceRequestDtoBuilder aMatchingServiceRequestDto() {
        return new MatchingServiceRequestDtoBuilder();
    }

    public MatchingServiceRequestDtoBuilder withFirstname(String firstname) {
        this.matchingDataset = matchingDataset.withFirstname(firstname);
        return this;
    }

    public MatchingServiceRequestDtoBuilder withSurname(String surname) {
        this.matchingDataset = matchingDataset.withSurname(surname);
        return this;
    }

    public MatchingServiceRequestDtoBuilder withCycle3Data(String name, String value) {
        this.cycle3Dataset = Optional.of(new Cycle3DatasetDto(ImmutableMap.of(name, value)));
        return this;
    }

    public MatchingServiceRequestDto build() {
        return new MatchingServiceRequestDto(matchingDataset.build(), cycle3Dataset, hashedPid, matchId, levelOfAssurance);
    }
}
