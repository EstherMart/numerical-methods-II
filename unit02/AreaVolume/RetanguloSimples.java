import java.util.function.DoubleUnaryOperator;

public class RetanguloSimples {
    // Método do retângulo (ponto médio)
    public static double integraRetangulo(double a, double b, int N, DoubleUnaryOperator f) {
        double Dx = (b - a) / N;
        double soma = 0.0;
        for (int i = 0; i < N; i++) {
            double xi = a + Dx / 2.0 + i * Dx;
            soma += Dx * f.applyAsDouble(xi);
        }
        return soma;
    }

    // Funções das questões dos documentos:
    // 1) f(x) = x^{-2/3}
    public static double func1(double x) {
        return Math.pow(x, -2.0 / 3.0);
    }

    // 2) f(x) = 1/sqrt(x)
    public static double func2(double x) {
        return 1.0 / Math.sqrt(x);
    }

    // 3) f(x) = sqrt(x)
    public static double func3(double x) {
        return Math.sqrt(x);
    }

    public static void main(String[] args) {
        double a = 0.0, b = 1.0;
        int N = 100000; // número de subintervalos suficiente para boa aproximação

        // Integral de x^{-2/3}
        double resultado1 = integraRetangulo(a, b, N, RetanguloSimples::func1);
        System.out.printf("Integrando f(x) = x^{-2/3} em [0,1] por retângulos: %.10f\n", resultado1);

        // Integral de 1/sqrt(x)
        double resultado2 = integraRetangulo(a, b, N, RetanguloSimples::func2);
        System.out.printf("Integrando f(x) = 1/sqrt(x) em [0,1] por retângulos: %.10f\n", resultado2);

        // Integral de sqrt(x)
        double resultado3 = integraRetangulo(a, b, N, RetanguloSimples::func3);
        System.out.printf("Integrando f(x) = sqrt(x) em [0,1] por retângulos: %.10f\n", resultado3);
    }
}