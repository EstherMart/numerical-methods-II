from math import sqrt 

# --- Funções auxiliares ---
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

def metodo_deslocado_puro_com_potencia(A, v0, mi, epsilon=1e-6, max_iter=1000):
    """
    Método da potência com deslocamento (usando método inverso puro já implementado).
    Aplica o método da potência inversa na matriz (A - μI)
    Recebe:
        A: matriz quadrada (lista de listas)
        v0: vetor inicial
        mi: deslocamento (shift μ)
        epsilon: tolerância
        max_iter: número máximo de iterações
    Retorna:
        lambda_aprox: autovalor aproximado mais próximo de mi
        autovetor: autovetor correspondente normalizado
    """
    n = len(A)

    # Step 1: Construir matriz deslocada B = A - μI
    A_menos_miI = [[A[i][j] - (mi if i == j else 0) for j in range(n)] for i in range(n)]

    # Step 2 + 3: Aplicar método da potência inversa à matriz deslocada
    lambda_barra, x_barra = metodo_inverso_puro(A_menos_miI, v0, epsilon, max_iter)

    # Step 4: Calcular autovalor final
    lambda_aprox = lambda_barra + mi

    # Step 5: Retornar resultado
    return lambda_aprox, x_barra