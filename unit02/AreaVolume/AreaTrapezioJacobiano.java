public class AreaTrapezioJacobiano {

    // Calcula a área do trapézio via integral dupla usando o jacobiano da mudança
    // de variáveis
    // B = base inferior, C = início da base superior, D = final da base superior, H
    // = altura
    public static double areaTrapezio(double B, double C, double D, double H, int N) {
        double soma = 0.0;
        double dAlpha = 1.0 / N;
        double dBeta = 1.0 / N;
        for (int i = 0; i < N; i++) {
            double alpha = dAlpha / 2.0 + i * dAlpha;
            for (int j = 0; j < N; j++) {
                double beta = dBeta / 2.0 + j * dBeta;
                double J = B * H + H * (D - C - B) * beta;
                double dA = J * dAlpha * dBeta;
                soma += dA;
            }
        }
        return soma;
    }

    public static void main(String[] args) {
        double B = 3.0; // base inferior
        double C = 1.0; // início da base superior
        double D = 5.0; // final da base superior
        double H = 2.0; // altura
        int N = 1000; // precisão (mais alto = mais preciso)

        double area = areaTrapezio(B, C, D, H, N);
        System.out.printf("Área calculada (integral dupla): %.10f\n", area);

        // Fórmula exata para comparar
        double areaExata = H / 2.0 * (B + (D - C));
        System.out.printf("Área exata: %.10f\n", areaExata);
    }
}