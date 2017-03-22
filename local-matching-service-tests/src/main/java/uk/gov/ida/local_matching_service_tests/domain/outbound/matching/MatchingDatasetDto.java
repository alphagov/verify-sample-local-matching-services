package uk.gov.ida.local_matching_service_tests.domain.outbound.matching;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class MatchingDatasetDto {

    private Optional<SimpleMdsValueDto<String>> firstName = Optional.empty();
    private Optional<SimpleMdsValueDto<String>> middleNames = Optional.empty();
    private List<SimpleMdsValueDto<String>> surnames = new ArrayList<>();
    private Optional<SimpleMdsValueDto<GenderDto>> gender = Optional.empty();
    private Optional<SimpleMdsValueDto<LocalDate>> dateOfBirth = Optional.empty();
    private List<AddressDto> addresses = new ArrayList<>();

    @SuppressWarnings("unused") // needed for JAXB
    private MatchingDatasetDto() {
    }

    public MatchingDatasetDto(
            Optional<SimpleMdsValueDto<String>> firstName,
            Optional<SimpleMdsValueDto<String>> middleNames,
            List<SimpleMdsValueDto<String>> surnames,
            Optional<SimpleMdsValueDto<GenderDto>> gender,
            Optional<SimpleMdsValueDto<LocalDate>> dateOfBirth,
            List<AddressDto> addresses) {

        this.firstName = firstName;
        this.middleNames = middleNames;
        this.surnames = surnames;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.addresses = addresses;
    }

    public Optional<SimpleMdsValueDto<String>> getFirstName() {
        return firstName;
    }

    public Optional<SimpleMdsValueDto<String>> getMiddleNames() {
        return middleNames;
    }

    public List<SimpleMdsValueDto<String>> getSurnames() {
        return surnames;
    }

    public Optional<SimpleMdsValueDto<GenderDto>> getGender() {
        return gender;
    }

    public Optional<SimpleMdsValueDto<LocalDate>> getDateOfBirth() {
        return dateOfBirth;
    }

    public List<AddressDto> getAddresses() {
        return addresses;
    }

}