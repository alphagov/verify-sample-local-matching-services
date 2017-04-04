package uk.gov.ida;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {
    private static final String ENV_PORT = System.getenv("JGS_PORT");
    private static final String PORT = ENV_PORT == null ? "8080" : ENV_PORT;
    private static final String BASE_URI = "http://localhost:" + PORT + "/";

    public static HttpServer startServer(String uri) {
        final ResourceConfig rc = new ResourceConfig().packages("uk.gov.ida");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(uri), rc);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer(BASE_URI);
    }
}

