#!/bin/bash

# ================================================
# 🚀 COMPILER SCRIPT - NUMERICAL METHODS II
# ================================================
# Uso:
#   ./compiler.sh relatorio01
# ================================================

# Verifica se o usuário passou o nome do relatório
if [ -z "$1" ]; then
  echo "❌ Erro: informe o nome do relatório!"
  echo "Uso: ./compiler.sh relatorio01"
  exit 1
fi

# Caminhos com base na estrutura relatorios/relatorioXX
REPORT_NAME="$1"
REPORT_DIR="relatorios/$REPORT_NAME"
SRC_DIR="$REPORT_DIR/src"
BIN_DIR="bin/$REPORT_NAME"

# Verifica se o diretório src existe
if [ ! -d "$SRC_DIR" ]; then
  echo "❌ Diretório de código fonte não encontrado: $SRC_DIR"
  exit 1
fi

# Cria o diretório bin se não existir
mkdir -p "$BIN_DIR"

# Compila os arquivos .java
echo "🔧 Compilando arquivos em $SRC_DIR..."
javac -d "$BIN_DIR" "$SRC_DIR"/*.java

# Detecta automaticamente a classe com método main
MAIN_FILE=$(grep -l "public static void main" "$SRC_DIR"/*.java | head -n 1)

# Verifica se encontrou algum arquivo com main
if [ -z "$MAIN_FILE" ]; then
  echo "❌ Nenhuma classe com método main encontrada!"
  exit 1
fi

# Extrai o nome da classe
MAIN_CLASS=$(basename "$MAIN_FILE" .java)

echo "✅ Compilação bem-sucedida!"
echo "🚀 Executando classe principal: $MAIN_CLASS"
echo "-----------------------------------------------"
java -cp "$BIN_DIR" "$MAIN_CLASS"
