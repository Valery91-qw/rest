package kata.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.nio.charset.Charset;

public class Requests {
    final String url = "http://94.198.50.185:7081/api/users";
    final URI uri;
    final BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString(Charset.defaultCharset());

    public Requests() throws URISyntaxException {
        this.uri = new URI(url);
    }

    public HttpRequest getHttpGet() {
        return HttpRequest.newBuilder(uri).build();
    };

    public HttpRequest getHttpPost(User user) {
        return HttpRequest.newBuilder(uri)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(user.toString()))
                .build();
    }

    public HttpRequest getHttpPut(User user) {
        user.setName("Thomas");
        user.setLastName("Shelby");
        return HttpRequest.newBuilder(uri)
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(user.toString()))
                .build();
    }

    public HttpRequest getHttpDelete(User user) throws URISyntaxException {
        long userId = user.getId();
        return HttpRequest.newBuilder(new URI(this.uri.toString() + "/" + userId))
                .DELETE()
                .build();
    }

    public BodyHandler<String> getBodyHandler() {
        return this.bodyHandler;
    }
}
