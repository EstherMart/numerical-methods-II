import java.util.function.DoubleUnaryOperator;

public class GaussHermiteQuadrature {

    /**
     * Exemplo de função para quadratura de Gauss-Hermite: f(x) = x^2
     * Pode ser trocado facilmente nesta linha por qualquer outra função compatível.
     */
    public static double exemploFuncao(double x) {
        return x * x;
    }

    /**
     * Implementa a quadratura de Gauss-Hermite (até n=4)
     * 
     * @param n    Número de pontos/ordem (2 a 4 suportado)
     * @param func Função a integrar (sem o peso e^{-x^2})
     * @return Aproximação numérica da integral ∫_{-∞}^{+∞} f(x)·e^{-x^2} dx
     */
    public static double gaussHermite(int n, DoubleUnaryOperator func) {
        double[] pesos = new double[n];
        double[] pontos = new double[n];

        if (n == 2) {
            pesos[0] = 0.8862269255;
            pesos[1] = 0.8862269255;
            pontos[0] = -0.7071067812;
            pontos[1] = 0.7071067812;
        } else if (n == 3) {
            pesos[0] = 0.2954089756;
            pesos[1] = 1.1816359006;
            pesos[2] = 0.2954089752;
            pontos[0] = -1.2247448714;
            pontos[1] = 0.0;
            pontos[2] = 1.2247448714;
        } else if (n == 4) {
            pesos[0] = 0.0813128354;
            pesos[1] = 0.8049140900;
            pesos[2] = 0.8049140900;
            pesos[3] = 0.0813128354;
            pontos[0] = -1.6506801239;
            pontos[1] = -0.5246476233;
            pontos[2] = 0.5246476233;
            pontos[3] = 1.6506801239;
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
        DoubleUnaryOperator func = GaussHermiteQuadrature::exemploFuncao; // f(x) = x^2

        System.out.println("*** GAUSS-HERMITE PARA ∫_{-∞}^{+∞} x^2·e^{-x^2} dx ***");
        for (int n = 2; n <= 4; n++) {
            double aprox = gaussHermite(n, func);
            System.out.printf("n = %d pontos -> Integral ≈ %.10f\n", n, aprox);
        }
        // Valor exato: sqrt(pi)/2 ≈ 0.8862269255
        System.out.printf("\nValor exato (f(x) = x^2): %.10f\n", Math.sqrt(Math.PI) / 2);
    }
}