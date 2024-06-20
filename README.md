# Concorrência em Java

Este repositório contém um trabalho sobre concorrência em Java, abordando conceitos, técnicas e exemplos práticos. O objetivo é fornecer uma base sólida sobre como gerenciar múltiplos threads e realizar operações simultâneas de forma eficiente e segura.

## Sumário

- [Introdução](#introdução)
- [Conceitos Básicos](#conceitos-básicos)
- [Threads em Java](#threads-em-java)
- [Sincronização](#sincronização)
- [Executors e Thread Pools](#executors-e-thread-pools)
- [Concorrência Avançada](#concorrência-avançada)
- [Exemplos Práticos](#exemplos-práticos)
- [Referências](#referências)

## Introdução

A concorrência em Java permite que múltiplas tarefas sejam executadas simultaneamente, o que pode melhorar significativamente o desempenho de aplicativos que precisam realizar várias operações ao mesmo tempo. Este trabalho explora como a linguagem Java lida com concorrência, incluindo a criação e gerenciamento de threads, sincronização de recursos compartilhados e o uso de frameworks de alto nível.

## Conceitos Básicos

### O que é Concorrência?

Concorrência refere-se à execução de várias tarefas ao mesmo tempo. Em Java, isso é realizado através de threads, que são pequenas unidades de processamento executadas em paralelo.

### Vantagens da Concorrência

- Melhor desempenho em sistemas multi-core.
- Melhor utilização de recursos do sistema.
- Redução do tempo de resposta em aplicativos interativos.

## Threads em Java

### Criando Threads

Há duas maneiras principais de criar threads em Java:
1. **Extendendo a classe `Thread`**
2. **Implementando a interface `Runnable`**

```java
// Extendendo a classe Thread
class MinhaThread extends Thread {
    public void run() {
        System.out.println("Thread executando");
    }
}

// Implementando a interface Runnable
class MinhaRunnable implements Runnable {
    public void run() {
        System.out.println("Runnable executando");
    }
}

public class Main {
    public static void main(String[] args) {
        MinhaThread thread = new MinhaThread();
        thread.start();
        
        Thread runnableThread = new Thread(new MinhaRunnable());
        runnableThread.start();
    }
}
