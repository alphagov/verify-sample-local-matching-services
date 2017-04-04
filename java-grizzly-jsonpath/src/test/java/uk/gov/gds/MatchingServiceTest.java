package uk.gov.gds;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.gov.ida.Main;

public class MatchingServiceTest {
    private HttpServer server;
    private WebTarget target;
    private static final String BASE_URI = "http://localhost:8080/";
    
    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer(BASE_URI);
        // create the client
        Client c = ClientBuilder.newClient();
        target = c.target(BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdownNow();
    }

    @Test
    public void testPost() {
        Response response = target.path("/java/grizzly/matching-service").request().post(Entity.entity("{}", MediaType.APPLICATION_JSON));
        assertEquals("{\"result\":\"no-match\"}", response.readEntity(String.class));
    }
    
    @Test
    public void submitCycle01Request_expectingMatch() throws IOException {
    	String json = new String(Files.readAllBytes(Paths.get("src/test/resources/test1.json")));
        Response response = target.path("/java/grizzly/matching-service").request().post(Entity.entity(json, MediaType.APPLICATION_JSON));
        assertEquals("{\"result\":\"match\"}", response.readEntity(String.class));
    }
}
