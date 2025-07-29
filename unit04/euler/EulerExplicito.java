package unit04.euler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class EulerExplicito {

    /**
     * Implementação do método de Euler Explícito para resolver EDOs
     *
     * @param S0 Vetor com as condições iniciais
     * @param t0 Tempo inicial
     * @param tf Tempo final
     * @param dt Passo de integração
     * @param F  Função do sistema (EDO) no formato BiFunction
     * @return Lista de estados com os valores da solução em cada passo
     */

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
