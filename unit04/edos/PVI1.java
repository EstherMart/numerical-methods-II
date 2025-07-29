package unit04.edos;

import java.util.function.BiFunction;

public class PVI1 {

    // Função que representa a EDO: dy/dt = (3/4) * y
    // Aqui uso BiFunction para generalizar a estrutura para outros métodos
    // S[0] representa o valor atual de y(t)
    public static BiFunction<double[], Double, double[]> funcao() {
        return (S, t) -> new double[] { (3.0 / 4.0) * S[0] };
    }

    // Condição inicial do problema: y(0) = 2
    // Retorno um vetor com um único valor porque essa EDO é de primeira ordem e escalar
    public static double[] condicaoInicial() {
        return new double[] { 2.0 };
    }
}
