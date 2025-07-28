from inverso import (
    metodo_inverso_puro,
    metodo_inverso_puro_com_potencia
)

print("-------------- SEJA BEM VINDO AO --------------")
print("           MÉTODO DA POTÊNCIA INVERSA")
print("---------------------------------------------------\n")

# --- Matriz A1 (3x3)
a1 = [[5, 2, 1], 
      [2, 3, 1], 
      [1, 1, 2]]
v0_a1 = [1, 0, 0]

# --- Matriz A2 (5x5)
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

a4 = [[4.0, 2.0, 1.0, 3.0, 5.0],
      [2.0, 9.0, 3.0, 7.0, 8.0],
      [1.0, 3.0,11.0, 6.0, 4.0],
      [3.0, 7.0, 6.0,14.0, 7.0],
      [5.0, 8.0, 4.0, 7.0,10.0]]
v0_a4 = [1, 0, 0, 0, 0]

# --- Versão com listas puras usando decomposição LU
lambda_inv_a1_puro, x_inv_a1_puro = metodo_inverso_puro(a1, v0_a1, epsilon=1e-5)
lambda_inv_a2_puro, x_inv_a2_puro = metodo_inverso_puro(a2, v0_a2, epsilon=1e-5)
lambda_inv_a3_puro, x_inv_a3_puro = metodo_inverso_puro(a3, v0_a3, epsilon=1e-6)
lambda_inv_a4_puro, x_inv_a4_puro = metodo_inverso_puro(a4, v0_a4, epsilon=1e-5)

# --- Versão com listas puras usando método da potência regular aplicado na matriz inversa (via método_inverso_puro_com_potencia)
lambda_inv_a1_pot, x_inv_a1_pot = metodo_inverso_puro_com_potencia(a1, v0_a1, epsilon=1e-5)
lambda_inv_a2_pot, x_inv_a2_pot = metodo_inverso_puro_com_potencia(a2, v0_a2, epsilon=1e-5)
lambda_inv_a3_pot, x_inv_a3_pot = metodo_inverso_puro_com_potencia(a3, v0_a3, epsilon=1e-6)
lambda_inv_a4_pot, x_inv_a4_pot = metodo_inverso_puro_com_potencia(a4, v0_a4, epsilon=1e-5)

print("======= Resultados da matriz A (3x3) =======")
print("Matriz A1:", a1)
print(f"[LU puro ] λ ≈ {lambda_inv_a1_puro:.6f}, x ≈ {tuple(round(x, 6) for x in x_inv_a1_puro)}")
print(f"[Potência] λ ≈ {lambda_inv_a1_pot:.6f}, x ≈ {tuple(round(x, 6) for x in x_inv_a1_pot)}\n")

print("======= Resultados da matriz A (5x5) =======")
print("Matriz A2:", a2)
print(f"[LU puro ] λ ≈ {lambda_inv_a2_puro:.6f}, x ≈ {tuple(round(x, 6) for x in x_inv_a2_puro)}")
print(f"[Potência] λ ≈ {lambda_inv_a2_pot:.6f}, x ≈ {tuple(round(x, 6) for x in x_inv_a2_pot)}\n")

print("======= Resultados da matriz A3 (3x3) =======")
print("Matriz A3:", a3)
print(f"[LU puro ] λ ≈ {lambda_inv_a3_puro:.6f}, x ≈ {tuple(round(x, 6) for x in x_inv_a3_puro)}")
print(f"[Potência] λ ≈ {lambda_inv_a3_pot:.6f}, x ≈ {tuple(round(x, 6) for x in x_inv_a3_pot)}\n")

print("======= Resultados da matriz A4 (B⁻¹A - 5x5) =======")
print("Matriz A4:", a4)
print(f"[LU puro ] λ ≈ {lambda_inv_a4_puro:.6f}, x ≈ {tuple(round(x, 6) for x in x_inv_a4_puro)}")
print(f"[Potência] λ ≈ {lambda_inv_a4_pot:.6f}, x ≈ {tuple(round(x, 6) for x in x_inv_a4_pot)}")