public class Perfect extends Condition {
    float growthMul = 1.5f;

    @Override
    public float growthCalc(float r) {
        return r + growthMul;
    }
}
