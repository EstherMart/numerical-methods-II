import java.util.function.DoubleUnaryOperator;

public class GaussLegendreQuadrature {

    /**
     * Exemplo de função para integração por Gauss-Legendre.
     * Pode ser trocada por qualquer outra função compatível com
     * DoubleUnaryOperator.
     */
    public static double exemploFuncao(double x) {
        return Math.sin(x);
        // return x * x * Math.log(x) + Math.exp(x); // Exemplo comentado original
    }

    /**
     * Função para converter do intervalo [-1, 1] para [a, b], conforme exigido pelo
     * método.
     */
    public static double converterIntervalo(double alpha, double a, double b) {
        return ((b + a) / 2.0) + ((b - a) / 2.0) * alpha;
    }

    /**
     * Quadratura de Gauss-Legendre para n = 2, 3 ou 4 pontos.
     * n: número de pontos de quadratura (2, 3 ou 4)
     * a, b: extremos do intervalo [a, b]
     * func: função a integrar (tipo DoubleUnaryOperator)
     */
    public static double gaussLegendre(double a, double b, int n, DoubleUnaryOperator func) {
        // Arrays para pesos e pontos; programado até n=4.
        double[] pesos = new double[n];
        double[] pontos = new double[n];

        if (n == 2) {
            pesos[0] = 1.0;
            pesos[1] = 1.0;
            pontos[0] = -0.5773502692;
            pontos[1] = 0.5773502692;
        } else if (n == 3) {
            pesos[0] = 0.5555555556;
            pesos[1] = 0.8888888889;
            pesos[2] = 0.5555555556;
            pontos[0] = -0.7745966692;
            pontos[1] = 0.0;
            pontos[2] = 0.7745966692;
        } else if (n == 4) {
            pesos[0] = 0.3478548451;
            pesos[1] = 0.6521451549;
            pesos[2] = 0.6521451549;
            pesos[3] = 0.3478548451;
            pontos[0] = -0.8611363116;
            pontos[1] = -0.3399810436;
            pontos[2] = 0.3399810436;
            pontos[3] = 0.8611363116;
        } else {
            throw new IllegalArgumentException(
                    "Somente n=2, 3 ou 4 são suportados na quadratura de Gauss-Legendre implementada.");
        }

        // Principal: somatório dos termos da quadratura
        double valorIntegral = 0.0;
        for (int i = 0; i < n; i++) {
            double x = converterIntervalo(pontos[i], a, b);
            valorIntegral += pesos[i] * func.applyAsDouble(x);
        }
        valorIntegral *= (b - a) / 2.0;

        return valorIntegral;
    }

    public static void main(String[] args) {
        double a = 0;
        double b = Math.PI;
        DoubleUnaryOperator f = GaussLegendreQuadrature::exemploFuncao; // sin(x)

        System.out.println("*** QUADRATURA GAUSS-LEGENDRE EM [0, π] PARA f(x) = sin(x) ***");
        for (int n = 2; n <= 4; n++) {
            double aprox = gaussLegendre(a, b, n, f);
            System.out.printf("n = %d pontos -> Integral ≈ %.10f\n", n, aprox);
        }
        System.out.printf("\nValor exato: 2.0000000000\n");
    }
}