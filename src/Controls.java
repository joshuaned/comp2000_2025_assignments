import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Controls {
    // Create the states
    List<Button> buttons = new ArrayList<Button>();

    public Controls() {
        // Create the buttons
        buttons.add(new CollectButton(815, 80));
        buttons.add(new CarrotButton(740, 150));
        buttons.add(new WaterCabbageButton(900, 150));
    }

    public void paint(Graphics g) {
        for(Button b: buttons) {
            b.paint(g, mousePos);
        }
    }
}
