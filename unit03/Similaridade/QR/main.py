import numpy as np
from qr import metodo_qr  # Sua função implementada em outro arquivo

def main():
    print("===============================================")
    print("      Bem-vinda ao programa de autovalores")
    print("             Método Iterativo QR              ")
    print("===============================================\n")

    # STEP 1: Exemplo 1 - Matriz simétrica 4x4
    A_exemplo = np.array([
        [4, 1, 2, 2],
        [1, 2, 0, 1],
        [2, 0, 3, 1],
        [2, 1, 1, 2]
    ], dtype=float)

    print("--> Exemplo 1 - Matriz simétrica de entrada (4x4):\n")
    print(A_exemplo)

    # STEP 2: Aplicar o método QR
    autovetores1, autovalores1 = metodo_qr(A_exemplo)

    # STEP 3: Exibir resultados
    print("\n✅ Resultados do Exemplo 1:")
    print("   Autovalores aproximados:")
    for i, val in enumerate(np.round(autovalores1, 6)):
        print(f"   λ{i+1} = {val}")

    print("\n   Autovetores (colunas da matriz P):")
    for i in range(autovetores1.shape[1]):
        vetor = np.round(autovetores1[:, i], 6)
        print(f"   v{i+1} = {vetor}")

    print("\n" + "="*60 + "\n")

    # STEP 4: Exemplo 2 - Matriz simétrica 5x5
    A_exemplo2 = np.array([
        [40, 8, 4, 2, 1],
        [8, 30, 12, 6, 2],
        [4, 12, 20, 1, 2],
        [2, 6, 1, 25, 4],
        [1, 2, 2, 4, 5]
    ], dtype=float)

    print("--> Exemplo 2 - Matriz simétrica de entrada (5x5):\n")
    print(A_exemplo2)

    # STEP 5: Aplicar o método QR
    autovetores2, autovalores2 = metodo_qr(A_exemplo2)

    # STEP 6: Exibir resultados
    print("\n✅ Resultados do Exemplo 2:")
    print("   Autovalores aproximados:")
    for i, val in enumerate(np.round(autovalores2, 6)):
        print(f"   λ{i+1} = {val}")

    print("\n   Autovetores (colunas da matriz P):")
    for i in range(autovetores2.shape[1]):
        vetor = np.round(autovetores2[:, i], 6)
        print(f"   v{i+1} = {vetor}")

    print("\n" + "="*60 + "\n")

if __name__ == "__main__":
    main()

