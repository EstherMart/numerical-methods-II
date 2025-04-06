# Numerical Methods II

This repository contains my implementations and experiments performed during the course of **Numerical Methods II**. Each report is organized in its own folder, containing the source codes, tests, and annotations.

The main language used is Java, but other languages such as C++ or Python may appear as the content of the course evolves.

## Goals

- Implement the algorithms studied in class
- Analyze results and validate numerical methods
- Explore different programming languages and paradigms

## Repository Structure

```text
numerical-methods-II/
├── relatorios/
│   ├── relatorio01/
│   │   └── src/              # Java source files
│   ├── relatorio02/
│   └── ...
├── bin/                      # Compiled .class files organized by report
│   ├── relatorio01/
│   └── relatorio02/
├── compiler.sh               # Build and run automation script
├── .gitignore
└── README.md
```

## How to Build and Run

### Prerequisites

Before you build and run the code in this repository, make sure that your environment meets the following requirements:

- Java Development Kit (JDK) 11 or higher: Projects are developed in Java. You can download the JDK from Oracle's official website or use an alternative distribution like OpenJDK.

- **Operating System**: The scripts provided are compatible with Unix-like systems (Linux and macOS). For Windows users, it is recommended to use [Git Bash](https://gitforwindows.org/) or [WSL](https://docs.microsoft.com/pt-br/windows/wsl/about) to run the scripts.

### Compilation and Execution

To facilitate the process of compiling and running the projects, a `compiler.sh` script has been made available at the root of the repository. Follow the steps below to use it:

1. Navigate to the root directory of the repository:

```bash
cd path/to/numerical-methods-II
```

2. Give the script permission to run (only needed the first time):

```bash
chmod +x compiler.sh
``` 

3. Run the script with the name of the report as the argument. For example, for relatorio01

```bash
./compiler.sh relatorio01
```

The script will compile the `.java` files located in `relatorio/relatorio01/src/` and store the corresponding `.class` files in `bin/relatorio01/.` It will then execute the main class of the report.