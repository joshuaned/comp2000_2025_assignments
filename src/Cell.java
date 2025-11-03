import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

public class Cell extends Rectangle {
  static int size = 35;
  char col;
  int row;
  int range = 4;
  Tile tile; // generic
  Plant plant;

  public Cell(char inCol, int inRow, int x, int y) {
    super(x, y, size, size);
    col = inCol;
    row = inRow;

    placeTiles();

    
  }

  public void placeTiles() {
    Random r = new Random(); // for random num

    // randomly decide the tile type
    int temp = r.nextInt(range);

    switch(temp) {
      case 0 -> tile = new Grass(this);
      case 1 -> tile = new Farmland(this);
      case 2 -> tile = new Water(this);
      case 3 -> tile = new ChalkSoil(this);
      default -> System.out.println("Tile type out of range");
    }
  }

  public void placePlant(int x) {
    switch(x) {
      case 0 -> plant = new Carrot(this);
      default -> System.out.println("Plant type out of range");
    }
  }

  public boolean hasPlant() {
    return plant != null;
  }

  public boolean tilePlantable(boolean isLand) {
    if (isLand) {
      return !(tile instanceof Water);
    }
    return true;
  }

  public void paint(Graphics g, Point mousePos) {
    if(contains(mousePos)) {
      g.setColor(Color.GRAY);
    } else {
      tile.paint(g); // instead of white put the biomes
    }
    g.setColor(Color.BLACK);
    g.drawRect(x, y, size, size);

    if(plant != null) plant.paint(g); // check to see if cell has a plant
  }

  @Override
  public boolean contains(Point p) {
    if(p != null) {
      return super.contains(p);
    } else {
      return false;
    }
  }
}
