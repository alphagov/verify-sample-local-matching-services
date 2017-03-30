package uk.gov.ida.local_matching_service_tests.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import uk.gov.ida.local_matching_service_tests.domain.inbound.UserAccountCreationResponseDto;
import uk.gov.ida.local_matching_service_tests.domain.outbound.shared.LevelOfAssuranceDto;
import uk.gov.ida.local_matching_service_tests.domain.outbound.user_account_creation.UserAccountCreationRequestDto;
import uk.gov.ida.local_matching_service_tests.support.TestJerseyClientBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class UserAccountCreationLMSTest {

    public static String USER_ACCOUNT_CREATION_URL = System.getProperty("USER_ACCOUNT_CREATION_URL");

    private Client client = TestJerseyClientBuilder.build();

    @Test
    public void submitUserAccountCreationRequest_expectingSuccess() throws JsonProcessingException {
        UserAccountCreationRequestDto userAccountCreationRequestDto = new UserAccountCreationRequestDto("successPid", LevelOfAssuranceDto.LEVEL_2);

        final Response response = client
                .target(USER_ACCOUNT_CREATION_URL)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(userAccountCreationRequestDto));

        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
        assertThat(response.getMediaType().isCompatible(MediaType.APPLICATION_JSON_TYPE)).isTrue();

        UserAccountCreationResponseDto userAccountCreationResponseDto = response.readEntity(UserAccountCreationResponseDto.class);

        assertThat(userAccountCreationResponseDto.getResult()).isEqualTo(UserAccountCreationResponseDto.SUCCESS);
    }

    @Test
    public void submitUserAccountCreationRequest_expectingFailure() throws JsonProcessingException {
        UserAccountCreationRequestDto userAccountCreationRequestDto = new UserAccountCreationRequestDto("failurePid", LevelOfAssuranceDto.LEVEL_2);

        final Response response = client
                .target(USER_ACCOUNT_CREATION_URL)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(userAccountCreationRequestDto));

        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
        assertThat(response.getMediaType().isCompatible(MediaType.APPLICATION_JSON_TYPE)).isTrue();

        UserAccountCreationResponseDto userAccountCreationResponseDto = response.readEntity(UserAccountCreationResponseDto.class);

        assertThat(userAccountCreationResponseDto.getResult()).isEqualTo(UserAccountCreationResponseDto.FAILURE);
    }

}
