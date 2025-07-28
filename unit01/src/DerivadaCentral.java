package src;

import interfaces.Filosofia;
import enums.OrdemErro;
import java.util.function.DoubleUnaryOperator;


/*
 Filosofia CENTRAL para diferenciação numérica.
 Usada para obter derivadas usando pontos simetricamente ao redor de 'x' (forward e backward ao mesmo tempo).
*/
public class DerivadaCentral implements Filosofia {
    @Override
    public double derivadaPrimeira(java.util.function.DoubleUnaryOperator f, double x, double delta, OrdemErro erro) {
        switch (erro) {
            case QUADRATICO:
                // (f(x+dx) - f(x-dx)) / 2dx  [erro ~ O(h²)]
                return (f.applyAsDouble(x + delta) - f.applyAsDouble(x - delta)) / (2.0 * delta);
            case QUARTA:
                // (-f(x+2dx) +8f(x+dx) -8f(x-dx) +f(x-2dx)) / 12dx  [erro ~ O(h⁴)]
                return (-f.applyAsDouble(x + 2.0 * delta) + 8.0 * f.applyAsDouble(x + delta)
                        - 8.0 * f.applyAsDouble(x - delta) + f.applyAsDouble(x - 2.0 * delta))
                        / (12.0 * delta);
            default:
                throw new IllegalArgumentException("Ordem de erro " + erro + " não suportada para filosofia central.");
        }
    }

    @Override
    public double derivadaSegunda(java.util.function.DoubleUnaryOperator f, double x, double delta, OrdemErro erro) {
        switch (erro) {
            case QUADRATICO:
                // (f(x+dx) -2f(x) +f(x-dx)) / dx²  [erro ~ O(h²)]
                return (f.applyAsDouble(x + delta) - 2.0 * f.applyAsDouble(x) + f.applyAsDouble(x - delta))
                        / (delta * delta);
            case QUARTA:
                // (-f(x+2dx) +16f(x+dx) -30f(x) +16f(x-dx) -f(x-2dx)) / 12dx²  [erro ~ O(h⁴)]
                return (-f.applyAsDouble(x + 2.0 * delta) + 16.0 * f.applyAsDouble(x + delta)
                        - 30.0 * f.applyAsDouble(x)
                        + 16.0 * f.applyAsDouble(x - delta)
                        - f.applyAsDouble(x - 2.0 * delta))
                        / (12.0 * delta * delta);
            default:
                throw new IllegalArgumentException("Ordem de erro " + erro + " não suportada para filosofia central.");
        }
    }

    @Override
    public double derivadaTerceira(java.util.function.DoubleUnaryOperator f, double x, double delta, OrdemErro erro) {
        switch (erro) {
            case QUADRATICO:
                // (f(x-2dx) -2f(x-dx) +2f(x+dx) -f(x+2dx)) / 2dx³  [erro ~ O(h²)]
                return (f.applyAsDouble(x - 2.0 * delta)
                        - 2.0 * f.applyAsDouble(x - delta)
                        + 2.0 * f.applyAsDouble(x + delta)
                        - f.applyAsDouble(x + 2.0 * delta))
                        / (2.0 * delta * delta * delta);
            case QUARTA:
                // (-f(x-3dx) +8f(x-2dx) -13f(x-dx) +13f(x+dx) -8f(x+2dx) +f(x+3dx)) / 8dx³
                return (-f.applyAsDouble(x - 3.0 * delta)
                        + 8.0 * f.applyAsDouble(x - 2.0 * delta)
                        - 13.0 * f.applyAsDouble(x - delta)
                        + 13.0 * f.applyAsDouble(x + delta)
                        - 8.0 * f.applyAsDouble(x + 2.0 * delta)
                        + f.applyAsDouble(x + 3.0 * delta))
                        / (8.0 * delta * delta * delta);
            default:
                throw new IllegalArgumentException("Ordem de erro " + erro + " não suportada para filosofia central.");
        }
    }
}