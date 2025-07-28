public class AreaTrianguloJacobiano {

    // Calcula a área do triângulo via mudança de variáveis e jacobiano, conforme aula 15
    public static double areaTriangulo(double B, double H, int N) {
        double soma = 0.0;
        double dAlpha = 1.0 / N;
        double dBeta = 1.0 / N;
        for (int i = 0; i < N; i++) {
            double alpha = dAlpha / 2.0 + i * dAlpha; // ponto médio
            for (int j = 0; j < N; j++) {
                double beta = dBeta / 2.0 + j * dBeta;
                // |J| = BH(1 - beta)
                double dA = B * H * (1 - beta) * dAlpha * dBeta;
                soma += dA;
            }
        }
        return soma;
    }

    public static void main(String[] args) {
        double B = 3.0;
        double H = 5.0;
        int N = 1000; // quanto maior, mais precisa a aproximação da integral dupla

        double area = areaTriangulo(B, H, N);
        System.out.printf("Área calculada (integral dupla): %.10f\n", area);

        // Para comparar com o valor exato:
        System.out.printf("Área exata (BH/2): %.10f\n", B * H / 2.0);
    }
}