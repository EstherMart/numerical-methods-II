package unit04.preditorcorretor;

import unit04.euler.Estado;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class AdamsBashforthMoulton {

    public static List<Estado> resolver(double[] S0, double t0, double tf, double dt,
            BiFunction<double[], Double, double[]> F) {
        List<Estado> resultados = new ArrayList<>();

        // estados iniciais: usar Runge-Kutta de 3ª ordem
        List<Estado> base = unit04.rungekutta.RungeKutta3Ordem.resolver(S0, t0, t0 + 2 * dt, dt, F);
        resultados.addAll(base);

        double t = t0 + 2 * dt;
        double[] S = base.get(2).S.clone();
        double[] S1 = base.get(1).S.clone();
        double[] S2 = base.get(0).S.clone();

        while (t <= tf + 1e-10) {
            double[] F0 = F.apply(S2, t - 2 * dt);
            double[] F1 = F.apply(S1, t - dt);
            double[] F2 = F.apply(S, t);

            // Predição (fórmula 30 - Adams-Bashforth)
            double[] Sp = new double[S.length];
            for (int i = 0; i < S.length; i++) {
                Sp[i] = S[i] + (dt / 12.0) * (23 * F2[i] - 16 * F1[i] + 5 * F0[i]);
            }

            // Correção (fórmula 41 - Adams-Moulton)
            double[] Fc = F.apply(Sp, t + dt);
            double[] Sn = new double[S.length];
            for (int i = 0; i < S.length; i++) {
                Sn[i] = S[i] + (dt / 12.0) * (5 * Fc[i] + 8 * F2[i] - F1[i]);
            }

            resultados.add(new Estado(t + dt, Sn));

            // Atualiza históricos
            S2 = S1;
            S1 = S;
            S = Sn;
            t += dt;
        }

        return resultados;
    }
}
