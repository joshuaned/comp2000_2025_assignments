import java.awt.Color;

public class Water extends Tile {
    public Water(Cell x) {
        super(x, Color.BLUE, 1);
        isWater = true;
    }
}
