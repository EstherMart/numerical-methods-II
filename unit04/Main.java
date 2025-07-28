package unit04;

import unit04.edos.PVI2;
import unit04.edos.PVI1;
import unit04.euler.EulerExplicito;
import unit04.euler.EulerImplicito;
import unit04.rungekutta.RungeKutta2Ordem;
import unit04.rungekutta.RungeKutta3Ordem;
import unit04.preditorcorretor.AdamsBashforthMoulton;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("🔷 Euler Explícito - PVI-1");
        EulerExplicito.resolver(PVI1.condicaoInicial(), 0, 2, 0.5, PVI1.funcao())
                .forEach(System.out::println);

        System.out.println("\n🟡 Euler Implícito - PVI-1");
        EulerImplicito.resolver(PVI1.condicaoInicial(), 0, 2, 0.5, PVI1.funcao())
                .forEach(System.out::println);

        System.out.println("\n🔷 Runge-Kutta 2ª Ordem - PVI-1");
        RungeKutta2Ordem.resolver(PVI1.condicaoInicial(), 0, 2, 0.5, PVI1.funcao())
                .forEach(System.out::println);

        System.out.println("\n🔷 Runge-Kutta 3ª Ordem - PVI-1");
        RungeKutta3Ordem.resolver(PVI1.condicaoInicial(), 0, 2, 0.5, PVI1.funcao())
                .forEach(System.out::println);

        System.out.println("\n🔷 Preditor-Corretor - PVI-1");
        AdamsBashforthMoulton.resolver(PVI1.condicaoInicial(), 0, 2, 0.5, PVI1.funcao())
                .forEach(System.out::println);
    }
}
