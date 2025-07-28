package unit04.euler;

import java.util.Arrays;

public class Estado {
    public double t;
    public double[] S;

    public Estado(double t, double[] S) {
        this.t = t;
        this.S = S.clone();
    }

    @Override
    public String toString() {
        return "t = " + t + ", S = " + Arrays.toString(S);
    }
}
