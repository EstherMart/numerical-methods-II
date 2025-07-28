import java.util.function.DoubleUnaryOperator;

public class GaussLaguerreQuadrature {

    /**
     * Exemplo de função para quadratura de Gauss-Laguerre: f(x) = x^2
     * Pode ser trocada por qualquer função adequada.
     */
    public static double exemploFuncao(double x) {
        return x * x;
    }

    /**
     * Método para calcular a integral pelo método de Gauss-Laguerre (n = 2, 3 ou 4
     * pontos).
     *
     * @param n    Número de pontos (ordem da quadratura, até 4)
     * @param func Função a integrar: DoubleUnaryOperator
     * @return Valor aproximado da integral de ∫₀^∞ f(x) e^(-x) dx
     */
    public static double gaussLaguerre(int n, DoubleUnaryOperator func) {
        double[] pesos = new double[n];
        double[] pontos = new double[n];

        if (n == 2) {
            pesos[0] = 0.8535533906;
            pesos[1] = 0.1464466094;
            pontos[0] = 0.5857864376;
            pontos[1] = 3.4142135624;
        } else if (n == 3) {
            pesos[0] = 0.7110930099;
            pesos[1] = 0.2785177336;
            pesos[2] = 0.0103892565;
            pontos[0] = 0.4157745568;
            pontos[1] = 2.2942803603;
            pontos[2] = 6.2899450829;
        } else if (n == 4) {
            pesos[0] = 0.6031541043;
            pesos[1] = 0.3574186924;
            pesos[2] = 0.0388879085;
            pesos[3] = 0.0005392947;
            pontos[0] = 0.3225476896;
            pontos[1] = 1.7457611012;
            pontos[2] = 4.5366202969;
            pontos[3] = 9.3950709123;
        } else {
            throw new IllegalArgumentException("Somente n = 2, 3 ou 4 são suportados.");
        }

        double valorIntegral = 0.0;
        for (int i = 0; i < n; i++) {
            valorIntegral += pesos[i] * func.applyAsDouble(pontos[i]);
        }
        return valorIntegral;
    }

    public static void main(String[] args) {
        DoubleUnaryOperator func = GaussLaguerreQuadrature::exemploFuncao;

        System.out.println("*** QUADRATURA DE GAUSS-LAGUERRE PARA ∫₀^∞ x^2·e^{-x} dx ***");
        for (int n = 2; n <= 4; n++) {
            double aprox = gaussLaguerre(n, func);
            System.out.printf("n = %d pontos -> Integral ≈ %.10f\n", n, aprox);
        }
        // Valor exato: Gamma(3) = 2!
        System.out.printf("\nValor exato (f(x) = x^2): 2.0000000000\n");
    }
}