import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Controls {
    // Use list for states, as its better for this application
    List<Button> states = new ArrayList<>();

    Button state;

    public Controls() {
        // add the buttons as states
        states.add(new CollectButton(815, 80));
        states.add(new CarrotButton(740, 150));
        states.add(new WaterCabbageButton(900, 150));

        state = states.get(0);
    }

    // Paint buttons
    public void paint(Graphics g, Point mouseLoc) {
        for(Button s: states) {
            s.paint(g, mouseLoc);
        }
    }

    public void mouseClicked(Point clickPos, Optional<Cell> selected, int money) {
        // Check if a button is clicked on
        for(Button s: states) {
            if(s.contains(clickPos)) {
                state = s;
            }
        }

        // Trigger button
        if(selected.isPresent()) {
            Cell cell = selected.get();

            if(state == states.get(0)) {
                return;
            }

            Optional<Plant> plantOp = state.makePlant(cell, money);
            if(plantOp.isPresent()) {
                Plant plant = plantOp.get();
                cell.plant = plant;
            }
        }
    }
}
