# 📏 Quantity Measurement Application

[![Java Version](https://img.shields.io/badge/Java-11%2B-orange.svg?style=flat-square)](https://www.oracle.com/java/)
[![Architecture](https://img.shields.io/badge/Architecture-Generic%20OOP-blue.svg?style=flat-square)](#-core-architecture)
[![Design Patterns](https://img.shields.io/badge/Design%20Patterns-Polymorphic%20Enum%20%7C%20Strategy-brightgreen.svg?style=flat-square)](#-design-decisions--patterns)
[![Testing](https://img.shields.io/badge/Testing-JUnit%205-red.svg?style=flat-square)](#-verification--testing)

An elegant, type-safe, and highly extensible Java-based measurement conversion and arithmetic engine. Designed with strict object-oriented design and SOLID principles, the application supports comparing, converting, and performing arithmetic operations across **Length**, **Weight**, and **Volume** domains while guaranteeing compile-time safety and physical consistency.

---

## 🚀 Key Features

*   🔒 **Compile-Time Type Safety**: Implemented via Java Generics to prevent cross-category operations (e.g., you cannot add weight to volume or compare length with weight).
*   🔄 **Dynamic Conversions**: A generic conversion engine that normalizes measurements using base unit ratios.
*   ➕➖➗ **Comprehensive Arithmetic**: Implements Addition, Subtraction (with target unit specification), and Division (calculating dimensionless scalar ratios).
*   🛡️ **Immutable Design**: Instances of measurements are immutable to prevent unintended side effects and ensure thread safety.
*   🧩 **Polymorphic Enums**: Leverages Java enums implementing strategy interfaces to encapsulate unit conversion factors cleanly.
*   ⚡ **DRY Refactoring**: Centralized validation, unit scaling, and operator logic using Java's functional `DoubleBinaryOperator` interface to maximize maintainability.

---

## 🧱 Core Architecture

The architecture decouples the physical quantity logic from the specific unit implementation. The core components are:

1.  **`IMeasurable`** (Interface): Defines the contract for all unit enums. It encapsulates the conversion logic to and from a category's base unit.
2.  **`Quantity<U extends Enum<U> & IMeasurable<U>>`** (Class): A generic container holding a numeric value and its corresponding unit. It handles equality checks, addition, subtraction, and division calculations.
3.  **Unit Enums** (`LengthUnit`, `WeightUnit`, `VolumeUnit`): Concrete enums that implement `IMeasurable` and manage unit-specific factors.

---

## 📊 Supported Units & Categories

Each category uses a specific **Base Unit** to which all other units are normalized under the hood:

| Category | Base Unit | Supported Units & Conversion Factors |
| :--- | :--- | :--- |
| **Length** 📏 | **Inch** | <ul><li>`INCH` (1.0)</li><li>`FEET` (12.0)</li><li>`YARD` (36.0)</li><li>`CENTIMETER` (0.4 / ~2.54 cm per inch)</li></ul> |
| **Weight** ⚖️ | **Kilogram** | <ul><li>`KILOGRAM` (1.0)</li><li>`GRAM` (0.001)</li><li>`POUND` (0.453592)</li></ul> |
| **Volume** 🧪 | **Litre** | <ul><li>`LITRE` (1.0)</li><li>`MILLILITRE` (0.001)</li><li>`GALLON` (3.78541)</li></ul> |

---

## 🚀 Use Case Progression (UC1 - UC13)

The application was developed incrementally following test-driven development (TDD):

### 🔹 UC1: Feet Equality
Initial comparison requirement. Compares two values in feet.
*   *Example:* `1.0 ft == 1.0 ft → true`

### 🔹 UC2: Feet & Inches Equality
Introduces inches, handling them as separate, parallel comparisons.
*   *Example:* `1.0 in == 1.0 in → true` (Feet and Inches cannot be compared yet)

### 🔹 UC3: Length Equality Refactoring (DRY)
Unifies feet and inches using a single class and an enum with base unit normalization (converting to inches/feet under the hood).
*   *Example:* `1.0 ft == 12.0 in → true`

### 🔹 UC4: Extended Length Units
Adds `YARD` and `CENTIMETER` without modifying core comparison logic.
*   *Example:* `1.0 yd == 36.0 in → true`, `1.0 cm == 0.4 in (approx) → true`

### 🔹 UC5: Unit Conversion API
Introduces a conversion API to change quantities between units.
*   *Example:* `1.0 ft` converted to `INCH` yields `12.0 in`

### 🔹 UC6: Addition of Lengths
Supports adding two measurements of the same category, returning the result in the unit of the first operand.
*   *Example:* `1.0 ft + 12.0 in = 2.0 ft`

### 🔹 UC7: Addition with Target Unit Specification
Enhances addition to allow specifying a custom target unit for the resulting quantity.
*   *Example:* `1.0 ft + 12.0 in` targeting `YARD` = `0.67 yd`

### 🔹 UC8: Architectural Refactoring
Decouples unit conversion factors from the core class and moves them into a standalone enum to improve cohesion.

### 🔹 UC9: Weight Category Support
Extends the framework to support weight units (`KILOGRAM`, `GRAM`, `POUND`) with their own equality, conversion, and addition.
*   *Example:* `1.0 kg == 1000.0 g → true`

### 🔹 UC10: Generic Quantity Refactoring
Introduces the generic `Quantity<U extends IMeasurable>` class. This uses compile-time checks to prevent mismatched arithmetic.
*   *Example:* `Quantity<LengthUnit>` and `Quantity<WeightUnit>` cannot be compared or added at compile time.

### 🔹 UC11: Volume Category Support
Leverages the generic architecture to seamlessly add `LITRE`, `MILLILITRE`, and `GALLON` without changing core code.
*   *Example:* `1.0 gallon == 3.785 litres → true`

### 🔹 UC12: Subtraction & Division
Completes the arithmetic suite by supporting subtraction (with custom target units) and division (returning a dimensionless scalar ratio).
*   *Example:* `1.0 Litre - 250 ml = 0.75 Litre`, `1.0 Litre / 250 ml = 4.0` (Scalar ratio)

### 🔹 UC13: DRY Refactoring & Centralized Arithmetic
Refactors the arithmetic logic to eliminate code duplication across operations. Exposes a cleaner `Quantity` class utilizing a central validation helper and an internal `ArithmeticOperation` enum powered by functional `DoubleBinaryOperator` lambdas.
*   *Example:* `1.0 ft + 12.0 in = 2.0 ft` (Delegated to a centralized arithmetic runner with strict category and null validations).

---

## 💡 Design Decisions & Patterns

*   **Strategy Pattern**: Unit enums act as strategies for conversion. The `IMeasurable` interface defines the conversion algorithms, and each enum constant defines its unique factor/formula.
*   **Open/Closed Principle (OCP)**: Adding new categories (e.g. Temperature, Speed) is done by creating a new enum implementing `IMeasurable`. No changes to `Quantity` are needed.
*   **Liskov Substitution Principle (LSP)**: Any unit category enum implementing `IMeasurable` can be substituted dynamically into `Quantity<U>`.
*   **Single Responsibility Principle (SRP)**:
    *   `Quantity` is responsible only for handling quantity values and arithmetic operations.
    *   Unit Enums (`LengthUnit`, etc.) are responsible only for defining unit conversion factors and labels.

---

## 🧪 Verification & Testing

The project has a suite of JUnit 5 unit tests covering:
*   Cross-unit equality and precision limits (e.g., float precision tolerances).
*   Unit conversion accuracy.
*   Commutative and associative properties of addition and subtraction.
*   Division checks including division by zero exceptions.
*   Strict validation checks (rejecting null units, infinite values, and mismatched category operations).

### Running Tests

To run the unit test suite locally:

```bash
# If using Gradle
./gradlew test

# If using Maven
mvn test
```
