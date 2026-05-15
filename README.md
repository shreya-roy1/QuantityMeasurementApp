# QuantityMeasurementApp
The Quantity Measurement App compares two quantities (length, weight, etc.) and provides a comparison between different units. It progresses further to add Conversion from one unit to another, and also will go on to support quantity arithmetic. The app will be developed incrementally, starting with simple use cases and progressively adding complexity to develop a full-stack app. Each use case defines a specific scope—avoid expanding beyond requirements to keep the application maintainable and focused. 

# Preconditions
- The generic Quantity<U extends IMeasurable> class from UC10 is fully operational.
- The IMeasurable interface defines unit conversion contracts.
- LengthUnit, WeightUnit, and VolumeUnit enums implement IMeasurable.
- All functionality from UC1–UC11 (equality, conversion, addition) is preserved and unaffected.
- New subtraction and division methods will be added to the Quantity<U> class.
- Corresponding demonstration methods will be added to QuantityMeasurementApp.
- Subtraction operations return Quantity<U> objects (same type as operands).
- Division operations return Quantity<U> objects (same type as operands).
- All operations support explicit target unit specification for result expression.
- Cross-category arithmetic (e.g., subtracting weight from length) is prevented through type safety.

# Main Flow
- Subtraction Operations
- 
- User Initiates Subtraction
  
Client calls Quantity<U>.subtract(Quantity<U> other) or Quantity<U>.subtract(Quantity<U> other, U targetUnit).
Method accepts another quantity and optionally a target unit.

- Input Validation
  
Verify that the other is non-null and has a valid unit.
Verify that both quantities belong to the same measurement category (type check via unit.getClass()).
Verify that all numeric values are finite (not NaN or infinite).

- Conversion to Base Unit

Convert both this and other to the common base unit using IMeasurable.convertToBaseUnit().
Subtract the converted values: baseResult = this.baseValue - other.baseValue.

- Convert Result to Target Unit

If no target unit is specified, use the unit of the first operand (implicit).
Convert the base result to the target unit using IMeasurable.convertFromBaseUnit().
Round the result to two decimal places for consistency.

- Return New Quantity

Create and return a new Quantity<U> object with the subtracted value and target unit.
Original objects remain unchanged (immutability principle).

- Cross-Category Type Safety

Positive result indicates the first operand is larger.
Negative result indicates the second operand is larger.
Zero result indicates quantities are equivalent.

- Division Operations


- User Initiates Division

Client calls Quantity<U>.divide(Quantity<U> other).
Method accepts another quantity and returns a dimensionless scalar.

- Input Validation

Verify that other is non-null and has a valid unit.
Verify that both quantities belong to the same measurement category.
Verify that all numeric values are finite.
Verify that the divisor (other) is not zero (prevent division by zero).

- Conversion to Base Unit

Convert both this and other to the common base unit.
Divide the base values: result = this.baseValue / other.baseValue.

- Return Dimensionless Result

Return the scalar result as a primitive double.
Result is dimensionless (no unit), representing a pure ratio.

- Result Interpretation

Result > 1.0 indicates first operand is larger.
Result < 1.0 indicates second operand is larger.
Result = 1.0 indicates quantities are equivalent.


# Postconditions
- Subtraction of two quantities produces a new Quantity<U> object with the correct difference.
- Result unit for subtraction is either the first operand's unit (implicit) or explicitly specified target unit.
- Original quantities remain unchanged; subtraction follows immutability principle.
- Cross-category subtraction (e.g., feet - kilograms) is prevented by type system.
- Division of two quantities returns a dimensionless double scalar value.
- Division by zero results in Double.POSITIVE_INFINITY or throws an exception (design choice).
- All arithmetic operations (addition, subtraction, division) coexist seamlessly.
- Demonstration methods in QuantityMeasurementApp showcase all operations.
- All previous functionality from UC1–UC11 is preserved.
- Subtraction and division work across all measurement categories (length, weight, volume).
- Mathematical properties are respected: subtraction is non-commutative, division is non-commutative.
  
