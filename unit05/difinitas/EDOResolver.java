package unit05.difinitas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Resolução da EDO: u''(x) + 7u'(x) - u(x) = 2
 * com condições de contorno u(0) = 10 e u(2) = 1
 * usando o método das diferenças finitas.
 */
public class EDOResolver {

    public static ResultadoSistema resolver(double a, double b, double uA, double uB, double deltaX) {
        int n = (int) ((b - a) / deltaX); // número de intervalos
        List<Double> pontos = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            pontos.add(a + i * deltaX); // ignora as bordas (u(0) e u(2))
        }

        int size = pontos.size();
        double[][] matriz = new double[size][size];
        double[] bVetor = new double[size];

        // Coeficientes do sistema tridiagonal
        double coefEsquerda = (1 / (deltaX * deltaX)) - (7 / (2 * deltaX));
        double coefCentral  = (-2 / (deltaX * deltaX)) - 1;
        double coefDireita  = (1 / (deltaX * deltaX)) + (7 / (2 * deltaX));

        // Montagem da matriz e do vetor b
        for (int i = 0; i < size; i++) {
            Arrays.fill(matriz[i], 0);
            if (i == 0) {
                matriz[i][i] = coefCentral;
                matriz[i][i + 1] = coefDireita;
                bVetor[i] = 2 - coefEsquerda * uA;
            } else if (i == size - 1) {
                matriz[i][i - 1] = coefEsquerda;
                matriz[i][i] = coefCentral;
                bVetor[i] = 2 - coefDireita * uB;
            } else {
                matriz[i][i - 1] = coefEsquerda;
                matriz[i][i] = coefCentral;
                matriz[i][i + 1] = coefDireita;
                bVetor[i] = 2;
            }
        }

        // Resolver o sistema linear (Ax = b)
        double[] solucao = resolverSistemaLinear(matriz, bVetor);

        return new ResultadoSistema(pontos, solucao);
    }

    // Resolução simples de sistema linear via eliminação gaussiana (ou usar lib externa)
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

    // Classe auxiliar para empacotar o resultado
    public record ResultadoSistema(List<Double> xs, double[] ys) {}
}
