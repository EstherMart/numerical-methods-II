package unit04.preditorcorretor;

import unit04.euler.Estado;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class AdamsBashforthMoulton {

    /**
     * Método preditor-corretor de Adams-Bashforth-Moulton de 4ª ordem
     * Primeiro uso Adams-Bashforth (explícito) para prever o próximo ponto,
     * depois uso Adams-Moulton (implícito) como corretor.
     *
     * @param S0 Condições iniciais
     * @param t0 Tempo inicial
     * @param tf Tempo final
     * @param dt Passo de integração
     * @param F  Função do sistema (EDO)
     * @return Lista com os estados resolvidos
     */
    public static List<Estado> resolver(double[] S0, double t0, double tf, double dt,
                                        BiFunction<double[], Double, double[]> F) {
        List<Estado> resultados = new ArrayList<>();

        // Para iniciar o método, preciso de 3 pontos anteriores
        // Uso Runge-Kutta de 3ª ordem para gerar os 3 primeiros estados
        List<Estado> base = unit04.rungekutta.RungeKutta3Ordem.resolver(S0, t0, t0 + 2 * dt, dt, F);
        resultados.addAll(base); // Salvo os estados iniciais

        // Estado atual é o último de Runge-Kutta
        double t = t0 + 2 * dt;
        double[] S = base.get(2).S.clone();   // S(t)
        double[] S1 = base.get(1).S.clone();  // S(t - dt)
        double[] S2 = base.get(0).S.clone();  // S(t - 2dt)

        // Loop principal do método de passo múltiplo
        while (t <= tf + 1e-10) {

            // Avalio a função F nos três estados anteriores
            double[] F0 = F.apply(S2, t - 2 * dt);  // F(t - 2dt)
            double[] F1 = F.apply(S1, t - dt);      // F(t - dt)
            double[] F2 = F.apply(S, t);            // F(t)

            // Predição com Adams-Bashforth de 4ª ordem (fórmula 30)
            double[] Sp = new double[S.length]; // estado predito
            for (int i = 0; i < S.length; i++) {
                Sp[i] = S[i] + (dt / 12.0) * (23 * F2[i] - 16 * F1[i] + 5 * F0[i]);
            }

            // Correção com Adams-Moulton de 4ª ordem (fórmula 41)
            double[] Fc = F.apply(Sp, t + dt); // F(t + dt) estimado com Sp
            double[] Sn = new double[S.length]; // estado corrigido
            for (int i = 0; i < S.length; i++) {
                Sn[i] = S[i] + (dt / 12.0) * (5 * Fc[i] + 8 * F2[i] - F1[i]);
            }

            // Armazeno o estado corrigido
            resultados.add(new Estado(t + dt, Sn));

            // Atualizo os estados para o próximo passo
            S2 = S1;
            S1 = S;
            S = Sn;
            t += dt;
        }

        return resultados;
    }
}
