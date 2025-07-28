import unit04.euler.*;
import unit04.edos.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("🔷 Euler Explícito - PVI-1");
        List<Estado> resultado1 = EulerExplicito.resolver(PVI1.condicaoInicial(), 0, 2, 0.5, PVI1.funcao());
        resultado1.forEach(System.out::println);

        System.out.println("\n🟡 Euler Implícito - PVI-1");
        List<Estado> resultado2 = EulerImplicito.resolver(PVI1.condicaoInicial(), 0, 2, 0.5, PVI1.funcao());
        resultado2.forEach(System.out::println);

        System.out.println("\n🔷 Euler Explícito - PVI-2");
        List<Estado> resultado3 = EulerExplicito.resolver(PVI2.condicaoInicial(), 0, 2, 0.1, PVI2.funcao());
        resultado3.forEach(System.out::println);

        System.out.println("\n🟡 Euler Implícito - PVI-2");
        List<Estado> resultado4 = EulerImplicito.resolver(PVI2.condicaoInicial(), 0, 2, 0.1, PVI2.funcao());
        resultado4.forEach(System.out::println);
    }
}
