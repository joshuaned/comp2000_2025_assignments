import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Client {
    List<ClientData> data = new ArrayList<>();
    int dataThreshold = 50;
    // Hold the current grid here - for changing the weather conditions
    Grid grid;

    public Client(Grid g) {
        grid = g;
    }

    public void request() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://13.238.167.130/weather"))
                .header("Accept", "text/event-stream")
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(HttpResponse::body)
                .thenAccept(inputStream -> {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                        reader.lines().map(s -> s.split(" ")).forEach(pieces -> {
                            // System.out.println("New weather report at: " + pieces[0] + " ");
                            // System.out.println("Condition: " + pieces[1] + " ");
                            // System.out.println("Location: ( " + pieces[2] + " , " + pieces[3] + " )");
                            // System.out.println("Strength: " + pieces[4] + " \n");

                            // consume values into ClientData
                            data.add(new ClientData(pieces[1], pieces[4], pieces[2], pieces[3]));

                            if(data.size() >= dataThreshold) {
                                pushValues();
                                data.clear();
                            }
                        });
                    } catch (IOException e) {
                        System.err.println("Error reading Server Side Event (SSE) stream: " + e.getMessage());
                    }
                })
                .join(); // Wait for the async operation to complete
    }

    public void pushValues() {
        for(ClientData e: data) {
            
        }
    }
}