public class Thunder extends Condition {
    float growthMul = -1f;
    Condition windy = new Windy();

    @Override
    public float growthCalc(float r) {
        return windy.growthCalc(r) + growthMul;
    }
    
    
}
