package src;

import interfaces.Filosofia;
import enums.OrdemErro;
import java.util.function.DoubleUnaryOperator;

/*
 Implementação da diferenciação backward (para trás) para derivadas aproximadas.
 Cada método calcula a derivada de ordem correspondente, de acordo com a ordem do erro requisitada.
 As fórmulas vêm das expansões de Taylor.
*/
public class DerivadaBackward implements Filosofia {

    // Primeira derivada usando diferentes ordens de erro
    @Override
    public double derivadaPrimeira(java.util.function.DoubleUnaryOperator f, double x, double delta, OrdemErro erro) {
        switch (erro) {
            case LINEAR:
                // (f(x) - f(x-dx)) / dx
                return (f.applyAsDouble(x) - f.applyAsDouble(x - delta)) / delta;
            case QUADRATICO:
                // (3f(x) -4f(x-dx) +f(x-2dx)) / 2dx
                return (3.0 * f.applyAsDouble(x) - 4.0 * f.applyAsDouble(x - delta) + f.applyAsDouble(x - 2.0 * delta))
                        / (2.0 * delta);
            case CUBICO:
                // (11f(x) -18f(x-dx) +9f(x-2dx) -2f(x-3dx)) / 6dx
                return (11.0 * f.applyAsDouble(x) - 18.0 * f.applyAsDouble(x - delta) + 9.0 * f.applyAsDouble(x - 2.0 * delta)
                        - 2.0 * f.applyAsDouble(x - 3.0 * delta))
                        / (6.0 * delta);
            case QUARTA:
                // (25f(x) -48f(x-dx) +36f(x-2dx) -16f(x-3dx) +3f(x-4dx)) / 12dx
                return (25.0 * f.applyAsDouble(x)
                        - 48.0 * f.applyAsDouble(x - delta)
                        + 36.0 * f.applyAsDouble(x - 2.0 * delta)
                        - 16.0 * f.applyAsDouble(x - 3.0 * delta)
                        + 3.0 * f.applyAsDouble(x - 4.0 * delta))
                        / (12.0 * delta);
            default:
                throw new IllegalArgumentException("Ordem de erro desconhecida!");
        }
    }

    // Segunda derivada usando diferentes ordens de erro
    @Override
    public double derivadaSegunda(java.util.function.DoubleUnaryOperator f, double x, double delta, OrdemErro erro) {
        switch (erro) {
            case LINEAR:
                // (f(x) -2f(x-dx) +f(x-2dx)) / dx²
                return (f.applyAsDouble(x) - 2.0 * f.applyAsDouble(x - delta) + f.applyAsDouble(x - 2.0 * delta))
                        / (delta * delta);
            case QUADRATICO:
                // (2f(x) -5f(x-dx) +4f(x-2dx) -f(x-3dx)) / dx²
                return (2.0 * f.applyAsDouble(x)
                        - 5.0 * f.applyAsDouble(x - delta)
                        + 4.0 * f.applyAsDouble(x - 2.0 * delta)
                        - f.applyAsDouble(x - 3.0 * delta))
                        / (delta * delta);
            case CUBICO:
                // (35f(x) -104f(x-dx) +114f(x-2dx) -56f(x-3dx) +11f(x-4dx)) / 12dx²
                return (35.0 * f.applyAsDouble(x)
                        - 104.0 * f.applyAsDouble(x - delta)
                        + 114.0 * f.applyAsDouble(x - 2.0 * delta)
                        - 56.0 * f.applyAsDouble(x - 3.0 * delta)
                        + 11.0 * f.applyAsDouble(x - 4.0 * delta))
                        / (12.0 * delta * delta);
            case QUARTA:
                // (45f(x) -154f(x-dx) +214f(x-2dx) -156f(x-3dx) +61f(x-4dx) -10f(x-5dx)) / 12dx²
                return (45.0 * f.applyAsDouble(x)
                        - 154.0 * f.applyAsDouble(x - delta)
                        + 214.0 * f.applyAsDouble(x - 2.0 * delta)
                        - 156.0 * f.applyAsDouble(x - 3.0 * delta)
                        + 61.0 * f.applyAsDouble(x - 4.0 * delta)
                        - 10.0 * f.applyAsDouble(x - 5.0 * delta))
                        / (12.0 * delta * delta);
            default:
                throw new IllegalArgumentException("Ordem de erro desconhecida");
        }
    }

    // Terceira derivada usando diferentes ordens de erro
    @Override
    public double derivadaTerceira(java.util.function.DoubleUnaryOperator f, double x, double delta, OrdemErro erro) {
        switch (erro) {
            case LINEAR:
                // (f(x) -3f(x-dx) +3f(x-2dx) -f(x-3dx)) / dx³
                return (f.applyAsDouble(x)
                        - 3.0 * f.applyAsDouble(x - delta)
                        + 3.0 * f.applyAsDouble(x - 2.0 * delta)
                        - f.applyAsDouble(x - 3.0 * delta))
                        / (delta * delta * delta);
            case QUADRATICO:
                // (-5f(x) +18f(x-dx) -24f(x-2dx) +14f(x-3dx) -3f(x-4dx)) / 2dx³
                return (-5.0 * f.applyAsDouble(x)
                        + 18.0 * f.applyAsDouble(x - delta)
                        - 24.0 * f.applyAsDouble(x - 2.0 * delta)
                        + 14.0 * f.applyAsDouble(x - 3.0 * delta)
                        - 3.0 * f.applyAsDouble(x - 4.0 * delta))
                        / (2.0 * delta * delta * delta);
            case CUBICO:
                // (17f(x) -71f(x-dx) +118f(x-2dx) -98f(x-3dx) +41f(x-4dx) -7f(x-5dx)) / 4dx³
                return (17.0 * f.applyAsDouble(x)
                        - 71.0 * f.applyAsDouble(x - delta)
                        + 118.0 * f.applyAsDouble(x - 2.0 * delta)
                        - 98.0 * f.applyAsDouble(x - 3.0 * delta)
                        + 41.0 * f.applyAsDouble(x - 4.0 * delta)
                        - 7.0 * f.applyAsDouble(x - 5.0 * delta))
                        / (4.0 * delta * delta * delta);
            case QUARTA:
                // (49f(x) -232f(x-dx) +461f(x-2dx) -496f(x-3dx) +307f(x-4dx) -104f(x-5dx) +15f(x-6dx)) / 8dx³
                return (49.0 * f.applyAsDouble(x)
                        - 232.0 * f.applyAsDouble(x - delta)
                        + 461.0 * f.applyAsDouble(x - 2.0 * delta)
                        - 496.0 * f.applyAsDouble(x - 3.0 * delta)
                        + 307.0 * f.applyAsDouble(x - 4.0 * delta)
                        - 104.0 * f.applyAsDouble(x - 5.0 * delta)
                        + 15.0 * f.applyAsDouble(x - 6.0 * delta))
                        / (8.0 * delta * delta * delta);
            default:
                throw new IllegalArgumentException("Ordem de erro desconhecida");
        }
    }
}