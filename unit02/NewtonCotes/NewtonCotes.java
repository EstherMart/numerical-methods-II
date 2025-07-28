
import java.util.function.DoubleUnaryOperator;

public class NewtonCotes {

    /*
     * IMPLEMENTAÇÃO DAS FÓRMULAS DE NEWTON-COTES
     * 
     * Aqui implemento tanto as fórmulas fechadas (includem pontos dos extremos)
     * quanto as abertas (não incluem os extremos), para polinômios de grau 1 até 4
     * e suas versões compostas.
     * 
     * Todas as funções recebem o intervalo [a, b] (e n nos casos compostos)
     * e uma função (DoubleUnaryOperator) representando f(x).
     */

    // FECHADAS ---------------------------------------

    // Trapezio (grau 1, usa f nos extremos)
    public static double ncFechadoTrapezio(double a, double b, DoubleUnaryOperator f) {
        return (b - a) * (f.applyAsDouble(a) + f.applyAsDouble(b)) / 2.0;
    }

    // Simpson (grau 2, f nos extremos e no meio)
    public static double ncFechadoSimpson(double a, double b, DoubleUnaryOperator f) {
        double m = (a + b) / 2.0;
        return (b - a) * (f.applyAsDouble(a) + 4.0 * f.applyAsDouble(m) + f.applyAsDouble(b)) / 6.0;
    }

    // Regra 3/8 (grau 3, divide em 3 subintervalos, 4 pontos)
    public static double ncFechado38(double a, double b, DoubleUnaryOperator f) {
        double h = (b - a) / 3.0;
        return (3.0 * h / 8.0) * (f.applyAsDouble(a)
                + 3.0 * f.applyAsDouble(a + h)
                + 3.0 * f.applyAsDouble(a + 2 * h)
                + f.applyAsDouble(b));
    }

    // ABERTAS ----------------------------------------

    // Ponto Médio (aberta, grau 1, usa apenas ponto meio)
    public static double ncAbertoMeio(double a, double b, DoubleUnaryOperator f) {
        return (b - a) * f.applyAsDouble((a + b) / 2);
    }

    // Grau 1 (2 pontos, aberta - não inclui extremos)
    public static double ncAberto2(double a, double b, DoubleUnaryOperator f) {
        double h = (b - a) / 3.0;
        return (3.0 / 2.0) * h * (f.applyAsDouble(a + h) + f.applyAsDouble(a + 2 * h));
    }

    // Grau 2 (3 pontos, aberta)
    public static double ncAberto3(double a, double b, DoubleUnaryOperator f) {
        double h = (b - a) / 4.0;
        return (4.0 / 3.0) * h * (2 * f.applyAsDouble(a + h)
                - f.applyAsDouble(a + 2 * h)
                + 2 * f.applyAsDouble(a + 3 * h));
    }

    // Grau 3 (4 pontos, aberta)
    public static double ncAberto4(double a, double b, DoubleUnaryOperator f) {
        double h = (b - a) / 5.0;
        return (5.0 / 24.0) * h * (11 * f.applyAsDouble(a + h)
                + f.applyAsDouble(a + 2 * h)
                + f.applyAsDouble(a + 3 * h)
                + 11 * f.applyAsDouble(a + 4 * h));
    }

    // FECHADAS COMPOSTAS -----------------------------

    // Trapezio composto (padrão n subintervalos)
    public static double ncFechadoTrapezioComp(double a, double b, int n, DoubleUnaryOperator f) {
        double h = (b - a) / n;
        double sum = (f.applyAsDouble(a) + f.applyAsDouble(b)) / 2.0;
        for (int i = 1; i < n; i++) {
            sum += f.applyAsDouble(a + i * h);
        }
        return h * sum;
    }

    // Simpson composta (n deve ser PAR)
    public static double ncFechadoSimpsonComp(double a, double b, int n, DoubleUnaryOperator f) {
        if (n % 2 != 0) {
            throw new IllegalArgumentException("n deve ser par para Simpson composta.");
        }
        double h = (b - a) / n;
        double sum = f.applyAsDouble(a) + f.applyAsDouble(b);
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            sum += (i % 2 == 0) ? 2 * f.applyAsDouble(x) : 4 * f.applyAsDouble(x);
        }
        return h * sum / 3.0;
    }

    // 3/8 composta (n deve ser múltiplo de 3)
    public static double ncFechado38Comp(double a, double b, int n, DoubleUnaryOperator f) {
        if (n % 3 != 0) {
            throw new IllegalArgumentException("n deve ser múltiplo de 3 para 3/8 composta.");
        }
        double h = (b - a) / n;
        double sum = f.applyAsDouble(a) + f.applyAsDouble(b);
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            sum += (i % 3 == 0) ? 2 * f.applyAsDouble(x) : 3 * f.applyAsDouble(x);
        }
        return 3.0 * h * sum / 8.0;
    }

    // ABERTAS COMPOSTAS -----------------------------

    // Composição da regra do ponto médio (usando n subintervalos)
    public static double ncAbertoMeioComp(double a, double b, int n, DoubleUnaryOperator f) {
        double h = (b - a) / n;
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            double x0 = a + i * h;
            double x1 = x0 + h;
            sum += ncAbertoMeio(x0, x1, f);
        }
        return sum;
    }

    // Composição da regra aberta de grau 1 (2 pontos)
    public static double ncAberto2Comp(double a, double b, int n, DoubleUnaryOperator f) {
        double h = (b - a) / n;
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            double x0 = a + i * h;
            double x1 = x0 + h;
            sum += ncAberto2(x0, x1, f);
        }
        return sum;
    }

    // Grau 2 composta (3 pontos aberta)
    public static double ncAberto3Comp(double a, double b, int n, DoubleUnaryOperator f) {
        double h = (b - a) / n;
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            double x0 = a + i * h;
            double x1 = x0 + h;
            sum += ncAberto3(x0, x1, f);
        }
        return sum;
    }

    // Grau 3 composta (4 pontos aberta)
    public static double ncAberto4Comp(double a, double b, int n, DoubleUnaryOperator f) {
        double h = (b - a) / n;
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            double x0 = a + i * h;
            double x1 = x0 + h;
            sum += ncAberto4(x0, x1, f);
        }
        return sum;
    }

    // FUNÇÃO TESTE E MAIN ---------------------------

    // Exemplo: f(x) = x^3
    public static double f1(double x) {
        return x * x * x;
    }

    // Método main apenas para testar e exibir os resultados de cada regra
    public static void main(String[] args) {
        int n = 12; // número de subintervalos nas fórmulas compostas
        double a = 0, b = 1;
        DoubleUnaryOperator func = NewtonCotes::f1;

        System.out.println("*** INTEGRAÇÃO DE x^3 NO INTERVALO [0,1] ***");
        System.out.printf("Trapezio composto (n=%d):     %.10f\n", n, ncFechadoTrapezioComp(a, b, n, func));
        System.out.printf("Simpson composto (n=%d):      %.10f\n", n, ncFechadoSimpsonComp(a, b, n, func));
        System.out.printf("3/8 composto (n=%d):          %.10f\n", n, ncFechado38Comp(a, b, n, func));
        System.out.printf("Aberta (ponto medio) comp (n=%d): %.10f\n", n, ncAbertoMeioComp(a, b, n, func));
        System.out.printf("Aberta grau 1 composta (n=%d):    %.10f\n", n, ncAberto2Comp(a, b, n, func));
        System.out.printf("Aberta grau 2 composta (n=%d):    %.10f\n", n, ncAberto3Comp(a, b, n, func));
        System.out.printf("Aberta grau 3 composta (n=%d):    %.10f\n", n, ncAberto4Comp(a, b, n, func));

        // Valor exato da integral de x^3 entre 0 e 1: 1/4 = 0.25
        System.out.printf("\nValor exato (1/4):                 0.2500000000\n");
    }
}
