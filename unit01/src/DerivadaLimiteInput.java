package unit01.src;

import java.util.Scanner;

/**
 * Esta classe implementa métodos de diferenciação numérica (central, para
 * frente, para trás)
 * aplicados à função seno (pois foi o que o professor utilizou em sala). O
 * objetivo é estimar a derivada em um ponto x dado um deltaX.
 * Esta função se baseia na definição fundamental da derivada:
 *
 * f '(x) = lim(h → 0) [f(x + h) - f(x)] / h
 *
 * Onde h é uma pequena variação em x (que aqui chamamos de deltaX).
 * Como não podemos calcular o limite numericamente (já que h → 0 seria h
 * infinitesimal),
 * usamos aproximações com valores pequenos de deltaX.
 *
 * Existem três formas comuns de fazer essa aproximação:
 *
 * ➤ Derivada Para Frente (Forward):
 * [f(x + h) - f(x)] / h
 *
 * ➤ Derivada Para Trás (Backward):
 * [f(x) - f(x - h)] / h
 *
 * ➤ Derivada Central (mais precisa que as anteriores):
 * [f(x + h) - f(x - h)] / (2 * h)
 */
public class DerivadaLimiteInput {

    /**
     * Função a ser derivada. Por padrão, usamos sin(x),
     * mas essa parte pode ser trocada facilmente por outras funções matemáticas.
     *
     * @param x ponto no domínio da função
     * @return valor de f(x)
     */

    public static double func(double x) {
        return Math.sin(x); // Pode trocar por Math.cos(x), Math.exp(x), utilizei essa pois estamos
                            // trabalhando com a função seno e etc..
    }

    /**
     * Calcula a derivada de uma função usando o método especificado.
     *
     * @param x         ponto da derivada
     * @param deltaX    variação de x
     * @param direction tipo de derivada: 0 (central), 1 (backword - à esquerda), 2
     *                  (forward - à direita)
     * @return valor da derivada no ponto x
     */

    public static double derivada(double x, double deltaX, int direction) {
        switch (direction) {
            case 0: // Central
                return (func(x + deltaX) - func(x - deltaX)) / (2 * deltaX);
            case 1: // Para trás (backward)
                return (func(x) - func(x - deltaX)) / deltaX;
            case 2: // Para frente (forward)
                return (func(x + deltaX) - func(x)) / deltaX;
            default:
                System.out.println("Direção inválida. Use 0 (central), 1 (para trás), ou 2 (para frente).");
                return Double.NaN;
        }
    }

    /**
     * Interface simples em terminal para interação com o usuário -- a ideia é
     * facilitar na hora de testar...
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println("    DERIVADA NUMÉRICA - UTILIZANDO LIMITE     ");
        System.out.println("        Função usada: f(x) = sen(x)           ");
        System.out.println("==============================================\n");

        System.out.print("Digite o ponto x em que deseja calcular a derivada: ");
        String xInput = scanner.next().replace(',', '.'); // Só tratando caso queira inserir o valor de outra forma.
        double x = Double.parseDouble(xInput);

        System.out.print("Digite o valor de delta x: ");
        String dxInput = scanner.next().replace(',', '.');
        double deltaX = Double.parseDouble(dxInput);

        int direction;
        double resultado;

        while (true) {
            System.out.println("\nEscolha o método de derivação:");
            System.out.println("[0] Derivada Central");
            System.out.println("[1] Derivada Para Trás (Backward)");
            System.out.println("[2] Derivada Para Frente (Forward)");
            System.out.print("Sua escolha: ");
            direction = scanner.nextInt();

            resultado = derivada(x, deltaX, direction);

            if (!Double.isNaN(resultado)) {
                break;
            }
        }

        System.out.printf("\n➡ Resultado: A derivada de sen(x) em x = %.5f é aproximadamente %.8f\n", x, resultado);
        scanner.close();
    }
}
