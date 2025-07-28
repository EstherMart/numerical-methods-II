public class AreaElipseJacobiano {

    // Integral dupla baseada na mudança de variáveis do documento para a elipse
    public static double areaElipse(double a, double b, int N) {
        double soma = 0.0;
        double dalpha = 1.0 / N;
        double dbeta = 2 * Math.PI / N;
        for (int i = 0; i < N; i++) {
            double alpha = dalpha / 2.0 + i * dalpha; // ponto médio
            for (int j = 0; j < N; j++) {
                double beta = dbeta / 2.0 + j * dbeta;
                double J = a * b * alpha;
                double dA = J * dalpha * dbeta;
                soma += dA;
            }
        }
        return soma;
    }

    public static void main(String[] args) {
        double a = 3.0; // semieixo maior
        double b = 1.5; // semieixo menor
        int N = 1000; // precisão numérica

        double area = areaElipse(a, b, N);
        System.out.printf("Área calculada (integral dupla): %.10f\n", area);

        // Valor analítico exato
        double areaExata = Math.PI * a * b;
        System.out.printf("Área exata: %.10f\n", areaExata);

        // Observação: Se a = b = R, a elipse vira um círculo e a área fica π R², como
        // esperado
    }
}