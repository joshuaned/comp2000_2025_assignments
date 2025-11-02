import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Controls {
    // Use list for states, as its better for this application
    List<Button> states = new ArrayList<>();

    Button state;

    public Controls() {
        // add the buttons as states
        states.add(new CollectButton(815, 80));
        states.add(new CarrotButton(740, 150));
        states.add(new WaterCabbageButton(900, 150));
    }

    // Paint buttons
    public void paint(Graphics g, Point mouseLoc) {
        for(Button s: states) {
            s.paint(g, mouseLoc);
        }
    }

    public void mouseClicked(Point clickPos) {
        // Check if a button is clicked on
        for(Button s: states) {
            if(s.contains(clickPos)) {
                state = s;
            }
        }

        // Trigger button
    }
}
