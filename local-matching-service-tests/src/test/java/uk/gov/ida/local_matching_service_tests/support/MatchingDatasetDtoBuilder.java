package uk.gov.ida.local_matching_service_tests.support;

import jersey.repackaged.com.google.common.collect.ImmutableList;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import uk.gov.ida.local_matching_service_tests.domain.outbound.matching.AddressDto;
import uk.gov.ida.local_matching_service_tests.domain.outbound.matching.GenderDto;
import uk.gov.ida.local_matching_service_tests.domain.outbound.matching.MatchingDatasetDto;
import uk.gov.ida.local_matching_service_tests.domain.outbound.matching.SimpleMdsValueDto;

import java.util.List;
import java.util.Optional;

import static uk.gov.ida.local_matching_service_tests.support.AddressDtoBuilder.anAddressDto;

public class MatchingDatasetDtoBuilder {

    private Optional<SimpleMdsValueDto<String>> firstName = Optional.of(getSimpleMdsValueString("John"));
    private Optional<SimpleMdsValueDto<String>> middleNames = Optional.of(getSimpleMdsValueString("A"));
    private List<SimpleMdsValueDto<String>> surnames = ImmutableList.of(getSimpleMdsValueString("Smith"));
    private Optional<SimpleMdsValueDto<GenderDto>> gender = Optional.of(getSimpleMdsValueGender(GenderDto.NOT_SPECIFIED));
    private Optional<SimpleMdsValueDto<LocalDate>> dateOfBirth = Optional.of(getSimpleMdsValueLocalDate(DateTime.now()));
    private List<AddressDto> addresses = ImmutableList.of(anAddressDto().build());

    private MatchingDatasetDtoBuilder() {}

    private SimpleMdsValueDto<String> getSimpleMdsValueString(String name) {
        return SimpleMdsValueDtoBuilder.<String>aSimpleMdsValueDto().withValue(name).build();
    }

    private SimpleMdsValueDto<LocalDate> getSimpleMdsValueLocalDate(DateTime dateTime) {
        return SimpleMdsValueDtoBuilder.<LocalDate>aSimpleMdsValueDto().withValue(dateTime.toLocalDate()).build();
    }

    private SimpleMdsValueDto<GenderDto> getSimpleMdsValueGender(GenderDto gender) {
        return SimpleMdsValueDtoBuilder.<GenderDto>aSimpleMdsValueDto().withValue(gender).build();
    }

    public static MatchingDatasetDtoBuilder aMatchingDatasetDto() {
        return new MatchingDatasetDtoBuilder();
    }

    public MatchingDatasetDtoBuilder withFirstname(String firstname) {
        this.firstName = Optional.of(getSimpleMdsValueString(firstname));
        return this;
    }

    public MatchingDatasetDtoBuilder withSurname(String surname) {
        this.surnames = ImmutableList.of(getSimpleMdsValueString(surname));
        return this;
    }

    public MatchingDatasetDto build() {
        return new MatchingDatasetDto(firstName, middleNames, surnames, gender, dateOfBirth, addresses);
    }

}
