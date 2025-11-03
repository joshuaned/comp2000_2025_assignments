public class ClientData {
    int condition = 0;
    float chance = 0f;
    int x = 0;
    int y = 0;

    public ClientData(String con, String cha, String x, String y) {
        switch(con) {
            case "rain":
                condition = 0;
                break;
            case "windy":
                condition = 1;
                break;
            case "windx":
                condition = 2;
                break;
            case "temp":
                condition = 3;
                break;
        }

        chance = Float.parseFloat(cha);
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
    }
}
