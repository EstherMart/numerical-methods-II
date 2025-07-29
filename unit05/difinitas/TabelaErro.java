package unit05.difinitas;

import java.util.List;

public class TabelaErro {

    public static void imprimir(EDOResolver.ResultadoSistema resultado) {
        List<Double> xs = resultado.xs();
        double[] ys = resultado.ys();

        System.out.println("|   x   |  Aproximado  |   Exato   |   Erro Relativo (%) |");
        System.out.println("----------------------------------------------------------");

        for (int i = 0; i < xs.size(); i++) {
            double x = xs.get(i);
            double yAprox = ys[i];
            double yExato = FuncoesExatas.u(x);
            double erro = Math.abs((yAprox - yExato) / yExato) * 100;
            System.out.printf("| %.3f | %12.6f | %9.6f | %18.6f |\n", x, yAprox, yExato, erro);
        }
    }
}
