package unit04.edos;

import java.util.function.BiFunction;

public class PVI2 {

    // Função que representa o sistema de EDOs da queda livre com resistência do ar
    // Esse sistema foi estudado na aula como exemplo de segunda ordem
    // Reescrevo como um sistema de duas equações de primeira ordem:
    // S[0] = v (velocidade), S[1] = y (posição)
    // dv/dt = -g - (k/m) * v
    // dy/dt = v
    public static BiFunction<double[], Double, double[]> funcao() {
        return (S, t) -> {

            double v = S[0];
            double y = S[1];
            double g = 10.0;
            double k = 0.5;
            double m = 0.5;      // massa do objeto

            // Retorno o vetor com as derivadas: [dv/dt, dy/dt]
            return new double[] {
                -g - (k / m) * v, // equação da velocidade (força resultante)
                v                // equação da posição
            };
        };
    }

    // Condições iniciais: v(0) = 3 m/s, y(0) = 150 m
    // Retorno um vetor com duas variáveis porque o sistema é de ordem 2
    public static double[] condicaoInicial() {
        return new double[] { 3.0, 150.0 };
    }
}
