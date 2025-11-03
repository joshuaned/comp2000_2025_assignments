import java.awt.Point;

public class ClientData {
    int condition;
    float chance;
    Point position;

    public ClientData(String con, String cha, String x, String y) {
        switch(con) {
            case "rain" -> condition = 1;
            case "windy" -> condition = 2;
            case "windx" -> condition = 3;
            case "temp" -> condition = 4;
        }

        chance = Float.parseFloat(cha);
        position.x = Integer.parseInt(x);
        position.y = Integer.parseInt(y);
    }
}
