package unit04.edos;

import java.util.function.BiFunction;

public class PVI2 {
    public static BiFunction<double[], Double, double[]> funcao() {
        return (S, t) -> {
            double v = S[0];
            double y = S[1];
            double g = 10.0;
            double k = 0.5;
            double m = 0.5;
            return new double[] {
                    -g - (k / m) * v, // dv/dt
                    v // dy/dt
            };
        };
    }

    public static double[] condicaoInicial() {
        return new double[] { 3.0, 150.0 };
    }
}
