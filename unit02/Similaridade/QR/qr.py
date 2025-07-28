"""
Estou implementando o método QR iterativo para encontrar os autovalores e autovetores de uma matriz quadrada. A ideia principal é aplicar repetidamente a transformação de similaridade `A_k = R_k @ Q_k`, onde `Q_k` e `R_k` vêm da decomposição QR da matriz da iteração anterior.

Sabemos que matrizes similares têm os mesmos autovalores, então com essas transformações a matriz `A_k` converge para uma forma quase diagonal, e seus autovalores aparecem na diagonal. Além disso, ao acumularmos os produtos das matrizes `Q_k`, conseguimos obter também os autovetores da matriz original.

A cada passo:
1. Faço a decomposição `A = Q @ R`
2. Recrio `A = R @ Q` (isso é uma transformação de similaridade)
3. Repito até que os elementos abaixo da diagonal fiquem suficientemente pequenos

No final, a diagonal de A contém os autovalores, e as **colunas da matriz acumulada P** são os autovetores.
"""
import numpy as np

# Step 0: Decomposição QR usando Gram-Schmidt clássico 
def qr_decomposicao_classica(matriz):
    """
    Decomposição QR via Gram-Schmidt (versão limpa).
    Recebe uma matriz A e retorna Q (ortogonal) e R (triangular superior) tal que A = QR.
    """
    n = matriz.shape[0]
    Q = np.zeros_like(matriz)
    R = np.zeros((n, n))

    for coluna in range(n):
        vetor_u = matriz[:, coluna].copy()

        for linha in range(coluna):
            projecao = np.dot(Q[:, linha], matriz[:, coluna])
            R[linha, coluna] = projecao
            vetor_u -= projecao * Q[:, linha]

        norma = np.linalg.norm(vetor_u)
        R[coluna, coluna] = norma
        Q[:, coluna] = vetor_u / norma

    return Q, R

# Step 1: Método QR principal
def metodo_qr(A, epsilon=1e-10, max_iter=1000):
    """
    Implementação do método QR iterativo para encontrar autovalores e autovetores.
    """
    n = A.shape[0]
    A_k = A.copy().astype(float)
    P_acumulado = np.identity(n)
    erro = 100.0
    iteracao = 0

    while erro > epsilon and iteracao < max_iter:
        # Step 2.1: Decomposição QR
        Q, R = qr_decomposicao_classica(A_k)

        # Exibir o passo atual
        # print(f"\nPasso {iteracao + 1}:")
        # print("Matriz Q:")
        # print(np.round(Q, 4))
        # print("Matriz R:")
        # print(np.round(R, 4))

        # Step 2.2: Atualiza A_k com transformação de similaridade
        A_k = R @ Q

        # Exibir a nova A_k
        print("Matriz A após transformação RQ:")
        print(np.round(A_k, 4))

        # Step 2.3: Atualiza matriz de autovetores
        P_acumulado = P_acumulado @ Q

        # Step 2.4: Verificar convergência (soma dos quadrados abaixo da diagonal)
        erro = 0.0
        for i in range(1, n):
            for j in range(i):
                erro += A_k[i, j] ** 2

        iteracao += 1

    autovalores = np.diag(A_k)
    return P_acumulado, autovalores