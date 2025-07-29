package unit05.difinitas;

public class FuncoesExatas {

    /**
     * Solução exata da EDO: usada para comparação
     */
    public static double u(double x) {
        return ((Math.exp(-x)) - (Math.exp(x))) / ((Math.exp(-2)) - Math.exp(2)) + 1;
    }
}
