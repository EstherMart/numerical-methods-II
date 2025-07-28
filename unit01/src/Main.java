package src;

import interfaces.Filosofia;
import enums.OrdemErro;
import java.util.function.DoubleUnaryOperator;

public class Main {
    public static void main(String[] args) {
        // Função de teste: f(x) = x³ -> f'(x) = 3x², f''(x) = 6x, f'''(x) = 6
        java.util.function.DoubleUnaryOperator func = (x) -> x * x * x;
        double x = 2.0;
        double delta = 1e-4;

        Filosofia[] filosofias = {
                new DerivadaBackward(),
                new DerivadaCentral(),
                new DerivadaForward()
        };
        String[] nomesFilosofia = {"Backward", "Central", "Forward"};

        System.out.printf("Função: f(x) = x^3\nPonto x = %.4f\tDelta = %.1e\n\n", x, delta);

        System.out.printf("Derivada Analítica primeira: f'(x) = %.5f\n", 3 * x * x);
        System.out.printf("Derivada Analítica segunda:  f''(x) = %.5f\n", 6 * x);
        System.out.printf("Derivada Analítica terceira: f'''(x) = %.5f\n", 6.0);
        System.out.println();

        for (int i = 0; i < filosofias.length; i++) {
            Filosofia filosofia = filosofias[i];
            String nome = nomesFilosofia[i];

            System.out.println("===" + nome + "===\n");

            // Primeira Derivada
            System.out.println("Primeira derivada:");
            for (OrdemErro erro : OrdemErro.values()) {
                try {
                    double res = filosofia.derivadaPrimeira(func, x, delta, erro);
                    System.out.printf("Ordem %10s: %f\n", erro, res);
                } catch (Exception e) {
                    System.out.printf("Ordem %10s: não suportado\n", erro);
                }
            }
            System.out.println();

            // Segunda Derivada
            System.out.println("Segunda derivada:");
            for (OrdemErro erro : OrdemErro.values()) {
                try {
                    double res2 = filosofia.derivadaSegunda(func, x, delta, erro);
                    System.out.printf("Ordem %10s: %f\n", erro, res2);
                } catch (Exception e) {
                    System.out.printf("Ordem %10s: não suportado\n", erro);
                }
            }
            System.out.println();

            // Terceira Derivada
            System.out.println("Terceira derivada:");
            for (OrdemErro erro : OrdemErro.values()) {
                try {
                    double res3 = filosofia.derivadaTerceira(func, x, delta, erro);
                    System.out.printf("Ordem %10s: %f\n", erro, res3);
                } catch (Exception e) {
                    System.out.printf("Ordem %10s: não suportado\n", erro);
                }
            }
            System.out.println();
        }

    }
}