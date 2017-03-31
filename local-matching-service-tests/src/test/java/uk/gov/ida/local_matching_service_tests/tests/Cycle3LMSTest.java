package uk.gov.ida.local_matching_service_tests.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import uk.gov.ida.local_matching_service_tests.domain.inbound.MatchingResponseDto;
import uk.gov.ida.local_matching_service_tests.domain.outbound.matching.MatchingServiceRequestDto;
import uk.gov.ida.local_matching_service_tests.support.TestJerseyClientBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.gov.ida.local_matching_service_tests.support.MatchingServiceRequestDtoBuilder.aMatchingServiceRequestDto;

public class Cycle3LMSTest {

    public static String LOCAL_MATCHING_SERVICE_URL = System.getProperty("MATCHING_URL");

    private Client client = TestJerseyClientBuilder.build();

    @Test
    public void submitCycle3Request_expectingMatch() throws JsonProcessingException {
        MatchingServiceRequestDto matchingServiceRequestDto = aMatchingServiceRequestDto()
                .withSurname("Frost")
                .withCycle3Data("nino", "goodvalue")
                .build();

        final Response response = client
                .target(LOCAL_MATCHING_SERVICE_URL)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(matchingServiceRequestDto));

        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
        assertThat(response.getMediaType().isCompatible(MediaType.APPLICATION_JSON_TYPE)).isTrue();

        MatchingResponseDto matchingResponseDto = response.readEntity(MatchingResponseDto.class);

        assertThat(matchingResponseDto.getResult()).isEqualTo(MatchingResponseDto.MATCH);
    }

    @Test
    public void submitCycle3Request_expectingNoMatch() throws JsonProcessingException {
        MatchingServiceRequestDto matchingServiceRequestDto = aMatchingServiceRequestDto()
                .withFirstname("Jack")
                .withSurname("Frost")
                .withCycle3Data("nino", "badvalue")
                .build();

        final Response response = client
                .target(LOCAL_MATCHING_SERVICE_URL)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(matchingServiceRequestDto));

        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
        assertThat(response.getMediaType().isCompatible(MediaType.APPLICATION_JSON_TYPE)).isTrue();

        MatchingResponseDto matchingResponseDto = response.readEntity(MatchingResponseDto.class);

        assertThat(matchingResponseDto.getResult()).isEqualTo(MatchingResponseDto.NO_MATCH);
    }

}
