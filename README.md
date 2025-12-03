# Pico y Placa Predictor - Backend (Java + Maven)

Proyecto backend desarrollado en **Java 17** utilizando **Maven**, cuyo objetivo es implementar un sistema capaz de **predecir si un vehículo puede circular** en función de las reglas de Pico y Placa, la placa, fecha y hora proporcionadas por el usuario.

Este repositorio contiene únicamente el backend. El frontend podrá agregarse más adelante si el tiempo lo permite.

---

## Estructura del proyecto

pico-placa-predictor/
├── src/
│ ├── main/
│ │ └── java/com/pico/
│ │ ├── application/
│ │ ├── domain/
│ │ │ ├── models/
│ │ │ └── rules/
│ │ └── infrastructure/
│ │ └── cli/
│ └── test/
│ └── java/com/pico/
├── target/ (ignorada por Git)
├── pom.xml
├── .gitignore
└── README.md


### Resumen de capas
- **application** → Servicios, lógica de orquestación.
- **domain/models** → Entidades y objetos de valor (request, placa, fecha, hora).
- **domain/rules** → Motor de reglas de Pico y Placa.
- **infrastructure/cli** → Adaptadores para entrada desde la terminal.
- **test** → Pruebas unitarias.

---

## Tecnologías utilizadas

- **Java 17**
- **Apache Maven**
- **JUnit 5**
- **Arquitectura en capas (Clean/Hexagonal inspirada)**

---

## Cómo compilar el proyecto

Desde la raíz del repositorio:

```bash
mvn clean install
```

## Cómo ejecutar el programa

Ejecutar el siguiente comanto:

```bash
mvn exec:java -Dexec.mainClass="com.pico.App"
```

## Ejecutar pruebas
```bash
mvn test
```

## Requisitos previos
- JDK 17 o superior
- Maven 3.8+
- Git
- VS Code (opcional)