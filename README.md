# Quantity Measurement App

UC13 refactors the arithmetic operations (addition, subtraction, division) implemented in UC12 to eliminate code duplication and enforce the DRY (Don't Repeat Yourself) principle. Instead of repeating unit conversion, base-unit normalization, and validation logic across multiple arithmetic methods, this use case introduces a centralized private helper method that encapsulates all common arithmetic logic.


By consolidating the repetitive code into a single, reusable helper method, UC13 improves maintainability, reduces bug risk, and establishes a scalable pattern for adding future arithmetic operations (multiplication, modulo, etc.) without duplicating logic. The public API remains unchanged; all behaviors from UC12 are preserved while the internal implementation is optimized for clarity and consistency.



### Disadvantages of UC12 Implementation

-> UC12's direct implementation of arithmetic operations exhibits several architectural flaws:


-> Code Duplication Across Arithmetic Methods

add(), subtract(), and divide() each contain nearly identical code:
Null checks for operand and unit
Category type compatibility verification via unit.getClass()
Finiteness validation for numeric values
Base-unit conversion via IMeasurable.convertToBaseUnit()
Explicit target unit handling
Explicit target unit handling
Any of these checks present in all three methods with minimal variation.
Future arithmetic operations (multiplication, modulo, etc.) would duplicate this pattern further.

-> DRY Principle Violation

Common validation logic is copied verbatim across methods.
Error messages and validation checks are not centralized.
Changes to validation rules require updates in multiple locations.
Inconsistencies between methods become possible (e.g., one method uses different null-check behavior).

-> Increased Maintenance Burden

Bug fixes or improvements to conversion logic must be applied in three+ places.
Risk of partial updates (fixing one method while missing others).
Refactoring becomes complex as changes ripple across multiple methods.
New developers struggle to understand why logic is repeated.

-> Reduced Code Readability

Length of each arithmetic method obscures the core operation logic.
Readers must parse validation/conversion boilerplate before understanding the actual arithmetic.
Intent of the method is buried in repetitive code.

-> Scalability Issues

Adding multiplication, modulo, or other operations compounds duplication.
Validation and conversion logic would be replicated 5+, 6+, 7+ times.
Codebase grows unnecessarily; complexity increases without adding functionality.

-> Inconsistent Error Handling

Each method might handle errors slightly differently.
Some might throw exceptions; others return special values.
No centralized place to adjust error-handling strategy.

-> Testing Complexity

Validation scenarios must be tested separately for each operation.
Tests for add(), subtract(), and divide() contain nearly identical test cases.
Bug fixes or validation changes require updating tests in multiple locations.


### Preconditions

All arithmetic operations from UC12 (add, subtract, divide) are fully functional and tested.
All unit enums (LengthUnit, WeightUnit, VolumeUnit, etc.) implement IMeasurable.
Behavior of arithmetic operations must remain unchanged after refactoring.
Existing test cases from UC12 will pass without modification.
The refactoring will be internal; public API signatures remain identical.
A centralized helper method will extract common logic.
Error handling and validation remain consistent across all operations.
Refactor will not change public method signatures or results.
