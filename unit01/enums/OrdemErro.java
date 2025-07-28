package enums;

import java.util.function.DoubleUnaryOperator;

// Enum que expressa as possíveis ordens de erro nos métodos numéricos
public enum OrdemErro {

    LINEAR,      // O(delta)
    QUADRATICO,  // O(delta^2)
    CUBICO,      // O(delta^3)
    QUARTA       // O(delta^4)
    
}