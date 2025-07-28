public class AreaElipseSuperficieJacobiano {

    static final double[] pontos = { -0.774596669241483, 0.0, 0.774596669241483 };
    static final double[] pesos = { 0.555555555555556, 0.888888888888889, 0.555555555555556 };

    // Exemplo: f(x, y) = z = 0.2(x^2 - y^2) -- aqui você pode ajustar para a
    // superfície desejada
    public static double integrando(double x, double y) {
        double gx = 0.4 * x;
        double gy = 0.4 * y;
        // Raiz do "elemento de área" para a superfície z = 0.2(x^2-y^2)
        return Math.sqrt(gx * gx + gy * gy + 1);
    }

    // 1) Mudança de variável elíptica (pol. generalizada)
    // x = 40 * alpha * cos(beta), y = 40 * alpha * sin(beta)
    public static double area_gauss_legendre_polar() {
        int N_alpha = 3, N_beta = 3;
        double soma = 0.0;
        for (int i = 0; i < N_alpha; i++) {
            double alpha = (pontos[i] + 1.0) / 2.0; // transformar [-1,1] para [0,1]
            double w_alpha = pesos[i] / 2.0; // ajustar peso para [0,1]
            for (int j = 0; j < N_beta; j++) {
                double beta = Math.PI * (pontos[j] + 1.0); // transformar [-1,1] para [0,2pi]
                double w_beta = pesos[j] * Math.PI; // ajustar peso para [0,2pi]

                double x = 40.0 * alpha * Math.cos(beta);
                double y = 40.0 * alpha * Math.sin(beta);
                double J = 1600.0 * alpha; // 40 * 40 * alpha

                soma += w_alpha * w_beta * integrando(x, y) * J;
            }
        }
        return soma;
    }

    // 2) Mudança de variável quadrada (como no problema 1, mas só integra onde está
    // na elipse!)
    public static double area_gauss_legendre_cartesiano() {
        int N = 3;
        double soma = 0.0;
        for (int i = 0; i < N; i++) {
            double alpha = pontos[i];
            double w_alpha = pesos[i];
            for (int j = 0; j < N; j++) {
                double beta = pontos[j];
                double w_beta = pesos[j];

                double x = 40.0 * alpha;
                double y = 40.0 * beta;
                // Verificar se o ponto está dentro da elipse (bom para integral parcial ou
                // comparar)
                if ((x * x) / 1600.0 + (y * y) / 1600.0 <= 1) {
                    double J = 1600.0; // |det(40,0;0,40)| = 40 * 40
                    soma += w_alpha * w_beta * integrando(x, y) * J;
                }
            }
        }
        return soma;
    }

    public static void main(String[] args) {
        double area_polar = area_gauss_legendre_polar();
        double area_cartesiana = area_gauss_legendre_cartesiano();

        System.out.printf("Área da superfície elíptica (mudança de variável tipo elipse/polar): %.2f m²\n", area_polar);
        System.out.printf("Área da superfície elíptica (mudança de variável tipo cartesian/quadrada): %.2f m²\n",
                area_cartesiana);
    }
}