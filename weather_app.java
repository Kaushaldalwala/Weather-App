import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class weather_app {
    public static void main(String[] args) throws IOException, InterruptedException {
        String api = "f1dd0571cac50a40f464a6a9de1b1027";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the city name (or 'q' / 'q2' to quit): ");
            String city = scanner.nextLine().trim(); // Trim spaces

            // Check if user wants to quit
            if (city.equalsIgnoreCase("q") || city.equalsIgnoreCase("q2")) {
                System.out.println("Thanks for using the Weather App! ❄️");
                break;
            }

            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + api + "&units=metric";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("\nWeather Data Response Code: " + response.statusCode());

            if (response.statusCode() == 200) { // Process only if successful
                Gson gson = new Gson();
                JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);
                JsonObject main = jsonResponse.getAsJsonObject("main");

                double temperature = main.get("temp").getAsDouble();
                double feelsLike = main.get("feels_like").getAsDouble();
                int humidity = main.get("humidity").getAsInt();

                System.out.println("🌡 Temperature: " + temperature + "°C");
                System.out.println("🥵 Feels Like: " + feelsLike + "°C");
                System.out.println("💧 Humidity: " + humidity + "%\n");
            } else {
                System.out.println("❌ Error fetching weather data! Check city name or API key.\n");
            }
        }

        scanner.close(); // Close scanner after loop ends
    }
}