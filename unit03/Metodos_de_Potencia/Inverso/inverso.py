from math import sqrt 
import numpy as np

# --- Funções auxiliares ---
def decomposicao_LU_pura(A):
    """
    Decomposição LU sem pivotamento, para matriz A (lista de listas).
    Retorna L e U (listas de listas).
    """
    n = len(A)
    # Inicializa L como identidade e U como cópia de A
    L = [[0.0]*n for _ in range(n)]
    U = [[0.0]*n for _ in range(n)]
    for i in range(n):
        L[i][i] = 1.0
        for j in range(n):
            U[i][j] = A[i][j]

    for j in range(n-1):
        if U[j][j] == 0:
            raise ValueError("Zero pivot encontrado. Considere pivotamento ou verifique a matriz A.")
        for i in range(j+1, n):
            L[i][j] = U[i][j] / U[j][j]
            for k in range(j, n):
                U[i][k] = U[i][k] - L[i][j] * U[j][k]
    return L, U

def resolver_sistema_LU_puro(L, U, b):
    """
    Resolve Ax=b usando decomposição LU (listas puras).
    1. Resolve Ly=b (substituição progressiva).
    2. Resolve Ux=y (substituição regressiva).
    """
    n = len(b)
    # Substituição progressiva Ly=b
    y = [0]*n
    for i in range(n):
        soma = 0
        for j in range(i):
            soma += L[i][j]*y[j]
        y[i] = b[i] - soma

    # Substituição regressiva Ux=y
    x = [0]*n
    for i in reversed(range(n)):
        soma = 0
        for j in range(i+1, n):
            soma += U[i][j]*x[j]
        if U[i][i] == 0:
            raise ValueError("Zero na diagonal de U, sistema não tem como obter uma resolução.")
        x[i] = (y[i] - soma)/U[i][i]
    return x

def normalizar(v):
    # Step 6 auxiliar: ||v||
    norma = sqrt(produto_interno(v, v))
    return [vi / norma for vi in v]

def multiplicar_matriz_vetor(M, v):
    # Step 7 auxiliar: A * v
    resultado = []
    for linha in M:
        soma = sum(linha[j] * v[j] for j in range(len(v)))
        resultado.append(soma)
    return resultado

def produto_interno(v1, v2):
     # Step 8 auxiliar: v1ᵀ * v2
    return sum(a * b for a, b in zip(v1, v2))

# PUXANDO O MÉTODO REGULAR PARA SER APLICADO NO STEP 2

# --- Método com listas puras ---
def metodo_potencia_puro(A, v0, epsilon=1e-6, max_iter=1000):     
    # Step 1: Receber matriz A, vetor inicial v0 e tolerância ε

    """
    Parâmetros:
    - A: matriz quadrada (lista de listas)
    - v0: vetor inicial (lista)
    - epsilon: tolerância para critério de parada
    - max_iter: número máximo de iterações permitidas

    Retorna:
    - lambda_aprox: autovalor dominante aproximado
    - autovetor: autovetor correspondente normalizado
    """

    n = len(A)
    lambda_novo = 0.0  # Step 2: Inicializar autovalor
    vk_novo = v0[:]    # Step 3: Copiar v0 para vk_novo
    
    for _ in range(max_iter):
        # Step 4: Copiar lambda_novo → lambda_velho
        lambda_velho = lambda_novo

        # Step 5: Copiar vk_novo → vk_velho
        vk_velho = vk_novo[:]

        # Step 6: Normalizar vk_velho → xk
        xk = normalizar(vk_velho)

        # Step 7: Calcular novo vetor vk_novo = A * xk
        vk_novo = multiplicar_matriz_vetor(A, xk)

        # Step 8: Estimar novo autovalor λ = xkᵀ * vk_novo
        lambda_novo = produto_interno(xk, vk_novo)

        # Step 9: Verificar convergência
        if lambda_novo != 0:
            erro = abs((lambda_novo - lambda_velho) / lambda_novo)
            if erro < epsilon:
                break

    # Step 10: Retornar λ e xk (autovalor e autovetor aproximados)
    return lambda_novo, xk

def inversa_matriz_pura(A):
    """
    Calcula a inversa de A (lista de listas) usando numpy para simplicidade.
    """
    A_np = np.array(A, dtype=float)
    A_inv_np = np.linalg.inv(A_np)
    return A_inv_np.tolist()

def metodo_inverso_puro_com_potencia(A, v0, epsilon=1e-6, max_iter=1000):
    """
    Método da potência inversa puro que chama o método da potência regular
    sobre a matriz inversa explícita de A.
    """
    # Step 1: Calcula inversa de A
    A_inv = inversa_matriz_pura(A)

    # Step 2: Aplica método da potência regular na inversa
    lambda_barra, x_barra = metodo_potencia_puro(A_inv, v0, epsilon, max_iter)

    # Step 3: Calcula autovalor final de A
    lambda_final = 1.0 / lambda_barra

    # Step 4: Autovetor correspondente
    autovetor_final = x_barra

    return lambda_final, autovetor_final

# --- Método da potência inversa puro ---
def metodo_inverso_puro(A, v0, epsilon=1e-6, max_iter=1000):
    """
    Implementa o método da potência inversa usando listas puras e decomposição LU.
    """
    n = len(A)
    # Step 1: Recebe A, v0, epsilon, max_iter
    # Step 2: Decomposição LU de A
    L, U = decomposicao_LU_pura(A)

    # Step 3: Inicializa lambda_n
    lambda_n = 0.0
    # Step 4: Copia v0 para v_old
    v_old = v0[:]

    for iter in range(max_iter):
        # Step 5: Copia lambda_n para lambda_old
        lambda_old = lambda_n

        # Step 6: Copia v_old para v_older
        v_older = v_old[:]

        # Step 7: Normaliza v_older para obter x
        x = normalizar(v_older) # <-- Aqui x̅, vetor com "barra"

        # Step 8: Resolve sistema LU para v_old: A * v_old = x → v_old = A^{-1} * x
        v_old = resolver_sistema_LU_puro(L, U, x)

        # Step 9: Calcula nova estimativa de lambda_n = x^T * v_old
        lambda_n = produto_interno(x, v_old) # <-- Aqui λ̅, autovalor estimado da matriz inversa A⁻¹

        # Step 10: Verifica convergência no erro relativo
        if lambda_n != 0:
            erro = abs((lambda_n - lambda_old) / lambda_n)
            if erro < epsilon:
                break

    # Step 11: Calcula lambda_final = 1 / lambda_n
    lambda_final = 1.0 / lambda_n
    # Step 12: Autovetor é o vetor x normalizado da última iteração
    autovetor_final = normalizar(v_old)

    return lambda_final, autovetor_final