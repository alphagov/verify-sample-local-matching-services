package uk.gov.ida.local_matching_service_tests.domain.outbound.matching;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Optional;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AddressDto {
    private boolean verified;
    private DateTime fromDate;
    private Optional<DateTime> toDate = Optional.empty();
    private Optional<String> postCode = Optional.empty();
    private List<String> lines;
    private Optional<String> internationalPostCode = Optional.empty();
    private Optional<String> uprn = Optional.empty();

    @SuppressWarnings("unused") // needed for JAXB
    private AddressDto() {
    }

    public AddressDto(
            List<String> lines,
            Optional<String> postCode,
            Optional<String> internationalPostCode,
            Optional<String> uprn,
            DateTime fromDate,
            Optional<DateTime> toDate,
            boolean verified) {

        this.lines = lines;
        this.postCode = postCode;
        this.internationalPostCode = internationalPostCode;
        this.uprn = uprn;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.verified = verified;
    }

    public List<String> getLines() {
        return lines;
    }

    public Optional<String> getPostCode() {
        return postCode;
    }

    public Optional<String> getInternationalPostCode() {
        return internationalPostCode;
    }

    public Optional<String> getUPRN() {
        return uprn;
    }

    public DateTime getFromDate() {
        return fromDate;
    }

    public Optional<DateTime> getToDate() {
        return toDate;
    }

    public boolean isVerified() {
        return verified;
    }

}
