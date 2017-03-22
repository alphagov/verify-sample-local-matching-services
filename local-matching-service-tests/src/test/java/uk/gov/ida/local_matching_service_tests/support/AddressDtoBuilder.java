package uk.gov.ida.local_matching_service_tests.support;

import jersey.repackaged.com.google.common.collect.ImmutableList;
import org.joda.time.DateTime;
import uk.gov.ida.local_matching_service_tests.domain.outbound.matching.AddressDto;

import java.util.List;
import java.util.Optional;

public class AddressDtoBuilder {

    private boolean verified = true;
    private DateTime fromDate = DateTime.now();
    private Optional<DateTime> toDate = Optional.empty();
    private Optional<String> postCode = Optional.of("ABC 123");
    private List<String> lines = ImmutableList.of("221b Baker Street");
    private Optional<String> internationalPostCode = Optional.empty();
    private Optional<String> uprn = Optional.empty();

    private AddressDtoBuilder() {}

    public static AddressDtoBuilder anAddressDto() {
        return new AddressDtoBuilder();
    }

    public AddressDto build() {
        return new AddressDto(lines, postCode, internationalPostCode, uprn, fromDate, toDate, verified);
    }
}
