package unit04.euler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class EulerImplicito {

    /**
     * Método de Euler Implícito, usando aproximação iterativa para resolver:
     * S_{n+1} = S_n + dt * F(S_{n+1}, t_{n+1})
     *
     * A função F depende do próximo estado, por isso uso iterações para encontrar S_{n+1}
     */
    
    public static List<Estado> resolver(double[] S0, double t0, double tf, double dt,
            BiFunction<double[], Double, double[]> F) {
        List<Estado> resultados = new ArrayList<>();
        double t = t0;
        double[] S = S0.clone();

        while (t <= tf + 1e-10) {
            resultados.add(new Estado(t, S));
            t += dt;

            // chute inicial: estado anterior
            double[] Snext = S.clone();
            for (int iter = 0; iter < 10; iter++) {
                double[] Fguess = F.apply(Snext, t);
                double[] Snew = new double[S.length];
                for (int i = 0; i < S.length; i++) {
                    Snew[i] = S[i] + dt * Fguess[i];
                }

                // convergência simples
                boolean convergiu = true;
                for (int i = 0; i < S.length; i++) {
                    if (Math.abs(Snew[i] - Snext[i]) > 1e-6) {
                        convergiu = false;
                        break;
                    }
                }
                if (convergiu)
                    break;
                Snext = Snew;
            }

            S = Snext;
        }

        return resultados;
    }
}
