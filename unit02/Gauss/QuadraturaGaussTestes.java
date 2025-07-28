import java.util.function.DoubleUnaryOperator;

public class QuadraturaGaussTestes {

    // ---- Gauss-Legendre ----
    // Exemplo: f(x) = sin(x) (ajuste caso queira função diferente)
    public static double funcaoLegendre(double x) {
        return Math.sin(x);
    }

    public static double gaussLegendre(double a, double b, int n) {
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
            throw new IllegalArgumentException("n=2,3,4 suportados em Gauss-Legendre");
        }
        double soma = 0.;
        for (int i = 0; i < n; i++) {
            double x = ((b + a) / 2.0) + ((b - a) / 2.0) * pontos[i];
            soma += pesos[i] * funcaoLegendre(x);
        }
        return soma * (b - a) / 2.;
    }

    // ---- Gauss-Hermite ----
    // f(x) = x^2 como exemplo
    public static double funcaoHermite(double x) {
        return x * x;
    }

    public static double gaussHermite(int n) {
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
            throw new IllegalArgumentException("n=2,3,4 suportados em Gauss-Hermite");
        }
        double soma = 0.;
        for (int i = 0; i < n; i++) {
            soma += pesos[i] * funcaoHermite(pontos[i]);
        }
        return soma;
    }

    // ---- Gauss-Laguerre ----
    // f(x) = x^2 como exemplo
    public static double funcaoLaguerre(double x) {
        return x * x;
    }

    public static double gaussLaguerre(int n) {
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
            throw new IllegalArgumentException("n=2,3,4 suportados em Gauss-Laguerre");
        }
        double soma = 0.;
        for (int i = 0; i < n; i++) {
            soma += pesos[i] * funcaoLaguerre(pontos[i]);
        }
        return soma;
    }

    // ---- Gauss-Chebyshev ----
    // f(x) = x^2 como exemplo
    public static double funcaoChebyshev(double x) {
        return x * x;
    }

    public static double gaussChebyshev(int n) {
        double valorIntegral = 0.0;
        double peso = Math.PI / n;
        for (int i = 1; i <= n; i++) {
            double ponto = Math.cos((2.0 * i - 1) * Math.PI / (2.0 * n));
            valorIntegral += peso * funcaoChebyshev(ponto);
        }
        return valorIntegral;
    }

    // ---- MAIN ----
    public static void main(String[] args) {
        System.out.printf("Gauss-Legendre:   %.8f\n", gaussLegendre(1, 3, 3));
        System.out.printf("Gauss-Hermite:    %.8f\n", gaussHermite(3));
        System.out.printf("Gauss-Laguerre:   %.8f\n", gaussLaguerre(3));
        System.out.printf("Gauss-Chebyshev:  %.8f\n", gaussChebyshev(4));
    }
}