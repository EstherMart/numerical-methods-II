public class AreaCirculoJacobiano {

    // Integral dupla baseada na mudança de variáveis da aula
    public static double areaCirculo(double R, int N) {
        double soma = 0.0;
        double dalpha = 1.0 / N;
        double dbeta = 2 * Math.PI / N;
        for (int i = 0; i < N; i++) {
            double alpha = dalpha / 2.0 + i * dalpha; // ponto médio
            for (int j = 0; j < N; j++) {
                double beta = dbeta / 2.0 + j * dbeta;
                double J = R * R * alpha; // |J| = R² * α
                double dA = J * dalpha * dbeta;
                soma += dA;
            }
        }
        return soma;
    }

    public static void main(String[] args) {
        double R = 2.0; // exemplo de raio
        int N = 1000; // precisao aumentada com N maior

        double area = areaCirculo(R, N);
        System.out.printf("Área calculada (integral dupla): %.10f\n", area);

        // Valor exato analítico
        double areaExata = Math.PI * R * R;
        System.out.printf("Área exata: %.10f\n", areaExata);
    }
}