package unit05;

import unit05.elementosfinitos.ElementosFinitosResolver;
import unit05.elementosfinitos.TabelaErroEF;

public class MainEF {

    public static void main(String[] args) {
        int n = 4; 
        
        var resultado = ElementosFinitosResolver.resolver(n);

        TabelaErroEF.imprimir(resultado);
    }
}
