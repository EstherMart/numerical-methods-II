from regular import (
    metodo_potencia_puro,
    metodo_potencia_numpy
)
import numpy as np

print("-------------- SEJA BEM VINDO AO --------------")
print("             MÉTODO DA POTÊNCIA REGULAR")
print("---------------------------------------------------\n")

# --- Matriz A1 (3x3) -- Exemplo ja visto anteriormente
a1 = [[5, 2, 1], 
      [2, 3, 1], 
      [1, 1, 2]]
v0_a1 = [1, 0, 0]

# --- Matriz A2 (5x5) -- Exemplo ja visto anteriormente
a2 = [[40, 8, 4, 2, 1],
      [8, 30, 12, 6, 2],
      [4, 12, 20, 1, 2],
      [2, 6, 1, 25, 4],
      [1, 2, 2, 4, 5]]
v0_a2 = [1, 0, 0, 0, 0]

# --- Matriz A3 (3x3)
a3 = [[2, -1, 0],
      [-1, 2, -1],
      [0, -1, 2]]
v0_a3 = [1, 1, 1]

# --- Versão com listas puras
lambda1_a1_puro, x1_a1_puro = metodo_potencia_puro(a1, v0_a1, epsilon=1e-5)
lambda1_a2_puro, x1_a2_puro = metodo_potencia_puro(a2, v0_a2, epsilon=1e-5)
lambda1_a3_puro, x1_a3_puro = metodo_potencia_puro(a3, v0_a3, epsilon=1e-6)

# --- Versão com NumPy
A1_np = np.array(a1)
A2_np = np.array(a2)
A3_np = np.array(a3)
v0_a1_np = np.array(v0_a1, dtype=float)
v0_a2_np = np.array(v0_a2, dtype=float)
v0_a3_np = np.array(v0_a3, dtype=float)

lambda1_a1_np, x1_a1_np = metodo_potencia_numpy(A1_np, v0_a1_np, epsilon=1e-5)
lambda1_a2_np, x1_a2_np = metodo_potencia_numpy(A2_np, v0_a2_np, epsilon=1e-5)
lambda1_a3_np, x1_a3_np = metodo_potencia_numpy(A3_np, v0_a3_np, epsilon=1e-6)


print("======= Resultados da matriz A (3x3) =======")
print("Matriz A1:", a1)
print(f"[Puro ] λ ≈ {lambda1_a1_puro:.6f}, x1 ≈ {tuple(round(x, 6) for x in x1_a1_puro)}")
print(f"[NumPy] λ ≈ {lambda1_a1_np:.6f}, x1 ≈ {tuple(round(x, 6) for x in x1_a1_np)}\n")

print("======= Resultados da matriz A (5x5) =======")
print("Matriz A2:", a2)
print(f"[Puro ] λ ≈ {lambda1_a2_puro:.6f}, x1 ≈ {tuple(round(x, 6) for x in x1_a2_puro)}")
print(f"[NumPy] λ ≈ {lambda1_a2_np:.6f}, x1 ≈ {tuple(round(x, 6) for x in x1_a2_np)}\n")

print("======= Resultados da matriz A3 (3x3) =======")
print("Matriz A3:", a3)
print(f"[Puro ] λ ≈ {lambda1_a3_puro:.6f}, x1 ≈ {tuple(round(x, 6) for x in x1_a3_puro)}")
print(f"[NumPy] λ ≈ {lambda1_a3_np:.6f}, x1 ≈ {tuple(round(x, 6) for x in x1_a3_np)}\n")