import java.awt.Color;
import java.awt.Graphics;

public abstract class Tile {
    // var
    Color color;
    Cell cell;
    boolean isWater = false;

    float growthRate = 1f;
    float growthRateVar = growthRate;

    float strength = 0f;
    int condition = 0;
    String conditionName = "None";

    public Tile(Cell x, Color y, float growth) {
        cell = x;
        color = y;
        growthRate = growth;
        growthRateVar = growthRate;
    }

    // method
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(cell.x, cell.y, 35, 35);
    }

    // determine growthmultipler
    public void updateGrowth(ClientData data) {
        if(data.condition != condition) {
            Condition temp = null;
            condition = data.condition;
            strength = data.strength;

            switch(data.condition) {
                // Use the decorator pattern to return a new growthrate
                case 1 -> temp = new Wet();
                case 2 -> temp = new Windy();
                case 3 -> temp = new Thunder();
                case 4 -> temp = new Perfect();
            }

            if(temp == null){
                return;
            }
            
            growthRateVar = temp.growthCalc(growthRate) + strength;
            conditionName = temp.getClass().getSimpleName();
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
