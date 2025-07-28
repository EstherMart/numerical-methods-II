from deslocamento import metodo_deslocado_puro_com_potencia

print("-------------- SEJA BEM VINDO AO --------------")
print("      MÉTODO DA POTÊNCIA COM DESLOCAMENTO")
print("---------------------------------------------------\n")

# --- Matriz A1 (3x3)
a1 = [[5, 2, 1], 
      [2, 3, 1], 
      [1, 1, 2]]
v0_a1 = [1, 0, 0]
deslocamento_a1 = 3

# --- Matriz A2 (5x5)
a2 = [[40, 8, 4, 2, 1],
      [8, 30, 12, 6, 2],
      [4, 12, 20, 1, 2],
      [2, 6, 1, 25, 4],
      [1, 2, 2, 4, 5]]
v0_a2 = [1, 0, 0, 0, 0]
deslocamento_a2 = 10

# --- Matriz A3 (3x3)
a3 = [[2, -1, 0],
      [-1, 2, -1],
      [0, -1, 2]]
v0_a3 = [1, 1, 1]
deslocamento_a3 = 2.8

# --- Aplicação do método deslocado com potência (versão pura com reaproveitamento do método inverso)
lambda_shift_a1, x_shift_a1 = metodo_deslocado_puro_com_potencia(a1, v0_a1, deslocamento_a1, epsilon=1e-5)
lambda_shift_a2, x_shift_a2 = metodo_deslocado_puro_com_potencia(a2, v0_a2, deslocamento_a2, epsilon=1e-5)
lambda_shift_a3, x_shift_a3 = metodo_deslocado_puro_com_potencia(a3, v0_a3, deslocamento_a3, epsilon=1e-6)

print("======= Resultados da matriz A (3x3) =======")
print("Matriz A1:", a1)
print(f"[μ = {deslocamento_a1}] λ ≈ {lambda_shift_a1:.6f}, x ≈ {tuple(round(x, 10) for x in x_shift_a1)}\n")

print("======= Resultados da matriz A (5x5) =======")
print("Matriz A2:", a2)
print(f"[μ = {deslocamento_a2}] λ ≈ {lambda_shift_a2:.6f}, x ≈ {tuple(round(x, 10) for x in x_shift_a2)}\n")

print("======= Resultados da matriz A3 (3x3) =======")
print("Matriz A3:", a3)
print(f"[μ = {deslocamento_a3}] λ ≈ {lambda_shift_a3:.6f}, x ≈ {tuple(round(x, 10) for x in x_shift_a3)}\n")