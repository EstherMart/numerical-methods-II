package unit04.rungekutta;

import unit04.euler.Estado;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class RungeKutta2Ordem {

    public static List<Estado> resolver(double[] S0, double t0, double tf, double dt,
            BiFunction<double[], Double, double[]> F) {
        List<Estado> resultados = new ArrayList<>();
        double[] S = S0.clone();
        double t = t0;

        while (t <= tf + 1e-10) {
            resultados.add(new Estado(t, S));

            // k1 = F(S, t)
            double[] k1 = F.apply(S, t);

            // S intermediário: S + dt * k1
            double[] Sinter = new double[S.length];
            for (int i = 0; i < S.length; i++) {
                Sinter[i] = S[i] + dt * k1[i];
            }

            // k2 = F(S + dt*k1, t + dt)
            double[] k2 = F.apply(Sinter, t + dt);

            // Atualiza S com média de k1 e k2
            for (int i = 0; i < S.length; i++) {
                S[i] += dt * 0.5 * (k1[i] + k2[i]);
            }

            t += dt;
        }

        return resultados;
    }
}
