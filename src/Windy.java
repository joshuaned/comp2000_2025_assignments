public class Windy extends Condition {
    float growthMul = -0.5f;
    Condition wet = new Wet();

    @Override
    public float growthCalc(float r) {
        return wet.growthCalc(r) + growthMul;
    }
    
    
}
