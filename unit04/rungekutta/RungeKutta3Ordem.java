package unit04.rungekutta;

import unit04.euler.Estado;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class RungeKutta3Ordem {

    /**
     * Método de Runge-Kutta de terceira ordem
     * Implementado com base na fórmula apresentada em aula (RK3 clássico)
     *
     * @param S0 Condições iniciais
     * @param t0 Tempo inicial
     * @param tf Tempo final
     * @param dt Passo de integração
     * @param F  Função do sistema (EDO)
     * @return Lista de Estados com os resultados passo a passo
     */
    public static List<Estado> resolver(double[] S0, double t0, double tf, double dt,
                                        BiFunction<double[], Double, double[]> F) {
        List<Estado> resultados = new ArrayList<>();
        double[] S = S0.clone();  // Estado atual
        double t = t0;

        // Loop de iteração até o tempo final
        while (t <= tf + 1e-10) {
            resultados.add(new Estado(t, S)); // Salvo o estado atual

            // Calculo dos coeficientes de Runge-Kutta
            double[] k1 = F.apply(S, t);  // k1 = f(S, t)

            // Calculo do estado intermediário para k2
            double[] Sk2 = new double[S.length];
            for (int i = 0; i < S.length; i++) {
                Sk2[i] = S[i] + 0.5 * dt * k1[i];
            }
            double[] k2 = F.apply(Sk2, t + 0.5 * dt);  // k2 = f(S + dt/2 * k1, t + dt/2)

            // Outro estado intermediário para k3
            double[] Sk3 = new double[S.length];
            for (int i = 0; i < S.length; i++) {
                Sk3[i] = S[i] - dt * k1[i] + 2 * dt * k2[i];
            }
            double[] k3 = F.apply(Sk3, t + dt);  // k3 = f(S - dt * k1 + 2dt * k2, t + dt)

            // Atualizo o estado usando a média ponderada dos k's
            for (int i = 0; i < S.length; i++) {
                S[i] += dt * (k1[i] + 4 * k2[i] + k3[i]) / 6.0;
            }

            t += dt; // Avanço o tempo
        }

        return resultados;
    }
}
