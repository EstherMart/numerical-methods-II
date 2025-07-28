public class AreaSuperficieParaboloide {

    // Pontos e pesos de Gauss-Legendre (n=3) no intervalo [-1, 1]
    static final double[] pontos = { -0.774596669241483,
            0.0,
            0.774596669241483 };
    static final double[] pesos = { 0.555555555555556,
            0.888888888888889,
            0.555555555555556 };

    public static void main(String[] args) {
        // Limites da região
        double xmin = -50.0, xmax = 50.0;
        double ymin = -50.0, ymax = 50.0;

        // Mudança de variável linear: x(α) = 50α, α ∈ [−1, 1]; y(β) = 50β, β ∈ [−1, 1]
        double J = 2500.0; // |J| = det [50 0; 0 50] = 50*50 = 2500

        double area = 0.0;
        // Integração dupla com quadratura de Gauss-Legendre 3x3
        for (int i = 0; i < 3; i++) {
            double alpha = pontos[i]; // α ∈ [-1,1]
            double x = 50 * alpha;

            for (int j = 0; j < 3; j++) {
                double beta = pontos[j]; // β ∈ [-1,1]
                double y = 50 * beta;

                // Função g(x, y) = sqrt((0.4∂f/∂x)^2 + (0.4∂f/∂y)^2 + 1)
                // Para f(x, y) = 0.2(x^2 - y^2), tem-se:
                // ∂f/∂x = 0.4x, ∂f/∂y = -0.4y
                double gx = 0.4 * x;
                double gy = 0.4 * y;
                double integrando = Math.sqrt(gx * gx + gy * gy + 1);

                area += pesos[i] * pesos[j] * integrando;
            }
        }
        area *= J;

        System.out.printf("Área aproximada da superfície (Gauss-Legendre 3x3): %.2f m²\n", area);
        // Resultado esperado ~146328.37 m² (segundo o documento)
    }
}