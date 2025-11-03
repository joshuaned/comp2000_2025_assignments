import java.awt.Color;
import java.awt.Graphics;

public abstract class Tile {
    // var
    Color color;
    Cell cell;
    boolean isWater = false;

    float growthRate = 1;
    float growthRateVar;

    float chance = 0f;
    int condition = 0;

    public Tile(Cell x, Color y) {
        cell = x;
        color = y;

        growthRateVar = growthRate;
    }

    // method
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(cell.x, cell.y, 35, 35);
    }

    // determine growthmultipler
    public void updateGrowth(ClientData data) {
        if(data.chance > chance && data.condition != condition) {
            switch(data.condition) {
                // Use the decorator pattern to return a new growthrate
                case 1 -> growthRateVar = new Wet().growthCalc(growthRate);
                case 2 -> growthRateVar = new Windy().growthCalc(growthRate);
                case 3 -> growthRateVar = new Thunder().growthCalc(growthRate);
                case 4 -> growthRateVar = new Perfect().growthCalc(growthRate);
            }
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
