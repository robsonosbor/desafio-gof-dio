# 💰 Desafio: Banco Digital com Java e Padrões GoF

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-green)

Este projeto foi desenvolvido como parte de um desafio da [Digital Innovation One](https://www.dio.me/) com foco na implementação utilizando **Java** e **Padrões de Projeto GoF (Gang of Four)**.

## 🎯 Objetivo

Demonstrar o uso prático da **Programação Orientada a Objetos (POO)** e aplicar os principais padrões de projeto para uma arquitetura mais robusta, reutilizável e legível.

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Maven
- IDE (IntelliJ IDEA / Eclipse)

## 🧱 Padrões GoF Aplicados

- **Singleton**: Utilizado para garantir uma única instância da classe `Banco`.
- **Strategy**: Implementado para estratégias de exibição e ações em contas.
- **Factory Method**: Para abstração da criação de contas digitais (`ContaCorrente`, `ContaPoupanca`).

## 🚀 Como Executar

Clone o repositório e execute com Maven:

```bash
git clone https://github.com/robsonosbor/desafio-gof-dio.git
cd desafio-gof-dio
mvn compile
mvn exec:java
```
