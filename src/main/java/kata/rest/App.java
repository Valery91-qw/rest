package kata.rest;

import java.net.CookieManager;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;

public class App {
    public static void main(String[] args) throws URISyntaxException {
        var user = new User(3, "James", "Brown", 3);
        var client = HttpClient.newBuilder().cookieHandler(new CookieManager()).build();
        var requests = new Requests();
        var result = new StringBuilder();

        client.sendAsync(
                requests.getHttpGet(),
                HttpResponse.BodyHandlers.ofString(Charset.defaultCharset()))
                .thenApply((res) -> {
                    client.sendAsync(
                            requests.getHttpPost(user),
                            HttpResponse.BodyHandlers.ofString(Charset.defaultCharset()))
                            .thenApply((res1) -> {
                                result.append(res1.body());
                                client.sendAsync(
                                        requests.getHttpPut(user),
                                        HttpResponse.BodyHandlers.ofString(Charset.defaultCharset()))
                                        .thenApply((res2) -> {
                                            result.append(res2.body());
                                            try {
                                                client.sendAsync(
                                                        requests.getHttpDelete(user),
                                                        HttpResponse.BodyHandlers.ofString(Charset.defaultCharset()))
                                                        .thenApply((res3) -> {
                                                            result.append(res3.body());
                                                            System.out.println(result);
                                                            return res3;
                                                        }).join();
                                            } catch (URISyntaxException e) {
                                                e.printStackTrace();
                                            }
                                            return res2;
                                        }).join();
                                return res1;
                            }).join();
                    return res;
                }).join();
    }
}
