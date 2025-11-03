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
import java.util.Random;

public class Client {
    List<ClientData> data = new ArrayList<>();
    int dataThreshold = 100;
    int chance = 3; // this determines how likely it is for a weather condition to take a cell
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
                            // consume values into ClientData
                            data.add(new ClientData(pieces[1], pieces[4], pieces[2], pieces[3]));

                            // check if grid is over the threshold
                            if(data.size() >= dataThreshold) {
                                pushValues(); // push 50 values into the checker lambda
                                data.clear(); // flush the values
                            }
                        });
                    } catch (IOException e) {
                        System.err.println("Error reading Server Side Event (SSE) stream: " + e.getMessage());
                    }
                })
                .join(); // Wait for the async operation to complete
    }

    public void pushValues() {
        data.forEach(cdat -> {
            if(grid.cellAtColRow(cdat.position.x, cdat.position.y).isPresent()) {
                // Decide if it takes the cell with a random num gen
                Random random = new Random();
                if(random.nextInt(chance) != 0) {
                    return;
                }

                Cell cell = grid.cellAtColRow(cdat.position.x, cdat.position.y).get();
                cell.tile.updateGrowth(cdat);
            }
        });
    }
}