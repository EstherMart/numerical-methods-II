import numpy as np
from householder import metodo_de_householder

def main():
    print("===============================================")
    print(" Bem-vinda ao programa de tridiagonalização")
    print("            Método de Householder             ")
    print("===============================================\n")

    # STEP 1: Exemplo 1 - Matriz simétrica 4x4
    A_exemplo = np.array([
        [4, 1, 2, 2],
        [1, 2, 0, 1],
        [2, 0, 3, 1],
        [2, 1, 1, 2]
    ])

    print("Matriz simétrica de entrada A (Exemplo 1):\n", A_exemplo)

    # STEP 2: Aplicar o método de Householder
    A_tridiag, H = metodo_de_householder(A_exemplo)

    # STEP 3: Exibir resultados do Exemplo 1
    print("\n --> Matriz tridiagonal resultante A# (Exemplo 1):\n", np.round(A_tridiag, 4))
    print("\n --> Matriz acumulada H (produto das matrizes de Householder) (Exemplo 1):\n", np.round(H, 4))

    # STEP 4: Verificar ortogonalidade de H
    identidade = np.eye(H.shape[0])
    erro = np.linalg.norm(H.T @ H - identidade)
    print(f"\nVerificação de ortogonalidade de H (||H.T*H - I||) (Exemplo 1): {erro:.2e}")

    print("\n" + "="*50 + "\n")

    # STEP 5: Exemplo 2 - Matriz simétrica 5x5
    A_exemplo2 = np.array([
        [40, 8, 4, 2, 1],
        [8, 30, 12, 6, 2],
        [4, 12, 20, 1, 2],
        [2, 6, 1, 25, 4],
        [1, 2, 2, 4, 5]
    ], dtype=float)

    print("Matriz simétrica de entrada A (Exemplo 2):\n", A_exemplo2)

    # STEP 6: Aplicar o método de Householder no segundo exemplo
    A_tridiag2, H2 = metodo_de_householder(A_exemplo2)

    # STEP 7: Exibir resultados do Exemplo 2
    print("\n --> Matriz tridiagonal resultante A# (Exemplo 2):\n", np.round(A_tridiag2, 4))
    print("\n --> Matriz acumulada H (produto das matrizes de Householder) (Exemplo 2):\n", np.round(H2, 4))

    # STEP 8: Verificar ortogonalidade de H2
    identidade2 = np.eye(H2.shape[0])
    erro2 = np.linalg.norm(H2.T @ H2 - identidade2)
    print(f"\nVerificação de ortogonalidade de H (||H.T*H - I||) (Exemplo 2): {erro2:.2e}")

if __name__ == "__main__":
    main()