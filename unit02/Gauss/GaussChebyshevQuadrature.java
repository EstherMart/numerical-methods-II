import java.util.function.DoubleUnaryOperator;

public class GaussChebyshevQuadrature {

    /**
     * Exemplo de função para a quadratura Gauss-Chebyshev: f(x) = x^2
     * Pode ser substituída facilmente.
     */
    public static double exemploFuncao(double x) {
        return x * x;
    }

    /**
     * Quadratura de Gauss-Chebyshev para n pontos.
     * Integrais do tipo: ∫_{-1}^{1} f(x) / sqrt(1-x^2) dx
     * Para peso w(x) = 1 / sqrt(1-x^2)
     *
     * @param n    Número de pontos
     * @param func Função a integrar
     * @return Valor aproximado da integral
     */
    public static double gaussChebyshev(int n, DoubleUnaryOperator func) {
        double valorIntegral = 0.0;
        double peso = Math.PI / n; // Todos os pesos são iguais em Gauss-Chebyshev

        // Cálculo dos pontos e soma dos termos
        for (int i = 1; i <= n; i++) {
            double ponto = Math.cos((2.0 * i - 1) * Math.PI / (2.0 * n));
            valorIntegral += peso * func.applyAsDouble(ponto);
        }
        return valorIntegral;
    }

    public static void main(String[] args) {
        DoubleUnaryOperator func = GaussChebyshevQuadrature::exemploFuncao; // f(x) = x^2

        System.out.println("*** GAUSS-CHEBYSHEV PARA ∫_{-1}^{1} x^2 / sqrt(1-x^2) dx ***");
        for (int n = 2; n <= 6; n++) {
            double aprox = gaussChebyshev(n, func);
            System.out.printf("n = %d pontos -> Integral ≈ %.10f\n", n, aprox);
        }
        // Valor exato para x^2: π/4
        System.out.printf("\nValor exato (f(x) = x^2): %.10f\n", Math.PI / 4);
    }
}