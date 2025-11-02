import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Optional;

public abstract class Button extends Rectangle {
    Plant plant;

    public Button(int x, int y) {
        super(x, y, 150, 60);
    }

    public void paint(Graphics g, Point mousePos) {
        if (contains(mousePos)) {
            g.setColor(Color.GRAY);
        } else {
            g.setColor(Color.WHITE);
        }
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);

        if (plant != null) {
            g.drawString(String.valueOf(plant), x + width/3, y + height/4);
            g.drawString("$ " + String.valueOf(plant.price), x + width/3, y + height/2);
            g.drawString("Type: " + (plant.waterPlant ? "Water" : "Land"), x + width/3, y + height/4*3 - 3);
            g.drawString("Growth time: " + String.valueOf(plant.growthTimeMax), x + width/3, y + height - 3);
            plant.paint(g);
        } else {
            g.drawString("Collect Mode", x + width/3 - 10, y + height/2);
        }
    }

    // this allows the buttons to make new plants
    public Optional<Plant> makePlant(Cell cell, Stage stage) {
        Optional<Plant> newPlant = Optional.empty();

        try {
            if(plant.waterPlant == cell.tile.isWater && stage.money >= this.plant.price) {
                stage.money -= this.plant.price;
                newPlant = Optional.of(plant.getClass().getDeclaredConstructor(Rectangle.class).newInstance(cell));
            }
            return newPlant;
        } catch (Exception e) {
            System.out.println("There was an issue creating a new instance of the plant.");
            return null;
        }
    }

    @Override
    public boolean contains(Point p) {
        if(p != null) {
            return super.contains(p);
        } else {
            return false;
        }
    }

    public String toString(){
        if (plant == null) {
            return "Collecting";
        } else {
            return ("Planting " + String.valueOf(plant));
        }
    }
}
