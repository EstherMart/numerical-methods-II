package unit04.edos;

import java.util.function.BiFunction;

public class PVI1 {
    public static BiFunction<double[], Double, double[]> funcao() {
        return (S, t) -> new double[] { (3.0 / 4.0) * S[0] };
    }

    public static double[] condicaoInicial() {
        return new double[] { 2.0 };
    }
}
