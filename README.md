# QuantityMeasurementApp
The Quantity Measurement App compares two quantities (length, weight, etc.) and provides a comparison between different units. It progresses further to add Conversion from one unit to another, and also will go on to support quantity arithmetic. The app will be developed incrementally, starting with simple use cases and progressively adding complexity to develop a full-stack app. Each use case defines a specific scope—avoid expanding beyond requirements to keep the application maintainable and focused. 

# Preconditions
- Quantity Length class (from UC3/UC4/UC5/UC6) and LengthUnit enum exist with FEET, INCHES, YARDS, CENTIMETERS.
- The conversionFactor for each LengthUnit is defined relative to a consistent base unit.
- Two Quantity Length objects or raw values with their respective units are provided.
- A target unit (distinct or same as operand units) is explicitly specified.
- All units belong to the same measurement category (length).

# Main Flow
- Client calls Quantity Length.add(length1, length2, targetUnit) with an explicit target unit parameter.
- The method validates:
- Both length1 and length2 are non-null and have valid LengthUnits.
targetUnit is non-null and a valid LengthUnit.
- All values are finite numbers (Double.isFinite or equivalent).
- Convert both length1 and length2 to a common base unit (feet).
- Add the converted values.
- Convert the sum from the base unit to the explicitly specified targetUnit.
- Return a new Quantity Length object representing the result in the target unit.

# Postconditions
- A new Quantity Length object is returned with the sum of the two measurements expressed in the explicitly specified target unit.
- The original Quantity Length objects remain unchanged (immutability principle).
- The result unit is always the specified target unit, not inferred from operands.
- Invalid inputs (null units, unsupported units, NaN, infinite, or mismatched categories) result in a documented exception (e.g., IllegalArgumentException).
- Addition is mathematically accurate within floating-point precision limits.
- Addition remains commutative: add(A, B, targetUnit) equals add(B, A, targetUnit).