import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.List;

public abstract class Plant implements PlantInterface {
    float growthTimeMax = 30;
    float growthTime = 0;
    int price = 0;
    int sellValue = 0;
    boolean waterPlant = false;
    boolean isGrown = false;

    float growthMultiplier = 1;

    Rectangle cell;
    Color color = Color.BLACK;
    List<Polygon> display;

    public Plant(Rectangle x, float y, int z, int a) {
        cell = x;
        growthTimeMax = y;
        price = z;
        sellValue = a;
    }

    public void paint(Graphics g) {
        // draw the plant
        for(Polygon p: display) {
            g.setColor(color);
            g.fillPolygon(p);
            g.setColor(color.GRAY);
            g.drawPolygon(p);
        }

        grow();
    }

    @Override
    public void grow() {
        if(growthTime >= growthTimeMax * 60) {
            isGrown = true;
            return;
        } else if (isGrown) {
            return;
        }

        growthTime = growthTime + (1 * growthMultiplier);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
