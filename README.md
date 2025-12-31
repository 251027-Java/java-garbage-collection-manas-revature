[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/ryfra96S)
# Lab: JVM Memory Analysis

## Goal
Analyze JVM memory usage and configure garbage collection settings for optimal performance.

## Learning Objectives
- Understand JVM memory regions (Heap, Stack, Metaspace)
- Enable and interpret GC logs
- Compare different garbage collectors
- Use JVM tuning flags

## Pre-requisites
- Java 17+ installed
- Basic understanding of Java memory model

## Tasks

### Task 1: Create Memory-Intensive Application
Create a Java application that allocates memory progressively.

**Starter Code Provided**: `MemoryLabApp.java`

### Task 2: Run with GC Logging
Run the application with the following JVM flags:
```bash
java -Xms128m -Xmx256m -Xlog:gc*:file=gc.log:time,uptime MemoryLabApp
```

**Questions to Answer**:
1. How many Minor GCs occurred? 5
2. How many Major/Full GCs occurred? 5
3. What was the longest GC pause time? 21.677ms

### Task 3: Compare Garbage Collectors
Run the application with different GC algorithms:

```bash
# Serial GC
java -XX:+UseSerialGC -Xmx256m -Xlog:gc*:file=gc-serial.log MemoryLabApp

# G1GC
java -XX:+UseG1GC -Xmx256m -Xlog:gc*:file=gc-g1.log MemoryLabApp

# ZGC (Java 11+)
java -XX:+UseZGC -Xmx256m -Xlog:gc*:file=gc-zgc.log MemoryLabApp
```

**Record your observations** in a table:

| GC Type | Total GC Events | Longest Pause | Throughput |
|---------|-----------------|---------------|------------|
| Serial  | 8               | 110 ms        | 99.89%     |
| G1GC    | 10              | 10 ms         | 99.98%     |
| ZGC     | 25              | .047 ms       | 99.99%     |

### Task 4: Tune for Low Latency
Configure G1GC to target a maximum pause time of 100ms:

```bash
java -XX:+UseG1GC -XX:MaxGCPauseMillis=100 -Xmx256m MemoryLabApp
```

**Questions**:
1. Did G1GC achieve the target pause time? Yes, the max pause time was 20 ms.
2. What trade-offs did you observe? The pause times were below 100 ms, but the total pause time was higher.

## Deliverables
1. Completed `MemoryLabApp.java`
2. GC log files from each run
3. Answers to all questions in a markdown file

## Starter Code
See `starter_code/MemoryLabApp.java`
