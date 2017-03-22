package uk.gov.ida.local_matching_service_tests.domain.outbound.matching;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;

@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class SimpleMdsValueDto<T> {

    private T value;
    private DateTime from;
    private DateTime to;
    private boolean verified;

    @SuppressWarnings("unused") // needed for JAXB
    private SimpleMdsValueDto() {}

    public SimpleMdsValueDto(T value, DateTime from, DateTime to, boolean verified) {
        this.value = value;
        this.from = from;
        this.to = to;
        this.verified = verified;
    }

    public T getValue() {
        return value;
    }

    public DateTime getFrom() {
        return from;
    }

    public DateTime getTo() {
        return to;
    }

    public boolean isVerified() {
        return verified;
    }

}
