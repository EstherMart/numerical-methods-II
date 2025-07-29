package unit05.elementosfinitos;

public class TabelaErroEF {

    public static void imprimir(ElementosFinitosResolver.ResultadoSistema resultado) {
        double[] xs = resultado.xs();
        double[] ys = resultado.ys();

        System.out.println("|   x   | Elementos Finitos |  Valor Exato  |    Erro Relativo (%)    |");
        System.out.println("-----------------------------------------------------------------------");

        for (int i = 0; i < xs.length; i++) {
            double x = xs[i];
            double yAprox = ys[i];
            double yExato = ElementosFinitosResolver.solucaoExata(x);
            double erro = Math.abs((yAprox - yExato) / yExato) * 100;
            System.out.printf("| %.3f |    %16.8f | %12.8f | %21.8f |\n", x, yAprox, yExato, erro);
        }
    }
}
