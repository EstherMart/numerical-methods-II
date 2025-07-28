package interfaces;

import enums.OrdemErro;
import java.util.function.DoubleUnaryOperator;

/*
 Interface Filosofia: Define métodos para derivadas numéricas de diversas ordens.
 O argumento 'f' é uma interface funcional (lambda ou método) representando a função a ser diferenciada.
*/
public interface Filosofia {

    // Primeira derivada
    double derivadaPrimeira(java.util.function.DoubleUnaryOperator f, double x, double delta, OrdemErro erro);

    // Segunda derivada
    double derivadaSegunda(java.util.function.DoubleUnaryOperator f, double x, double delta, OrdemErro erro);

    // Terceira derivada
    double derivadaTerceira(java.util.function.DoubleUnaryOperator f, double x, double delta, OrdemErro erro);

}