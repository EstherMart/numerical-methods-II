import numpy as np

"""
Implementação do Método de Householder para tridiagonalização de matrizes simétricas.

O método transforma uma matriz simétrica A em uma matriz tridiagonal A# similar a A,
ou seja, existe uma matriz ortogonal H tal que:

    A# = H^T * A * H

A matriz tridiagonal facilita o cálculo de autovalores e autovetores em métodos posteriores.

---

Resumo do método (baseado no pseudocódigo das notas de aula do professor Creto):

1. Inicializa H como a matriz identidade (H ← I).
2. Define A^(0) ← A (cópia da matriz original).
3. Para cada passo i de 0 até n-3 (total n-2 passos):
   a. Constrói a matriz de Householder H_i baseada na coluna i da matriz A^(i):
      - Essa matriz H_i é ortogonal e tem a propriedade de zerar os elementos abaixo
        da posição (i, i+1) na coluna i.
   b. Aplica a transformação de similaridade no passo i:
      
      A^(i+1) = H_i * A^(i) * H_i

   c. Atualiza A^(i) para o próximo passo.
   d. Acumula o produto das transformações em H:

      H ← H * H_i

4. Após os n-2 passos, A^(n-2) é uma matriz tridiagonal similar a A, e H é a matriz ortogonal acumulada.

---

Esta implementação segue explicitamente o pseudocódigo da aula
"""

def matriz_householder_para_coluna(A, i):
    """
    Constrói a matriz de Householder baseada na coluna i da matriz A,
    seguindo passo a passo o pseudocódigo da aula, com correção de sinal.
    """
    n = A.shape[0]
    
    # STEP 1: Inicializar vetores nulos w, w', e
    w = np.zeros(n)
    w_prime = np.zeros(n)
    e = np.zeros(n)

    # STEP 2: Copiar os elementos abaixo da diagonal da coluna i
    w[i+1:] = A[i+1:, i]

    # STEP 3: Calcular o comprimento (norma) do vetor w
    Lw = np.linalg.norm(w)

    # STEP 4: Copiar ±Lw na posição i+1 do vetor w'
    sinal_oposto = 1 if w[i+1] < 0 else -1
    w_prime[i+1] = sinal_oposto * Lw

    # STEP 5: Calcular vetor N = w - w'
    N = w - w_prime

    # STEP 6: Normalizar vetor n = N / ||N||
    norm_N = np.linalg.norm(N)
    if norm_N == 0:
        return np.identity(n)  # Nenhuma transformação necessária
    n_vec = N / norm_N

    # STEP 7: Construir a matriz de Householder: H = I - 2 * n * n^T
    I = np.identity(n)
    H = I - 2 * np.outer(n_vec, n_vec)

    # STEP 8: Retornar H
    return H


def metodo_de_householder(A):
    """
    Aplica o método de Householder para tridiagonalizar a matriz simétrica A.
    Retorna a matriz tridiagonal A_tridiag e o produto acumulado das transformações H.
    """
    A = A.copy().astype(float)
    n = A.shape[0]

    H_total = np.identity(n)
    A_prev = A.copy()

    for i in range(n - 2):
        # STEP A: Construir a matriz de Householder H_i
        H_i = matriz_householder_para_coluna(A_prev, i)

        # STEP B: Aplicar transformação de similaridade
        A_curr = H_i @ A_prev @ H_i

        # STEP C: Atualizar para o próximo passo
        A_prev = A_curr

        # STEP D: Acumular produto das transformações
        H_total = H_total @ H_i

        # Exibir o passo atual
        print(f"\nPasso {i+1}:")
        print("Matriz de Householder H_i:")
        print(np.round(H_i, 4))
        print("Matriz A após transformação:")
        print(np.round(A_curr, 4))

    A_tridiag = A_prev
    return A_tridiag, H_total