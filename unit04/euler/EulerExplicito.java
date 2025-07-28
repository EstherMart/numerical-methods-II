package unit04.euler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class EulerExplicito {
    public static List<Estado> resolver(double[] S0, double t0, double tf, double dt,
            BiFunction<double[], Double, double[]> F) {
        List<Estado> resultados = new ArrayList<>();
        double t = t0;
        double[] S = S0.clone();

        while (t <= tf + 1e-10) {
            resultados.add(new Estado(t, S));
            double[] dS = F.apply(S, t);
            for (int i = 0; i < S.length; i++) {
                S[i] += dt * dS[i];
            }
            t += dt;
        }

        return resultados;
    }
}
