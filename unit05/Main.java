package unit05;

import unit05.difinitas.EDOResolver;
import unit05.difinitas.FuncoesExatas;
import unit05.difinitas.TabelaErro;

public class Main {
    public static void main(String[] args) {
        // Dados do problema
        double a = 0.0;
        double b = 2.0;
        double uA = 10.0;
        double uB = 1.0;
        double deltaX = 0.1;

        // Resolver o sistema
        var resultado = EDOResolver.resolver(a, b, uA, uB, deltaX);

        // Mostrar a tabela de erro
        TabelaErro.imprimir(resultado);
    }
}
