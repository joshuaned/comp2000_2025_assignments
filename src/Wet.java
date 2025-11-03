public class Wet extends Condition {
    float growthMul = 1f;

    @Override
    public float growthCalc(float r) {
        return r + growthMul;
    }
    
    
}
