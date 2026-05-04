# QuantityMeasurementApp
The Quantity Measurement App compares two quantities (length, weight, etc.) and provides a comparison between different units. It progresses further to add Conversion from one unit to another, and also will go on to support quantity arithmetic. The app will be developed incrementally, starting with simple use cases and progressively adding complexity to develop a full-stack app. Each use case defines a specific scope—avoid expanding beyond requirements to keep the application maintainable and focused. 
ent categories without refactoring existing code.
- Code cohesion is improved; unit-specific logic is centralized in the unit class.

# Preconditions
- The QuantityMeasurementApp class is instantiated.
- Two or more numerical values with their respective weight unit types (kilogram, gram, pound) are provided for comparison, conversion, or addition.
- The conversion factors between supported weight units are defined as constants relative to kilogram (base unit).
- The WeightUnit enum exists as a standalone class with conversion responsibility (mirroring UC8 refactoring for LengthUnit).
- Length functionality from UC1–UC8 remains fully operational and unaffected.
- Weight and length measurements are treated as separate, incomparable categories.

# Main Flow

- Equality Comparison:
1.User inputs two numerical values with their respective weight unit types.
2.QuantityWeight class validates the input values to ensure they are numeric and units are valid.
3.Both values are converted to the common base unit (kilogram) using WeightUnit conversion methods.
4.The converted values are compared for equality using the overridden equals() method.
5.The result of the comparison (true or false) is returned.

- Unit Conversion:
1.User inputs a numerical value, source unit, and target unit.
2.QuantityWeight.convertTo(targetUnit) converts the measurement to the target unit.
3.The method normalizes through the base unit (kilogram) and applies appropriate conversion factors.
4.A new QuantityWeight object is returned with the converted value and target unit.

- Addition Operations:
1.User inputs two QuantityWeight objects and optionally a target unit.
2.Both measurements are converted to the base unit (kilogram).
3.The converted values are summed.
4.The result is converted to the target unit (either first operand's unit or explicitly specified unit).
5.A new QuantityWeight object representing the sum is returned.


# Postconditions
1.Weight measurements of the same unit and value are considered equal.
2.Weight measurements of different units but equivalent values are considered equal (e.g., 1 kg = 1000 g = 2.20462 lb).
3.Unit conversions between weight units produce mathematically accurate results within floating-point precision.
4.Addition of two weight measurements produces a new QuantityWeight object without modifying originals (immutability).
5.All previous functionality from UC1–UC8 for length measurements is preserved and works correctly.
6.Length and weight measurements are treated as separate, incomparable categories (1 foot ≠ 1 kilogram).
7.The architectural pattern established supports straightforward addition of new measurement categories (temperature, volume, etc.).
