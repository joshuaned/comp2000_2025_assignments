import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Stage {
  Grid grid;
  List<Actor> actors;
  int money = 10;

  // State controller
  Controls controls = new Controls();
  // Website client grabber
  Client client = new Client();

  public Stage() {
    grid = new Grid();
    actors = new ArrayList<Actor>();
    actors.add(new Cat(grid.cellAtColRow(0, 0).get()));
    actors.add(new Dog(grid.cellAtColRow(0, 15).get()));
    actors.add(new Bird(grid.cellAtColRow(12, 9).get()));

    // Start the client request stream
    new Thread(() -> {
      client.request();
    }).start();
  }

  public void paint(Graphics g, Point mouseLoc) {
    grid.paint(g, mouseLoc);
    for(Actor a: actors) {
      a.paint(g);
    }

    // always visable UI
    g.drawString("Money: $" + String.valueOf(money), 740, 15);
    g.drawString(String.valueOf(controls.state), 820, 15);

    Optional<Cell> underMouse = grid.cellAtPoint(mouseLoc);
    if(underMouse.isPresent()) {
      Cell hoverCell = underMouse.get();
      g.setColor(Color.DARK_GRAY);
      g.drawString(String.valueOf(hoverCell.tile), 740, 30);
      g.drawString(String.valueOf(hoverCell.col) + String.valueOf(hoverCell.row), 740, 45);

      if(hoverCell.hasPlant()){
        g.drawString("Planted: " + String.valueOf(hoverCell.plant), 800, 30);
        if (hoverCell.plant.isGrown) {
          g.drawString("Ready to harvest!", 800, 45);
        } else {
          DecimalFormat df = new DecimalFormat();
          df.setMaximumFractionDigits(2); // how many digits to show
          g.drawString("Time until grown: " + String.valueOf(df.format(hoverCell.plant.growthTimeMax - hoverCell.plant.growthTime/60)), 800, 45);
        }
      }
    }

    controls.paint(g, mouseLoc);
  }

  public void mouseClicked(Point p) {
    Optional<Cell> selected =  grid.cellAtPoint(p);
    controls.mouseClicked(p, selected, this);

    // put plant into cell depending on currentMode
    // Optional<Cell> selected =  grid.cellAtPoint(p);
    // if(selected.isPresent()) {
    //   Cell cell = selected.get();
    //   if(!(currentMode instanceof CollectButton)) { // all other buttons will house instances of plant
    //     // check if its a water plant
    //     boolean tileCheck = (cell.tile.isWater && currentMode.plant.waterPlant || !cell.tile.isWater && !currentMode.plant.waterPlant);
    //     if(tileCheck && money >= currentMode.plant.price) {
    //       cell.plant = currentMode.makePlant(cell);
    //       money -= cell.plant.price;
    //       // push the growthBoost into plant object
    //       cell.plant.growthMultiplier += cell.tile.growthBoost;
    //     }
    //   } else if (currentMode instanceof CollectButton && cell.hasPlant()) {
    //     if (cell.plant.isGrown) {
    //       money += cell.plant.sellValue;
    //       cell.plant = null;
    //     }
    //   }
    // }
  }
}
