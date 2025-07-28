import java.util.function.DoubleUnaryOperator;

public class ExponencialTransformIntegrador {

    // Integração numérica simples por métodos dos retângulos (pontocentral)
    public static double integra(double a, double b, int N, DoubleUnaryOperator f) {
        double Dx = (b - a) / N;
        double area = 0.0;
        for (int i = 0; i < N; i++) {
            double xi = a + Dx / 2.0 + i * Dx;
            area += Dx * f.applyAsDouble(xi);
        }
        return area;
    }

    // Chamada automática até erro relativo menor que eps
    public static double integralComErro(double a, double b, double eps, DoubleUnaryOperator f) {
        int N = 1;
        double erro = 1, areaAnt = 0, area = 0;
        while (erro > eps) {
            N = N * 2;
            area = integra(a, b, N, f);
            if (area != 0)
                erro = Math.abs((area - areaAnt) / area);
            areaAnt = area;
        }
        return area;
    }

    // ========== MUDANÇAS DE VARIÁVEIS ==========
    // Exponencial simples: x = a + (b-a)*0.5*(1+tanh(s))
    // Os limites reais [-infinito, +infinito] (em s) são aproximados por [-C,+C]
    // f_transformada calcula f(x(s))*dx/ds (Jacobiano)
    public static DoubleUnaryOperator mudancaExponencialSimples(
            double a, double b, DoubleUnaryOperator f) {
        return (double s) -> {
            double t = Math.tanh(s); // mapeia s∈(-inf,inf) para t∈(-1,1)
            double x = ((b + a) / 2.0) + ((b - a) / 2.0) * t;
            double dxds = (b - a) / 2.0 * (1 - Math.pow(t, 2)) / Math.cosh(s) / Math.cosh(s);
            return f.applyAsDouble(x) * dxds;
        };
    }

    // Exponencial dupla: x = a + (b-a)*0.5*(1+tanh(pi/2*sinh(s)))
    public static DoubleUnaryOperator mudancaExponencialDupla(
            double a, double b, DoubleUnaryOperator f) {
        return (double s) -> {
            double arg = (Math.PI / 2.0) * Math.sinh(s);
            double t = Math.tanh(arg);
            double x = ((b + a) / 2.0) + ((b - a) / 2.0) * t;
            double dt_ds = (Math.PI / 2.0) * Math.cosh(s) * (1 - Math.pow(t, 2)); // dt/ds
            double dxds = (b - a) / 2.0 * dt_ds;
            return f.applyAsDouble(x) * dxds;
        };
    }

    // Exemplos de funções para testar, inclusive as das notas de aula #13 e #14
    // ["f(x) = x^{-2/3}", f(x) = 1/sqrt{x}]
    public static double fTeste1(double x) {
        return Math.pow(x, -2.0 / 3.0);
    }

    public static double fTeste2(double x) {
        return 1.0 / Math.sqrt(x);
    }

    public static double fTeste3(double x) {
        return Math.sqrt(x);
    }

    public static void main(String[] args) {
        // Parâmetros gerais da integração
        double a = 0.0, b = 1.0;
        double eps = 1e-8; // precisão desejada
        int C = 8; // Limite para corte dos infinitos (usado para s em (-C, +C))

        // Montando as funções para integrar de acordo com cada tarefa
        DoubleUnaryOperator[] funclist = {
                ExponencialTransformIntegrador::fTeste1,
                ExponencialTransformIntegrador::fTeste2,
                ExponencialTransformIntegrador::fTeste3
        };

        String[] nomesFuncoes = {
                "f(x) = x^{-2/3}",
                "f(x) = 1/sqrt(x)",
                "f(x) = sqrt(x)"
        };

        System.out.println("INTEGRAÇÃO NUMÉRICA COM MUDANÇAS EXPONENCIAIS (SIMPLES E DUPLA)");
        System.out.println("Limite de integração: [0,1], eps = " + eps + ", C = " + C + "\n");

        for (int idx = 0; idx < funclist.length; idx++) {
            System.out.println(">> Testando: " + nomesFuncoes[idx]);
            DoubleUnaryOperator f = funclist[idx];

            DoubleUnaryOperator gSimples = mudancaExponencialSimples(a, b, f);
            double integralSimples = integralComErro(-C, C, eps, gSimples);
            System.out.printf("  Resultado (exp. simples): %.10f\n", integralSimples);

            DoubleUnaryOperator gDupla = mudancaExponencialDupla(a, b, f);
            double integralDupla = integralComErro(-C, C, eps, gDupla);
            System.out.printf("  Resultado (exp. dupla):   %.10f\n", integralDupla);

            System.out.println();
        }

    }
}