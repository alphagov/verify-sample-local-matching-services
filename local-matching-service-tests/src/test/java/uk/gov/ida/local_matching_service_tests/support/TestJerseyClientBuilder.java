package uk.gov.ida.local_matching_service_tests.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.client.Client;

public class TestJerseyClientBuilder {

    private TestJerseyClientBuilder() {}

    public static Client build() {
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new Jdk8Module())
                .registerModule(new JodaModule());
        return JerseyClientBuilder.createClient().register(new JacksonJsonProvider(objectMapper));
    }

}
