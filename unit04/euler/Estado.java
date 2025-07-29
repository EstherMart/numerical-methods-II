package unit04.euler;

import java.util.Arrays;

public class Estado {
    public double t;       // tempo atual
    public double[] S;     // vetor de variáveis do sistema (ex: y, v, etc)

    public Estado(double t, double[] S) {
        this.t = t;
        this.S = S.clone();
    }

    @Override
    public String toString() {
        // Para facilitar a visualização dos resultados em testes e debug
        return "t = " + t + ", S = " + Arrays.toString(S);
    }
}
