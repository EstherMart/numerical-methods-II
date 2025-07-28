import numpy as np
from math import sqrt 

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


# --- Método com NumPy ---
def metodo_potencia_numpy(A, v0, epsilon=1e-6, max_iter=1000):
    # Step 1
    """
    Parâmetros:
    - A: matriz quadrada
    - v0: vetor inicial
    - epsilon: tolerância para o erro relativo
    - max_iter: número máximo de iterações

    Retorna:
    - lambda_aprox: autovalor dominante
    - x_aprox: autovetor normalizado
    """

    x = v0 / np.linalg.norm(v0)
    lambda_old = 0.0 # Step 2

    for _ in range(max_iter):
        v = A @ x
        x = v / np.linalg.norm(v)
        lambda_new = x.T @ A @ x

        # Critério de parada (erro relativo)
        if lambda_old != 0:
            erro = abs((lambda_new - lambda_old) / lambda_new)
            if erro < epsilon:
                break
        
        lambda_old = lambda_new

    return lambda_new, x

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