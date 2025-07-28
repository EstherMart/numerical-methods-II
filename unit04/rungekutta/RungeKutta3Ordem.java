package unit04.rungekutta;

import unit04.euler.Estado;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class RungeKutta3Ordem {

    public static List<Estado> resolver(double[] S0, double t0, double tf, double dt,
            BiFunction<double[], Double, double[]> F) {
        List<Estado> resultados = new ArrayList<>();
        double[] S = S0.clone();
        double t = t0;

        while (t <= tf + 1e-10) {
            resultados.add(new Estado(t, S));

            double[] k1 = F.apply(S, t);

            double[] Sk2 = new double[S.length];
            for (int i = 0; i < S.length; i++) {
                Sk2[i] = S[i] + 0.5 * dt * k1[i];
            }
            double[] k2 = F.apply(Sk2, t + 0.5 * dt);

            double[] Sk3 = new double[S.length];
            for (int i = 0; i < S.length; i++) {
                Sk3[i] = S[i] - dt * k1[i] + 2 * dt * k2[i];
            }
            double[] k3 = F.apply(Sk3, t + dt);

            for (int i = 0; i < S.length; i++) {
                S[i] += dt * (k1[i] + 4 * k2[i] + k3[i]) / 6.0;
            }

            t += dt;
        }

        return resultados;
    }
}
