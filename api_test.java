import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class api_test {
    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "https://api.github.com/users/kaushaldalwala";

        // Creating HTTP request
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        var client = HttpClient.newBuilder().build();

        // Sending the request and getting the response
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Printing the response body
        System.out.println("Response Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
    }
}