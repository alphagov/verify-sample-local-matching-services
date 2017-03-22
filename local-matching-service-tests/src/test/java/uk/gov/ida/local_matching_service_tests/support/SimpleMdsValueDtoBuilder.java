package uk.gov.ida.local_matching_service_tests.support;

import org.joda.time.DateTime;
import uk.gov.ida.local_matching_service_tests.domain.outbound.matching.SimpleMdsValueDto;

public class SimpleMdsValueDtoBuilder<T> {

    private T value;
    private DateTime from = DateTime.now();
    private DateTime to = DateTime.now();
    private boolean verified = true;

    private SimpleMdsValueDtoBuilder() {}

    public static <T> SimpleMdsValueDtoBuilder<T> aSimpleMdsValueDto() {
        return new SimpleMdsValueDtoBuilder<>();
    }

    public SimpleMdsValueDtoBuilder<T> withValue(T value) {
        this.value = value;
        return this;
    }

    public SimpleMdsValueDto<T> build() {
        return new SimpleMdsValueDto<>(value, from, to, verified);
    }
}
