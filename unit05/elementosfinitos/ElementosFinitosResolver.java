package unit05.elementosfinitos;

import java.util.Arrays;

/**
 * Resolução da EDO via método dos elementos finitos 1D
 * para o problema:
 * u''(x) + 7u'(x) - u(x) = 2,
 * com condições de contorno u(0) = 0 e u(1) = 1,
 * conforme exemplo dado (com deltaX = 1/n).
 *
 */
public class ElementosFinitosResolver {

    public static ResultadoSistema resolver(int n) {
        double deltaX = 1.0 / n;

        // Montar a matriz K global (tamanho n-1)
        double[][] K = new double[n-1][n-1];
        for (int i = 0; i < n-1; i++) {
            Arrays.fill(K[i], 0);
        }

        // Montar o vetor b global (tamanho n-1)
        double[] b = new double[n-1];
        Arrays.fill(b, 0);

        // Parâmetros do elemento local Ki, conforme o Python
        double Li = deltaX;

        // Coeficientes do elemento local Ki (matriz 2x2)
        // N1_in = -1/2, N1_fin = 1/2
        double N1_in = -0.5;
        double N1_fin = 0.5;

        double[][] Ki = new double[2][2];
        Ki[0][0] = (N1_in * (2 / Li) * N1_in * (2 / Li) * (Li / 2)) * 2 + ((8.0 / 3.0) * (Li / 8.0));
        Ki[0][1] = (N1_in * (2 / Li) * N1_fin * (2 / Li) * (Li / 2)) * 2 + ((4.0 / 3.0) * (Li / 8.0));
        Ki[1][0] = (N1_fin * (2 / Li) * N1_in * (2 / Li) * (Li / 2)) * 2 + ((4.0 / 3.0) * (Li / 8.0));
        Ki[1][1] = (N1_fin * (2 / Li) * N1_fin * (2 / Li) * (Li / 2)) * 2 + ((8.0 / 3.0) * (Li / 8.0));

        // Montar a matriz global K somando os elementos locais
        for (int i = 0; i < n - 2; i++) {
            K[i][i] += Ki[0][0];
            K[i][i + 1] += Ki[0][1];
            K[i + 1][i] += Ki[1][0];
            K[i + 1][i + 1] += Ki[1][1];
        }

        // Ajuste das bordas conforme código Python
        K[0][0] += Ki[1][1];
        K[n-2][n-2] += Ki[0][0];

        // Montar o vetor b, com condição de contorno no último elemento
        b[n-2] = Ki[0][1] * (-1);

        // Resolver o sistema linear Kx = b
        double[] x = resolverSistemaLinear(K, b);

        // Construir lista de pontos (x = i * deltaX, ignorando 0 e 1)
        double[] pontos = new double[n-1];
        for (int i = 0; i < n-1; i++) {
            pontos[i] = (i + 1) * deltaX;
        }

        return new ResultadoSistema(pontos, x);
    }

    // Resolver sistema linear via eliminação gaussiana (simples)
    private static double[] resolverSistemaLinear(double[][] A, double[] b) {
        int n = b.length;
        double[][] M = new double[n][n];
        double[] B = b.clone();
        for (int i = 0; i < n; i++) {
            M[i] = A[i].clone();
        }

        // Eliminação
        for (int k = 0; k < n; k++) {
            for (int i = k + 1; i < n; i++) {
                double f = M[i][k] / M[k][k];
                for (int j = k; j < n; j++) {
                    M[i][j] -= f * M[k][j];
                }
                B[i] -= f * B[k];
            }
        }

        // Substituição regressiva
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            x[i] = B[i];
            for (int j = i + 1; j < n; j++) {
                x[i] -= M[i][j] * x[j];
            }
            x[i] /= M[i][i];
        }

        return x;
    }

    // Função exata usada para comparação
    public static double solucaoExata(double x) {
        return (Math.exp(-x) - Math.exp(x)) / (Math.exp(-1) - Math.exp(1));
    }

    // Classe para empacotar resultado
    public record ResultadoSistema(double[] xs, double[] ys) {}

}